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
package com.univocity.articles.csvcomparison;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import com.univocity.articles.csvcomparison.parser.*;

public class CorrectnessComparison {

	private static final String CORRECTNESS_FILE =  "correctness.csv";
	private static final String CORRECTNESS_FILE_ENCODING =  "ascii";

	private static String[][] expectedResult = new String[][] {
		{ "Year", "Make", "Model", "Description", "Price" },
		{ "1997", "Ford", "E350", "ac, abs, moon", "3000.00" },
		{ "1999", "Chevy", "Venture \"Extended Edition\"", null, "4900.00" },
		{ "1996", "Jeep", "Grand Cherokee", "MUST SELL!\nair, moon roof, loaded", "4799.00" },
		{ "1999", "Chevy", "Venture \"Extended Edition, Very Large\"", null, "5000.00" },
		{ null, null, "Venture \"Extended Edition\"", null, "4900.00" }
	};

	private static void assertHeadersAndValuesMatch(File file, String fileEncoding, AbstractParser parser) throws Exception {

		Reader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), fileEncoding));

			List<String[]> parsedRows = parser.parseRows(reader);

			if (parsedRows.size() != expectedResult.length) {
				System.err.println("Parser " + parser.getName() + " produced " + parsedRows.size() + " rows instead of " + expectedResult.length);
				return;
			}
			for (int i = 0; i < expectedResult.length; i++) {
				String[] row = parsedRows.get(i);
				String[] expectedRow = expectedResult[i];

				if (row.length != expectedRow.length) {
					System.err.println("Parser " + parser.getName() + " produced " + Arrays.toString(row) + " rows instead of " + Arrays.toString(expectedRow));
					return;
				}

				for (int j = 0; j < expectedRow.length; j++) {
					String value = row[j] == null ? "" : String.valueOf(row[j]).trim();
					String expected = expectedRow[j] == null ? "" : String.valueOf(expectedRow[j]).trim();

					if (!value.equals(expected)) {
						System.err.println("Parser " + parser.getName() + " produced " + value + " instead of " + expected + ", at row " + j);
					}
				}
			}
		} finally {
			if(reader != null) {
				reader.close();
			}
		}
	}

	public static void main(final String... args) throws URISyntaxException {

		final File input;
		final URL inputUrl = CorrectnessComparison.class.getClassLoader().getResource(CORRECTNESS_FILE);
		if(inputUrl != null) {
			input = new File(inputUrl.toURI());
		} else {
			if(args.length > 0) {
				input = new File(args[0], CORRECTNESS_FILE);
				if(!input.exists()) {
					throw new IllegalStateException("Could not find '" + CORRECTNESS_FILE + "' in classpath or in folder: " + args[0]);
				}
			} else {
				throw new IllegalStateException("Could not find '" + CORRECTNESS_FILE + "' in classpath");
			}
		}

		for (AbstractParser parser : Parsers.list()) {
			try {
        System.out.println("try Parser " + parser.getName());
				assertHeadersAndValuesMatch(input, CORRECTNESS_FILE_ENCODING, parser);
			} catch (Throwable ex) {
				System.err.println("Parser " + parser.getName() + " threw exception: " + ex.getMessage());
			}
		}

	}

}
