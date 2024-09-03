package diacritics.owo.command;

import static net.minecraft.server.command.CommandManager.argument;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.server.command.ServerCommandSource;

public class Argument<S> extends Part<RequiredArgumentBuilder<ServerCommandSource, S>, Argument<S>> {
  private final ArgumentType<S> type;

  public Argument(String name, ArgumentType<S> type) {
    super(name);
    this.type = type;
  }

  public ArgumentType<S> type() {
    return this.type();
  }

  @Override
  public RequiredArgumentBuilder<ServerCommandSource, S> argumentBuilder() {
    return argument(this.name(), this.type);
  }

  @Override
  public Argument<S> get() {
    return this;
  }
}
