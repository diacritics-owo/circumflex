package diacritics.owo.registry.initalizer;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class EnchantmentRegistryInitializer extends RegistryInitializer<Enchantment> {
  @Override
  public Class<Enchantment> entryClass() {
    return Enchantment.class;
  }

  @Override
  public Registry<Enchantment> registry() {
    return Registries.ENCHANTMENT;
  }
}
