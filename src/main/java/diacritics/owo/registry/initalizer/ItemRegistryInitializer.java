package diacritics.owo.registry.initalizer;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public abstract class ItemRegistryInitializer extends RegistryInitializer<Item> {
  @Override
  public Class<Item> entryClass() {
    return Item.class;
  }

  @Override
  public Registry<Item> registry() {
    return Registries.ITEM;
  }
}
