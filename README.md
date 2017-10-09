# csv-parsers-comparison

This project aims to compare all CSV parsers for Java in existence, or at least the ones that seem to work and are 
available to the general public. There are too many and the intention here is to help you decide which one is the best 
for you. Commercial parsers are welcome in the test. Please send us the details of your commercial parser and we will 
include the results. 

Currently, we are only testing parsing performance. As the input file, we will be using the 
famous [worldcitiespop.txt](http://www.maxmind.com/download/worldcities/worldcitiespop.txt.gz), which is made available 
for free by [Maxmind](http://www.maxmind.com). It contains more than 3 million rows, which should be sufficient for our test.

## Building and Running

Prerequisites: Git, wget, gunzip, GNU Patch, Apache Maven 3, and Java 1.6+.
The appropriate CSV parser library version will be chosen depending on your JDK version (i.e. 1.6, 1.7, or 1.8).

If you wish to reproduce our performance results:

```bash
$ git clone https://github.com/uniVocity/csv-parsers-comparison.git
$ cd csv-parsers-comparison
$ wget http://www.maxmind.com/download/worldcities/worldcitiespop.txt.gz
$ gunzip worldcitiespop.txt.gz
$ git checkout src/
$ mvn clean package
$ java -jar target/csv-parsers-comparison-1.0-uber.jar .
```

NOTE: the `.` at the end of the last command, this tells Java the folder containing the `worldcitiespop.txt`. You can 
alternatively specify a path to any folder that contains a `worldcitiespop.txt` file.

If you just want to run from the main() method, download the [worldcitiespop.txt](http://www.maxmind.com/download/worldcities/worldcitiespop.txt.gz) and place 
it under `src/main/resources/` before executing the main class [PerformanceComparison.java](./src/main/java/com/univocity/articles/csvcomparison/PerformanceComparison.java).

Our test is very simple and involves just counting the number of rows read from the input file. The implementation using 
each parser is [here](./src/main/java/com/univocity/articles/csvcomparison/parser). 

### Important
 The input file is **not** [RFC 4180](https://www.rfc-editor.org/rfc/rfc4180.txt) compliant. We generate a compliant 
 version using the [HugeFileGenerator](./src/main/java/com/univocity/articles/csvcomparison/HugeFileGenerator.java) 
 class to test the parsers against a generated file with the same data, but enclosed within quotes and properly escaped.
 
It's important to notice that there's no such thing as a CSV standard and we do not recommend you to use parsers that 
follow the RFC strictly, as they will blow up in face of non-compliant inputs. The reality is: your parser must be 
ready to process crooked data instead of going belly up. In the end, your client is the one who tells you what you
must swallow, and in many circumstances it's not up to you to decide how your data is going to be generated.

We generate a RFC compliant version to give those sensitive parsers a chance to see how they perform.
Once again, we consider their usage risky.

## CSV Parsers

This is the list of all parsers currently tested.

| Parser                       |   Version         | Website                                                                                            |
|------------------------------|------------------:|----------------------------------------------------------------------------------------------------|
| uniVocity-parsers' CsvParser |             2.5.7 | [www.univocity.com](http://www.univocity.com)                                                      |
| CSVeed                       |             0.5.0 | [csveed.org](http://csveed.org)                                                                    |
| Apache Commons CSV           |           1.4/1.5 | [commons.apache.org/proper/commons-csv] (http://commons.apache.org/proper/commons-csv)             |
| OpenCSV                      |               4.0 | [opencsv.sourceforge.net](http://opencsv.sourceforge.net/)                                         |
| SuperCSV                     |             2.4.0 | [supercsv.sourceforge.net](http://supercsv.sourceforge.net/)                                       |
| JavaCSV                      |               2.0 | [sourceforge.net/projects/javacsv](http://sourceforge.net/projects/javacsv)                        |
| jCSV                         |             1.4.0 | [code.google.com/p/jcsv](https://code.google.com/p/jcsv/)                                          |
| flatpack                     |       3.4.2/4.0.1 | [flatpack.sourceforge.net](http://flatpack.sourceforge.net/)                                       |
| SimpleCSV                    |               2.1 | [github.com/quux00/simplecsv](https://github.com/quux00/simplecsv)                                 |
| gj-csv                       |               1.0 | ?                                                                                                  |
| esperio-csv                  |       5.5.0/6.1.0 | [www.espertech.com](http://www.espertech.com/)                                                     |
| way-io                       |      1.25.0/2.1.0 | [www.objectos.com.br](http://www.objectos.com.br/)                                                 |
| beanIO                       |             2.1.0 | [beanio.org](http://beanio.org/)                                                                   |
| jackson-dataformat-csv       | 2.6.7/2.7.9/2.9.1 | [github.com/FasterXML/jackson-dataformat-csv](http://github.com/FasterXML/jackson-dataformat-csv)  |
| OsterMiller Utils            |           1.07.00 | [ostermiller.org/utils/CSV.html](http://ostermiller.org/utils/CSV.html)                            |
| SimpleFlatMapper CSV parser  |            3.13.2 | [github.com/arnaudroger/SimpleFlatMapper](https://github.com/arnaudroger/SimpleFlatMapper)         |
| Diergo Easy CSV Streamable   |             3.1.0 | [github.com/aburmeis/decs](https://github.com/aburmeis/decs)                                       |
| Product Collections          |             1.4.5 | [github.com/marklister/product-collections](https://github.com/marklister/product-collections)     |

## Statistics (updated 9th of October, 2017)

Results will vary depending on your setup and hardware, here is mine: 

 * CPU: AMD Ryzen 7 1700 Eight-Core Processor @ 3.0 GHz
 * RAM: 32 GB
 * Storage: 1TB SSD drive
 * OS: Arch Linux 64-bit 
 * JDK: 1.8.0_144 64-bit (Linux)
 * JDK: 1.7.0_80 64-bit (Linux)
 * JDK: 1.6.0_45 64-bit (Linux)

*Note* [uniVocity-parsers](http://github.com/uniVocity/univocity-parsers/) provides an option to select the fields you
 are interested in, and our parsers will execute faster by not processing values that are not selected. It makes quite 
 a difference in performance but we removed this test as the other parsers don't have a similar feature.

### Processing 3,173,958 rows of non [RFC 4180](https://www.rfc-editor.org/rfc/rfc4180.txt) compliant input. No quoted values.

## JDK 9
| Parser                                     | Average time       | % Slower than best | Best time | Worst time |
|--------------------------------------------|-------------------:|-------------------:|----------:|-----------:|
| uniVocity CSV parser   | 908 ms        | Best time!    | 812 ms        | 994 ms |
| SimpleFlatMapper CSV parser    | 996 ms        | 9%    | 984 ms        | 1005 ms |
| Jackson CSV parser     | 1553 ms       | 71%           | 1492 ms       | 1612 ms |
| Product Collections parser     | 1590 ms       | 75%           | 1546 ms       | 1683 ms |
| Java CSV Parser        | 1670 ms       | 83%           | 1639 ms       | 1727 ms |
| JCSV Parser    | 1874 ms       | 106%          | 1831 ms       | 1928 ms |
| Oster Miller CSV parser        | 1922 ms       | 111%          | 1889 ms       | 1946 ms |
| Simple CSV parser      | 2055 ms       | 126%          | 1994 ms       | 2108 ms |
| Gen-Java CSV   | 2066 ms       | 127%          | 2021 ms       | 2117 ms |
| OpenCSV        | 2139 ms       | 135%          | 2099 ms       | 2189 ms |
| SuperCSV       | 2218 ms       | 144%          | 2170 ms       | 2251 ms |
| Way IO Parser          | 2750 ms       | 202%          | 2717 ms       | 2821 ms |
| Apache Commons CSV     | 3147 ms       | 246%          | 3099 ms       | 3225 ms |

## JDK 8
| Parser                                     | Average time       | % Slower than best | Best time | Worst time |
|--------------------------------------------|-------------------:|-------------------:|----------:|-----------:|
| uniVocity CSV parser   | 802 ms        | Best time!    | 765 ms        | 831 ms |
| SimpleFlatMapper CSV parser    | 879 ms        | 9%    | 835 ms        | 903 ms |
| Jackson CSV parser     | 1089 ms       | 35%           | 1060 ms       | 1154 ms |
| Diergo Easy CSV Streamable     | 1290 ms       | 60%           | 1269 ms       | 1311 ms |
| Simple CSV parser      | 1491 ms       | 85%           | 1396 ms       | 1619 ms |
| Product Collections parser     | 1527 ms       | 90%           | 1476 ms       | 1559 ms |
| JCSV Parser    | 1622 ms       | 102%          | 1574 ms       | 1712 ms |
| SuperCSV       | 1665 ms       | 107%          | 1639 ms       | 1711 ms |
| Java CSV Parser        | 1678 ms       | 109%          | 1665 ms       | 1702 ms |
| Gen-Java CSV   | 1747 ms       | 117%          | 1722 ms       | 1792 ms |
| Oster Miller CSV parser        | 1809 ms       | 125%          | 1774 ms       | 1829 ms |
| OpenCSV        | 1875 ms       | 133%          | 1859 ms       | 1893 ms |
| Way IO Parser          | 2514 ms       | 213%          | 2491 ms       | 2554 ms |
| Apache Commons CSV     | 2533 ms       | 215%          | 2491 ms       | 2577 ms |

## JDK 7
| Parser                                     | Average time       | % Slower than best | Best time | Worst time |
|--------------------------------------------|-------------------:|-------------------:|----------:|-----------:|
| uniVocity CSV parser   | 875 ms        | Best time!    | 838 ms        | 919 ms |
| SimpleFlatMapper CSV parser    | 998 ms        | 14%           | 983 ms        | 1010 ms |
| Jackson CSV parser     | 1121 ms       | 28%           | 1093 ms       | 1167 ms |
| JCSV Parser    | 1597 ms       | 82%           | 1535 ms       | 1627 ms |
| Product Collections parser     | 1623 ms       | 85%           | 1587 ms       | 1673 ms |
| Simple CSV parser      | 1639 ms       | 87%           | 1548 ms       | 1751 ms |
| SuperCSV       | 1661 ms       | 89%           | 1638 ms       | 1677 ms |
| OpenCSV        | 1664 ms       | 90%           | 1636 ms       | 1700 ms |
| Java CSV Parser        | 1675 ms       | 91%           | 1598 ms       | 1747 ms |
| Oster Miller CSV parser        | 1790 ms       | 104%          | 1754 ms       | 1820 ms |
| Way IO Parser          | 2297 ms       | 162%          | 2263 ms       | 2321 ms |
| Gen-Java CSV   | 2398 ms       | 174%          | 2366 ms       | 2483 ms |
| Apache Commons CSV     | 2417 ms       | 176%          | 2372 ms       | 2440 ms |


## JDK 6
| Parser                                     | Average time       | % Slower than best | Best time | Worst time |
|--------------------------------------------|-------------------:|-------------------:|----------:|-----------:|
| uniVocity CSV parser   | 905 ms        | Best time!    | 863 ms        | 941 ms |
| SimpleFlatMapper CSV parser    | 967 ms        | 6%    | 952 ms        | 985 ms |
| Jackson CSV parser     | 1258 ms       | 39%           | 1252 ms       | 1263 ms |
| Product Collections parser     | 1479 ms       | 63%           | 1458 ms       | 1504 ms |
| SuperCSV       | 1747 ms       | 93%           | 1724 ms       | 1779 ms |
| OpenCSV        | 1753 ms       | 93%           | 1728 ms       | 1774 ms |
| Java CSV Parser        | 1764 ms       | 94%           | 1758 ms       | 1780 ms |
| JCSV Parser    | 1909 ms       | 110%          | 1893 ms       | 1932 ms |
| Oster Miller CSV parser        | 1913 ms       | 111%          | 1886 ms       | 1938 ms |
| Simple CSV parser      | 2128 ms       | 135%          | 2080 ms       | 2151 ms |
| Apache Commons CSV     | 2321 ms       | 156%          | 2311 ms       | 2330 ms |
| Gen-Java CSV   | 2396 ms       | 164%          | 2362 ms       | 2424 ms |
| Way IO Parser          | 2423 ms       | 167%          | 2401 ms       | 2441 ms |


 * `Esperio-csv` and `CSVeed` were unable to process the file and threw exceptions.
 * `Flatpack` hanged so I had to remove it from the test [here](./src/main/java/com/univocity/articles/csvcomparison/parser/Parsers.java).
 * `BeanIO` threw an exception I could understand and debug. Turns out it is unable to parse fields when the quote
  character is part of the value, e.g. `value1, val"ue2, value3 `. 
 
### Processing 3,173,958 rows of [RFC 4180](https://www.rfc-editor.org/rfc/rfc4180.txt) compliant input. All values quoted.

**Note** this input file has all the values enclosed within quotes. We generated the input like this on purpose as 
the algorithm to process quotes is a bit different.

## JDK 9
| Parser                                     | Average time       | % Slower than best | Best time  | Worst time |
|--------------------------------------------|-------------------:|-------------------:|-----------:|-----------:|
| SimpleFlatMapper CSV parser    | 1348 ms       | Best time!    | 1327 ms       | 1382 ms |
| uniVocity CSV parser   | 1382 ms       | 2%    | 1346 ms       | 1416 ms |
| Jackson CSV parser     | 1474 ms       | 9%    | 1459 ms       | 1507 ms |
| Product Collections parser     | 1701 ms       | 26%           | 1651 ms       | 1727 ms |
| Java CSV Parser        | 1975 ms       | 46%           | 1949 ms       | 1996 ms |
| JCSV Parser    | 2294 ms       | 70%           | 2244 ms       | 2319 ms |
| Gen-Java CSV   | 2570 ms       | 90%           | 2523 ms       | 2611 ms |
| Simple CSV parser      | 2741 ms       | 103%          | 2701 ms       | 2757 ms |
| Oster Miller CSV parser        | 2763 ms       | 104%          | 2716 ms       | 2808 ms |
| SuperCSV       | 2776 ms       | 105%          | 2743 ms       | 2851 ms |
| OpenCSV        | 2862 ms       | 112%          | 2818 ms       | 2892 ms |
| Apache Commons CSV     | 2973 ms       | 120%          | 2907 ms       | 3182 ms |
| Way IO Parser          | 3474 ms       | 157%          | 3439 ms       | 3515 ms |
| Esperio CSV parser     | 3787 ms       | 180%          | 3749 ms       | 3869 ms |
| Bean IO Parser         | 4288 ms       | 218%          | 4194 ms       | 4471 ms |


## JDK 8
| Parser                                     | Average time       | % Slower than best | Best time  | Worst time |
|--------------------------------------------|-------------------:|-------------------:|-----------:|-----------:|
| SimpleFlatMapper CSV parser    | 1119 ms       | Best time!    | 1103 ms       | 1135 ms |
| Jackson CSV parser     | 1180 ms       | 5%    | 1163 ms       | 1194 ms |
| uniVocity CSV parser   | 1296 ms       | 15%           | 1240 ms       | 1344 ms |
| Product Collections parser     | 1487 ms       | 32%           | 1466 ms       | 1505 ms |
| Diergo Easy CSV Streamable     | 1561 ms       | 39%           | 1548 ms       | 1585 ms |
| Java CSV Parser        | 1836 ms       | 64%           | 1783 ms       | 1860 ms |
| JCSV Parser    | 1990 ms       | 77%           | 1971 ms       | 2008 ms |
| Simple CSV parser      | 2054 ms       | 83%           | 1965 ms       | 2116 ms |
| Gen-Java CSV   | 2185 ms       | 95%           | 2137 ms       | 2217 ms |
| SuperCSV       | 2218 ms       | 98%           | 2213 ms       | 2225 ms |
| Apache Commons CSV     | 2439 ms       | 117%          | 2420 ms       | 2463 ms |
| OpenCSV        | 2509 ms       | 124%          | 2465 ms       | 2607 ms |
| Oster Miller CSV parser        | 2613 ms       | 133%          | 2574 ms       | 2634 ms |
| Way IO Parser          | 3134 ms       | 180%          | 3090 ms       | 3172 ms |
| Esperio CSV parser     | 3369 ms       | 201%          | 3327 ms       | 3403 ms |
| Bean IO Parser         | 3469 ms       | 210%          | 3432 ms       | 3529 ms |


## JDK 7
| Parser                                     | Average time       | % Slower than best | Best time  | Worst time |
|--------------------------------------------|-------------------:|-------------------:|-----------:|-----------:|
| Jackson CSV parser     | 1225 ms       | Best time!    | 1218 ms       | 1230 ms |
| SimpleFlatMapper CSV parser    | 1237 ms       | 0%    | 1218 ms       | 1263 ms |
| uniVocity CSV parser   | 1362 ms       | 11%           | 1302 ms       | 1410 ms |
| Product Collections parser     | 1726 ms       | 40%           | 1687 ms       | 1746 ms |
| Java CSV Parser        | 1926 ms       | 57%           | 1900 ms       | 1958 ms |
| JCSV Parser    | 1982 ms       | 61%           | 1955 ms       | 2008 ms |
| SuperCSV       | 2105 ms       | 71%           | 2093 ms       | 2130 ms |
| OpenCSV        | 2116 ms       | 72%           | 2083 ms       | 2150 ms |
| Simple CSV parser      | 2260 ms       | 84%           | 2229 ms       | 2343 ms |
| Oster Miller CSV parser        | 2700 ms       | 120%          | 2674 ms       | 2717 ms |
| Way IO Parser          | 2817 ms       | 129%          | 2787 ms       | 2846 ms |
| Gen-Java CSV   | 2910 ms       | 137%          | 2895 ms       | 2944 ms |
| Apache Commons CSV     | 3025 ms       | 146%          | 3008 ms       | 3040 ms |
| Bean IO Parser         | 3850 ms       | 214%          | 3822 ms       | 3881 ms |
| Esperio CSV parser     | 4454 ms       | 263%          | 4424 ms       | 4487 ms |


## JDK 6
| Parser                                     | Average time       | % Slower than best | Best time  | Worst time |
|--------------------------------------------|-------------------:|-------------------:|-----------:|-----------:|
| SimpleFlatMapper CSV parser    | 1354 ms       | Best time!    | 1345 ms       | 1367 ms |
| uniVocity CSV parser   | 1391 ms       | 2%    | 1369 ms       | 1416 ms |
| Product Collections parser     | 1751 ms       | 29%           | 1734 ms       | 1764 ms |
| Jackson CSV parser     | 1771 ms       | 30%           | 1767 ms       | 1776 ms |
| SuperCSV       | 2100 ms       | 55%           | 2081 ms       | 2129 ms |
| JCSV Parser    | 2436 ms       | 79%           | 2407 ms       | 2466 ms |
| OpenCSV        | 2469 ms       | 82%           | 2450 ms       | 2483 ms |
| Oster Miller CSV parser        | 2598 ms       | 91%           | 2573 ms       | 2616 ms |
| Java CSV Parser        | 2672 ms       | 97%           | 2661 ms       | 2680 ms |
| Gen-Java CSV   | 2875 ms       | 112%          | 2842 ms       | 2913 ms |
| Simple CSV parser      | 2876 ms       | 112%          | 2852 ms       | 2928 ms |
| Apache Commons CSV     | 3162 ms       | 133%          | 3129 ms       | 3246 ms |
| Way IO Parser          | 3171 ms       | 134%          | 3142 ms       | 3203 ms |
| Bean IO Parser         | 4068 ms       | 200%          | 4058 ms       | 4079 ms |
| Esperio CSV parser     | 4440 ms       | 227%          | 4398 ms       | 4515 ms |



 * `CSVeed` was unable to process the file and threw exception with the message "Parsing symbol OTHER_SYMBOL [44] in state ESCAPING".
 * `Flatpack` blew up the Java heap space so I had to remove it from the test [here](./src/main/java/com/univocity/articles/csvcomparison/parser/Parsers.java).


## Reliability (updated 9th of October, 2017)

The following parsers were unable to process the [RFC 4180](https://www.rfc-editor.org/rfc/rfc4180.txt) compliant file
 [correctness.csv](./src/main/resources/correctness.csv). This test is executed using the class [CorrectnessComparison.java](./src/main/java/com/univocity/articles/csvcomparison/CorrectnessComparison.java)

| Parser                                     | Error |
|--------------------------------------------|:------|
|CSVeed										 | CSVeed threw exception "Illegal state transition: Parsing symbol QUOTE_SYMBOL [34] in state INSIDE_FIELD" |
|jCSV Parser                                 | JCSV Parser produced ["Year,Make,Model,Description,Price"] instead of ["Year", "Make", "Model", "Description", "Price"] |
|Simple CSV parser                           | Simple CSV parser threw exception "The separator, quote, and escape characters must be different!" |
|Way IO Parser                               | Way IO Parser threw exception "Could not convert  to class java.lang.String" |
|Gen-Java CSV                                | Gen-Java CSV produced 7 rows instead of 6 |
|Flatpack									 | Flatpack produced 5 rows instead of 6 |

The exact same errors have been reported 3 years ago when I last updated this page. Just avoid these parsers. 

## Conclusion

Currently, three parsers stand out as the fastest CSV parsers for Java:

*uniVocity-parsers*, *SimpleFlatMapper* and *Jackson CSV*. Keep in mind that *Simpleflatmapper* is a very simple 
implementation that does not provide any customization options. Results are affected by a simple change in the JDK version,
however these three parsers are always at the top.

[uniVocity-parsers](http://www.univocity.com/pages/about-parsers) is the fastest when parsing data without quotes but lags
behind *SimpleFlatMapper* and *Jackson CSV* when processing values inside quotes. This is due to the heaps of options 
available for parsing quoted values, handling of problematic inputs with unescaped quotes, and all sorts of corner cases around this.
 
We will keep working to improve the performance of our parsers, and will try to update the results of this benchmark
every time a new parser is added to the list.

Head on to the [uniVocity-parsers github page](http://github.com/uniVocity/univocity-parsers/) to get access to its
source code and documentation. Contributions are welcome.

#### Commercial support is available for your peace of mind. [Click here to learn more.](http://www.univocity.com/products/parsers-support)

