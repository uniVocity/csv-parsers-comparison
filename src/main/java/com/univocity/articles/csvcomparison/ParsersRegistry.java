package com.univocity.articles.csvcomparison;

import com.univocity.articles.csvcomparison.parser.AbstractParser;
import com.univocity.articles.csvcomparison.parser.Parsers;

import java.util.List;

public class ParsersRegistry {

    @SuppressWarnings("unchecked")
    public static List<AbstractParser> getParsers() {
        try {
            Class<?> java8Parsers = Class.forName("com.univocity.articles.csvcomparison.parser8.Parsers");
            return (List<AbstractParser>) java8Parsers.getMethod("list").invoke(null);
        } catch (LinkageError e) {
            return Parsers.list();
        } catch (Exception e) {
            throw new RuntimeException("Cannot get parsers", e);
        }
    }
}
