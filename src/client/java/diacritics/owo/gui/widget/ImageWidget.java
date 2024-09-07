package diacritics.owo.gui.widget;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.text.Text;

public class ImageWidget extends ClickableWidget {
  private NativeImage image;

  public ImageWidget(int x, int y, NativeImage image) {
    this(x, y, Text.empty(), image);
  }

  public ImageWidget(int x, int y, Text message, NativeImage image) {
    super(x, y, image.getWidth(), image.getHeight(), message);
    this.image = image;
  }

  public NativeImage getImage() {
    return this.image;
  }

  public void setImage(NativeImage image) {
    this.image = image;
    this.setWidth(image.getWidth());
    this.height = image.getHeight(); // there's no setheight method? (1.20.1)
  }

  @Override
  protected void appendClickableNarrations(NarrationMessageBuilder builder) {}

  @Override
  protected void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
    for (int x = 0; x < this.getWidth(); x++) {
      for (int y = 0; y < this.getHeight(); y++) {
        context.fill(x + this.getX(), y + this.getY(), x + this.getX() + 1, y + this.getY() + 1,
            this.image.getColor(x, y));
      }
    }
  }
}
