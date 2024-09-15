package diacritics.owo;

import com.mojang.serialization.MapCodec;
import diacritics.owo.registry.initalizer.ChunkGeneratorRegistryInitializer;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class TestModChunkGenerators extends ChunkGeneratorRegistryInitializer {
  public static final MapCodec<? extends ChunkGenerator> owo = OwoChunkGenerator.CODEC;
}
