package diacritics.owo.command;

import net.minecraft.server.command.CommandManager.RegistrationEnvironment;

public enum RegistrationContext {
  ANY, INTEGRATED, DEDICATED, NONE;

  public boolean matches(RegistrationContext other) {
    return (this == ANY || other == ANY || this == other) && this != NONE;
  }

  public boolean matches(RegistrationEnvironment other) {
    return this.matches(from(other));
  }

  public static RegistrationContext from(RegistrationEnvironment environment) {
    switch (environment) {
      case ALL:
        return ANY;
      case INTEGRATED:
        return INTEGRATED;
      case DEDICATED:
        return DEDICATED;
      default:
        return NONE;
    }
  }
}
