package diacritics.owo;

import diacritics.owo.annotation.Group;
import diacritics.owo.annotation.Id;
import diacritics.owo.registry.initalizer.ItemRegistryInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.entry.RegistryEntry;

public class TestModItems extends ItemRegistryInitializer {
  public Item ITEM = new Item((new Item.Settings()));

  @Id(namespace = "owo", value = "uwu")
  @Group(namespace = "testmod", value = "group")
  @Group("tools_and_utilities")
  public Item OWO = new Item((new Item.Settings()));

  @Id(namespace = "owo")
  public RegistryEntry<Item> ANOTHER = RegistryEntry.of(new Item((new Item.Settings())));

  public String ignored = "this string field will be ignored";
}
