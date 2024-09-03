package diacritics.owo.registry.initalizer;

import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ItemGroupRegistryInitializer extends RegistryInitializer<ItemGroup> {
  @Override
  public Class<ItemGroup> entryClass() {
    return ItemGroup.class;
  }

  @Override
  public Registry<ItemGroup> registry() {
    return Registries.ITEM_GROUP;
  }
}
