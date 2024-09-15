package diacritics.owo.world.gen.chunk;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.GenerationStep.Carver;
import net.minecraft.world.gen.chunk.Blender;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.noise.NoiseConfig;

public abstract class CustomNoiseChunkGenerator extends NoiseChunkGenerator {
  public CustomNoiseChunkGenerator(BiomeSource biomeSource,
      RegistryEntry<ChunkGeneratorSettings> settings) {
    super(biomeSource, settings);
  }

  private NoiseConfig cache = null;

  abstract public NoiseConfig wrapNoiseConfig(NoiseConfig noiseConfig);

  private final NoiseConfig wrapNoiseConfigWithCache(NoiseConfig noiseConfig) {
    if (cache == null) {
      this.cache = this.wrapNoiseConfig(noiseConfig);
    }

    return this.cache;
  }

  @Override
  public CompletableFuture<Chunk> populateBiomes(NoiseConfig noiseConfig, Blender blender,
      StructureAccessor structureAccessor, Chunk chunk) {
    return super.populateBiomes(this.wrapNoiseConfigWithCache(noiseConfig), blender,
        structureAccessor, chunk);
  }

  @Override
  public int getHeight(int x, int z, Type heightmap, HeightLimitView world,
      NoiseConfig noiseConfig) {
    return super.getHeight(x, z, heightmap, world, this.wrapNoiseConfigWithCache(noiseConfig));
  }

  @Override
  public VerticalBlockSample getColumnSample(int x, int z, HeightLimitView world,
      NoiseConfig noiseConfig) {
    return super.getColumnSample(x, z, world, this.wrapNoiseConfigWithCache(noiseConfig));
  }

  @Override
  public void getDebugHudText(List<String> text, NoiseConfig noiseConfig, BlockPos pos) {
    super.getDebugHudText(text, this.wrapNoiseConfigWithCache(noiseConfig), pos);
  }

  @Override
  public void buildSurface(ChunkRegion region, StructureAccessor structures,
      NoiseConfig noiseConfig, Chunk chunk) {
    super.buildSurface(region, structures, this.wrapNoiseConfigWithCache(noiseConfig), chunk);
  }

  @Override
  public void carve(ChunkRegion chunkRegion, long seed, NoiseConfig noiseConfig,
      BiomeAccess biomeAccess, StructureAccessor structureAccessor, Chunk chunk,
      Carver carverStep) {
    super.carve(chunkRegion, seed, this.wrapNoiseConfigWithCache(noiseConfig), biomeAccess,
        structureAccessor, chunk, carverStep);
  }

  @Override
  public CompletableFuture<Chunk> populateNoise(Blender blender, NoiseConfig noiseConfig,
      StructureAccessor structureAccessor, Chunk chunk) {
    return super.populateNoise(blender, this.wrapNoiseConfigWithCache(noiseConfig),
        structureAccessor, chunk);
  }
}
