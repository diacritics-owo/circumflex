package diacritics.owo.registry.initalizer;

import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class PotionRegistryInitializer extends RegistryInitializer<Potion> {
  @Override
  public Class<Potion> entryClass() {
    return Potion.class;
  }

  @Override
  public Registry<Potion> registry() {
    return Registries.POTION;
  }
}
