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

import me.mneri.csv.CsvDeserializer;
import me.mneri.csv.CsvException;
import me.mneri.csv.CsvReader;
import me.mneri.csv.RecyclableCsvLine;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

class MneriCsvParser extends AbstractParser {
    private static class DummyDeserializer implements CsvDeserializer<RecyclableCsvLine> {
        @Override
        public RecyclableCsvLine deserialize(RecyclableCsvLine line) {
            // At this point parsing has been completed. For the sake of the benchmark we return line here, but you
            // never want to do that. In fact, line is recycled between iterations of CsvReader.
            return line;
        }
    }

    private static class StringArrayCsvDeserializer implements CsvDeserializer<String[]> {
        @Override
        public String[] deserialize(RecyclableCsvLine line) {
            int count = line.getFieldCount();
            String[] strings = new String[count];

            for (int i = 0; i < count; i++) {
                strings[i] = line.getString(i);
            }

            return strings;
        }
    }

    protected MneriCsvParser() {
        super("mneri/csv");
    }

    @Override
    public List<String[]> parseRows(final Reader input) throws IOException, CsvException {
        List<String[]> lines = new ArrayList<String[]>();
        CsvReader<String[]> reader = null;

        try {
            reader = CsvReader.open(input, new StringArrayCsvDeserializer());

            while (reader.hasNext()) {
                lines.add(reader.next());
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return lines;
    }

    @Override
    public void processRows(final Reader input) throws IOException, CsvException {
        CsvReader<?> reader = null;

        try {
            reader = CsvReader.open(input, new DummyDeserializer());

            while (reader.hasNext()) {
                process(reader.next());
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
