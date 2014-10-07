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
	public int countRows(final File input) throws Exception {

		int count = 0;

		CSVParse csvParser = new ExcelCSVParser(toReader(input));
		while (csvParser.getLine() != null) {
			count++;
		}

		return count;
	}

	@Override
	public List<String[]> parseRows(final File input) throws Exception {

		final CSVParse csvParser = new ExcelCSVParser(toReader(input));

		return asList(csvParser.getAllValues());
	}

}
