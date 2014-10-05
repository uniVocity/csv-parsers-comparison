# csv-parsers-comparison

This project aims to compare all CSV parsers for Java in existence, or at least the ones that seem to work. There are too many and the intention here is to help you decide which one is the best for you.

Currently, we are only testing parsing performance. As the input file, we will be using the famous [worldcitiespop.txt](http://www.maxmind.com/download/worldcities/worldcitiespop.txt.gz), which is made available for free by [Maxmind](http://www.maxmind.com). It contains more than 3 million rows, which should be sufficient for our test.

Please download the [worldcitiespop.txt](http://www.maxmind.com/download/worldcities/worldcitiespop.txt.gz) and place it under `src/main/resources/` before executing the main class [PerformanceComparison.java](./src/main/java/com/univocity/articles/csvcomparison/PerformanceComparison.java).

Our test is very simple and involves just counting the number of rows read from the input file. The implementation using each parser is [here](./src/main/java/com/univocity/articles/csvcomparison/parser). 

## CSV Parsers

This is the list of all parsers tested:

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
| way-io                       |     1.6.0 | [http://www.objectos.com.br](http://www.objectos.com.br/)                              |


## Statistics

Results will vary depending on your setup and hardware. For reference, here's my (very) modest hardware, an ultrabook: 

 * CPU: Intel i5-3337U @ 1.8 GHz
 * RAM: 4 GB
 * Storage: 128GB SSD drive
 * OS: Arch Linux 64 bit 

These are the statistics I got after processing 3,173,958 rows:

| Parser                                     | Average time       | % Slower than best | Best time | Worst time |
|--------------------------------------------|-------------------:|-------------------:|----------:|-----------:|
|uniVocity CSV parser - with field selection |  2108 ms           | BEST!              | 2019 ms   | 2162 ms    |
|uniVocity CSV parser                        |  2748 ms           | 30%                | 2703 ms   | 2806 ms    |
|OpenCSV                                     |  3431 ms           | 62%                | 3366 ms   | 3571 ms    |
|jCSV Parser                                 |  3472 ms           | 64%                | 3395 ms   | 3522 ms    |
|JavaCSV Parser                              |  3531 ms           | 67%                | 3379 ms   | 3640 ms    |
|Simple CSV parser                           |  3921 ms           | 86%                | 3635 ms   | 4107 ms    |
|SuperCSV                                    |  4189 ms           | 98%                | 4126 ms   | 4242 ms    |
|Apache Commons CSV                          |  4293 ms           | 103%               | 4175 ms   | 4431 ms    |
|Way IO Parser                               |  4949 ms           | 134%               | 4839 ms   | 5058 ms    |
|Gen-Java CSV                                |  9940 ms           | 371%               | 9825 ms   | 10229 ms   |

 * `Esperio-csv` and `CSVeed` were unable to process the file and threw exceptions.
 * `Flatpack` hanged so I had to remove it from the test [here](./src/main/java/com/univocity/articles/csvcomparison/parser/Parsers.java). 

*Note* [uniVocity-parsers](http://github.com/uniVocity/univocity-parsers/) provides an option to select the fields you are interested in, and our parsers will execute faster by not processing values that are not selected. As it can be seen in the results above, it makes quite a difference in performance.

## Conclusion

These results demonstrate clearly that [uniVocity](http://www.univocity.com) developed the *fastest CSV parser for Java* to date. We are extremely proud to see the huge performance advantage [uniVocity-parsers](http://www.univocity.com/pages/about-parsers) offers over all other options. Not to mention the unique features that make our parsers not only the faster, but also the most feature complete and extensible text parsing architecture available for the Java platform.

Head on to the [uniVocity-parsers github page](http://github.com/uniVocity/univocity-parsers/) to get access to its source code and documentation. Contributions are welcome. 

#### Commercial support is available for your peace of mind. [Click here to learn more.](http://www.univocity.com/products/parsers-support)