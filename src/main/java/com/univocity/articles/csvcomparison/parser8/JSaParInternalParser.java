package com.univocity.articles.csvcomparison.parser8;

import com.univocity.articles.csvcomparison.parser.AbstractParser;
import org.jsapar.parse.csv.CsvLineReader;
import org.jsapar.parse.csv.CsvLineReaderStates;

import java.io.Reader;
import java.util.List;

public class JSaParInternalParser extends AbstractParser {

    protected JSaParInternalParser() {
        super("JSaPar internal");
    }

    @Override
    public void processRows(Reader reader) throws Exception {
        CsvLineReader lineReader = new CsvLineReaderStates("\n", reader, true, 1024*8);
        List<String> line=null;
        do{
            line = lineReader.readLine(",", '"');
            if(line != null && !line.isEmpty())
                this.process(line);
        }while(!lineReader.eofReached());
    }

    @Override
    public List<String[]> parseRows(Reader reader) throws Exception {
        return null;
    }
}
