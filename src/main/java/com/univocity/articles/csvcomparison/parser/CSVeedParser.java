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

import org.csveed.api.*;

class CSVeedParser extends AbstractParser {

	protected CSVeedParser() {
		super("CSVeed");
	}

	@Override
	public void processRows(File input) throws Exception {
		CsvClient<Row> parser = new CsvClientImpl<Row>(toReader(input));
		while (process(parser.readRow()));
	}

	@Override
	public List<String[]> parseRows(File input) throws Exception {
		List<String[]> rows = new ArrayList<String[]>();

		Row row;
		CsvClient<Row> parser = new CsvClientImpl<Row>(toReader(input));
		while ((row = parser.readRow()) != null) {
			String[] data = new String[row.size()];
			for (int i = 0; i < data.length; i++) {
				data[i] = row.get(i);
			}
			rows.add(data);
		}

		return rows;
	}

}
