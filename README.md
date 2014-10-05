# csv-parsers-comparison

This project aims to compare all CSV parsers for Java in existence, or at least the ones that seem to work. There are too many and the intention here is to help you decide which one is the best for you.

Currently, we are only testing parsing performance. As the input file, we will be using the famous [worldcitiespop.txt](http://www.maxmind.com/download/worldcities/worldcitiespop.txt.gz), which is made available for free by [Maxmind](http://www.maxmind.com). It contains more than 3 million rows, which should be sufficient for our test.

Please download the [worldcitiespop.txt](http://www.maxmind.com/download/worldcities/worldcitiespop.txt.gz) and place it under `src/main/resources/` before executing the main class [PerformanceComparison.java](./src/main/java/com/univocity/articles/csvcomparison/PerformanceComparison.java).

Our test is very simple and involves just counting the number of rows read from the input file. The implementation using each parser is [here](./src/main/java/com/univocity/articles/csvcomparison/parser). 

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
| way-io                       |     1.6.0 | [www.objectos.com.br](http://www.objectos.com.br/)                              |
| beanIO                       |     2.1.0 | [beanio.org](http://beanio.org/)                              |
| DataPipeline's CSVReader*    |     2.3.4 | [northconcepts.com/data-pipeline/](http://northconcepts.com/data-pipeline/)   |



## Statistics (updated 6th of October, 2014)

Results will vary depending on your setup and hardware. For reference, here's my (very) modest hardware, an ultrabook: 

 * CPU: Intel i5-3337U @ 1.8 GHz
 * RAM: 4 GB
 * Storage: 128GB SSD drive
 * OS: Arch Linux 64 bit 

These are the statistics I got after processing 3,173,958 rows:

| Parser                                     | Average time       | % Slower than best | Best time | Worst time |
|--------------------------------------------|-------------------:|-------------------:|----------:|-----------:|
|uniVocity CSV parser - with field selection |  1492 ms           | BEST!              | 1458 ms   | 1593 ms    |
|uniVocity CSV parser                        |  1871 ms           | 25%                | 1825 ms   | 1966 ms    |
|OpenCSV                                     |  2294 ms           | 53%                | 2271 ms   | 2371 ms    |
|JavaCSV Parser                              |  2380 ms           | 59%                | 2362 ms   | 2410 ms    |
|jCSV Parser                                 |  2415 ms           | 61%                | 2375 ms   | 2489 ms    |
|DataPipeline's CSVReader					 |	2442 ms  		  |	63%				   | 2376 ms   | 2591 ms	|
|Simple CSV parser                           |  2600 ms           | 74%                | 2535 ms   | 2686 ms    |
|SuperCSV                                    |  2658 ms           | 78%                | 2613 ms   | 2752 ms    |
|Apache Commons CSV                          |  2800 ms           | 87%                | 2771 ms   | 2836 ms    |
|Way IO Parser                               |  3329 ms           | 123%               | 3238 ms   | 3418 ms    |
|Gen-Java CSV                                |  6145 ms           | 311%               | 6064 ms   | 6257 ms    |

 * `Esperio-csv` and `CSVeed` were unable to process the file and threw exceptions.
 * `Flatpack` hanged so I had to remove it from the test [here](./src/main/java/com/univocity/articles/csvcomparison/parser/Parsers.java).
 * `BeanIO` threw an exception I could understand and debug. Turns out it is unable to parse fields when the quote character is part of the value, e.g. `value1, val"ue2, value3 `. 

*Note* [uniVocity-parsers](http://github.com/uniVocity/univocity-parsers/) provides an option to select the fields you are interested in, and our parsers will execute faster by not processing values that are not selected. As it can be seen in the results above, it makes quite a difference in performance.

*Note (2)* The previous results were discarded after I discovered a zombie process running on the background. Nevertheless, the performance difference results were similar in percentage terms.

## Reliability (updated 6th of October, 2014)

The following parsers were unable to process the [RFC 4180](https://www.rfc-editor.org/rfc/rfc4180.txt) compliant file [correctness.csv](./src/main/resources/correctness.csv). This test is executed using the class [CorrectnessComparison.java](./src/main/java/com/univocity/articles/csvcomparison/CorrectnessComparison.java)

| Parser                                     | Error |
|--------------------------------------------|:------|
|CSVeed										 | CSVeed threw exception Illegal state transition: Parsing symbol QUOTE_SYMBOL [34] in state INSIDE_FIELD |
|jCSV Parser                                 | JCSV Parser produced ["Year,Make,Model,Description,Price"] instead of ["Year", "Make", "Model", "Description", "Price"] |
|Simple CSV parser                           | Simple CSV parser threw exception The separator, quote, and escape characters must be different! |
|Way IO Parser                               | Way IO Parser threw exception Could not convert  to class java.lang.String |
|Gen-Java CSV                                | Gen-Java CSV produced 7 rows instead of 6 |

## Conclusion

These results demonstrate clearly that [uniVocity](http://www.univocity.com) developed the *fastest CSV parser for Java* to date. We are extremely proud to see the huge performance advantage [uniVocity-parsers](http://www.univocity.com/pages/about-parsers) offers over all other options. Not to mention the unique features that make our parsers not only the faster, but also the most feature complete and extensible text parsing architecture available for the Java platform.

Head on to the [uniVocity-parsers github page](http://github.com/uniVocity/univocity-parsers/) to get access to its source code and documentation. Contributions are welcome. 

#### Commercial support is available for your peace of mind. [Click here to learn more.](http://www.univocity.com/products/parsers-support)