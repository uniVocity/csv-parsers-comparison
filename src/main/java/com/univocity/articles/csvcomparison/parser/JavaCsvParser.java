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

import com.csvreader.*;

class JavaCsvParser extends AbstractParser {

	protected JavaCsvParser() {
		super("Java CSV Parser");
	}

	@Override
	public void processRows(File input) throws Exception {

		CsvReader reader = new CsvReader(toReader(input));
		while (reader.readRecord()){
			process(reader.getValues());
		}
	}

	@Override
	public List<String[]> parseRows(File input) throws Exception {
		List<String[]> rows = new ArrayList<String[]>();

		CsvReader reader = new CsvReader(toReader(input));
		while (reader.readRecord()) {
			rows.add(reader.getValues());
		}

		return rows;

	}

}
