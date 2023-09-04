package factory.content;

import mindustry.type.*;

public class FactoryItems{
	
	public static Item coins;
	
	public static void load() {
		coins = new Item("coins"){{
            		hardness = 1;
        	}};
	}
}
