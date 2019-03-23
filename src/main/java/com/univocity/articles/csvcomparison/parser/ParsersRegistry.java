/*******************************************************************************
 * Copyright 2014 uniVocity Software Pty Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.univocity.articles.csvcomparison.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParsersRegistry {

    private static List<AbstractParser> parsers = null;

    public static List<AbstractParser> getParsers() {
        if(parsers == null) {
            parsers = getAllParsers();
        }
        return parsers;
    }

    private static List<AbstractParser> getAllParsers() {
        // Get Parsers for current VM version
        final List<AbstractParser> parsers = new ArrayList<AbstractParser>(Parsers.list());

        // Also include Java 8 only parsers?
        final String javaVersion = System.getProperty("java.version");
        System.out.println("Detected Java version: " + javaVersion);

        if(javaVersion != null && javaVersion.matches("(?:1[.]8[.]|1[.]9[.]|9[.]|\\d{2}[.]).*")) {
            System.out.println("Also enabling Java 8 and above parsers!");
            parsers.addAll(getJava8OnlyParsers());
        }

        return Collections.unmodifiableList(parsers);
    }

    private static List<AbstractParser> getJava8OnlyParsers() {
        try {
            final Class<?> java8Parsers = Class.forName("com.univocity.articles.csvcomparison.parser8.Parsers");
            return (List<AbstractParser>) java8Parsers.getMethod("list").invoke(null);
        } catch (final LinkageError e) {
            throw new RuntimeException("Cannot get Java 8 Only parsers", e);
        } catch (final Exception e) {
            throw new RuntimeException("Cannot get Java 8 Only parsers", e);
        }
    }
}
