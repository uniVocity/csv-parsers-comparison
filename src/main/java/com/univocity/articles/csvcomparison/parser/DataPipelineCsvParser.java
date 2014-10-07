/**
 * Note: this test depends on the DataPipeline.jar that must be obtained at: http://northconcepts.com/data-pipeline/
 *
 * Once obtain this library, add it to your classpath locally.
 */
/*
package com.univocity.articles.csvcomparison.parser;

import java.io.*;
import java.util.*;

import com.northconcepts.datapipeline.core.*;
import com.northconcepts.datapipeline.csv.*;

public class DataPipelineCsvParser extends AbstractParser {

	protected DataPipelineCsvParser() {
		super("Data pipeline");
	}

	@Override
	public void processRows(File input) throws Exception {

		DataReader reader = new CSVReader(input).setFieldNamesInFirstRow(true);
		reader.open();
		while (process(reader.read()));
		reader.close();

	}

	@Override
	public List<String[]> parseRows(File input) throws Exception {
		List<String[]> rows = new ArrayList<String[]>();

		DataReader reader = new CSVReader(input)
		.setAllowMultiLineText(true)
		.setFieldNamesInFirstRow(false);

		reader.open();
		Record record;
		while ((record = reader.read()) != null) {
			rows.add(record.getValues().toArray(new String[record.getFieldCount()]));
		}
		reader.close();

		return rows;
	}

}
*/