package diacritics.owo.registry.initalizer;

import com.mojang.serialization.MapCodec;
import diacritics.owo.util.CircumflexHelpers;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class ChunkGeneratorRegistryInitializer
    extends RegistryInitializer<MapCodec<? extends ChunkGenerator>> {
  @Override
  public Class<MapCodec<? extends ChunkGenerator>> entryClass() {
    return CircumflexHelpers.conform(MapCodec.class);
  }

  @Override
  public Registry<MapCodec<? extends ChunkGenerator>> registry() {
    return Registries.CHUNK_GENERATOR;
  }
}
