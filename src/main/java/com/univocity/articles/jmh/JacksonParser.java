package com.univocity.articles.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;

@State(Scope.Benchmark)
public class JacksonParser  {
	CsvMapper csvMapper;
	
	@Setup
	public void init() {
		csvMapper = new CsvMapper();
		csvMapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
	}
	
	@Benchmark
	public void parseFile(final FileToProcess fileToProcess, final Blackhole blackhole) throws Exception {

		MappingIterator<String[]> iterator = csvMapper.reader(String[].class).readValues(fileToProcess.getReader());

		int i = 0;
		while (iterator.hasNext()) {
			blackhole.consume(iterator.next());
			i++;
		}
		if (i != fileToProcess.nbRows) {
			throw new Error("nb rows does no match");
		}

	}
}
