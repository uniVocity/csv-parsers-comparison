package com.univocity.articles.csvcomparison.parser;


import java.io.File;
import java.util.*;

import org.sfm.csv.parser.CsvParser;
import org.sfm.utils.ListHandler;
import org.sfm.utils.RowHandler;

public class SimpleFlatMapperParser extends AbstractParser {
	CsvParser csvParser = new CsvParser();
	protected SimpleFlatMapperParser() {

		super("SimpleFlatMapper CSV parser");
	}

	@Override
	public int countRows(final File input) throws Exception {
		return csvParser.readRows(toReader(input), new RowHandler<String[]>() {
			public int count;
			@Override
			public void handle(String[] t) throws Exception {
				count++;
			}
		}).count;
	}

	@Override
	public List<String[]> parseRows(final File input) throws Exception {
		return csvParser.readRows(toReader(input), new ListHandler<String[]>()).getList();
	}

}
