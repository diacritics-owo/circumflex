package diacritics.owo;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import diacritics.owo.util.CircumflexHelpers;
import diacritics.owo.world.gen.chunk.CustomNoiseChunkGenerator;
import diacritics.owo.world.gen.noise.NoiseRouterBuilder;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.noise.NoiseParametersKeys;

public class OwoChunkGenerator extends CustomNoiseChunkGenerator {
  public static final MapCodec<OwoChunkGenerator> CODEC =
      RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(
            BiomeSource.CODEC.fieldOf("biome_source").forGetter(OwoChunkGenerator::getBiomeSource),
            ChunkGeneratorSettings.REGISTRY_CODEC.fieldOf("settings")
                .forGetter(OwoChunkGenerator::getSettings))
            .apply(instance, instance.stable(OwoChunkGenerator::new));
      });

  public OwoChunkGenerator(BiomeSource biomeSource,
      RegistryEntry<ChunkGeneratorSettings> settings) {
    super(biomeSource, settings);
  }

  @Override
  protected MapCodec<? extends ChunkGenerator> getCodec() {
    return CODEC;
  }

  @Override
  public NoiseConfig wrapNoiseConfig(NoiseConfig noiseConfig) {
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
