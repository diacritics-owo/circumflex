---
sidebar_position: 3
---

# More widgets

A demo of these widgets may be viewed by cloning this repository, running `./gradlew runTestmodClient` (`./gradlew.bat runTestmodClient` on Windows), and pressing T in-game.

## `ImageWidget`

The vanilla `IconWidget` allows you to render an image, but it requires the image to be registered as a texture within a resource pack. `ImageWidget` allows you to render a `NativeImage` to the screen dynamically.

Example:

```java
new ImageWidget(0, 0,
    NativeImage
        .read(new FileInputStream(FabricLoader.getInstance().getModContainer(MyMod.MOD_ID)
            .get().findPath("assets/" + MyMod.MOD_ID + "/texture.png").get()
            .toAbsolutePath().toString())));
```

## `PathWidget`

`PathWidget` allows the drawing of a parametric path to the screen, given a range and two functions for the x and y positions.

Example:

```java
new PathWidget(DoubleRange.between(0, this.height), n -> n, n -> this.height - n) // FloatRange for minecraft 1.20.1/circumflex 1.1.0
  .color(PathWidget.Color.solid(0xFFFF00FF)) // the color of the line - a custom color function may be specified with new PathWidget.Color(...) (default 0xFFFF0000)
  .step(1); // the step to use when calculating the values of the function (default 0.5)
```
