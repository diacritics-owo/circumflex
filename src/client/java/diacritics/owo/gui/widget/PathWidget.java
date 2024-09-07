package diacritics.owo.gui.widget;

import java.util.function.Function;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.predicate.NumberRange.FloatRange;
import net.minecraft.text.Text;

// TODO: step
// TODO: validate range
public class PathWidget extends ClickableWidget {
  private FloatRange domain;
  private Function<Double, Double> x;
  private Function<Double, Double> y;
  private Color color = Color.solid(0xFFFF0000);

  public PathWidget(FloatRange domain, Function<Double, Double> x, Function<Double, Double> y) {
    this(Text.empty(), domain, x, y);
  }

  public PathWidget(Text message, FloatRange domain, Function<Double, Double> x,
      Function<Double, Double> y) {
    super(0, 0, 0, 0, message);
    this.domain = domain;
    this.x = x;
    this.y = y;
  }

  public PathWidget color(Color color) {
    this.color = color;
    return this;
  }

  public FloatRange getDomain() {
    return this.domain;
  }

  public void setDomain(FloatRange domain) {
    this.domain = domain;
  }

  public Color getColor() {
    return this.color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  protected void appendClickableNarrations(NarrationMessageBuilder builder) {}

  @Override
  protected void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
    for (double n = this.domain.getMin(); n < this.domain.getMax(); n += 1) {
      int x = this.x.apply(n).intValue();
      int y = this.y.apply(n).intValue();

      context.fill(x, y, x + 1, y + 1, this.color.color(n));
    }
  }

  public static class Color {
    private final Function<Double, Integer> color;

    public Color(Function<Double, Integer> color) {
      this.color = color;
    }

    public int color(double n) {
      return this.color.apply(n);
    }

    public static Color solid(int color) {
      return new Color(n -> color);
    }
  }
}
