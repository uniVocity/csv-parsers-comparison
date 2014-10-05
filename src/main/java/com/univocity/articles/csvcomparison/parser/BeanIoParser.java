package com.univocity.articles.csvcomparison.parser;

import java.io.*;
import java.util.*;

import org.beanio.stream.csv.*;

public class BeanIoParser extends AbstractParser{

	protected BeanIoParser() {
		super("Bean IO Parser");
	}

	@Override
	public int countRows(File input) throws Exception {
		int count = 0;
		
		CsvReader reader = new CsvReader(toReader(input));
		
		while(reader.read() != null){
			count++;
		}
		
		return count;
	}

	@Override
	public List<String[]> parseRows(File input) throws Exception {
		List<String[]> rows = new ArrayList<String[]>();
		
		CsvParserConfiguration cfg = new CsvParserConfiguration();
		cfg.setMultilineEnabled(true);
		cfg.setEscape('"');
		cfg.setQuote('"');
		cfg.setDelimiter(',');
		
		CsvReader reader = new CsvReader(toReader(input), cfg);
		
		String[] row;
		while((row = reader.read()) != null){
			rows.add(row);
		}
		
		return rows;
	}

}
