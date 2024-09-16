---
sidebar_position: 4
---

# `ChunkGenerator`s

From version 1.3.0 onwards, Circumflex provides some `ChunkGenerator`s to ease the process of creating one.

## `CustomNoiseChunkGenerator`

`CustomNoiseChunkGenerator` allows you to create a `NoiseChunkGenerator` with custom noise configuration from within code. While configuring `NoiseChunkGenerator` is possible entirely within datapacks (see https://minecraft.wiki/w/Noise_router), doing it within code allows for greater customisability.

### Example

First, create the chunk generator by extending `CustomNoiseChunkGenerator`:

```java title="MyChunkGenerator.java"
public class MyChunkGenerator extends CustomNoiseChunkGenerator {
  public static final MapCodec<MyModChunkGenerator> CODEC =
      RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(
            BiomeSource.CODEC.fieldOf("biome_source").forGetter(MyChunkGenerator::getBiomeSource),
            ChunkGeneratorSettings.REGISTRY_CODEC.fieldOf("settings")
                .forGetter(MyChunkGenerator::getSettings))
            .apply(instance, instance.stable(MyChunkGenerator::new));
      });

  public MyChunkGenerator(BiomeSource biomeSource,
      RegistryEntry<ChunkGeneratorSettings> settings) {
    super(biomeSource, settings);
  }

  @Override
  protected MapCodec<? extends ChunkGenerator> getCodec() {
    return CODEC;
  }

  @Override
  public NoiseConfig wrapNoiseConfig(NoiseConfig noiseConfig) {
    // this replaces the final density function - see https://minecraft.wiki/w/Noise_router
    noiseConfig.noiseRouter = new NoiseRouterBuilder(noiseConfig.getNoiseRouter())
        .finalDensity(DensityFunctionTypes.mul(
            DensityFunctionTypes
                .noise(CircumflexHelpers.noiseParameters(NoiseParametersKeys.GRAVEL), 1, 1),
            DensityFunctionTypes
                .noise(CircumflexHelpers.noiseParameters(NoiseParametersKeys.CAVE_CHEESE), 1, 1)))
        .build();

    return noiseConfig;
  }
}
```

Then, register the codec (this can be done using [registry initializers](/registry-initializer)):

```java title="MyModChunkGenerators.java"
public class MyModChunkGenerators extends ChunkGeneratorRegistryInitializer {
  public static final MapCodec<? extends ChunkGenerator> myChunkGenerator = MyChunkGenerator.CODEC;
}
```

```java title="MyMod.java"
public class MyMod implements ModInitializer {
	public static final String MOD_ID = "mymod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		RegistryInitializer.register(MyModChunkGenerators.class, MOD_ID);
	}
}
```

Finally, you need some way to use the chunk generator. One way to do this is by creating a world preset for it, adding it to the world preset tag, and providing a translation for it.

```json title="resources/data/mymod/worldgen/world_preset/my_world_preset.json"
{
  "dimensions": {
    "minecraft:overworld": {
      "type": "minecraft:overworld",
      "generator": {
        "type": "mymod:my_chunk_generator",
        "settings": "minecraft:overworld",
        "biome_source": {
          "type": "minecraft:multi_noise",
          "preset": "minecraft:overworld"
        }
      }
    }
  }
}
```

Note that the following file is under the Minecraft namespace, not your mod's:

```json title="resources/data/minecraft/tags/worldgen/world_preset/normal.json"
{
  "replace": false,
  "values": ["mymod:my_world_preset"]
}
```

This is optional:

```json title="assets/mymod/lang/en_us.json"
{
  "generator.mymod.my_world_preset": "My World Preset"
}
```

Now, upon launching Minecraft, you should be able to change the world preset to the one you've just added.

The example chunk generator given earlier produces something like the following:

![a surreal scene](../static/img/surreal-generation.png)
