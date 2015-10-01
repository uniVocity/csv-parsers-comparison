package com.univocity.articles.csvcomparison.parser8;

import com.univocity.articles.csvcomparison.parser.AbstractParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

@SuppressWarnings("unused")
public class Parsers {
    
    private static final List<AbstractParser> JAVA8_PARSERS = asList(
        new DecsParser()
    );

    public static List<AbstractParser> list() {
        List<AbstractParser> allParsers = new ArrayList<>(com.univocity.articles.csvcomparison.parser.Parsers.list());
        allParsers.addAll(JAVA8_PARSERS);
        return Collections.unmodifiableList(allParsers);
    }

}
