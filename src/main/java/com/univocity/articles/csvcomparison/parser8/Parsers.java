package com.univocity.articles.csvcomparison.parser8;

import com.univocity.articles.csvcomparison.parser.AbstractParser;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Parsers {

    public static List<AbstractParser> list() {
        List<AbstractParser> allParsers = new ArrayList<>(com.univocity.articles.csvcomparison.parser.Parsers.list());
        allParsers.add(new DecsParser());
        return allParsers;
    }

}
