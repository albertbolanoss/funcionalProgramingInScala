# Functional Programing using Scala

This project is all about getting you up to speed with functional programming concepts using Scala. 
Packed with examples and practical code, it demonstrates the core ideas of functional programming in action,
helping you to really nail down the basics.

## Requirements

- [Scala 3.6.3](https://www.scala-lang.org/download/all.html) or greater.
- [SBT 1.10.7](https://www.scala-sbt.org/download/) or greater
- Optionally, can create environment variables to link to the /bin directory.
Doing this makes it more convenient to execute commands without specifying the full path every time.

## How Build & Run

### Compile and test using SBT

```sh
sbt -version # 1.10.7 or greater

sbt new scala/scala3.g8                         # Create project template compatible with scala 3
$ sbt new scala/scala3-cross.g8                 # Create project template compatible with scala 3 and scala 2
sbt clean                                       # Clean build and compiled clases 
sbt update                                      # Update dependencies
sbt compile                                     # Compile source
sbt test                                        # Execute tests
sbt testOnly org.example.FunctionBaseSuite      # Execute one specify Suite
```

### Compile and Run using scalac and scala

```sh
scala -version # 3.6.3
scalac {path of module to compile}
scala {path of module to run}
```

## Content

| Topic                                                             | Path                                  |
|-------------------------------------------------------------------|---------------------------------------|
| First approach to functional programming (Coffee Shop).           | com.example.cafeshop.*                |
| Functional programming core concepts.                             | com.example.FunctionBase.scala        |
| Mathematical functions                                            | com.example.MathBase.scala            |

## References

- [Scala Documentation](https://docs.scala-lang.org/scala3/book/tools-sbt.html)
- [MUnit](https://scalameta.org/munit/docs/getting-started.html)