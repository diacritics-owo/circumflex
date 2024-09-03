package diacritics.owo.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;

@FunctionalInterface
public interface Executable extends Command<ServerCommandSource> {
  default int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
    return run(new Context(context));
  };

  int run(Context context) throws CommandSyntaxException;
}
