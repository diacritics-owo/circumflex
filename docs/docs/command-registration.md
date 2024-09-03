# Command Registration API

Circumflex provides a wrapper around Mojang's [Brigadier](https://github.com/mojang/brigadier) that allows for easier and more flexible command building and registration.

## Introduction

Commands are generally registered in the mod initializer. Taking the example command from the [Fabric Wiki](https://fabricmc.net/wiki/tutorial:commands):

```java
CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
  dispatcher.register(literal("mul")
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
```

And with Circumflex:

```java
new Literal("mul")
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
    }))).register();
```

The documentation for this is currently in progress, so please take a look at the [source](https://github.com/diacritics-owo/circumflex/tree/main/src/main/java/diacritics/owo/command).
