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

import org.apache.commons.csv.*;

class CommonsCsvParser extends AbstractParser {

	protected CommonsCsvParser() {
		super("Apache Commons CSV");
	}

	@Override
	public void processRows(File input) throws Exception {
		CSVFormat format = CSVFormat.RFC4180;
		CSVParser parser = CSVParser.parse(input, getEncoding(), format);
		for (CSVRecord record : parser) {
			process(record);
		}
	}

	@Override
	public List<String[]> parseRows(File input) throws Exception {
		CSVFormat format = CSVFormat.RFC4180;
		CSVParser parser = CSVParser.parse(input, getEncoding(), format);

		List<String[]> rows = new ArrayList<String[]>();

		for (CSVRecord record : parser) {
			String[] row = new String[record.size()];
			for (int i = 0; i < row.length; i++) {
				row[i] = record.get(i);
			}
			rows.add(row);
		}
		return rows;
	}

}
