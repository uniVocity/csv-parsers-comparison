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
| uniVocity-parsers' CsvParser |             2.6.0 | [www.univocity.com](http://www.univocity.com)                                                      |
| CSVeed                       |             0.5.0 | [csveed.org](http://csveed.org)                                                                    |
| Apache Commons CSV           |           1.4/1.5 | [commons.apache.org/proper/commons-csv] (http://commons.apache.org/proper/commons-csv)             |
| OpenCSV                      |               4.1 | [opencsv.sourceforge.net](http://opencsv.sourceforge.net/)                                         |
| SuperCSV                     |             2.4.0 | [supercsv.sourceforge.net](http://supercsv.sourceforge.net/)                                       |
| JavaCSV                      |               2.0 | [sourceforge.net/projects/javacsv](http://sourceforge.net/projects/javacsv)                        |
| jCSV                         |             1.4.0 | [code.google.com/p/jcsv](https://code.google.com/p/jcsv/)                                          |
| flatpack                     |       3.4.2/4.0.1 | [flatpack.sourceforge.net](http://flatpack.sourceforge.net/)                                       |
| SimpleCSV                    |               2.1 | [github.com/quux00/simplecsv](https://github.com/quux00/simplecsv)                                 |
| gj-csv                       |               1.0 | ?                                                                                                  |
| esperio-csv                  |       5.5.0/7.0.0 | [www.espertech.com](http://www.espertech.com/)                                                     |
| way-io                       |      1.25.0/2.1.0 | [www.objectos.com.br](http://www.objectos.com.br/)                                                 |
| beanIO                       |             2.1.0 | [beanio.org](http://beanio.org/)                                                                   |
| jackson-dataformat-csv       | 2.6.7/2.7.9/2.9.4 | [github.com/FasterXML/jackson-dataformat-csv](http://github.com/FasterXML/jackson-dataformat-csv)  |
| OsterMiller Utils            |           1.07.00 | [ostermiller.org/utils/CSV.html](http://ostermiller.org/utils/CSV.html)                            |
| SimpleFlatMapper CSV parser  |            3.15.9 | [github.com/arnaudroger/SimpleFlatMapper](https://github.com/arnaudroger/SimpleFlatMapper)         |
| Diergo Easy CSV Streamable   |             3.1.0 | [github.com/aburmeis/decs](https://github.com/aburmeis/decs)                                       |
| Product Collections          |             1.4.5 | [github.com/marklister/product-collections](https://github.com/marklister/product-collections)     |

## Statistics (updated 28th of February, 2018)

Results will vary depending on your setup and hardware, here is mine: 

 * CPU: AMD Ryzen 7 1700 Eight-Core Processor @ 4.0 GHz
 * RAM: 32 GB
 * Storage: 1TB SSD drive
 * OS: Arch Linux 64-bit 
 * JDK: 9.0.4 64-bit (Linux)
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
| uniVocity CSV parser   | 739 ms        | Best time!    | 707 ms        | 768 ms |
| SimpleFlatMapper CSV parser    | 861 ms        | 16%           | 848 ms        | 901 ms |
| Jackson CSV parser     | 1212 ms       | 64%           | 1169 ms       | 1238 ms |
| Product Collections parser     | 1409 ms       | 90%           | 1389 ms       | 1451 ms |
| Java CSV Parser        | 1498 ms       | 102%          | 1490 ms       | 1508 ms |
| JCSV Parser    | 1681 ms       | 127%          | 1660 ms       | 1710 ms |
| Oster Miller CSV parser        | 1772 ms       | 139%          | 1762 ms       | 1780 ms |
| Gen-Java CSV   | 1799 ms       | 143%          | 1790 ms       | 1805 ms |
| Simple CSV parser      | 1861 ms       | 151%          | 1832 ms       | 1900 ms |
| SuperCSV       | 1893 ms       | 156%          | 1858 ms       | 1964 ms |
| OpenCSV        | 2022 ms       | 173%          | 2007 ms       | 2037 ms |
| Apache Commons CSV     | 2424 ms       | 228%          | 2409 ms       | 2442 ms |
| Way IO Parser          | 2577 ms       | 248%          | 2532 ms       | 2638 ms |

## JDK 8
| Parser                                     | Average time       | % Slower than best | Best time | Worst time |
|--------------------------------------------|-------------------:|-------------------:|----------:|-----------:|
| uniVocity CSV parser   | 723 ms        | Best time!    | 716 ms        | 736 ms |
| SimpleFlatMapper CSV parser    | 769 ms        | 6%    | 761 ms        | 778 ms |
| Jackson CSV parser     | 924 ms        | 27%           | 914 ms        | 944 ms |
| Diergo Easy CSV Streamable     | 1186 ms       | 64%           | 1173 ms       | 1217 ms |
| Simple CSV parser      | 1273 ms       | 76%           | 1255 ms       | 1297 ms |
| Product Collections parser     | 1328 ms       | 83%           | 1314 ms       | 1354 ms |
| JCSV Parser    | 1440 ms       | 99%           | 1432 ms       | 1459 ms |
| SuperCSV       | 1497 ms       | 107%          | 1478 ms       | 1511 ms |
| Java CSV Parser        | 1512 ms       | 109%          | 1495 ms       | 1548 ms |
| Gen-Java CSV   | 1517 ms       | 109%          | 1440 ms       | 1541 ms |
| Oster Miller CSV parser        | 1639 ms       | 126%          | 1634 ms       | 1649 ms |
| OpenCSV        | 1687 ms       | 133%          | 1676 ms       | 1702 ms |
| Apache Commons CSV     | 2197 ms       | 203%          | 2187 ms       | 2208 ms |
| Way IO Parser          | 2318 ms       | 220%          | 2260 ms       | 2368 ms |

## JDK 7
| Parser                                     | Average time       | % Slower than best | Best time | Worst time |
|--------------------------------------------|-------------------:|-------------------:|----------:|-----------:|
| uniVocity CSV parser   | 771 ms        | Best time!    | 742 ms        | 827 ms |
| SimpleFlatMapper CSV parser    | 888 ms        | 15%           | 881 ms        | 899 ms |
| Jackson CSV parser     | 1015 ms       | 31%           | 986 ms        | 1055 ms |
| JCSV Parser    | 1371 ms       | 77%           | 1362 ms       | 1376 ms |
| Product Collections parser     | 1405 ms       | 82%           | 1376 ms       | 1482 ms |
| Simple CSV parser      | 1450 ms       | 88%           | 1362 ms       | 1594 ms |
| Java CSV Parser        | 1477 ms       | 91%           | 1464 ms       | 1518 ms |
| SuperCSV       | 1500 ms       | 94%           | 1477 ms       | 1520 ms |
| OpenCSV        | 1529 ms       | 98%           | 1518 ms       | 1545 ms |
| Oster Miller CSV parser        | 1615 ms       | 109%          | 1594 ms       | 1638 ms |
| Gen-Java CSV   | 2096 ms       | 171%          | 2059 ms       | 2195 ms |
| Way IO Parser          | 2118 ms       | 174%          | 2102 ms       | 2139 ms |
| Apache Commons CSV     | 2204 ms       | 185%          | 2194 ms       | 2226 ms |


## JDK 6
| Parser                                     | Average time       | % Slower than best | Best time | Worst time |
|--------------------------------------------|-------------------:|-------------------:|----------:|-----------:|
| uniVocity CSV parser   | 824 ms        | Best time!    | 811 ms        | 844 ms |
| SimpleFlatMapper CSV parser    | 879 ms        | 6%    | 871 ms        | 887 ms |
| Jackson CSV parser     | 1071 ms       | 29%           | 1062 ms       | 1085 ms |
| Product Collections parser     | 1368 ms       | 66%           | 1360 ms       | 1375 ms |
| SuperCSV       | 1561 ms       | 89%           | 1537 ms       | 1629 ms |
| OpenCSV        | 1579 ms       | 91%           | 1555 ms       | 1604 ms |
| Java CSV Parser        | 1605 ms       | 94%           | 1596 ms       | 1617 ms |
| Oster Miller CSV parser        | 1744 ms       | 111%          | 1736 ms       | 1757 ms |
| JCSV Parser    | 1766 ms       | 114%          | 1758 ms       | 1776 ms |
| Simple CSV parser      | 1905 ms       | 131%          | 1888 ms       | 1918 ms |
| Apache Commons CSV     | 2105 ms       | 155%          | 2094 ms       | 2123 ms |
| Gen-Java CSV   | 2135 ms       | 159%          | 2085 ms       | 2200 ms |
| Way IO Parser          | 2148 ms       | 160%          | 2133 ms       | 2161 ms |


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
| uniVocity CSV parser   | 982 ms        | Best time!    | 966 ms        | 1002 ms |
| SimpleFlatMapper CSV parser    | 1137 ms       | 15%           | 1125 ms       | 1156 ms |
| Jackson CSV parser     | 1338 ms       | 36%           | 1313 ms       | 1369 ms |
| Product Collections parser     | 1480 ms       | 50%           | 1467 ms       | 1504 ms |
| Java CSV Parser        | 1735 ms       | 76%           | 1718 ms       | 1761 ms |
| JCSV Parser    | 2043 ms       | 108%          | 2029 ms       | 2055 ms |
| Gen-Java CSV   | 2162 ms       | 120%          | 2134 ms       | 2182 ms |
| Oster Miller CSV parser        | 2353 ms       | 139%          | 2335 ms       | 2389 ms |
| SuperCSV       | 2511 ms       | 155%          | 2495 ms       | 2522 ms |
| Simple CSV parser      | 2544 ms       | 159%          | 2529 ms       | 2558 ms |
| OpenCSV        | 2665 ms       | 171%          | 2647 ms       | 2686 ms |
| Apache Commons CSV     | 2917 ms       | 197%          | 2851 ms       | 3020 ms |
| Way IO Parser          | 3243 ms       | 230%          | 3227 ms       | 3252 ms |
| Esperio CSV parser     | 3406 ms       | 246%          | 3349 ms       | 3458 ms |
| Bean IO Parser         | 3740 ms       | 280%          | 3681 ms       | 3792 ms |


## JDK 8
| Parser                                     | Average time       | % Slower than best | Best time  | Worst time |
|--------------------------------------------|-------------------:|-------------------:|-----------:|-----------:|
| uniVocity CSV parser   | 855 ms        | Best time!    | 839 ms        | 870 ms |
| SimpleFlatMapper CSV parser    | 964 ms        | 12%           | 959 ms        | 971 ms |
| Jackson CSV parser     | 1023 ms       | 19%           | 1009 ms       | 1058 ms |
| Diergo Easy CSV Streamable     | 1385 ms       | 61%           | 1378 ms       | 1394 ms |
| Product Collections parser     | 1388 ms       | 62%           | 1386 ms       | 1391 ms |
| Java CSV Parser        | 1642 ms       | 92%           | 1635 ms       | 1650 ms |
| JCSV Parser    | 1756 ms       | 105%          | 1739 ms       | 1765 ms |
| Simple CSV parser      | 1813 ms       | 112%          | 1804 ms       | 1841 ms |
| Gen-Java CSV   | 1954 ms       | 128%          | 1950 ms       | 1961 ms |
| SuperCSV       | 1989 ms       | 132%          | 1975 ms       | 2001 ms |
| Apache Commons CSV     | 2152 ms       | 151%          | 2145 ms       | 2161 ms |
| OpenCSV        | 2234 ms       | 161%          | 2227 ms       | 2256 ms |
| Oster Miller CSV parser        | 2292 ms       | 168%          | 2285 ms       | 2306 ms |
| Way IO Parser          | 2915 ms       | 240%          | 2905 ms       | 2932 ms |
| Esperio CSV parser     | 2981 ms       | 248%          | 2944 ms       | 3044 ms |
| Bean IO Parser         | 3238 ms       | 278%          | 3219 ms       | 3263 ms |


## JDK 7
| Parser                                     | Average time       | % Slower than best | Best time  | Worst time |
|--------------------------------------------|-------------------:|-------------------:|-----------:|-----------:|
| uniVocity CSV parser   | 960 ms        | Best time!    | 945 ms        | 984 ms |
| SimpleFlatMapper CSV parser    | 1021 ms       | 6%    | 1018 ms       | 1031 ms |
| Jackson CSV parser     | 1102 ms       | 14%           | 1092 ms       | 1112 ms |
| Product Collections parser     | 1475 ms       | 53%           | 1467 ms       | 1492 ms |
| Java CSV Parser        | 1659 ms       | 72%           | 1652 ms       | 1665 ms |
| JCSV Parser    | 1709 ms       | 78%           | 1689 ms       | 1728 ms |
| SuperCSV       | 1848 ms       | 92%           | 1837 ms       | 1858 ms |
| Simple CSV parser      | 1939 ms       | 101%          | 1887 ms       | 2028 ms |
| OpenCSV        | 2022 ms       | 110%          | 2013 ms       | 2032 ms |
| Oster Miller CSV parser        | 2422 ms       | 152%          | 2406 ms       | 2436 ms |
| Gen-Java CSV   | 2551 ms       | 165%          | 2546 ms       | 2562 ms |
| Way IO Parser          | 2621 ms       | 173%          | 2613 ms       | 2628 ms |
| Apache Commons CSV     | 2804 ms       | 192%          | 2798 ms       | 2814 ms |
| Bean IO Parser         | 3460 ms       | 260%          | 3420 ms       | 3564 ms |
| Esperio CSV parser     | 4021 ms       | 318%          | 3996 ms       | 4058 ms |


## JDK 6
| Parser                                     | Average time       | % Slower than best | Best time  | Worst time |
|--------------------------------------------|-------------------:|-------------------:|-----------:|-----------:|
| SimpleFlatMapper CSV parser    | 1187 ms       | Best time!    | 1183 ms       | 1193 ms |
| uniVocity CSV parser   | 1216 ms       | 2%    | 1206 ms       | 1226 ms |
| Jackson CSV parser     | 1397 ms       | 17%           | 1391 ms       | 1413 ms |
| Product Collections parser     | 1488 ms       | 25%           | 1478 ms       | 1496 ms |
| SuperCSV       | 1873 ms       | 57%           | 1864 ms       | 1892 ms |
| OpenCSV        | 2205 ms       | 85%           | 2198 ms       | 2214 ms |
| JCSV Parser    | 2227 ms       | 87%           | 2210 ms       | 2250 ms |
| Oster Miller CSV parser        | 2323 ms       | 95%           | 2316 ms       | 2334 ms |
| Java CSV Parser        | 2437 ms       | 105%          | 2428 ms       | 2454 ms |
| Simple CSV parser      | 2510 ms       | 111%          | 2472 ms       | 2609 ms |
| Gen-Java CSV   | 2590 ms       | 118%          | 2542 ms       | 2609 ms |
| Apache Commons CSV     | 2739 ms       | 130%          | 2734 ms       | 2748 ms |
| Way IO Parser          | 2831 ms       | 138%          | 2819 ms       | 2843 ms |
| Bean IO Parser         | 3685 ms       | 210%          | 3661 ms       | 3721 ms |
| Esperio CSV parser     | 4002 ms       | 237%          | 3986 ms       | 4039 ms |



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

We will keep working to improve the performance of our parsers, and will try to update the results of this benchmark
every time a new parser is added to the list.

Head on to the [uniVocity-parsers github page](http://github.com/uniVocity/univocity-parsers/) to get access to its
source code and documentation. Contributions are welcome.

#### Commercial support is available for your peace of mind. [Click here to learn more.](http://www.univocity.com/products/parsers-support)

