[![Actions Status](https://github.com/nnsquik/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/nnsquik/java-project-78/actions)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=nnsquik_java-project-78&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=nnsquik_java-project-78)

# Data Validator

A Java Library for validating data of different types.

## Requirements
-   Java 21+
- Gradle 8+
- JUnit 5

## Installation
```bash
git clone https://github.com/nnsquik/java-project-78.git
cd java-project-78
make build
```

## Usage

### String

```java
var schema = new Validator().string();
schema.required();
schema.minLength(5).contains("hex").isValid("hexlet"); // true
```

### Number

```java
var schema = new Validator().number();
schema.required().positive().range(5, 10);
schema.isValid(7); // true
schema.isValid(-1); // false
```

### Map

```java
var schema = new Validator().map();
schema.required().sizeOf(2);

Map> shemas = new HashMap<>();
schemas.put("firstName", v.string().required());
schemas.put("lastName", v.string().required().minLength(2));
schema.shape(schemas);
```

## Running tests
```bash
make build
```