package diacritics.owo;

import diacritics.owo.registry.initalizer.BlockRegistryInitializer;
import diacritics.owo.util.NoItem;
import diacritics.owo.util.Path;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

public class TestModBlocks extends BlockRegistryInitializer {
  public Block BLOCK = new Block(AbstractBlock.Settings.create());

  @Path("hewwo")
  public Block HI = new Block(AbstractBlock.Settings.create());

  @NoItem
  public Block UNOBTAINABLE = new Block(AbstractBlock.Settings.create());
}
