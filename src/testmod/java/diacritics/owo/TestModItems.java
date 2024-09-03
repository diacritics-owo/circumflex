package diacritics.owo;

import diacritics.owo.annotation.Group;
import diacritics.owo.annotation.Id;
import diacritics.owo.registry.initalizer.ItemRegistryInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.entry.RegistryEntry;

public class TestModItems extends ItemRegistryInitializer {
  public static final Item ITEM = new Item((new Item.Settings()));

  @Id(namespace = "owo", value = "uwu")
  @Group(namespace = TestMod.MOD_ID, value = "group")
  @Group("tools_and_utilities")
  public static final Item OWO = new Item((new Item.Settings()));

  @Id(namespace = "owo")
  public static final RegistryEntry<Item> ANOTHER = RegistryEntry.of(new Item((new Item.Settings())));

  public static final String ignored = "this string field will be ignored";
}
