package diacritics.owo.registry.initalizer;

import diacritics.owo.util.CircumflexHelpers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class EntityRegistryInitializer extends RegistryInitializer<EntityType<?>> {
  @Override
  public Class<EntityType<?>> entryClass() {
    return CircumflexHelpers.conform(Entity.class);
  }

  @Override
  public Registry<EntityType<?>> registry() {
    return Registries.ENTITY_TYPE;
  }
}
