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
import java.util.Map.Entry;

import com.univocity.articles.csvcomparison.parser.*;

public class PerformanceComparison {

	private final File file;

	PerformanceComparison(File file) {
		this.file = file;
	}

	private long run(AbstractParser parser) throws Exception {
		long start = System.currentTimeMillis();

		parser.processRows(file);

		long time = (System.currentTimeMillis() - start);
		System.out.println("took " + time + " ms to read " + parser.getRowCount() + " rows. ");
		parser.resetRowCount();
		System.setProperty("blackhole", parser.getBlackhole());
		return time;
	}

	private TreeMap<Long, String> orderByAverageTime(int loops, Map<String, Long[]> stats) {
		TreeMap<Long, String> averages = new TreeMap<Long, String>();

		for (Entry<String, Long[]> parserTimes : stats.entrySet()) {
			Long[] times = parserTimes.getValue();
			long average = 0L;
			//we are discarding the first recorded time here to take into account JIT optimizations
			for (int i = 1; i < times.length; i++) {
				average = average + times[i];
			}
			average = average / (loops - 1);
			averages.put(average, parserTimes.getKey());
		}

		return averages;
	}

	private long getBestTime(Long[] times) {
		long best = times[1];
		for (int i = 1; i < times.length; i++) {
			if (times[i] < best) {
				best = times[i];
			}
		}
		return best;
	}

	private long getWorstTime(Long[] times) {
		long worst = times[1];
		for (int i = 1; i < times.length; i++) {
			if (times[i] > worst) {
				worst = times[i];
			}
		}
		return worst;
	}

	private void printResults(int loops, Map<String, Long[]> stats) {
		System.out.println("\n=========\n AVERAGES \n=========\n");

		Map<Long, String> averages = orderByAverageTime(loops, stats);
		long bestTime = 0;
		for (Entry<Long, String> average : averages.entrySet()) {
			long time = average.getKey();
			String parser = average.getValue();
			System.out.print("| " + parser + " \t | " + time + " ms ");
			
			if (time == -1) {
				System.out.println("Could not execute");
				continue;
			}

			if (bestTime != 0) {
				long increasePercentage = time * 100 / bestTime - 100;
				System.out.print(" \t | " + increasePercentage + "% ");
			} else{
				bestTime = time;
				System.out.print(" \t | Best time! ");
			}

			long best = getBestTime(stats.get(parser));
			long worst = getWorstTime(stats.get(parser));

			System.out.println(" \t | " + best + " ms \t | " + worst + " ms |");
			
		}
	}

	public void execute(final int loops) throws Exception {
		Map<String, Long[]> stats = new HashMap<String, Long[]>();

		for (AbstractParser parser : Parsers.list()) {
			Long[] times = new Long[loops];
			Arrays.fill(times, -1L);
			stats.put(parser.getName(), times);
		}

		for (int i = 0; i < loops; i++) {
			for (AbstractParser parser : Parsers.list()) {
				try {
					System.out.print("Loop " + (i + 1) + " - executing " + parser.getName() + "... ");
					long time = run(parser);

					stats.get(parser.getName())[i] = time;
				} catch (Throwable ex) {
					System.out.println("Parser " + parser.getName() + " threw exception " + ex.getMessage());
				}
				System.gc();
				Thread.sleep(500);
			}
		}

		printResults(loops, stats);
	}

	public static void main(String... args) throws Exception {
		
		int loops = 6;
		File input = new File("src/main/resources/worldcitiespop.txt");
	
		new PerformanceComparison(input).execute(loops);

		File hugeInput = new File("src/main/resources/worldcitiespop_huge.txt");
		
		
		//executes only if the file has not been generated yet.
		
		
		//Previously, we created a huge file with the original input, replicated 15 times. All fields enclosed within quotes.
		//It would generate a file with 47,609,385 rows
		
		//Now, creates a copy of the original input. All fields enclosed within quotes. 
		//Overall performance is the similar in percentage terms, regardless of size. No point in melting our CPU's to get the same result.
		
		HugeFileGenerator.generateHugeFile(input, 1, hugeInput);

		System.out.println("==================================");
		System.out.println("=== Processing huge input file ===");
		System.out.println("==================================");

		
		new PerformanceComparison(hugeInput).execute(loops);
	}

}
