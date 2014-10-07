package com.univocity.articles.jmh;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

@State(Scope.Benchmark)
public class FileToProcess {

	@Param("src/main/resources/worldcitiespop.txt")
	public String inputFile;
	
	@Param(value= { "1", "1000", "1000000", "-1"})
	public int nbRows;
	
	private File file;
	
	@TearDown
	public void destroy() throws IOException {
		file.delete();
	}
	
	@Setup
	public void init() throws IOException {
		
		file = File.createTempFile("bench" + nbRows, ".txt");
		System.out.println("Write file " + file);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "UTF-8"));
			try {
				String line;
				int i = 0;
				while((nbRows < 0 || i < nbRows) && (line = reader.readLine()) != null) {
					bw.write(line);
					bw.write("\n");
					i++;
				};
				
			} finally {
				reader.close();
			}
			
		} finally {
			bw.flush();
			bw.close();
		}
	}

	public Reader getReader() throws FileNotFoundException, UnsupportedEncodingException {
		return new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
	}
	
}
