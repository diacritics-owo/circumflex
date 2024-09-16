package diacritics.owo.util;

import com.ibm.icu.impl.Pair;
import diacritics.owo.annotation.Group;
import diacritics.owo.annotation.Id;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler.NoiseParameters;
import java.lang.reflect.Field;
import java.util.Optional;
import org.jetbrains.annotations.Nullable;

public class CircumflexHelpers {
  public static final WrapperLookup WRAPPER_LOOKUP = BuiltinRegistries.createWrapperLookup();
  public static final RegistryWrapper<NoiseParameters> NOISE_PARAMETERS_WRAPPER =
      WRAPPER_LOOKUP.getWrapperOrThrow(RegistryKeys.NOISE_PARAMETERS);

  public static Identifier toIdentifier(Id identifier, Identifier defaultIdentifier) {
    return Identifier.of(
        identifier.namespace().equals("") ? defaultIdentifier.getNamespace()
            : identifier.namespace(),
        identifier.value().equals("") ? defaultIdentifier.getPath() : identifier.value());
  }

  public static void addToGroups(Pair<Boolean, Field> fieldData, Item item) {
    Group[] groups = fieldData.second.getAnnotationsByType(Group.class);

    if (groups != null) {
      for (Group group : groups) {
        ItemGroupEvents.modifyEntriesEvent(RegistryKey.of(RegistryKeys.ITEM_GROUP,
            Identifier.of(group.namespace(), group.value()))).register(content -> {
              content.add(item);
            });
      }
    }
  }

  @SuppressWarnings("unchecked")
  public static <T> Class<T> conform(Class<?> input) {
    return (Class<T>) input;
  }

  @Nullable
  public static RegistryEntry<NoiseParameters> noiseParameters(RegistryKey<NoiseParameters> key) {
    Optional<RegistryEntry.Reference<NoiseParameters>> entry =
        NOISE_PARAMETERS_WRAPPER.getOptional(key);
    return entry.isPresent() ? entry.get() : null;
  }
}
