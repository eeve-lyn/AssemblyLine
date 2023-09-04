package factory.content;

import factory.world.blocks.storage.*;
import mindustry.world.*;
import mindustry.world.meta.*;
import mindustry.world.blocks.defense.*;

public class FactoryBlocks{
	
	public static Block starter;
	
	public static void load() {
		starter = new ItemStarter("starter"){{
			buildVisibility = BuildVisibility.shown;
		}};
	}
}
