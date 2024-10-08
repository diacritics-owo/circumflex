package diacritics.owo.command;

import java.util.ArrayList;
import java.util.List;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.server.command.ServerCommandSource;

public abstract class Part<T extends ArgumentBuilder<ServerCommandSource, T>, U extends Part<T, U>> {
  private final String name;
  private Executable command;
  private List<ArgumentBuilder<ServerCommandSource, ?>> arguments = new ArrayList<>();

  public Part(String name) {
    this.name = name;
  }

  public String name() {
    return this.name;
  }

  public Executable command() {
    return this.command;
  }

  public List<ArgumentBuilder<ServerCommandSource, ?>> arguments() {
    return this.arguments;
  }

  public U execute(Executable command) {
    this.command = command;
    return this.get();
  }

  public U with(ArgumentBuilder<ServerCommandSource, ?> argument) {
    this.arguments.add(argument);
    return this.get();
  }

  public U with(Part<?, ?> argument) {
    return this.with(argument.build());
  }

  public T build() {
    T result = this.argumentBuilder();

    if (this.command != null) {
      result.executes(context -> this.command.run(context));
    }

    this.arguments.forEach(argument -> result.then(argument));

    return result;
  }

  abstract public T argumentBuilder();

  abstract public U get();
}
