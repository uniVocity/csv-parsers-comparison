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

import br.com.objectos.comuns.io.*;
import br.com.objectos.comuns.io.csv.*;

class WayIoParser extends AbstractParser {

	protected WayIoParser() {
		super("Way IO Parser");
	}

	@Override
	public void processRows(File input) throws Exception {
		CsvFile reader = CsvFile.parse(input);

		ParsedLines lines = reader.getLines();
		for (Line line : lines) {
			process(line);
		}

	}

	@Override
	public List<String[]> parseRows(File input) throws Exception {
		List<String[]> rows = new ArrayList<String[]>();
		CsvFile reader = CsvFile.parse(input);

		ParsedLines lines = reader.getLines();

		//the API does not help us to provide the number of rows in each column
		final int COLS = 5;

		for (Line line : lines) {
			String[] row = new String[COLS];
			for (int i = 0; i < COLS; i++) {
				String value = line.column(i).get(String.class);
				row[i] = value;
			}
			rows.add(row);
		}
		return rows;
	}

}
