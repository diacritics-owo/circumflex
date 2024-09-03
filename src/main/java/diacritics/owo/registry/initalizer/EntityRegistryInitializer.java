package diacritics.owo.registry.initalizer;

import diacritics.owo.util.Helpers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class EntityRegistryInitializer extends RegistryInitializer<EntityType<?>> {
  @Override
  public Class<EntityType<?>> entryClass() {
    return Helpers.conform(Entity.class);
  }

  @Override
  public Registry<EntityType<?>> registry() {
    return Registries.ENTITY_TYPE;
  }
}
