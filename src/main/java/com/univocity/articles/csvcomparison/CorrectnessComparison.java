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
import java.util.*;

import com.univocity.articles.csvcomparison.parser.*;

public class CorrectnessComparison {

	private static File file = new File("src/main/resources/correctness.csv");

	private static String[][] expectedResult = new String[][] {
		{ "Year", "Make", "Model", "Description", "Price" },
		{ "1997", "Ford", "E350", "ac, abs, moon", "3000.00" },
		{ "1999", "Chevy", "Venture \"Extended Edition\"", null, "4900.00" },
		{ "1996", "Jeep", "Grand Cherokee", "MUST SELL!\nair, moon roof, loaded", "4799.00" },
		{ "1999", "Chevy", "Venture \"Extended Edition, Very Large\"", null, "5000.00" },
		{ null, null, "Venture \"Extended Edition\"", null, "4900.00" }
	};

	private static void assertHeadersAndValuesMatch(AbstractParser parser) throws Exception {

		List<String[]> parsedRows = parser.parseRows(file);

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
	}

	public static void main(String... args) {

		for (AbstractParser parser : Parsers.list()) {
			try {
				assertHeadersAndValuesMatch(parser);
			} catch (Throwable ex) {
				System.err.println("Parser " + parser.getName() + " threw exception " + ex.getMessage());
			}
		}

	}

}
