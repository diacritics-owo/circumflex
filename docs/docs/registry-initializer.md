---
sidebar_position: 1
---

# Registry Initializer API

Circumflex provides a registry initializer API (similar to and heavily inspired by that of [owo-lib](https://docs.wispforest.io/owo/registration/)) to allow for automatic registration of items, blocks, etc.

See the [test mod](https://github.com/diacritics-owo/circumflex/tree/main/src/testmod/java/diacritics/owo) for examples of usage.

## Introduction

The following is how one would generally register things into a registry:

```java title="src/main/java/com/example/item/MyModItems.java"
public class MyModItems {
  public static final Item ITEM = register("item", new Item(...));
  public static final Item ANOTHER_ITEM = register("second_item", new Item(...)); // notice that the id doesn't match the field name

  public static Item register(String id, Item item) {
    return Registry.register(Registries.ITEM, Identifier.of(MyMod.MOD_ID, id), item);
  }

  // this initializer is called in the mod entrypoint
  public static void initialize() {
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
      content.add(ITEM);
      content.add(ANOTHER_ITEM);
    });

    ItemGroupEvents.modifyEntriesEvent(MyModItemGroups.CUSTOM_GROUP).register(content -> {
      content.add(ITEM);
    });
  }
}
```

With the registry initializer API, this can be simplified to:

```java title="src/main/java/com/example/item/MyModItems.java"
public class MyModItems extends ItemRegistryInitializer {
  @Group("tools_and_utilities")
  @Group(namespace = MyMod.MOD_ID, value = "custom_group")
  public static final Item ITEM = new Item(...);

  @Id("second_item")
  @Group("tools_and_utilities")
  public static final Item ANOTHER_ITEM = new Item(...);
}
```

In the mod initializer, you would then call `RegistryInitializer.register(MyModItems.class, MOD_ID)`.

Note that registry initializers also support fields of type `RegistryEntry`, and that fields not of type `T` or `RegistryEntry<T>` will be ignored.

## Default Registry Intializers

Circumflex provides a few registry initializers by default:

- `BlockEntityRegistryInitializer` (`Registries.BLOCK_ENTITY_TYPE`)
- `BlockRegistryInitializer` (`Registries.BLOCK`; also registers `BlockItem`s by default - see [`@NoItem`](#noitem))
- `EnchantmentRegistryInitializer` (`Registries.ENCHANTMENT`; 1.20.1 only)
- `EntityRegistryInitializer` (`Registries.ENTITY_TYPE`)
- `ItemGroupRegistryInitializer` (`Registries.ITEM_GROUP`)
- `ItemRegistryInitializer` (`Registries.ITEM`)
- `PotionRegistryInitializer` (`Registries.POTION`)

## Custom Registries

Creating a registry initializer for a registry that Circumflex doesn't provide one for is simple. Using `Registries.PARTICLE_TYPE` as an example:

```java
public class ParticleTypeRegistryInitializer extends RegistryInitializer<ParticleType> {
  @Override
  public Class<ParticleType> entryClass() {
    return ParticleType.class; // if it's generic, e.g. BlockEntityType<T>, use CircumflexHelpers.conform (Helpers.conform for circumflex 1.1.0)
  }

  @Override
  public Registry<ParticleType> registry() {
    return Registries.PARTICLE_TYPE;
  }
}
```

And you're done! You can now create a class extending `ParticleTypeRegistryInitializer` and use it as shown in the examples above.

### Advanced Usage

Using custom registry initializers as above will be enough for most use cases, but you cannot, for example, do things like dynamically register `BlockItem`s or use custom annotations. To do so, you must override either `void afterRegistration(Identifier identifier, T value)` or (not recommended) `void afterRegistration(Identifier identifier, Pair<Boolean, Field> fieldData)`.

```java
public class ... {
  ...

  @Override
  protected void afterRegistration(Identifier identifier, T value) {
    // do things here
  }

  // only override this if you need to use custom annotations
  protected void afterRegistration(
    Identifier identifier,
    Pair<Boolean, Field> fieldData /// the first item is a boolean indicating whether the field is a registryentry, and the second is a java.lang.reflect.Field
  ) {
    // the default implementation is just: this.afterRegistration(identifier, this.getFieldValue(fieldData))
  }

  ...
}
```

It is **strongly** recommended that you look at [`BlockRegistryInitializer`](https://github.com/diacritics-owo/circumflex/blob/main/src/main/java/diacritics/owo/registry/initalizer/BlockRegistryInitializer.java) and [`ItemRegistryInitializer`](https://github.com/diacritics-owo/circumflex/blob/main/src/main/java/diacritics/owo/registry/initalizer/ItemRegistryInitializer.java) for examples of dynamic registration and custom annotations.

## Annotations

### `@Id`

Sets a custom ID for the item

- Valid on all items
- Uses the default namespace (passed to `register()`) or identifier (lowercase field name) if not present

```java
@Id // does nothing
```

```java
@Id("path")
```

```java
@Id(namespace = "namespace")
```

```java
@Id(value = "path")
```

```java
@Id(namespace = "namespace", value = "path")
```

### `@Group`

Adds an item to an item group

- Valid on items and blocks (only takes effect on blocks if [`@NoItem`](#noitem) is _not_ present)
- Uses the vanilla namespace if not present
- May be present multiple times

```java
@Group("path")
```

```java
@Group(value = "path")
```

```java
@Group(namespace = "namespace", value = "path")
```

### `@NoItem`

Prevents a `BlockItem` from being generated

- Valid on blocks

```
@NoItem
```
