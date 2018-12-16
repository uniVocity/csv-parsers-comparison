package com.univocity.articles.csvcomparison.parser8;

import com.univocity.articles.csvcomparison.parser.AbstractParser;
import org.jsapar.TextParser;
import org.jsapar.model.Cell;
import org.jsapar.schema.CsvSchema;
import org.jsapar.schema.CsvSchemaLine;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class JSaParParser extends AbstractParser {

    private final TextParser textParser;

    JSaParParser() {
        super("JSaPar");
        CsvSchema inputSchema = new CsvSchema();
        inputSchema.setLineSeparator("\n");
        CsvSchemaLine line1 = new CsvSchemaLine("Line");
        line1.setFirstLineAsSchema(true);  // Get cell names and order from first row
        line1.setQuoteChar('"');
        line1.setCellSeparator(",");
        inputSchema.addSchemaLine(line1);

        // Create a text parser that does nothing more than parse.
        textParser = new TextParser(inputSchema);
        // Just print any error to stderr.
        textParser.setErrorEventListener(e -> System.err.println("Error: " + e.getError()));
        // Set cell cache to 0 since input mostly varies from line to line
        textParser.getParseConfig().setMaxCellCacheSize(0);

    }

    @Override
    public void processRows(Reader reader) throws Exception {
        textParser.parse(reader, event -> process(event.getLine()));
    }

    @Override
    public List<String[]> parseRows(Reader reader) throws Exception {
        List<String[]> rows = new ArrayList<>();
        textParser.parse(reader, e -> {
            if (rows.isEmpty()) {
                // Header row labels are present in all lines but not as a separate line.
                rows.add(e.getLine().stream().map(Cell::getName).toArray(String[]::new));
            }
            rows.add(e.getLine().stream().map(Cell::getStringValue).toArray(String[]::new));
        });
        return rows;
    }
}
