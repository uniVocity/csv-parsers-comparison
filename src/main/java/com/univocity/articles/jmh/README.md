
# How to run the jmh test

## Build from maven

```bash
mvn clean install
```

## Run the benchmark command
 
```bash
java -jar target/benchmarks.jar  -foe -p nbRows=10,1000,100000
```

## wait for the result

```
Benchmark                                                             (inputFile)  (nbRows)   Mode  Samples      Score  Score error  Units
c.u.a.j.JacksonParser.parseFile             src/main/resources/worldcitiespop.txt        10  thrpt       20  10000.478      142.915  ops/s
c.u.a.j.OpenCsvParser.parseFile             src/main/resources/worldcitiespop.txt        10  thrpt       20   9805.582      175.291  ops/s
c.u.a.j.SimpleFlatMapperParser.parseFile    src/main/resources/worldcitiespop.txt        10  thrpt       20  10397.233      274.887  ops/s
c.u.a.j.UnivocityParser.parseFile           src/main/resources/worldcitiespop.txt        10  thrpt       20    278.275        9.191  ops/s
```