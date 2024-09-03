# Circumflex

A Fabric/Quilt library mod

## Table of Contents

- [Circumflex](#circumflex)
  - [Table of Contents](#table-of-contents)
  - [Features](#features)
    - [Easier Command Registration](#easier-command-registration)

## Features

### Easier Command Registration

```java
// regular
CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
  dispatcher.register(literal("mul1")
      .then(argument("value", IntegerArgumentType.integer()).executes(context -> {
        final int value = IntegerArgumentType.getInteger(context, "value");
        final int result = value * value;
        context.getSource().sendFeedback(
            () -> Text.literal("%s × %s = %s".formatted(value, value, result)), false);
        return result;
      }).then(argument("value2", IntegerArgumentType.integer()).executes(context -> {
        final int value = IntegerArgumentType.getInteger(context, "value");
        final int value2 = IntegerArgumentType.getInteger(context, "value2");
        final int result = value * value2;
        context.getSource().sendFeedback(
            () -> Text.literal("%s × %s = %s".formatted(value, value2, result)), false);
        return result;
      }))));
});

// with circumflex
new Literal("mul2")
    .with(new Argument<>("value", IntegerArgumentType.integer()).execute(context -> {
      final int value = context.get("value", int.class);
      final int result = value * value;
      context.source().sendFeedback(
          () -> Text.literal("%s × %s = %s".formatted(value, value, result)), false);
      return result;
    }).with(new Argument<>("value2", IntegerArgumentType.integer()).execute(context -> {
      final int value = context.get("value", int.class);
      final int value2 = context.get("value2", int.class);
      final int result = value * value2;
      context.source().sendFeedback(
          () -> Text.literal("%s × %s = %s".formatted(value, value2, result)), false);
      return result;
    }))).register(); // you can also register it yourself by calling .build() and passing the result to dispatcher.register normally
```
