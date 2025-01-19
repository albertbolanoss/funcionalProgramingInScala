# Functional Programing using Scala

This project is all about getting you up to speed with functional programming concepts using Scala. 
Packed with examples and practical code, it demonstrates the core ideas of functional programming in action,
helping you to really nail down the basics.

## Requirements

- [Scala 3.4.2](https://www.scala-lang.org/download/all.html) or greater.
- [Gradle 8.7](https://gradle.org/releases) or greater.
- Optionally, can create environment variables to link to the /bin directory.
Doing this makes it more convenient to execute commands without specifying the full path every time.

## How Build & Run

### Build & Run using Gradle

```sh
gradle --version # Gradle 8.7
gradle build
gradle run
```

#### Install Gradle wrappers

If you prefer working with Gradle wrappers, follow these steps to set them up:

```sh
gradle --version # Gradle 8.7
gradle wrapper

# Run gradlew or gradlew.bat depending on your operating system specifications.

# Linux or Mac
gradlew build
gradlew run

# Windows
gradlew.bat build
gradlew.bat run
```

### Build & Run using Scalac

```sh
scala -version #
scalac {path of module to compile}
scala {path of module to run}
```

#### Run single Modules

```sh
# Core concept of functions (Functions Pure, Monomorphobic, Polymorphobic, Partial application, Curring, Uncurring, Composition)
scala src/main/scala/com/example/FunctionModule.scala
```