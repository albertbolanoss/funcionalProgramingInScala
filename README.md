# Functional Programing using Scala

This project is all about getting you up to speed with functional programming concepts using Scala. 
Packed with examples and practical code, it demonstrates the core ideas of functional programming in action,
helping you to really nail down the basics.

## Requirements

- [Scala 3.4.2](https://www.scala-lang.org/download/all.html) or greater.
- [SBT 1.10.2](https://www.scala-sbt.org/download/) or greater or [Gradle 8.7](https://gradle.org/releases) or greater.
- Optionally, can create environment variables to link to the /bin directory.
Doing this makes it more convenient to execute commands without specifying the full path every time.

## How Build & Run

### Compile and test using SBT

```sh
sbt -version # 1.10.2 or greater
sbt compile
sbt test
sbt testOnly org.example.FunctionBaseSuite
```

### Compile and test using Gradle

```sh
gradle --version # 8.7 or greater
gradle build
gradle test  # The report is generated in lib/reports/tests/test/index.html
gradle test --tests "org.example.FunctionBaseSuite"
```

### Compile and Run using scalac and scala

```sh
scala -version # 3.4.2
scalac {path of module to compile}
scala {path of module to run}
```

## Content

| Topic                                                             | Path                                  |
|-------------------------------------------------------------------|---------------------------------------|
| First approach to functional programming (Coffee Shop).           | com.example.cafeshop.*                |
| Functional programming core concepts.                             | com.example.FunctionBase.scala        |
| Mathematical functions                                            | com.example.MathBase.scala            |