package com.univocity.articles.csvcomparison.parser;

import com.Ostermiller.util.CSVParse;
import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.ExcelCSVParser;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

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
