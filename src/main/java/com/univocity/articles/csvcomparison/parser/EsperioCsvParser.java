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

import com.espertech.esperio.*;
import com.espertech.esperio.csv.*;

class EsperioCsvParser extends AbstractParser {

	protected EsperioCsvParser() {
		super("Esperio CSV parser");
	}

	@Override
	public void processRows(File input) throws Exception {

		AdapterInputSource adapterInputSource = new AdapterInputSource(input);
		CSVReader reader = new CSVReader(adapterInputSource);
		try {
			while (process(reader.getNextRecord()));
		} catch (EOFException ex) {
			//end of file, return... lovely implementation
		}
	}

	@Override
	public List<String[]> parseRows(File input) throws Exception {
		List<String[]> rows = new ArrayList<String[]>();
		AdapterInputSource adapterInputSource = new AdapterInputSource(input);
		CSVReader reader = new CSVReader(adapterInputSource);
		String[] row;
		try {
			while ((row = reader.getNextRecord()) != null) {
				rows.add(row);
			}
		} catch (EOFException ex) {
			//end of file, return... lovely implementation
		}
		return rows;
	}

}
