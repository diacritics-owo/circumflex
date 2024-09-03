package diacritics.owo.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;

public class Context {
  public final CommandContext<ServerCommandSource> context;

  public Context(CommandContext<ServerCommandSource> context) {
    this.context = context;
  }

  public <T> T get(String key, Class<T> class_) {
    return this.context.getArgument(key, class_);
  }

  public ServerCommandSource source() {
    return this.context.getSource();
  }
}
