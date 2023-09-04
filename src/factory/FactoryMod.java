package factory;

import arc.*;
import arc.util.*;
import factory.content.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class FactoryMod extends Mod{

    public FactoryMod(){
        Log.info("Loaded FactoryMod constructor.");
    }

    @Override
    public void loadContent(){
    	FactoryItems.load();
    	FactoryBlocks.load();
        Log.info("Loading some example content.");
    }

}
