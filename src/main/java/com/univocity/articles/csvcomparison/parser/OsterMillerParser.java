package com.univocity.articles.csvcomparison.parser;

import static java.util.Arrays.*;

import java.io.*;
import java.util.*;

import com.Ostermiller.util.*;

public class OsterMillerParser extends AbstractParser {

	protected OsterMillerParser() {

		super("Oster Miller CSV parser");
	}

	@Override
	public void processRows(final File input) throws Exception {

		CSVParse csvParser = new ExcelCSVParser(toReader(input));
		while (process(csvParser.getLine()));
	}

	@Override
	public List<String[]> parseRows(final File input) throws Exception {

		final CSVParse csvParser = new ExcelCSVParser(toReader(input));

		return asList(csvParser.getAllValues());
	}

}
