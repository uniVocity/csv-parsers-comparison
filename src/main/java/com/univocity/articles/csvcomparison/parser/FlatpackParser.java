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

import net.sf.flatpack.*;

class FlatpackParser extends AbstractParser {

	protected FlatpackParser() {
		super("Flatpack");
	}

	@Override
	public void processRows(final Reader input) throws Exception {

		Parser parser = DefaultParserFactory.getInstance().newDelimitedParser(input, ',', '\n');
		DataSet dataset = parser.parse();
		while (process(dataset.next()));
	}

	@Override
	public List<String[]> parseRows(final Reader input) throws Exception {
		List<String[]> rows = new ArrayList<String[]>();
		Parser parser = DefaultParserFactory.getInstance().newDelimitedParser(input, ',', '"');

		DataSet dataset = parser.parse();

		while (dataset.next()) {
			Record record = dataset.getRecord().get();
			String[] row = new String[record.getColumns().length];
			int i = 0;
			for (String column : record.getColumns()) {
				row[i++] = record.getString(column);
			}
			rows.add(row);
		}
		return rows;
	}

}
