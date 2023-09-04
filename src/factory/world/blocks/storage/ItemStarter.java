package factory.world.blocks.storage;

import arc.scene.ui.layout.*;
import arc.util.*;
import arc.util.io.*;
import factory.content.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.*;

import static mindustry.Vars.*;

public class ItemStarter extends Block {
	// in secons
	public float genTime = 3;
	public int genPrice = 5;
	
	public ItemStarter(String name){
		super(name);
		update = true;
		solid = true;
		hasItems = true;
		sync = true;
		rotate = true;
		configurable = true;
		saveConfig = true;
		clearOnDoubleTap = true;
		
		config(Item.class, (ItemStarterBuild tile, Item item) -> tile.startItem = item);
        	configClear((ItemStarterBuild tile) -> tile.startItem = null);
	}
	
	public class ItemStarterBuild extends Building {
		public float lastTime;
		public Item startItem = null;

		@Override
		public void updateTile(){
			//if the core has 5 anucoins
			
			if(
			state.teams.get(team).core().items.has(FactoryItems.coins, genPrice) 
			&& (Time.time - lastTime) / 60 > genTime
			&& startItem != null /* supposed to be item != null */
			){
				generate();
				lastTime = Time.time;
			}
			
		}
	
		public void generate(){
			Building front = front();
			if(front == null) return;
			//remove 5 anucoins from le core
			state.teams.get(team).core().items.remove(FactoryItems.coins, genPrice);
			if(front.acceptItem(this, startItem)){
				front.handleItem(this, startItem);
			}
		}
		
		@Override
       	public void buildConfiguration(Table table){
       		ItemSelection.buildTable(ItemStarter.this, table, content.items(), () -> startItem, this::configure);
		}
		
		@Override
	        public Item config(){
        	    return startItem;
        	}

        	@Override
        	public void write(Writes write){
        	    super.write(write);
        	    write.s(startItem == null ? -1 : startItem.id);
        	}

        	@Override
        	public void read(Reads read, byte revision){
        	    super.read(read, revision);
        	    int id = read.s();
        	    startItem = id == -1 ? null : content.items().get(id);
        	}
		
	}
}
