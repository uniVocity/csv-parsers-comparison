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

import com.univocity.parsers.csv.*;

class UnivocityParser extends AbstractParser {

	protected UnivocityParser() {
		super("uniVocity CSV parser");
	}

	@Override
	public int countRows(File input) {
		CsvParserSettings settings = new CsvParserSettings();
		CsvParser parser = new CsvParser(settings);

		parser.beginParsing(toReader(input));

		int count = 0;
		while (parser.parseNext() != null) {
			count++;
		}
		return count;
	}

	@Override
	public List<String[]> parseRows(File input) {

		CsvParserSettings settings = new CsvParserSettings();
		CsvParser parser = new CsvParser(settings);

		return parser.parseAll(toReader(input));
	}

}
