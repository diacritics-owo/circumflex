package diacritics.owo.registry.initalizer;

import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.ibm.icu.impl.Pair;
import diacritics.owo.util.Namespace;
import diacritics.owo.util.Path;

// TOOD: registryentry
public abstract class RegistryInitializer<T> {
  abstract public Class<T> entryClass();

  abstract public Registry<T> registry();

  public final void register(String namespace) {
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

      String ns = field.isAnnotationPresent(Namespace.class)
          ? field.getDeclaredAnnotation(Namespace.class).value()
          : namespace;
      String path =
          field.isAnnotationPresent(Path.class) ? field.getDeclaredAnnotation(Path.class).value()
              : field.getName().toLowerCase();

      register(Identifier.of(ns, path), fieldData);
    }
  }

  @SuppressWarnings("unchecked")
  private final T getFieldValue(Pair<Boolean, Field> fieldData) {
    boolean registryEntry = fieldData.first;
    Field field = fieldData.second;

    try {
      return registryEntry ? ((RegistryEntry<T>) field.get(this)).value() : (T) field.get(this);
    } catch (IllegalArgumentException | IllegalAccessException e) {
      // TODO: handle this...?
      throw new RuntimeException(e);
    }
  }

  private void register(Identifier identifier, Pair<Boolean, Field> fieldData) {
    Registry.register(this.registry(), identifier, getFieldValue(fieldData));
    this.afterRegistration(identifier, fieldData);
  }

  private void afterRegistration(Identifier identifier, Pair<Boolean, Field> fieldData) {
    afterRegistration(identifier, getFieldValue(fieldData));
  }

  private void afterRegistration(Identifier identifier, T value) {}
}
