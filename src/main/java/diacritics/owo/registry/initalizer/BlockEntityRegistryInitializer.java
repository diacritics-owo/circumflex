package diacritics.owo.registry.initalizer;

import diacritics.owo.util.Helpers;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class BlockEntityRegistryInitializer extends RegistryInitializer<BlockEntityType<?>> {
  @Override
  public Class<BlockEntityType<?>> entryClass() {
    return Helpers.conform(BlockEntityType.class);
  }

  @Override
  public Registry<BlockEntityType<?>> registry() {
    return Registries.BLOCK_ENTITY_TYPE;
  }
}
