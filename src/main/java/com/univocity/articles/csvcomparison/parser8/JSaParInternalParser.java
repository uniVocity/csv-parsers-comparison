package com.univocity.articles.csvcomparison.parser8;

import com.univocity.articles.csvcomparison.parser.AbstractParser;
import org.jsapar.parse.csv.CsvLineReader;

import java.io.Reader;
import java.util.List;

public class JSaParInternalParser extends AbstractParser {

    protected JSaParInternalParser() {
        super("JSaPar internal");
    }

    @Override
    public void processRows(Reader reader) throws Exception {
        CsvLineReader lineReader = new CsvLineReader("\n", reader);
        List<String> line=null;
        do{
            line = lineReader.readLine(",", '"');
            if(line != null && !line.isEmpty())
                this.process(line);
        }while(line!=null);
    }

    @Override
    public List<String[]> parseRows(Reader reader) throws Exception {
        return null;
    }
}
