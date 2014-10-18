# csv-parsers-comparison

This project aims to compare all CSV parsers for Java in existence, or at least the ones that seem to work and are available to the general public. There are too many and the intention here is to help you decide which one is the best for you. Commercial parsers are welcome in the test. Please send us the details of your commercial parser and we will include the results. 

Currently, we are only testing parsing performance. As the input file, we will be using the famous [worldcitiespop.txt](http://www.maxmind.com/download/worldcities/worldcitiespop.txt.gz), which is made available for free by [Maxmind](http://www.maxmind.com). It contains more than 3 million rows, which should be sufficient for our test.

Please download the [worldcitiespop.txt](http://www.maxmind.com/download/worldcities/worldcitiespop.txt.gz) and place it under `src/main/resources/` before executing the main class [PerformanceComparison.java](./src/main/java/com/univocity/articles/csvcomparison/PerformanceComparison.java).

Our test is very simple and involves just counting the number of rows read from the input file. The implementation using each parser is [here](./src/main/java/com/univocity/articles/csvcomparison/parser). 

### Important
 The input file is **not** [RFC 4180](https://www.rfc-editor.org/rfc/rfc4180.txt) compliant. We generate a compliant version using the [HugeFileGenerator](./src/main/java/com/univocity/articles/csvcomparison/HugeFileGenerator.java) class to test the parsers against a generated file with the same data, but enclosed within quotes and properly escaped.
 
It's important to notice that there's no such thing as a CSV standard and we do not recommend you to use parsers that follow the RFC strictly, as they will blow up in face of non-compliant inputs. The reality is: your parser must be ready to process crooked data instead of going belly up. In the end, your client is the one who tells you what you must swallow, and in many circumstances it's not up to you to decide how your data is going to be generated.

We generate a RFC compliant version to give those sensitive parsers a chance to see how they perform. Once again, we consider their usage risky.

## CSV Parsers

This is the list of all parsers currently tested. Parsers followed by * are commercial an their jars are not included in this project. You have to download them independently:

| Parser                       |   Version | Website                                                                                            |
|------------------------------|----------:|----------------------------------------------------------------------------------------------------|
| uniVocity-parsers' CsvParser |     1.0.3 | [www.univocity.com](http://www.univocity.com)                                                      |
| CSVeed                       |     0.4.0 | [csveed.org](http://csveed.org)                                                                    |
| Apache Commons CSV           |       1.0 | [commons.apache.org/proper/commons-csv] (http://commons.apache.org/proper/commons-csv)             |
| OpenCSV                      |       2.3 | [opencsv.sourceforge.net](http://opencsv.sourceforge.net/)                                         |
| SuperCSV                     |     2.2.0 | [supercsv.sourceforge.net](http://supercsv.sourceforge.net/)                                       |
| JavaCSV                      |       2.0 | [sourceforge.net/projects/javacsv](http://sourceforge.net/projects/javacsv)                        |
| jCSV                         |     1.4.0 | [code.google.com/p/jcsv](https://code.google.com/p/jcsv/)                                          |
| flatpack                     |     3.4.2 | [flatpack.sourceforge.net](http://flatpack.sourceforge.net/)                                       |
| SimpleCSV                    |       2.0 | [github.com/quux00/simplecsv](https://github.com/quux00/simplecsv)                                 |
| gj-csv                       |       1.0 | ?                                                                                                  |
| esperio-csv                  |    4.11.0 | [www.espertech.com](http://www.espertech.com/)                                                     |
| way-io                       |     1.6.0 | [www.objectos.com.br](http://www.objectos.com.br/)                                                 |
| beanIO                       |     2.1.0 | [beanio.org](http://beanio.org/)                                                                   |
| DataPipeline's CSVReader*    |     2.3.4 | [northconcepts.com/data-pipeline/](http://northconcepts.com/data-pipeline/)                        |
| jackson-dataformat-csv       |     2.4.2 | [github.com/FasterXML/jackson-dataformat-csv](http://github.com/FasterXML/jackson-dataformat-csv)  |
| OsterMiller Utils            |     1.0.6 | [ostermiller.org/utils/CSV.html](http://ostermiller.org/utils/CSV.html)                            |
| SimpleFlatMapper CSV parser  |	 0.9.9 | [github.com/arnaudroger/SimpleFlatMapper](https://github.com/arnaudroger/SimpleFlatMapper)         |


## Statistics (updated 10th of October, 2014)

Results will vary depending on your setup and hardware. For reference, here's my (very) modest hardware, an ultrabook: 

 * CPU: Intel i5-3337U @ 1.8 GHz
 * RAM: 4 GB
 * Storage: 128GB SSD drive
 * OS: Arch Linux 64-bit 
 * JDK: 1.6.0_45 64-bit (Linux)

*Note* [uniVocity-parsers](http://github.com/uniVocity/univocity-parsers/) provides an option to select the fields you are interested in, and our parsers will execute faster by not processing values that are not selected. It makes quite a difference in performance but we removed this test as the other parsers don't have a similar feature.

### Processing 3,173,958 rows of non [RFC 4180](https://www.rfc-editor.org/rfc/rfc4180.txt) compliant input.


| Parser                                     | Average time       | % Slower than best | Best time | Worst time |
|--------------------------------------------|-------------------:|-------------------:|----------:|-----------:|
| SimpleFlatMapper CSV parser 	 | 1723 ms  	 | Best time!  	 | 1631 ms 	 | 1922 ms |
| uniVocity CSV parser 	 | 1816 ms  	 | 5%  	 | 1771 ms 	 | 1866 ms |
| Jackson CSV parser 	 | 1989 ms  	 | 15%  	 | 1896 ms 	 | 2143 ms |
| OpenCSV 	 | 2565 ms  	 | 48%  	 | 2518 ms 	 | 2647 ms |
| JCSV Parser 	 | 2663 ms  	 | 54%  	 | 2591 ms 	 | 2743 ms |
| Java CSV Parser 	 | 2743 ms  	 | 59%  	 | 2699 ms 	 | 2791 ms |
| Oster Miller CSV parser 	 | 2915 ms  	 | 69%  	 | 2820 ms 	 | 3003 ms |
| Data pipeline 	 | 2930 ms  	 | 70%  	 | 2745 ms 	 | 3072 ms |
| Simple CSV parser 	 | 2948 ms  	 | 71%  	 | 2832 ms 	 | 3119 ms |
| SuperCSV 	 | 3044 ms  	 | 76%  	 | 2910 ms 	 | 3201 ms |
| Apache Commons CSV 	 | 3077 ms  	 | 78%  	 | 3021 ms 	 | 3132 ms |
| Way IO Parser 	 | 3559 ms  	 | 106%  	 | 3482 ms 	 | 3603 ms |
| Gen-Java CSV 	 | 7182 ms  	 | 316%  	 | 7020 ms 	 | 7385 ms |

 * `Esperio-csv` and `CSVeed` were unable to process the file and threw exceptions.
 * `Flatpack` hanged so I had to remove it from the test [here](./src/main/java/com/univocity/articles/csvcomparison/parser/Parsers.java).
 * `BeanIO` threw an exception I could understand and debug. Turns out it is unable to parse fields when the quote character is part of the value, e.g. `value1, val"ue2, value3 `. 
 
### Processing 3,173,958 rows of [RFC 4180](https://www.rfc-editor.org/rfc/rfc4180.txt) compliant input.

**Note** this input file has all the values enclosed within quotes. We generated the input like this on purpose as the algorithm to process quotes is a bit different.

| Parser                                     | Average time       | % Slower than best | Best time  | Worst time |
|--------------------------------------------|-------------------:|-------------------:|-----------:|-----------:|
| SimpleFlatMapper CSV parser 	 | 2411 ms  	 | Best time!  	 | 2280 ms 	 | 2523 ms |
| uniVocity CSV parser 	 | 2487 ms  	 | 3%  	 | 2301 ms 	 | 2920 ms |
| Jackson CSV parser 	 | 2703 ms  	 | 12%  	 | 2593 ms 	 | 2817 ms |
| Data pipeline 	 | 3100 ms  	 | 28%  	 | 3035 ms 	 | 3205 ms |
| JCSV Parser 	 | 3367 ms  	 | 39%  	 | 3277 ms 	 | 3460 ms |
| OpenCSV 	 | 3417 ms  	 | 41%  	 | 3377 ms 	 | 3448 ms |
| SuperCSV 	 | 3692 ms  	 | 53%  	 | 3605 ms 	 | 3743 ms |
| Java CSV Parser 	 | 3758 ms  	 | 55%  	 | 3566 ms 	 | 4117 ms |
| Oster Miller CSV parser 	 | 3842 ms  	 | 59%  	 | 3726 ms 	 | 3935 ms |
| Simple CSV parser 	 | 3923 ms  	 | 62%  	 | 3734 ms 	 | 4076 ms |
| Apache Commons CSV 	 | 4299 ms  	 | 78%  	 | 4059 ms 	 | 4475 ms |
| Way IO Parser 	 | 4599 ms  	 | 90%  	 | 4444 ms 	 | 4670 ms |
| Esperio CSV parser 	 | 5396 ms  	 | 123%  	 | 5323 ms 	 | 5492 ms |
| Gen-Java CSV 	 | 8697 ms  	 | 260%  	 | 8449 ms 	 | 9091 ms |
| Bean IO Parser 	 | 10182 ms  	 | 322%  	 | 10019 ms 	 | 10372 ms |



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

Currently, three parsers stand out as the fastest CSV parsers for Java:

*uniVocity-parsers*, *SimpleFlatMapper* and *Jackson CSV*. 

Keep in mind that *Simpleflatmapper* is a very simple implementation that does not provide any customization options.


### When processing values not enclosed within quotes:
* *SimpleFlatMapper* is barely faster than *uniVocity-parsers* when processing values that are not enclosed within quotes.
* *Jackson CSV* follows closely and all other parsers follow very far behind.

### When processing values enclosed within quotes:
* *SimpleFlatMapper* and *uniVocity-parsers* have comparable performance when processing values enclosed within quotes
* *Jackson CSV* comes behind, yet it is still way faster than the others.

We are extremely proud to see the huge performance advantage [uniVocity-parsers](http://www.univocity.com/pages/about-parsers) offers over most of the other parsers. Not to mention the unique features that put our parsers among the faster, most feature complete and extensible text parsing architectures available for the Java platform.

We will keep working to improve the performance of our parsers, and will update the results of this benchmark every time a new parser is added to the list.

Head on to the [uniVocity-parsers github page](http://github.com/uniVocity/univocity-parsers/) to get access to its source code and documentation. Contributions are welcome.

#### Commercial support is available for your peace of mind. [Click here to learn more.](http://www.univocity.com/products/parsers-support)
