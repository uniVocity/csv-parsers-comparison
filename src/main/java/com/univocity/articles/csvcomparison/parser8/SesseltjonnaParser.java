package com.univocity.articles.csvcomparison.parser8;

import java.io.File;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.skjolber.stcsv.CsvReader;
import com.github.skjolber.stcsv.builder.StringArrayCsvReaderBuilder;
import com.github.skjolber.stcsv.sa.StringArrayCsvReader;
import com.github.skjolber.stcsv.sa.rfc4180.RFC4180StringArrayCsvReader;
import com.univocity.articles.csvcomparison.parser.AbstractParser;

public class SesseltjonnaParser extends AbstractParser {

    public SesseltjonnaParser() {
        super("Sesseltjonna-csv databinding");
    }

    @Override
    public void processRows(final Reader input) throws Exception {
        CsvReader<String[]> reader = StringArrayCsvReader.builder().build(input);
        
        String[] next;
        do {
            next = reader.next();
            if(next == null) {
                break;
            }
            process(next);
        } while(true);
    }

    @Override
    public List<String[]> parseRows(final Reader input) throws Exception {
        CsvReader<String[]> reader = StringArrayCsvReader.builder().build(input);
        List<String[]> arrayList = new ArrayList<>(10000);
        
        String[] next;
        do {
            next = reader.next();
            if(next == null) {
                break;
            }
            // the same array is returned, so make a copy
            String[] copy = new String[next.length];
            System.arrayCopy(next, 0, copy, 0, next.length);
            arrayList.add(copy); 
        } while(true);

        return arrayList;
    }
    
}
