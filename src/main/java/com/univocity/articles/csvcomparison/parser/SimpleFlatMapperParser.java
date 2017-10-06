package com.univocity.articles.csvcomparison.parser;


import java.io.File;
import java.util.*;

import org.simpleflatmapper.csv.CsvParser;

public class SimpleFlatMapperParser extends AbstractParser {
	protected SimpleFlatMapperParser() {

		super("SimpleFlatMapper CSV parser");
	}

	@Override
	public void processRows(final File input) throws Exception {
		final Iterator<String[]> it = CsvParser.iterator(toReader(input));
		while(it.hasNext()) {
			process(it.next());
		}
	}

	@Override
	public List<String[]> parseRows(final File input) throws Exception {
		final List<String[]> list = new ArrayList<String[]>();
		final Iterator<String[]> it = CsvParser.iterator(toReader(input));
		while(it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}
}
