package diacritics.owo;

import diacritics.owo.registry.initalizer.ItemRegistryInitializer;
import diacritics.owo.util.Namespace;
import diacritics.owo.util.Path;
import net.minecraft.item.Item;
import net.minecraft.registry.entry.RegistryEntry;

public class TestModItems extends ItemRegistryInitializer {
  public Item ITEM = new Item((new Item.Settings()));

  @Namespace("owo")
  @Path("uwu")
  public Item OWO = new Item((new Item.Settings()));

  public RegistryEntry<Item> ANOTHER = RegistryEntry.of(new Item((new Item.Settings())));

  public String ignored = "this string field will be ignored";
}
