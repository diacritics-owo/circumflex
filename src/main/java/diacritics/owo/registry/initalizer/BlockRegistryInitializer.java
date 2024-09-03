package diacritics.owo.registry.initalizer;

import java.lang.reflect.Field;
import com.ibm.icu.impl.Pair;
import diacritics.owo.annotation.NoItem;
import diacritics.owo.util.Helpers;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockRegistryInitializer extends RegistryInitializer<Block> {
  @Override
  public Class<Block> entryClass() {
    return Block.class;
  }

  @Override
  public Registry<Block> registry() {
    return Registries.BLOCK;
  }

  @Override
  protected void afterRegistration(Identifier identifier, Pair<Boolean, Field> fieldData) {
    if (!fieldData.second.isAnnotationPresent(NoItem.class)) {
      Item item = new BlockItem(this.getFieldValue(fieldData), new Item.Settings());
      Registry.register(Registries.ITEM, identifier, item);

      Helpers.addToGroups(fieldData, item);
    }
  }
}
