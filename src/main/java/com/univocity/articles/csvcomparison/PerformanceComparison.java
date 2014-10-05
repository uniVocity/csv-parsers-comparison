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

	private static File file = new File("src/main/resources/worldcitiespop.txt");
	private static final int LOOPS = 6;

	private static long run(AbstractParser parser) throws Exception {
		long start = System.currentTimeMillis();

		int rows = parser.countRows(file);

		long time = (System.currentTimeMillis() - start);
		System.out.println("took " + time + " ms to read " + rows + " rows");
		return time;
	}

	private static TreeMap<Long, String> orderByAverageTime(Map<String, Long[]> stats) {
		TreeMap<Long, String> averages = new TreeMap<Long, String>();

		for (Entry<String, Long[]> parserTimes : stats.entrySet()) {
			Long[] times = parserTimes.getValue();
			long average = 0L;
			//we are discarding the first recorded time here to take into account JIT optimizations
			for (int i = 1; i < times.length; i++) {
				average = average + times[i];
			}
			average = average / (LOOPS - 1);
			averages.put(average, parserTimes.getKey());
		}

		return averages;
	}
	

	private static long getBestTime(Long[] times) {
		long best = times[1];
		for (int i = 1; i < times.length; i++) {
			if (times[i] < best) {
				best = times[i];
			}
		}
		return best;
	}

	private static long getWorstTime(Long[] times) {
		long worst = times[1];
		for (int i = 1; i < times.length; i++) {
			if (times[i] > worst) {
				worst = times[i];
			}
		}
		return worst;
	}

	public static void main(String... args) throws Exception {
		Map<String, Long[]> stats = new HashMap<String, Long[]>();

		for (AbstractParser parser : Parsers.list()) {
			Long[] times = new Long[LOOPS];
			Arrays.fill(times, -1L);
			stats.put(parser.getName(), times);
		}

		for (int i = 0; i < LOOPS; i++) {
			for (AbstractParser parser : Parsers.list()) {
				try {
					System.out.print("Loop " + (i + 1) + " - executing " + parser.getName() + "... ");
					long time = run(parser);

					stats.get(parser.getName())[i] = time;
				} catch (Throwable ex) {
					System.out.println("Parser " + parser.getName() + " threw exception " + ex.getMessage());
				}
				System.gc();
				Thread.sleep(200);
			}
		}

		System.out.println("\n=========\n AVERAGES \n=========\n");

		Map<Long, String> averages = orderByAverageTime(stats);
		long previousTime = 0;
		for (Entry<Long, String> average : averages.entrySet()) {
			long time = average.getKey();
			String parser = average.getValue();
			System.out.print(parser + "\t - " + time + " ms.");

			if (time == -1) {
				System.out.println("Could not execute");
				continue;
			}

			if (previousTime != 0) {
				long increasePercentage = time * 100 / previousTime;
				System.out.print("+" + increasePercentage + "%");
			}
			previousTime = time;

			long best = getBestTime(stats.get(parser));
			long worst = getWorstTime(stats.get(parser));

			System.out.println("\t(Best: " + best + " ms. Worst: " + worst + " ms.)");
		}
	}

}
