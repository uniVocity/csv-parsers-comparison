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
package com.univocity.articles.csvcomparison.parser8;

import com.univocity.articles.csvcomparison.parser.AbstractParser;
import diergo.csv.Row;

import java.io.Reader;
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
    public void processRows(final Reader input) throws Exception {
        asLines(input)
                .map(csvParser().separatedBy(',').inLaxMode().build()).flatMap(Collection::stream)
                .forEach(this::process);
    }

    @Override
    public List<String[]> parseRows(final Reader input) throws Exception {
        return asLines(input)
                .map(csvParser().separatedBy(',').build()).flatMap(Collection::stream)
                .map(this::toStringArray).collect(toList());
    }

    private String[] toStringArray(Row row) {
        return stream(spliterator(row.iterator(), row.getLength(), SIZED), false).toArray(String[]::new);
    }
}
