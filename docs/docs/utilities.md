---
sidebar_position: 5
---

# Utilities

## `NoiseRouterBuilder`

`NoiseRouterBuilder` allows you to build a `NoiseRouter` given an initial value, allowing for easier building. There is also a method provided, `CircumflexHelpers.noiseParameters`, which allows you to easily retrieve noise parameters given a registry key.

Without `NoiseRouterBuilder`:

```java
new NoiseRouter(router.barrierNoise(), router.fluidLevelFloodednessNoise(),
    router.fluidLevelSpreadNoise(), router.lavaNoise(), router.temperature(),
    router.vegetation(), router.continents(), router.erosion(), router.depth(), router.ridges(),
    router.initialDensityWithoutJaggedness(),
    DensityFunctionTypes.mul(
        DensityFunctionTypes.noise(BuiltinRegistries.createWrapperLookup()
            .getWrapperOrThrow(RegistryKeys.NOISE_PARAMETERS)
            .getOrThrow(NoiseParametersKeys.GRAVEL), 1, 1),
        DensityFunctionTypes.noise(BuiltinRegistries.createWrapperLookup()
            .getWrapperOrThrow(RegistryKeys.NOISE_PARAMETERS)
            .getOrThrow(NoiseParametersKeys.CAVE_CHEESE), 1, 1)),
    router.veinToggle(), router.veinRidged(), router.veinGap());
```

With it:

```java
new NoiseRouterBuilder(noiseConfig.getNoiseRouter())
    .finalDensity(DensityFunctionTypes.mul(
        DensityFunctionTypes
            .noise(CircumflexHelpers.noiseParameters(NoiseParametersKeys.GRAVEL), 1, 1),
        DensityFunctionTypes
            .noise(CircumflexHelpers.noiseParameters(NoiseParametersKeys.CAVE_CHEESE), 1, 1)))
    .build();
```
