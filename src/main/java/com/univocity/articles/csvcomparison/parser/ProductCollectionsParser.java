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

import java.io.*;
import java.util.*;

import com.github.marklister.collections.io.*;

class ProductCollectionsParser extends AbstractParser {

    protected ProductCollectionsParser() {
        super("Product Collections parser");
    }

    @Override
    public void processRows(final Reader input) throws Exception {
        final CSVReader reader = new CSVReader(input,',','"',1);
        try {
            while(reader.hasNext()) process(reader.next());
        } finally {
            reader.reader().close();
        }
    }

    @Override
    public List<String[]> parseRows(final Reader input) throws Exception {
        final CSVReader reader = new CSVReader(input,',','"',0);
        try {
            final List<String[]> values = new ArrayList<String[]>();
            while (reader.hasNext()) {
                values.add(reader.next());
            }
            return values;
        } finally {
            reader.reader().close();
        }
    }

}
