package diacritics.owo;

import diacritics.owo.annotation.Group;
import diacritics.owo.annotation.Id;
import diacritics.owo.annotation.NoItem;
import diacritics.owo.registry.initalizer.BlockRegistryInitializer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

public class TestModBlocks extends BlockRegistryInitializer {
  public static final Block BLOCK = new Block(AbstractBlock.Settings.create());

  @Id("hewwo")
  @Group(namespace = TestMod.MOD_ID, value = "group")
  public static final Block HI = new Block(AbstractBlock.Settings.create());

  @NoItem
  public static final Block UNOBTAINABLE = new Block(AbstractBlock.Settings.create());
}
