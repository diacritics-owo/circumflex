package diacritics.owo;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMod implements ModInitializer {
	public static final String MOD_ID = "testmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		System.out.println("hello :3");

		Commands.initialize();

		new TestModItemGroups().register(MOD_ID);
		new TestModItems().register(MOD_ID);
		new TestModBlocks().register(MOD_ID);
	}
}
