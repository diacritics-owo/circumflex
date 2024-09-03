package diacritics.owo;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import diacritics.owo.registry.initalizer.RegistryInitializer;

public class TestMod implements ModInitializer {
	public static final String MOD_ID = "testmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		System.out.println("hello :3");

		Commands.initialize();

		RegistryInitializer.register(TestModItemGroups.class, MOD_ID);
		RegistryInitializer.register(TestModItems.class, MOD_ID);
		RegistryInitializer.register(TestModBlocks.class, MOD_ID);
	}
}
