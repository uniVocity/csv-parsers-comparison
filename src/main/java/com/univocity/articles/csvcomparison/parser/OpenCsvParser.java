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

import au.com.bytecode.opencsv.*;

class OpenCsvParser extends AbstractParser {

	protected OpenCsvParser() {
		super("OpenCSV");
	}

	@Override
	public void processRows(File input) throws Exception {
		CSVReader reader = new CSVReader(toReader(input));
		try {
			while (process(reader.readNext()));
		} finally {
			reader.close();
		}
	}

	@Override
	public List<String[]> parseRows(File input) throws Exception {
		CSVReader reader = new CSVReader(toReader(input));
		try {
			return reader.readAll();
		} finally {
			reader.close();
		}
	}

}
