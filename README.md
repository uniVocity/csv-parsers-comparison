# csv-parsers-comparison

This project aims to compare all CSV parsers for Java in existence, or at least the ones that seem to work. There are too many and the intention here is to help you decide which one is the best for you.

Currently, we are only testing parsing performance. As the input file, we will be using the famous [worldcitiespop.txt](http://www.maxmind.com/download/worldcities/worldcitiespop.txt.gz), which is made available for free by [Maxmind](http://www.maxmind.com). It contains more than 3 million rows, which should be sufficient for our test.

Please download the [worldcitiespop.txt](http://www.maxmind.com/download/worldcities/worldcitiespop.txt.gz) and place it under `src/main/resources/` before executing the main class [PerformanceComparison.java](./src/main/java/com/univocity/articles/csvcomparison/PerformanceComparison.java).

Our test is very simple and involves just counting the number of rows read from the input file. The implementation using each parser is [here](./src/main/java/com/univocity/articles/csvcomparison/parser). 

### Important
 The input file is **not** [RFC 4180](https://www.rfc-editor.org/rfc/rfc4180.txt) compliant. We generate a compliant version using the [HugeFileGenerator](./src/main/java/com/univocity/articles/csvcomparison/HugeFileGenerator.java) class to test the parsers against a generated file with 47,609,385 rows.
 
It's important to notice that there's no such thing as a CSV standard and we do not recommend you to use parsers that follow the RFC strictly, as they will blow up in face of non-compliant inputs. The reality is: your parser must be ready to process crooked data instead of going belly up. In the end, your client is the one that tells you what you must swallow, and in many circumstances it's not up to you to decide how your data is going to be generated.

We generate a RFC compliant version to give those sensitive parsers a chance to see how they perform. Once again, we consider their usage risky.

## CSV Parsers

This is the list of all parsers tested. Parsers follows by * are commercial an their jars are not included in this project. You have to download them independently:

| Parser                       |   Version | Website                                              |
|------------------------------|----------:|------------------------------------------------------|
| uniVocity-parsers' CsvParser |     1.0.2 | [www.univocity.com](http://www.univocity.com)        |
| CSVeed                       |     0.4.0 | [csveed.org](http://csveed.org)                      |
| Apache Commons CSV           |       1.0 | [commons.apache.org/proper/commons-csv] (http://commons.apache.org/proper/commons-csv) |
| OpenCSV                      |       2.3 | [opencsv.sourceforge.net](http://opencsv.sourceforge.net/)                             |
| SuperCSV                     |     2.2.0 | [supercsv.sourceforge.net](http://supercsv.sourceforge.net/)                           |
| JavaCSV                      |       2.0 | [sourceforge.net/projects/javacsv](http://sourceforge.net/projects/javacsv)            |
| jCSV                         |     1.4.0 | [code.google.com/p/jcsv](https://code.google.com/p/jcsv/)                              |
| flatpack                     |     3.4.2 | [flatpack.sourceforge.net](http://flatpack.sourceforge.net/)                           |
| SimpleCSV                    |       2.0 | [github.com/quux00/simplecsv](https://github.com/quux00/simplecsv)                     |
| gj-csv                       |       1.0 | ?                                                                                      |
| esperio-csv                  |    4.11.0 | [www.espertech.com](http://www.espertech.com/)                                         |
| way-io                       |     1.6.0 | [www.objectos.com.br](http://www.objectos.com.br/)                              		|
| beanIO                       |     2.1.0 | [beanio.org](http://beanio.org/)                              							|
| DataPipeline's CSVReader*    |     2.3.4 | [northconcepts.com/data-pipeline/](http://northconcepts.com/data-pipeline/)   			|



## Statistics (updated 7th of October, 2014)

Results will vary depending on your setup and hardware. For reference, here's my (very) modest hardware, an ultrabook: 

 * CPU: Intel i5-3337U @ 1.8 GHz
 * RAM: 4 GB
 * Storage: 128GB SSD drive
 * OS: Arch Linux 64 bit 


*Note* [uniVocity-parsers](http://github.com/uniVocity/univocity-parsers/) provides an option to select the fields you are interested in, and our parsers will execute faster by not processing values that are not selected. As it can be seen in the results below, it makes quite a difference in performance.

### Processing 3,173,958 rows of non [RFC 4180](https://www.rfc-editor.org/rfc/rfc4180.txt) compliant input.


| Parser                                     | Average time       | % Slower than best | Best time | Worst time |
|--------------------------------------------|-------------------:|-------------------:|----------:|-----------:|
|uniVocity CSV parser - with field selection |  1,492 ms           | BEST!              | 1,458 ms   | 1,593 ms    |
|uniVocity CSV parser                        |  1,871 ms           | 25%                | 1,825 ms   | 1,966 ms    |
|OpenCSV                                     |  2,294 ms           | 53%                | 2,271 ms   | 2,371 ms    |
|JavaCSV Parser                              |  2,380 ms           | 59%                | 2,362 ms   | 2,410 ms    |
|jCSV Parser                                 |  2,415 ms           | 61%                | 2,375 ms   | 2,489 ms    |
|DataPipeline's CSVReader					 |	2,442 ms  		   | 63%  	 		    | 2,376 ms   | 2,591 ms	   |
|Simple CSV parser                           |  2,600 ms           | 74%                | 2,535 ms   | 2,686 ms    |
|SuperCSV                                    |  2,658 ms           | 78%                | 2,613 ms   | 2,752 ms    |
|Apache Commons CSV                          |  2,800 ms           | 87%                | 2,771 ms   | 2,836 ms    |
|Way IO Parser                               |  3,329 ms           | 123%               | 3,238 ms   | 3,418 ms    |
|Gen-Java CSV                                |  6,145 ms           | 311%               | 6,064 ms   | 6,257 ms    |


 * `Esperio-csv` and `CSVeed` were unable to process the file and threw exceptions.
 * `Flatpack` hanged so I had to remove it from the test [here](./src/main/java/com/univocity/articles/csvcomparison/parser/Parsers.java).
 * `BeanIO` threw an exception I could understand and debug. Turns out it is unable to parse fields when the quote character is part of the value, e.g. `value1, val"ue2, value3 `. 
 
### Processing 47,609,385 rows of [RFC 4180](https://www.rfc-editor.org/rfc/rfc4180.txt) compliant input.

**Note** this input file has all the values enclosed within quotes. We generated the input like this on purpose as the algorithm to process quotes is a bit different.

| Parser                                     | Average time       | % Slower than best | Best time  | Worst time |
|--------------------------------------------|-------------------:|-------------------:|-----------:|-----------:|
|uniVocity CSV parser - with field selection |  28,548 ms         | BEST!              | 28,390 ms  | 28,656 ms  |
|uniVocity CSV parser                        |  34,749 ms         | 21%                | 34,513 ms  | 35,024 ms  |
|JavaCSV Parser                              |  45,576 ms         | 59%                | 43,865 ms  | 46,739 ms  |
|DataPipeline's CSVReader					 |	46,778 ms  		  |	63%				   | 44,791 ms  | 48,009 ms  |
|Simple CSV parser                           |  47,443 ms         | 66%                | 46,137 ms  | 48,502 ms  |
|OpenCSV                                     |  49,686 ms         | 74%                | 45,172 ms  | 61,872 ms  |
|SuperCSV                                    |  51,310 ms         | 79%                | 50,147 ms  | 51,913 ms  |
|jCSV Parser                                 |  53,165 ms         | 86%                | 51,833 ms  | 55,594 ms  |
|Apache Commons CSV                          |  64,631 ms         | 126%               | 63,585 ms  | 67,059 ms  |
|Way IO Parser                               |  65,018 ms         | 127%               | 61,822 ms  | 66,929 ms  |
|esperio-csv								 |  71,890 ms		  | 151%			   | 70,484 ms  | 73,838 ms  |
|Gen-Java CSV                                |  119,886 ms        | 319%               | 117,193 ms | 121,769 ms |
|beanIO	 	                                 |  139,618 ms        | 389%               | 133,673 ms | 147,379 ms |

 * `CSVeed` was unable to process the file and threw exception with the message "Parsing symbol OTHER_SYMBOL [44] in state ESCAPING".
 * `Flatpack` blew up the Java heap space so I had to remove it from the test [here](./src/main/java/com/univocity/articles/csvcomparison/parser/Parsers.java).


## Reliability (updated 6th of October, 2014)

The following parsers were unable to process the [RFC 4180](https://www.rfc-editor.org/rfc/rfc4180.txt) compliant file [correctness.csv](./src/main/resources/correctness.csv). This test is executed using the class [CorrectnessComparison.java](./src/main/java/com/univocity/articles/csvcomparison/CorrectnessComparison.java)

| Parser                                     | Error |
|--------------------------------------------|:------|
|CSVeed										 | CSVeed threw exception "Illegal state transition: Parsing symbol QUOTE_SYMBOL [34] in state INSIDE_FIELD" |
|jCSV Parser                                 | JCSV Parser produced ["Year,Make,Model,Description,Price"] instead of ["Year", "Make", "Model", "Description", "Price"] |
|Simple CSV parser                           | Simple CSV parser threw exception "The separator, quote, and escape characters must be different!" |
|Way IO Parser                               | Way IO Parser threw exception "Could not convert  to class java.lang.String" |
|Gen-Java CSV                                | Gen-Java CSV produced 7 rows instead of 6 |
|Flatpack									 | Flatpack produced 5 rows instead of 6 |

## Conclusion

These results demonstrate clearly that [uniVocity](http://www.univocity.com) developed the *fastest CSV parser for Java* to date. We are extremely proud to see the huge performance advantage [uniVocity-parsers](http://www.univocity.com/pages/about-parsers) offers over all other options. Not to mention the unique features that make our parsers not only the faster, but also the most feature complete and extensible text parsing architecture available for the Java platform.

Head on to the [uniVocity-parsers github page](http://github.com/uniVocity/univocity-parsers/) to get access to its source code and documentation. Contributions are welcome. 

#### Commercial support is available for your peace of mind. [Click here to learn more.](http://www.univocity.com/products/parsers-support)