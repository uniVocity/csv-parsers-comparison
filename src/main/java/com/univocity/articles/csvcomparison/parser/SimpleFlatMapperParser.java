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
	public void processRows(final File input) throws Exception {
		csvParser.readRows(toReader(input), new RowHandler<String[]>() {
			@Override
			public void handle(String[] t) throws Exception {
				process(t);
			}
		});
	}

	@Override
	public List<String[]> parseRows(final File input) throws Exception {
		return csvParser.readRows(toReader(input), new ListHandler<String[]>()).getList();
	}

}
