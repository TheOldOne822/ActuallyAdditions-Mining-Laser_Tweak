package theoldone822.AALaserMineTweak;

import de.ellpeck.actuallyadditions.api.ActuallyAdditionsAPI;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "aalaserminetweak", name = "aalaserminetweak", version = "1.0", dependencies = "required-after:actuallyadditions")
public class AALaserMineTweak {
	
	String minList[];
	String minListd[] = {"oreMythril@400@s", "oreAdamantium@300@s", "oreOnyx@100@n", "oreFyrite@400@n", "oreMalachite@250@n", "oreAshstone@150@n", "oreDragonstone@50@n", "oreArgonite@100@n"};
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        minList = config.get("stuff", "MINING_LENS_EXTRA", minListd, "By default, the mining lens has a set number of ores it can generate. If there is an ore that it should be able to generate, add its OreDictionary name followed by an @ and the weight that it should have (the higher, the more often it will generate), followed by another @ and then an s for it to generate in stone and an n for it to generate in netherrack. For instance: oreCheese@100@s would add cheese ore with a weight of 100 that generates in stone.").getStringList();
        config.save();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		for(int i = 0; i < minList.length; i++){
			try{
				String[] split = minList[i].split("@");
				if("n".equals(split[2])){
					ActuallyAdditionsAPI.addMiningLensNetherOre(split[0], Integer.parseInt(split[1]));
				} else if("s".equals(split[2])){
					ActuallyAdditionsAPI.addMiningLensStoneOre(split[0], Integer.parseInt(split[1]));
				}
			}
			catch(Exception e){
				
			}
			
		}
		
	}
}
