package diacritics.owo;

import org.lwjgl.glfw.GLFW;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class TestModClient implements ClientModInitializer {
  private static KeyBinding openScreen;

  @Override
  public void onInitializeClient() {
    openScreen = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.testmod.open_screen",
        InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_T, "category.testmod.keybindings"));

    ClientTickEvents.END_CLIENT_TICK.register(client -> {
      while (openScreen.wasPressed()) {
        MinecraftClient.getInstance().setScreen(new TestModScreen());
      }
    });
  }
}
