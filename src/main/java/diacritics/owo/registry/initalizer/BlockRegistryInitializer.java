package diacritics.owo.registry.initalizer;

import java.lang.reflect.Field;
import com.ibm.icu.impl.Pair;
import diacritics.owo.util.NoItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

// TODO: more registry initializers
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
      afterRegistration(identifier, getFieldValue(fieldData));
    }
  }

  @Override
  protected void afterRegistration(Identifier identifier, Block value) {
    Registry.register(Registries.ITEM, identifier, new BlockItem(value, new Item.Settings()));
  }
}
