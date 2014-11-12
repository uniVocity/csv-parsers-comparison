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
| uniVocity-parsers' CsvParser |     1.2.0 | [www.univocity.com](http://www.univocity.com)                                                      |
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


## Statistics (updated 12nd of November, 2014)

Results will vary depending on your setup and hardware. For reference, here's my (very) modest hardware, an ultrabook: 

 * CPU: Intel i5-3337U @ 1.8 GHz
 * RAM: 4 GB
 * Storage: 128GB SSD drive
 * OS: Arch Linux 64-bit 
 * JDK: 1.6.0_45 64-bit (Linux)
 * JDK: 1.7.0_71 64-bit (Linux)
 * JDK: 1.8.0_25 64-bit (Linux)

*Note* [uniVocity-parsers](http://github.com/uniVocity/univocity-parsers/) provides an option to select the fields you are interested in, and our parsers will execute faster by not processing values that are not selected. It makes quite a difference in performance but we removed this test as the other parsers don't have a similar feature.

### Processing 3,173,958 rows of non [RFC 4180](https://www.rfc-editor.org/rfc/rfc4180.txt) compliant input.

## JDK 6
| Parser                                     | Average time       | % Slower than best | Best time | Worst time |
|--------------------------------------------|-------------------:|-------------------:|----------:|-----------:|
| SimpleFlatMapper CSV parser 	 | 1780 ms  	 | Best time!  	 | 1694 ms 	 | 1949 ms |
| uniVocity CSV parser 	 | 1823 ms  	 | 2%  	 | 1755 ms 	 | 1893 ms |
| Jackson CSV parser 	 | 1935 ms  	 | 8%  	 | 1810 ms 	 | 2145 ms |
| OpenCSV 	 | 2663 ms  	 | 49%  	 | 2513 ms 	 | 2856 ms |
| JCSV Parser 	 | 2670 ms  	 | 50%  	 | 2602 ms 	 | 2778 ms |
| Java CSV Parser 	 | 2757 ms  	 | 54%  	 | 2692 ms 	 | 2830 ms |
| Data pipeline 	 | 2896 ms  	 | 62%  	 | 2817 ms 	 | 3030 ms |
| Oster Miller CSV parser 	 | 2902 ms  	 | 63%  	 | 2761 ms 	 | 2943 ms |
| Simple CSV parser 	 | 3054 ms  	 | 71%  	 | 2906 ms 	 | 3196 ms |
| SuperCSV 	 | 3104 ms  	 | 74%  	 | 2907 ms 	 | 3231 ms |
| Apache Commons CSV 	 | 3291 ms  	 | 84%  	 | 3183 ms 	 | 3402 ms |
| Way IO Parser 	 | 3645 ms  	 | 104%  	 | 3458 ms 	 | 3792 ms |
| Gen-Java CSV 	 | 7065 ms  	 | 296%  	 | 6971 ms 	 | 7185 ms |

## JDK 7
| Parser                                     | Average time       | % Slower than best | Best time | Worst time |
|--------------------------------------------|-------------------:|-------------------:|----------:|-----------:|
| SimpleFlatMapper CSV parser 	 | 1670 ms  	 | Best time!  	 | 1479 ms 	 | 1803 ms |
| uniVocity CSV parser 	 | 1854 ms  	 | 11%  	 | 1790 ms 	 | 1988 ms |
| Jackson CSV parser 	 | 2022 ms  	 | 21%  	 | 1835 ms 	 | 2138 ms |
| JCSV Parser 	 | 2371 ms  	 | 41%  	 | 2312 ms 	 | 2443 ms |
| Oster Miller CSV parser 	 | 2709 ms  	 | 62%  	 | 2587 ms 	 | 2787 ms |
| Java CSV Parser 	 | 2791 ms  	 | 67%  	 | 2570 ms 	 | 2965 ms |
| OpenCSV 	 | 2893 ms  	 | 73%  	 | 2663 ms 	 | 3087 ms |
| SuperCSV 	 | 2938 ms  	 | 75%  	 | 2797 ms 	 | 3039 ms |
| Data pipeline 	 | 3169 ms  	 | 89%  	 | 3024 ms 	 | 3428 ms |
| Apache Commons CSV 	 | 3221 ms  	 | 92%  	 | 3096 ms 	 | 3360 ms |
| Simple CSV parser 	 | 3282 ms  	 | 96%  	 | 3167 ms 	 | 3474 ms |
| Way IO Parser 	 | 4115 ms  	 | 146%  	 | 3993 ms 	 | 4343 ms |
| Gen-Java CSV 	 | 9557 ms  	 | 472%  	 | 9138 ms 	 | 9845 ms |

## JDK 8
| Parser                                     | Average time       | % Slower than best | Best time | Worst time |
|--------------------------------------------|-------------------:|-------------------:|----------:|-----------:|
| uniVocity CSV parser 	 | 1757 ms  	 | Best time!  	 | 1696 ms 	 | 1841 ms |
| Jackson CSV parser 	 | 1834 ms  	 | 4%  	 | 1751 ms 	 | 1922 ms |
| SimpleFlatMapper CSV parser 	 | 2313 ms  	 | 31%  	 | 2138 ms 	 | 2542 ms |
| JCSV Parser 	 | 2525 ms  	 | 43%  	 | 2392 ms 	 | 2794 ms |
| Oster Miller CSV parser 	 | 2759 ms  	 | 57%  	 | 2680 ms 	 | 2841 ms |
| Java CSV Parser 	 | 2856 ms  	 | 62%  	 | 2741 ms 	 | 2975 ms |
| SuperCSV 	 | 2878 ms  	 | 63%  	 | 2711 ms 	 | 3110 ms |
| Simple CSV parser 	 | 2942 ms  	 | 67%  	 | 2758 ms 	 | 3331 ms |
| OpenCSV 	 | 2984 ms  	 | 69%  	 | 2824 ms 	 | 3097 ms |
| Apache Commons CSV 	 | 3057 ms  	 | 73%  	 | 3015 ms 	 | 3143 ms |
| Data pipeline 	 | 3381 ms  	 | 92%  	 | 3021 ms 	 | 4042 ms |
| Way IO Parser 	 | 4156 ms  	 | 136%  	 | 4049 ms 	 | 4291 ms |
| Gen-Java CSV 	 | 6902 ms  	 | 292%  	 | 6740 ms 	 | 6995 ms |

 * `Esperio-csv` and `CSVeed` were unable to process the file and threw exceptions.
 * `Flatpack` hanged so I had to remove it from the test [here](./src/main/java/com/univocity/articles/csvcomparison/parser/Parsers.java).
 * `BeanIO` threw an exception I could understand and debug. Turns out it is unable to parse fields when the quote character is part of the value, e.g. `value1, val"ue2, value3 `. 
 
### Processing 3,173,958 rows of [RFC 4180](https://www.rfc-editor.org/rfc/rfc4180.txt) compliant input.

**Note** this input file has all the values enclosed within quotes. We generated the input like this on purpose as the algorithm to process quotes is a bit different.

## JDK 6
| Parser                                     | Average time       | % Slower than best | Best time  | Worst time |
|--------------------------------------------|-------------------:|-------------------:|-----------:|-----------:|
| uniVocity CSV parser 	 | 2234 ms  	 | Best time!  	 | 2119 ms 	 | 2352 ms |
| SimpleFlatMapper CSV parser 	 | 2356 ms  	 | 5%  	 | 2261 ms 	 | 2438 ms |
| Jackson CSV parser 	 | 2640 ms  	 | 18%  	 | 2513 ms 	 | 2879 ms |
| Data pipeline 	 | 3168 ms  	 | 41%  	 | 3011 ms 	 | 3261 ms |
| OpenCSV 	 | 3407 ms  	 | 52%  	 | 3273 ms 	 | 3496 ms |
| JCSV Parser 	 | 3439 ms  	 | 53%  	 | 3378 ms 	 | 3542 ms |
| SuperCSV 	 | 3612 ms  	 | 61%  	 | 3526 ms 	 | 3776 ms |
| Java CSV Parser 	 | 3636 ms  	 | 62%  	 | 3566 ms 	 | 3796 ms |
| Simple CSV parser 	 | 3871 ms  	 | 73%  	 | 3751 ms 	 | 4022 ms |
| Oster Miller CSV parser 	 | 3887 ms  	 | 73%  	 | 3766 ms 	 | 4002 ms |
| Apache Commons CSV 	 | 4380 ms  	 | 96%  	 | 4050 ms 	 | 4630 ms |
| Way IO Parser 	 | 4554 ms  	 | 103%  	 | 4477 ms 	 | 4655 ms |
| Esperio CSV parser 	 | 5683 ms  	 | 154%  	 | 5601 ms 	 | 5754 ms |
| Gen-Java CSV 	 | 8750 ms  	 | 291%  	 | 8653 ms 	 | 8893 ms |
| Bean IO Parser 	 | 10350 ms  	 | 363%  	 | 10191 ms 	 | 10576 ms |


## JDK 7
| Parser                                     | Average time       | % Slower than best | Best time  | Worst time |
|--------------------------------------------|-------------------:|-------------------:|-----------:|-----------:|
| uniVocity CSV parser 	 | 2159 ms  	 | Best time!  	 | 2055 ms 	 | 2258 ms |
| Jackson CSV parser 	 | 2397 ms  	 | 11%  	 | 2271 ms 	 | 2622 ms |
| SimpleFlatMapper CSV parser 	 | 2436 ms  	 | 12%  	 | 2202 ms 	 | 2664 ms |
| JCSV Parser 	 | 3195 ms  	 | 47%  	 | 3065 ms 	 | 3428 ms |
| SuperCSV 	 | 3337 ms  	 | 54%  	 | 3101 ms 	 | 3752 ms |
| Data pipeline 	 | 3387 ms  	 | 56%  	 | 3209 ms 	 | 3728 ms |
| OpenCSV 	 | 3675 ms  	 | 70%  	 | 3472 ms 	 | 3887 ms |
| Java CSV Parser 	 | 3964 ms  	 | 83%  	 | 3796 ms 	 | 4101 ms |
| Simple CSV parser 	 | 4264 ms  	 | 97%  	 | 4067 ms 	 | 4480 ms |
| Apache Commons CSV 	 | 4311 ms  	 | 99%  	 | 4071 ms 	 | 4523 ms |
| Oster Miller CSV parser 	 | 4329 ms  	 | 100%  	 | 4053 ms 	 | 4840 ms |
| Way IO Parser 	 | 5081 ms  	 | 135%  	 | 4960 ms 	 | 5363 ms |
| Esperio CSV parser 	 | 5881 ms  	 | 172%  	 | 5689 ms 	 | 6144 ms |
| Gen-Java CSV 	 | 11894 ms  	 | 450%  	 | 11163 ms 	 | 12488 ms |
| Bean IO Parser 	 | 13838 ms  	 | 540%  	 | 13441 ms 	 | 14210 ms |

## JDK 8
| Parser                                     | Average time       | % Slower than best | Best time  | Worst time |
|--------------------------------------------|-------------------:|-------------------:|-----------:|-----------:|
| uniVocity CSV parser 	 | 2179 ms  	 | Best time!  	 | 2077 ms 	 | 2281 ms |
| Jackson CSV parser 	 | 2308 ms  	 | 5%  	 | 2163 ms 	 | 2427 ms |
| SimpleFlatMapper CSV parser 	 | 3135 ms  	 | 43%  	 | 2845 ms 	 | 3362 ms |
| JCSV Parser 	 | 3390 ms  	 | 55%  	 | 3287 ms 	 | 3507 ms |
| Data pipeline 	 | 3466 ms  	 | 59%  	 | 3217 ms 	 | 3611 ms |
| SuperCSV 	 | 3516 ms  	 | 61%  	 | 3213 ms 	 | 3925 ms |
| Apache Commons CSV 	 | 3533 ms  	 | 62%  	 | 3367 ms 	 | 3711 ms |
| Java CSV Parser 	 | 3796 ms  	 | 74%  	 | 3703 ms 	 | 3966 ms |
| OpenCSV 	 | 4016 ms  	 | 84%  	 | 3768 ms 	 | 4190 ms |
| Simple CSV parser 	 | 4051 ms  	 | 85%  	 | 3952 ms 	 | 4224 ms |
| Oster Miller CSV parser 	 | 4165 ms  	 | 91%  	 | 3945 ms 	 | 4281 ms |
| Way IO Parser 	 | 4923 ms  	 | 125%  	 | 4760 ms 	 | 5151 ms |
| Esperio CSV parser 	 | 5321 ms  	 | 144%  	 | 4977 ms 	 | 5725 ms |
| Gen-Java CSV 	 | 8941 ms  	 | 310%  	 | 8618 ms 	 | 9277 ms |
| Bean IO Parser 	 | 11232 ms  	 | 415%  	 | 10824 ms 	 | 11634 ms |

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

*uniVocity-parsers*, *SimpleFlatMapper* and *Jackson CSV*. Keep in mind that *Simpleflatmapper* is a very simple implementation that does not provide any customization options.

It is impressive how a change in the JDK affects the results. We are extremely proud to see the huge performance advantage [uniVocity-parsers](http://www.univocity.com/pages/about-parsers) offers over most of the other parsers. Not to mention the unique features that put our parsers among the faster, most feature complete and extensible text parsing architectures available for the Java platform.

We will keep working to improve the performance of our parsers, and will update the results of this benchmark every time a new parser is added to the list.

Head on to the [uniVocity-parsers github page](http://github.com/uniVocity/univocity-parsers/) to get access to its source code and documentation. Contributions are welcome.

#### Commercial support is available for your peace of mind. [Click here to learn more.](http://www.univocity.com/products/parsers-support)
