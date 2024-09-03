package diacritics.owo;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import diacritics.owo.command.Argument;
import diacritics.owo.command.Literal;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;
import static net.minecraft.server.command.CommandManager.*;

public class Commands {
  public static void initialize() {
    CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
      dispatcher.register(literal("mul1")
          .then(argument("value", IntegerArgumentType.integer()).executes(context -> {
            final int value = IntegerArgumentType.getInteger(context, "value");
            final int result = value * value;
            context.getSource().sendFeedback(
                () -> Text.literal("%s × %s = %s".formatted(value, value, result)), false);
            return result;
          }).then(argument("value2", IntegerArgumentType.integer()).executes(context -> {
            final int value = IntegerArgumentType.getInteger(context, "value");
            final int value2 = IntegerArgumentType.getInteger(context, "value2");
            final int result = value * value2;
            context.getSource().sendFeedback(
                () -> Text.literal("%s × %s = %s".formatted(value, value2, result)), false);
            return result;
          }))));
    });

    new Literal("mul2")
        .with(new Argument<>("value", IntegerArgumentType.integer()).execute(context -> {
          final int value = IntegerArgumentType.getInteger(context, "value");
          final int result = value * value;
          context.getSource().sendFeedback(
              () -> Text.literal("%s × %s = %s".formatted(value, value, result)), false);
          return result;
        }).with(new Argument<>("value2", IntegerArgumentType.integer()).execute(context -> {
          final int value = IntegerArgumentType.getInteger(context, "value");
          final int value2 = IntegerArgumentType.getInteger(context, "value2");
          final int result = value * value2;
          context.getSource().sendFeedback(
              () -> Text.literal("%s × %s = %s".formatted(value, value2, result)), false);
          return result;
        }))).register();
  }
}
