# Random Extension Library

An extension of **Java**'s standard `Random` class, providing advanced random generation capabilities. This library offers comprehensive random number generation methods, including:

- Random number generation within specific ranges
- Random selection from collections, lists, and arrays
- Enum random selection

## Features

### Random Number Generation

- Generate random numbers within open and closed intervals
- Support for multiple numeric types: `int`, `long`, `float`, `double`

### Random Selection

- Randomly select elements from:
  - Collections
  - Lists
  - Arrays
  - Enum types

### Probability and Boolean Generation

- Generate random booleans with custom probability
- Thread-local singleton access

## Usage

### Random Number Generation

```java
// Get the thread-local RandomExt instance
RandomExt random = RandomExt.getInstance();

// Generate random integer between 1 and 100 (inclusive)
int randomInt = random.nextIntClosed(1, 100);

// Generate random double between 0 and 1 (exclusive)
double randomDouble = random.nextDouble(0, 1);
```

### Random Element Selection

```java
// Select a random element from a list
List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry", "Date");
String randomFruit = random.nextElement(fruits);

// Select a random enum constant
enum Color { RED, GREEN, BLUE, YELLOW }
Color randomColor = random.nextEnum(Color.class);
```

### Probability-based Boolean Generation

```java
// Generate a boolean with 30% probability of being true
boolean randomEvent = random.nextBoolean(0.3);
```

## Thread Safety

`RandomExt` is thread-safe when accessed through the `getInstance()` method, which provides a thread-local instance of the random generator.

## Error Handling

- `IllegalArgumentException` for invalid range specifications
- `NullPointerException` for `null` inputs
- `IllegalArgumentException` for empty collections or arrays