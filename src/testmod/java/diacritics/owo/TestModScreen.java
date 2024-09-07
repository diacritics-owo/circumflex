package diacritics.owo;

import java.io.FileInputStream;
import diacritics.owo.gui.widget.ImageWidget;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.NarratedMultilineTextWidget;
import net.minecraft.client.texture.NativeImage;
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
    this.text = this.addDrawableChild(
        new NarratedMultilineTextWidget(this.textRenderer, Text.literal("hello!"), this.width));
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
    this.n += 0.05;
    this.image.setPosition(
        (int) (Math.cos(this.n) * (this.width / 4)) + ((this.width - this.image.getWidth()) / 2),
        (int) (Math.sin(this.n) * (this.height / 4))
            + ((this.height - this.image.getHeight()) / 2));
    this.n = this.n % (2 * Math.PI);
  }

  @Override
  public void render(DrawContext context, int mouseX, int mouseY, float delta) {
    this.renderBackground(context);
    context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 70, 16777215);
    super.render(context, mouseX, mouseY, delta);
  }
}
