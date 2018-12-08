package com.univocity.articles.csvcomparison.parser8;

import com.univocity.articles.csvcomparison.parser.AbstractParser;
import org.jsapar.Text2StringConverter;
import org.jsapar.TextParser;
import org.jsapar.model.Cell;
import org.jsapar.schema.CsvSchema;
import org.jsapar.schema.CsvSchemaCell;
import org.jsapar.schema.CsvSchemaLine;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class JSaParParser extends AbstractParser {

    private final CsvSchema inputSchema;
    private final TextParser textParser;

    protected JSaParParser() {
        super("JSaPar");
        inputSchema = new CsvSchema();
        // Create a text parser that does nothing more than parse.
        textParser = new TextParser(inputSchema);
        // Just print any error to stderr.
        textParser.setErrorEventListener(e -> System.err.println("Error: " + e.getError()));
        // Set cell cache to 0 since input varies totally from line to line
        textParser.getParseConfig().setMaxCellCacheSize(0);
        inputSchema.setLineSeparator("\n");

        CsvSchemaLine line1 = new CsvSchemaLine("Line");
        line1.setFirstLineAsSchema(true);  // Get cell names and order from first row
        line1.setQuoteChar('"');
        line1.setCellSeparator(",");

        inputSchema.addSchemaLine(line1);

    }

    @Override
    public void processRows(Reader reader) throws Exception {
        textParser.parse(reader, event -> {
        });
    }

    @Override
    public List<String[]> parseRows(Reader reader) throws Exception {
        List<String[]> rows = new ArrayList<>();
        textParser.parse(reader, e -> {
            if (rows.isEmpty())
                rows.add(e.getLine().stream().map(Cell::getName).toArray(String[]::new));
            rows.add(e.getLine().stream().map(Cell::getStringValue).toArray(String[]::new));
        });
        return rows;
    }
}
