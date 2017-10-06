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
	public void processRows(final Reader input) throws Exception {
		CSVParse csvParser = new ExcelCSVParser(input);
		while (process(csvParser.getLine()));
	}

	@Override
	public List<String[]> parseRows(final Reader input) throws Exception {
		final CSVParse csvParser = new ExcelCSVParser(input);
		return asList(csvParser.getAllValues());
	}

}
