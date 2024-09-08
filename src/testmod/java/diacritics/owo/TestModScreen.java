package diacritics.owo;

import java.io.FileInputStream;
import diacritics.owo.gui.widget.ImageWidget;
import diacritics.owo.gui.widget.PathWidget;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.NarratedMultilineTextWidget;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.predicate.NumberRange.DoubleRange;
import net.minecraft.text.Text;

public class TestModScreen extends Screen {
  private NarratedMultilineTextWidget text;
  private ImageWidget image;

  private double n = 0;

  public TestModScreen() {
    super(Text.empty());
  }

  @Override
  protected void init() {
    DoubleRange domain = DoubleRange.between(-(this.width / 2), this.width / 2);

    this.addDrawableChild(new PathWidget(domain, n -> n + (this.width / 2),
        n -> (this.height / 2) - 3 * (Math.pow(n, 3) * (this.height / Math.pow(this.width, 3))))
            .color(PathWidget.Color.solid(0xFFFF00FF)));

    this.text = this.addDrawableChild(
        new NarratedMultilineTextWidget(this.width, Text.literal("hello!"), this.textRenderer, 6));
    this.text.setPosition((this.width - this.text.getWidth()) / 2, (this.height / 2) - (9 / 2));

    try {
      this.image = this.addDrawableChild(new ImageWidget(0, 0,
          NativeImage
              .read(new FileInputStream(FabricLoader.getInstance().getModContainer(TestMod.MOD_ID)
                  .get().findPath("assets/" + TestMod.MOD_ID + "/emerald.png").get()
                  .toAbsolutePath().toString()))));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean shouldCloseOnEsc() {
    return true;
  }

  @Override
  public void tick() {
    this.n += 0.03;
    this.image.setPosition(
        (int) (Math.cos(this.n) * (this.width / 4)) + ((this.width - this.image.getWidth()) / 2),
        (int) (Math.sin(this.n) * (this.height / 4))
            + ((this.height - this.image.getHeight()) / 2));
    this.n = this.n % (2 * Math.PI);
  }

  @Override
  public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
    this.applyBlur(delta);
    this.renderInGameBackground(context);
  }
}
