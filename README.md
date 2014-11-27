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
| uniVocity-parsers' CsvParser |     1.3.0 | [www.univocity.com](http://www.univocity.com)                                                      |
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
| DataPipeline's CSVReader*    |     2.3.5 | [northconcepts.com/data-pipeline/](http://northconcepts.com/data-pipeline/)                        |
| jackson-dataformat-csv       |     2.4.2 | [github.com/FasterXML/jackson-dataformat-csv](http://github.com/FasterXML/jackson-dataformat-csv)  |
| OsterMiller Utils            |     1.0.6 | [ostermiller.org/utils/CSV.html](http://ostermiller.org/utils/CSV.html)                            |
| SimpleFlatMapper CSV parser  |  1.0.0rc2 | [github.com/arnaudroger/SimpleFlatMapper](https://github.com/arnaudroger/SimpleFlatMapper)         |


## Statistics (updated 28th of November, 2014)

Results will vary depending on your setup and hardware. For reference, here's my (very) modest hardware, an ultrabook: 

 * CPU: Intel i5-3337U @ 1.8 GHz
 * RAM: 4 GB
 * Storage: 128GB SSD drive
 * OS: Arch Linux 64-bit 
 * JDK: 1.8.0_25 64-bit (Linux)
 * JDK: 1.7.0_71 64-bit (Linux)
 * JDK: 1.6.0_45 64-bit (Linux)

*Note* [uniVocity-parsers](http://github.com/uniVocity/univocity-parsers/) provides an option to select the fields you are interested in, and our parsers will execute faster by not processing values that are not selected. It makes quite a difference in performance but we removed this test as the other parsers don't have a similar feature.

### Processing 3,173,958 rows of non [RFC 4180](https://www.rfc-editor.org/rfc/rfc4180.txt) compliant input.

## JDK 8
| Parser                                     | Average time       | % Slower than best | Best time | Worst time |
|--------------------------------------------|-------------------:|-------------------:|----------:|-----------:|
| uniVocity CSV parser 	 | 1792 ms  	 | Best time!  	 | 1654 ms 	 | 1930 ms |
| Jackson CSV parser 	 | 1992 ms  	 | 11%  	 | 1897 ms 	 | 2130 ms |
| SimpleFlatMapper CSV parser 	 | 2324 ms  	 | 29%  	 | 2120 ms 	 | 2581 ms |
| JCSV Parser 	 | 2733 ms  	 | 52%  	 | 2458 ms 	 | 2886 ms |
| SuperCSV 	 | 2840 ms  	 | 58%  	 | 2631 ms 	 | 3184 ms |
| Oster Miller CSV parser 	 | 2876 ms  	 | 60%  	 | 2647 ms 	 | 3193 ms |
| Simple CSV parser 	 | 2887 ms  	 | 61%  	 | 2815 ms 	 | 3012 ms |
| Java CSV Parser 	 | 2905 ms  	 | 62%  	 | 2747 ms 	 | 3172 ms |
| OpenCSV 	 | 3112 ms  	 | 73%  	 | 2995 ms 	 | 3226 ms |
| Apache Commons CSV 	 | 3136 ms  	 | 75%  	 | 3057 ms 	 | 3332 ms |
| Data pipeline 	 | 3434 ms  	 | 91%  	 | 3198 ms 	 | 3567 ms |
| Way IO Parser 	 | 4313 ms  	 | 140%  	 | 4133 ms 	 | 4467 ms |
| Gen-Java CSV 	 | 6715 ms  	 | 274%  	 | 6634 ms 	 | 6797 ms |

## JDK 7
| Parser                                     | Average time       | % Slower than best | Best time | Worst time |
|--------------------------------------------|-------------------:|-------------------:|----------:|-----------:|
| uniVocity CSV parser 	 | 1870 ms  	 | Best time!  	 | 1840 ms 	 | 1900 ms |
| SimpleFlatMapper CSV parser 	 | 1904 ms  	 | 1%  	 | 1720 ms 	 | 2097 ms |
| Jackson CSV parser 	 | 1975 ms  	 | 5%  	 | 1862 ms 	 | 2099 ms |
| JCSV Parser 	 | 2497 ms  	 | 33%  	 | 2323 ms 	 | 2755 ms |
| Oster Miller CSV parser 	 | 2795 ms  	 | 49%  	 | 2659 ms 	 | 2975 ms |
| SuperCSV 	 | 2862 ms  	 | 53%  	 | 2715 ms 	 | 3029 ms |
| Java CSV Parser 	 | 2894 ms  	 | 54%  	 | 2667 ms 	 | 3137 ms |
| OpenCSV 	 | 2957 ms  	 | 58%  	 | 2788 ms 	 | 3122 ms |
| Data pipeline 	 | 3049 ms  	 | 63%  	 | 2903 ms 	 | 3267 ms |
| Apache Commons CSV 	 | 3192 ms  	 | 70%  	 | 3112 ms 	 | 3292 ms |
| Simple CSV parser 	 | 3322 ms  	 | 77%  	 | 3205 ms 	 | 3537 ms |
| Way IO Parser 	 | 4150 ms  	 | 121%  	 | 3942 ms 	 | 4394 ms |
| Gen-Java CSV 	 | 8858 ms  	 | 373%  	 | 8519 ms 	 | 9215 ms |

## JDK 6
| Parser                                     | Average time       | % Slower than best | Best time | Worst time |
|--------------------------------------------|-------------------:|-------------------:|----------:|-----------:|
| SimpleFlatMapper CSV parser 	 | 1749 ms  	 | Best time!  	 | 1718 ms 	 | 1790 ms |
| uniVocity CSV parser 	 | 1911 ms  	 | 9%  	 | 1796 ms 	 | 2031 ms |
| Jackson CSV parser 	 | 1983 ms  	 | 13%  	 | 1906 ms 	 | 2103 ms |
| OpenCSV 	 | 2611 ms  	 | 49%  	 | 2567 ms 	 | 2675 ms |
| JCSV Parser 	 | 2653 ms  	 | 51%  	 | 2600 ms 	 | 2707 ms |
| Java CSV Parser 	 | 2782 ms  	 | 59%  	 | 2706 ms 	 | 2879 ms |
| Data pipeline 	 | 2805 ms  	 | 60%  	 | 2688 ms 	 | 2894 ms |
| Simple CSV parser 	 | 3098 ms  	 | 77%  	 | 2945 ms 	 | 3421 ms |
| Oster Miller CSV parser 	 | 3122 ms  	 | 78%  	 | 3022 ms 	 | 3210 ms |
| SuperCSV 	 | 3185 ms  	 | 82%  	 | 3059 ms 	 | 3351 ms |
| Apache Commons CSV 	 | 3358 ms  	 | 91%  	 | 3280 ms 	 | 3445 ms |
| Way IO Parser 	 | 3743 ms  	 | 114%  	 | 3683 ms 	 | 3827 ms |
| Gen-Java CSV 	 | 7144 ms  	 | 308%  	 | 6991 ms 	 | 7278 ms |

 * `Esperio-csv` and `CSVeed` were unable to process the file and threw exceptions.
 * `Flatpack` hanged so I had to remove it from the test [here](./src/main/java/com/univocity/articles/csvcomparison/parser/Parsers.java).
 * `BeanIO` threw an exception I could understand and debug. Turns out it is unable to parse fields when the quote character is part of the value, e.g. `value1, val"ue2, value3 `. 
 
### Processing 3,173,958 rows of [RFC 4180](https://www.rfc-editor.org/rfc/rfc4180.txt) compliant input.

**Note** this input file has all the values enclosed within quotes. We generated the input like this on purpose as the algorithm to process quotes is a bit different.


## JDK 8
| Parser                                     | Average time       | % Slower than best | Best time  | Worst time |
|--------------------------------------------|-------------------:|-------------------:|-----------:|-----------:|
| uniVocity CSV parser 	 | 2270 ms  	 | Best time!  	 | 2178 ms 	 | 2352 ms |
| Jackson CSV parser 	 | 2416 ms  	 | 6%  	 | 2294 ms 	 | 2490 ms |
| SuperCSV 	 | 3116 ms  	 | 37%  	 | 2956 ms 	 | 3267 ms |
| JCSV Parser 	 | 3172 ms  	 | 39%  	 | 3049 ms 	 | 3325 ms |
| SimpleFlatMapper CSV parser 	 | 3405 ms  	 | 50%  	 | 3177 ms 	 | 3709 ms |
| Data pipeline 	 | 3424 ms  	 | 50%  	 | 3355 ms 	 | 3478 ms |
| Apache Commons CSV 	 | 3620 ms  	 | 59%  	 | 3500 ms 	 | 3769 ms |
| Simple CSV parser 	 | 3793 ms  	 | 67%  	 | 3635 ms 	 | 3896 ms |
| Java CSV Parser 	 | 3864 ms  	 | 70%  	 | 3703 ms 	 | 4046 ms |
| OpenCSV 	 | 4115 ms  	 | 81%  	 | 3955 ms 	 | 4532 ms |
| Oster Miller CSV parser 	 | 4165 ms  	 | 83%  	 | 3988 ms 	 | 4343 ms |
| Esperio CSV parser 	 | 5355 ms  	 | 135%  	 | 5122 ms 	 | 5591 ms |
| Way IO Parser 	 | 5400 ms  	 | 137%  	 | 5016 ms 	 | 5588 ms |
| Gen-Java CSV 	 | 8558 ms  	 | 277%  	 | 8106 ms 	 | 8844 ms |
| Bean IO Parser 	 | 10796 ms  	 | 375%  	 | 10504 ms 	 | 11341 ms |

## JDK 7
| Parser                                     | Average time       | % Slower than best | Best time  | Worst time |
|--------------------------------------------|-------------------:|-------------------:|-----------:|-----------:|
| uniVocity CSV parser 	 | 2352 ms  	 | Best time!  	 | 2286 ms 	 | 2449 ms |
| Jackson CSV parser 	 | 2432 ms  	 | 3%  	 | 2234 ms 	 | 2627 ms |
| SimpleFlatMapper CSV parser 	 | 2556 ms  	 | 8%  	 | 2373 ms 	 | 2836 ms |
| Data pipeline 	 | 3281 ms  	 | 39%  	 | 3084 ms 	 | 3596 ms |
| SuperCSV 	 | 3296 ms  	 | 40%  	 | 2989 ms 	 | 3500 ms |
| JCSV Parser 	 | 3305 ms  	 | 40%  	 | 3145 ms 	 | 3692 ms |
| OpenCSV 	 | 3859 ms  	 | 64%  	 | 3671 ms 	 | 4092 ms |
| Java CSV Parser 	 | 3884 ms  	 | 65%  	 | 3749 ms 	 | 4139 ms |
| Oster Miller CSV parser 	 | 4054 ms  	 | 72%  	 | 3804 ms 	 | 4255 ms |
| Simple CSV parser 	 | 4246 ms  	 | 80%  	 | 4093 ms 	 | 4516 ms |
| Apache Commons CSV 	 | 4303 ms  	 | 82%  	 | 4197 ms 	 | 4405 ms |
| Way IO Parser 	 | 5095 ms  	 | 116%  	 | 4935 ms 	 | 5270 ms |
| Esperio CSV parser 	 | 6001 ms  	 | 155%  	 | 5760 ms 	 | 6375 ms |
| Gen-Java CSV 	 | 10918 ms  	 | 364%  	 | 10345 ms 	 | 11305 ms |
| Bean IO Parser 	 | 12839 ms  	 | 445%  	 | 11992 ms 	 | 13641 ms |

## JDK 6
| Parser                                     | Average time       | % Slower than best | Best time  | Worst time |
|--------------------------------------------|-------------------:|-------------------:|-----------:|-----------:|
| uniVocity CSV parser 	 | 2395 ms  	 | Best time!  	 | 2368 ms 	 | 2444 ms |
| SimpleFlatMapper CSV parser 	 | 2539 ms  	 | 6%  	 | 2480 ms 	 | 2582 ms |
| Jackson CSV parser 	 | 2809 ms  	 | 17%  	 | 2669 ms 	 | 2982 ms |
| Data pipeline 	 | 3197 ms  	 | 33%  	 | 3067 ms 	 | 3395 ms |
| JCSV Parser 	 | 3423 ms  	 | 42%  	 | 3293 ms 	 | 3678 ms |
| OpenCSV 	 | 3570 ms  	 | 49%  	 | 3457 ms 	 | 3847 ms |
| Java CSV Parser 	 | 3745 ms  	 | 56%  	 | 3541 ms 	 | 3977 ms |
| SuperCSV 	 | 3808 ms  	 | 58%  	 | 3638 ms 	 | 4062 ms |
| Oster Miller CSV parser 	 | 3944 ms  	 | 64%  	 | 3750 ms 	 | 4161 ms |
| Simple CSV parser 	 | 3962 ms  	 | 65%  	 | 3776 ms 	 | 4226 ms |
| Apache Commons CSV 	 | 4315 ms  	 | 80%  	 | 4189 ms 	 | 4448 ms |
| Way IO Parser 	 | 4667 ms  	 | 94%  	 | 4566 ms 	 | 4773 ms |
| Esperio CSV parser 	 | 5625 ms  	 | 134%  	 | 5496 ms 	 | 5778 ms |
| Gen-Java CSV 	 | 8977 ms  	 | 274%  	 | 8759 ms 	 | 9293 ms |
| Bean IO Parser 	 | 10848 ms  	 | 352%  	 | 10385 ms 	 | 11225 ms |


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
