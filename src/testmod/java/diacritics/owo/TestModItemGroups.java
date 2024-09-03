package diacritics.owo;

import diacritics.owo.registry.initalizer.ItemGroupRegistryInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;

public class TestModItemGroups extends ItemGroupRegistryInitializer {
  // we won't add the items here in order to demo the group annotation in the item and block
  // initializers
  public ItemGroup GROUP =
      // best vanilla flower, fight me
      FabricItemGroup.builder().icon(() -> new ItemStack(Items.LILY_OF_THE_VALLEY))
          .displayName(Text.translatable("itemGroup.testmod.group")).build();
}
