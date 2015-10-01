package com.univocity.articles.csvcomparison.parser8;

import com.univocity.articles.csvcomparison.parser.AbstractParser;
import diergo.csv.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;

import static diergo.csv.CsvParserBuilder.csvParser;
import static diergo.csv.Readers.asLines;
import static java.util.Spliterator.SIZED;
import static java.util.Spliterators.spliterator;
import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

class DecsParser extends AbstractParser {

    public DecsParser() {
        super("Diergo Easy CSV Streamable");
    }

    @Override
    public void processRows(File input) throws Exception {
        asLines(new InputStreamReader(new FileInputStream(input), getEncodingName()))
            .map(csvParser().separatedBy(',').inLaxMode().build()).flatMap(Collection::stream)
            .forEach(this::process);
    }

    @Override
    public List<String[]> parseRows(File input) throws Exception {
        return asLines(new InputStreamReader(new FileInputStream(input), getEncodingName()))
            .map(csvParser().separatedBy(',').build()).flatMap(Collection::stream)
            .map(this::toStringArray).collect(toList());
    }

    private String[] toStringArray(Row row) {
        return stream(spliterator(row.iterator(), row.getLength(), SIZED), false).toArray(String[]::new);
    }
}
