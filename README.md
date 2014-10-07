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
| uniVocity-parsers' CsvParser |     1.0.2 | [www.univocity.com](http://www.univocity.com)                                                      |
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
| SimpleFlatMapper CSV parser  |	 0.9.8 | [github.com/arnaudroger/SimpleFlatMapper](https://github.com/arnaudroger/SimpleFlatMapper)         |


## Statistics (updated 8th of October, 2014)

Results will vary depending on your setup and hardware. For reference, here's my (very) modest hardware, an ultrabook: 

 * CPU: Intel i5-3337U @ 1.8 GHz
 * RAM: 4 GB
 * Storage: 128GB SSD drive
 * OS: Arch Linux 64 bit 


*Note* [uniVocity-parsers](http://github.com/uniVocity/univocity-parsers/) provides an option to select the fields you are interested in, and our parsers will execute faster by not processing values that are not selected. It makes quite a difference in performance but we removed this test as the other parsers don't have a similar feature.

### Processing 3,173,958 rows of non [RFC 4180](https://www.rfc-editor.org/rfc/rfc4180.txt) compliant input.


| Parser                                     | Average time       | % Slower than best | Best time | Worst time |
|--------------------------------------------|-------------------:|-------------------:|----------:|-----------:|
| SimpleFlatMapper CSV parser 	 | 1661 ms  	 | Best time!  	 | 1564 ms 	 | 1739 ms |
| uniVocity CSV parser 	 | 1911 ms  	 | 15%  	 | 1861 ms 	 | 2017 ms |
| Jackson CSV parser 	 | 1919 ms  	 | 15%  	 | 1857 ms 	 | 1989 ms |
| OpenCSV 	 | 2484 ms  	 | 49%  	 | 2421 ms 	 | 2511 ms |
| JCSV Parser 	 | 2628 ms  	 | 58%  	 | 2520 ms 	 | 2825 ms |
| Java CSV Parser 	 | 2658 ms  	 | 60%  	 | 2625 ms 	 | 2729 ms |
| Data pipeline 	 | 2803 ms  	 | 68%  	 | 2708 ms 	 | 2975 ms |
| Oster Miller CSV parser 	 | 2915 ms  	 | 75%  	 | 2825 ms 	 | 3028 ms |
| Simple CSV parser 	 | 2977 ms  	 | 79%  	 | 2870 ms 	 | 3237 ms |
| SuperCSV 	 | 2984 ms  	 | 79%  	 | 2907 ms 	 | 3095 ms |
| Apache Commons CSV 	 | 3251 ms  	 | 95%  	 | 3079 ms 	 | 3708 ms |
| Way IO Parser 	 | 3571 ms  	 | 114%  	 | 3471 ms 	 | 3673 ms |
| Gen-Java CSV 	 | 6773 ms  	 | 307%  	 | 6663 ms 	 | 6835 ms |

 * `Esperio-csv` and `CSVeed` were unable to process the file and threw exceptions.
 * `Flatpack` hanged so I had to remove it from the test [here](./src/main/java/com/univocity/articles/csvcomparison/parser/Parsers.java).
 * `BeanIO` threw an exception I could understand and debug. Turns out it is unable to parse fields when the quote character is part of the value, e.g. `value1, val"ue2, value3 `. 
 
### Processing 3,173,958 rows of [RFC 4180](https://www.rfc-editor.org/rfc/rfc4180.txt) compliant input.

**Note** this input file has all the values enclosed within quotes. We generated the input like this on purpose as the algorithm to process quotes is a bit different.

| Parser                                     | Average time       | % Slower than best | Best time  | Worst time |
|--------------------------------------------|-------------------:|-------------------:|-----------:|-----------:|
| uniVocity CSV parser 	 | 2303 ms  	 | Best time!  	 | 2266 ms 	 | 2339 ms |
| Jackson CSV parser 	 | 2603 ms  	 | 13%  	 | 2517 ms 	 | 2665 ms |
| SimpleFlatMapper CSV parser 	 | 2626 ms  	 | 14%  	 | 2584 ms 	 | 2686 ms |
| Data pipeline 	 | 3128 ms  	 | 35%  	 | 3095 ms 	 | 3141 ms |
| JCSV Parser 	 | 3352 ms  	 | 45%  	 | 3279 ms 	 | 3495 ms |
| OpenCSV 	 | 3397 ms  	 | 47%  	 | 3330 ms 	 | 3482 ms |
| SuperCSV 	 | 3491 ms  	 | 51%  	 | 3375 ms 	 | 3680 ms |
| Java CSV Parser 	 | 3598 ms  	 | 56%  	 | 3540 ms 	 | 3668 ms |
| Oster Miller CSV parser 	 | 3818 ms  	 | 65%  	 | 3766 ms 	 | 3838 ms |
| Simple CSV parser 	 | 3869 ms  	 | 67%  	 | 3812 ms 	 | 3899 ms |
| Apache Commons CSV 	 | 4339 ms  	 | 88%  	 | 4233 ms 	 | 4597 ms |
| Way IO Parser 	 | 4602 ms  	 | 99%  	 | 4431 ms 	 | 4853 ms |
| Esperio CSV parser 	 | 5367 ms  	 | 133%  	 | 5296 ms 	 | 5481 ms |
| Gen-Java CSV 	 | 7998 ms  	 | 247%  	 | 7882 ms 	 | 8114 ms |
| Bean IO Parser 	 | 9554 ms  	 | 314%  	 | 9422 ms 	 | 9718 ms |


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

*uniVocity-parsers*, *SimpleFlatMapper* and *Jackson CSV*

### When processing values not enclosed within quotes:
* *SimpleFlatMapper* is incredibly faster than any other parser when processing values that are not enclosed within quotes.
* *uniVocity-parsers* and *Jackson CSV* and offer comparable processing speed over such inputs.

### When processing values enclosed within quotes:
* *uniVocity-parsers* is currently the faster parser to process values enclosed within quotes
* *SimpleFlatMapper* and *Jackson CSV* offer comparable processing speed over such inputs.

We are extremely proud to see the huge performance advantage [uniVocity-parsers](http://www.univocity.com/pages/about-parsers) offers over most of the other parsers. Not to mention the unique features that put our parsers among the faster, most feature complete and extensible text parsing architectures available for the Java platform.

We will keep working to improve the performance of our parsers, and will update the results of this benchmark every time a new parser is added to the list.

Head on to the [uniVocity-parsers github page](http://github.com/uniVocity/univocity-parsers/) to get access to its source code and documentation. Contributions are welcome.

#### Commercial support is available for your peace of mind. [Click here to learn more.](http://www.univocity.com/products/parsers-support)
