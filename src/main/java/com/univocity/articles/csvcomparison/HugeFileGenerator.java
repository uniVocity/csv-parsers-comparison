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

import com.univocity.parsers.csv.*;

public class HugeFileGenerator {

	public static void generateHugeFile(File input, String inputEncoding, int timesToExpand, File hugeFile) throws Exception {

		if (hugeFile.exists()) {
			System.out.println("Huge file already generated.");
			return;
		}

		CsvParser parser = new CsvParser(new CsvParserSettings());

		CsvWriterSettings settings = new CsvWriterSettings();
		settings.setQuoteAllFields(true); //let's see how all parsers perform when the contents are enclosed within quotes.

		CsvWriter writer = new CsvWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(hugeFile), inputEncoding)), settings);
		long totalTime = 0L;
		try {
			Object[] row;
			for (int i = 0; i < timesToExpand; i++) {
				long start = System.currentTimeMillis();

				parser.beginParsing(new BufferedReader(new InputStreamReader(new FileInputStream(input), inputEncoding)));
				while ((row = parser.parseNext()) != null) {
					writer.writeRow(row);
				}
				long loopTime = System.currentTimeMillis() - start;
				totalTime += loopTime;
				System.out.println("Loop " + (i + 1) + " took " + loopTime + "ms. Total time: " + totalTime + " ms");
			}
		} finally {
			writer.close();
		}
		System.out.println("Finished!");
	}

}
