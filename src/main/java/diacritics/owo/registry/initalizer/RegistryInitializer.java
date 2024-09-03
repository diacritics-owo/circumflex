package diacritics.owo.registry.initalizer;

import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.ibm.icu.impl.Pair;
import diacritics.owo.annotation.Id;
import diacritics.owo.util.Helpers;

public abstract class RegistryInitializer<T> {
  abstract public Class<T> entryClass();

  abstract public Registry<T> registry();

  public RegistryInitializer() {}

  public static final void register(Class<? extends RegistryInitializer<?>> class_,
      String namespace) {
    try {
      class_.getDeclaredConstructor().newInstance().register(namespace);
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
      throw new RuntimeException(e);
    }
  }

  protected final void register(String namespace) {
    List<Pair<Boolean, Field>> fields = Arrays.stream(this.getClass().getFields()).map(field -> {
      // registryentry
      if ((field.getType().equals(RegistryEntry.class)
          && ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]
              .equals(this.entryClass()))) {
        return Pair.of(true, field);
      }

      return field.getType().equals(this.entryClass()) ? Pair.of(false, field) : null;
    }).filter(field -> field != null).collect(Collectors.toList());

    for (Pair<Boolean, Field> fieldData : fields) {
      Field field = fieldData.second;

      Identifier defaultIdentifier = Identifier.of(namespace, field.getName().toLowerCase());

      Identifier identifier = field.isAnnotationPresent(Id.class)
          ? Helpers.toIdentifier(field.getAnnotation(Id.class), defaultIdentifier)
          : defaultIdentifier;

      register(identifier, fieldData);
    }
  }

  @SuppressWarnings("unchecked")
  protected final T getFieldValue(Pair<Boolean, Field> fieldData) {
    boolean registryEntry = fieldData.first;
    Field field = fieldData.second;

    try {
      return registryEntry ? ((RegistryEntry<T>) field.get(this)).value() : (T) field.get(this);
    } catch (IllegalArgumentException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  protected void register(Identifier identifier, Pair<Boolean, Field> fieldData) {
    Registry.register(this.registry(), identifier, getFieldValue(fieldData));
    this.afterRegistration(identifier, fieldData);
  }

  protected void afterRegistration(Identifier identifier, Pair<Boolean, Field> fieldData) {
    this.afterRegistration(identifier, this.getFieldValue(fieldData));
  }

  protected void afterRegistration(Identifier identifier, T value) {}
}
