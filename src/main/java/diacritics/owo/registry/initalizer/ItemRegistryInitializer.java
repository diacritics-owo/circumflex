package diacritics.owo.registry.initalizer;

import java.lang.reflect.Field;
import com.ibm.icu.impl.Pair;
import diacritics.owo.util.Helpers;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public abstract class ItemRegistryInitializer extends RegistryInitializer<Item> {
  @Override
  public Class<Item> entryClass() {
    return Item.class;
  }

  @Override
  public Registry<Item> registry() {
    return Registries.ITEM;
  }

  @Override
  protected void afterRegistration(Identifier identifier, Pair<Boolean, Field> fieldData) {
    Helpers.registerGroup(fieldData, this.getFieldValue(fieldData));
  }
}
