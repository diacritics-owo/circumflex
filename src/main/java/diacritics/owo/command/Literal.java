package diacritics.owo.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import static net.minecraft.server.command.CommandManager.*;

public class Literal extends Part<LiteralArgumentBuilder<ServerCommandSource>, Literal> {
  public Literal(String name) {
    super(name);
  }

  public Literal register() {
    return this.register(RegistrationContext.ANY);
  }

  public Literal register(RegistrationContext context) {
    CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
      if (context.matches(environment)) {
        dispatcher.register(this.build());
      }
    });

    return this;
  }

  @Override
  public LiteralArgumentBuilder<ServerCommandSource> argumentBuilder() {
    return literal(this.name());
  }

  @Override
  public Literal get() {
    return this;
  }
}
