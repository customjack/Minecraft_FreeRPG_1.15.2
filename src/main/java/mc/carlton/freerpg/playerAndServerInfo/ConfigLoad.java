package mc.carlton.freerpg.playerAndServerInfo;

import mc.carlton.freerpg.FreeRPG;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConfigLoad {
    Plugin plugin = FreeRPG.getPlugin(FreeRPG.class);
    static int saveStatsTimer;
    static String defaultLanguage;
    static boolean allowExplosions;
    static boolean allowBuild;
    static boolean allowPvP;
    static boolean allowHurtAnimals;
    static ArrayList<Integer> maxLevels = new ArrayList<>();
    static ArrayList<Integer> soulsInfo = new ArrayList<>();
    static ArrayList<Double> multipliers = new ArrayList<>();
    static ArrayList<Double> tokensInfo = new ArrayList<>();
    static ArrayList<Double> levelingInfo = new ArrayList<>();
    static ArrayList<Object> diggingInfo = new ArrayList<>();
    static ArrayList<Object> woodcuttingInfo = new ArrayList<>();
    static ArrayList<Object> fishingInfo = new ArrayList<>();
    static ArrayList<Object> fishingInfoBaseChances = new ArrayList<>();
    static ArrayList<Object> fishingInfoHotRod = new ArrayList<>();
    static ArrayList<Object> fishingInfoEnchants = new ArrayList<>();


    public void setConfig(){
        try {
            File userdata = new File(String.valueOf(Bukkit.getServer().getPluginManager().getPlugin("FreeRPG").getDataFolder()));
            File f = new File(userdata,"config.yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(f);

            Map<String, Object> general = new HashMap<>();
            Map<String, Object> tokens = new HashMap<>();
            Map<String, Object> souls = new HashMap<>();
            Map<String, Object> leveling = new HashMap<>();

            //general.* config
            general.put("playerBaseHP",20.0);
            general.put("defaultLanguage","enUs");
            general.put("allowCustomExplosions",true);
            general.put("allowBuild",true);
            general.put("allowPvP",true);
            general.put("allowHurtAnimals",true);
            general.put("saveStatsTimer",600);

            //tokens.*
            tokens.put("automaticPassiveUpgradesPerLevel",1.0);
            tokens.put("levelsPerPassiveToken",1.0);
            tokens.put("levelPerSkillToken",100.0);
            tokens.put("levelsPerGlobalToken",1000.0);
            tokens.put("startingPassiveTokens",0);
            tokens.put("startingSkillTokens",0);
            tokens.put("startingGlobalTokens",0);

            //souls.*
            souls.put("startingSouls",0);
            souls.put("refundCost",250);

            //leveling.*
            leveling.put("maxLevel",-1);
            leveling.put("exponentialGrowthFactor",1.001595);
            leveling.put("exponentialReferenceLevel",1000);
            leveling.put("exponentialReferenceEXP",10000000.0);
            leveling.put("levelBeginLinear",1000);
            leveling.put("LinearEXPperLevel",20000.0);

            for (String key : general.keySet()) {
                String generalString = "general." + key;
                if (!config.contains(generalString)) {
                    config.set(generalString,general.get(key));
                }
            }

            for (String key : tokens.keySet()) {
                String tokensString = "tokens." + key;
                if (!config.contains(tokensString)) {
                    config.set(tokensString,tokens.get(key));
                }
            }

            for (String key : souls.keySet()) {
                String soulsString = "souls." + key;
                if (!config.contains(soulsString)) {
                    config.set(soulsString,souls.get(key));
                }
            }

            for (String key : leveling.keySet()) {
                String levelingString = "leveling." + key;
                if (!config.contains(levelingString)) {
                    config.set(levelingString,leveling.get(key));
                }
            }

            config.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setConfigData() {
        FileConfiguration config = plugin.getConfig();

        saveStatsTimer = config.getInt("general.saveStatsTimer");
        defaultLanguage = config.getString("general.defaultLanguage");
        allowExplosions = config.getBoolean("general.allowCustomExplosions");
        allowBuild = config.getBoolean("general.allowCustomExplosions");
        allowPvP = config.getBoolean("general.allowCustomExplosions");
        allowHurtAnimals = config.getBoolean("general.allowCustomExplosions");

        maxLevels.add(Integer.valueOf(config.getString("leveling.maxLevel")));
        maxLevels.add(Integer.valueOf(config.getString("digging.maxLevel")));
        maxLevels.add(Integer.valueOf(config.getString("woodcutting.maxLevel")));
        maxLevels.add(Integer.valueOf(config.getString("mining.maxLevel")));
        maxLevels.add(Integer.valueOf(config.getString("farming.maxLevel")));
        maxLevels.add(Integer.valueOf(config.getString("fishing.maxLevel")));
        maxLevels.add(Integer.valueOf(config.getString("archery.maxLevel")));
        maxLevels.add(Integer.valueOf(config.getString("beastMastery.maxLevel")));
        maxLevels.add(Integer.valueOf(config.getString("swordsmanship.maxLevel")));
        maxLevels.add(Integer.valueOf(config.getString("defense.maxLevel")));
        maxLevels.add(Integer.valueOf(config.getString("axeMastery.maxLevel")));
        maxLevels.add(Integer.valueOf(config.getString("repair.maxLevel")));
        maxLevels.add(Integer.valueOf(config.getString("agility.maxLevel")));
        maxLevels.add(Integer.valueOf(config.getString("alchemy.maxLevel")));
        maxLevels.add(Integer.valueOf(config.getString("smelting.maxLevel")));
        maxLevels.add(Integer.valueOf(config.getString("enchanting.maxLevel")));

        soulsInfo.add(Integer.valueOf(config.getString("souls.startingSouls")));
        soulsInfo.add(Integer.valueOf(config.getString("souls.refundCost")));

        multipliers.add(Double.valueOf(config.getString("multipliers.globalMultiplier")));
        multipliers.add(Double.valueOf(config.getString("multipliers.diggingMultiplier")));
        multipliers.add(Double.valueOf(config.getString("multipliers.woodcuttingMultiplier")));
        multipliers.add(Double.valueOf(config.getString("multipliers.miningMultiplier")));
        multipliers.add(Double.valueOf(config.getString("multipliers.farmingMultiplier")));
        multipliers.add(Double.valueOf(config.getString("multipliers.fishingMultiplier")));
        multipliers.add(Double.valueOf(config.getString("multipliers.archeryMultiplier")));
        multipliers.add(Double.valueOf(config.getString("multipliers.beastMasteryMultiplier")));
        multipliers.add(Double.valueOf(config.getString("multipliers.swordsmanshipMultiplier")));
        multipliers.add(Double.valueOf(config.getString("multipliers.defenseMultiplier")));
        multipliers.add(Double.valueOf(config.getString("multipliers.axeMasteryMultiplier")));
        multipliers.add(Double.valueOf(config.getString("multipliers.repairMultiplier")));
        multipliers.add(Double.valueOf(config.getString("multipliers.agilityMultiplier")));
        multipliers.add(Double.valueOf(config.getString("multipliers.alchemyMultiplier")));
        multipliers.add(Double.valueOf(config.getString("multipliers.smeltingMultiplier")));
        multipliers.add(Double.valueOf(config.getString("multipliers.enchantingMultiplier")));

        tokensInfo.add(Double.valueOf(config.getString("tokens.automaticPassiveUpgradesPerLevel")));
        tokensInfo.add(Double.valueOf(config.getString("tokens.levelsPerPassiveToken")));
        tokensInfo.add(Double.valueOf(config.getString("tokens.levelPerSkillToken")));
        tokensInfo.add(Double.valueOf(config.getString("tokens.levelsPerGlobalToken")));
        tokensInfo.add(Double.valueOf(config.getString("tokens.startingPassiveTokens")));
        tokensInfo.add(Double.valueOf(config.getString("tokens.startingSkillTokens")));
        tokensInfo.add(Double.valueOf(config.getString("tokens.startingGlobalTokens")));

        levelingInfo.add(Double.valueOf(config.getString("leveling.maxLevel")));
        levelingInfo.add(Double.valueOf(config.getString("leveling.exponentialGrowthFactor")));
        levelingInfo.add(Double.valueOf(config.getString("leveling.exponentialReferenceLevel")));
        levelingInfo.add(Double.valueOf(config.getString("leveling.exponentialReferenceEXP")));
        levelingInfo.add(Double.valueOf(config.getString("leveling.levelBeginLinear")));
        levelingInfo.add(Double.valueOf(config.getString("leveling.LinearEXPperLevel")));

        diggingInfo.add(Material.matchMaterial(config.getString("digging.drops.drop1Name")));
        diggingInfo.add(Integer.valueOf(config.getString("digging.drops.drop1Amount")));
        diggingInfo.add(Material.matchMaterial(config.getString("digging.drops.drop2Name")));
        diggingInfo.add(Integer.valueOf(config.getString("digging.drops.drop2Amount")));
        diggingInfo.add(Material.matchMaterial(config.getString("digging.drops.drop3Name")));
        diggingInfo.add(Integer.valueOf(config.getString("digging.drops.drop3Amount")));
        diggingInfo.add(Material.matchMaterial(config.getString("digging.drops.drop4Name")));
        diggingInfo.add(Integer.valueOf(config.getString("digging.drops.drop4Amount")));
        diggingInfo.add(Material.matchMaterial(config.getString("digging.drops.drop5Name")));
        diggingInfo.add(Integer.valueOf(config.getString("digging.drops.drop5Amount")));
        diggingInfo.add(Material.matchMaterial(config.getString("digging.drops.drop6Name")));
        diggingInfo.add(Integer.valueOf(config.getString("digging.drops.drop6Amount")));
        diggingInfo.add(Material.matchMaterial(config.getString("digging.drops.drop7Name")));
        diggingInfo.add(Integer.valueOf(config.getString("digging.drops.drop7Amount")));
        diggingInfo.add(Material.matchMaterial(config.getString("digging.drops.drop8Name")));
        diggingInfo.add(Integer.valueOf(config.getString("digging.drops.drop8Amount")));
        diggingInfo.add(Material.matchMaterial(config.getString("digging.drops.drop9Name")));
        diggingInfo.add(Integer.valueOf(config.getString("digging.drops.drop9Amount")));
        diggingInfo.add(Material.matchMaterial(config.getString("digging.drops.drop10Name")));
        diggingInfo.add(Integer.valueOf(config.getString("digging.drops.drop10Amount")));
        diggingInfo.add(Double.valueOf(config.getString("digging.drops.drop10BaseChance")));
        diggingInfo.add(Material.matchMaterial(config.getString("digging.drops.drop11Name")));
        diggingInfo.add(Integer.valueOf(config.getString("digging.drops.drop11Amount")));
        diggingInfo.add(Double.valueOf(config.getString("digging.drops.drop11BaseChance")));
        diggingInfo.add(Material.matchMaterial(config.getString("digging.drops.drop12Name")));
        diggingInfo.add(Integer.valueOf(config.getString("digging.drops.drop12Amount")));
        diggingInfo.add(Double.valueOf(config.getString("digging.drops.drop12BaseChance")));
        diggingInfo.add(Material.matchMaterial(config.getString("digging.drops.drop13Name")));
        diggingInfo.add(Integer.valueOf(config.getString("digging.drops.drop13Amount")));
        diggingInfo.add(Double.valueOf(config.getString("digging.drops.drop13BaseChance")));
        diggingInfo.add(Material.matchMaterial(config.getString("digging.drops.drop14Name")));
        diggingInfo.add(Integer.valueOf(config.getString("digging.drops.drop14Amount")));
        diggingInfo.add(Double.valueOf(config.getString("digging.drops.drop14BaseChance")));
        diggingInfo.add(Material.matchMaterial(config.getString("digging.drops.drop15Name")));
        diggingInfo.add(Integer.valueOf(config.getString("digging.drops.drop15Amount")));
        diggingInfo.add(Double.valueOf(config.getString("digging.drops.drop15BaseChance")));

        woodcuttingInfo.add(Material.matchMaterial(config.getString("woodcutting.drops.leavesDrop1Name")));
        woodcuttingInfo.add(Integer.valueOf(config.getString("woodcutting.drops.leavesDrop1Amount")));
        woodcuttingInfo.add(Double.valueOf(config.getString("woodcutting.drops.leavesDrop1Chance")));
        woodcuttingInfo.add(Material.matchMaterial(config.getString("woodcutting.drops.leavesDrop2Name")));
        woodcuttingInfo.add(Integer.valueOf(config.getString("woodcutting.drops.leavesDrop2Amount")));
        woodcuttingInfo.add(Double.valueOf(config.getString("woodcutting.drops.leavesDrop2Chance")));
        woodcuttingInfo.add(Material.matchMaterial(config.getString("woodcutting.drops.leavesDrop3Name")));
        woodcuttingInfo.add(Integer.valueOf(config.getString("woodcutting.drops.leavesDrop3Amount")));
        woodcuttingInfo.add(Double.valueOf(config.getString("woodcutting.drops.leavesDrop3Chance")));
        woodcuttingInfo.add(Material.matchMaterial(config.getString("woodcutting.drops.leavesDrop4Name")));
        woodcuttingInfo.add(Integer.valueOf(config.getString("woodcutting.drops.leavesDrop4Amount")));
        woodcuttingInfo.add(Double.valueOf(config.getString("woodcutting.drops.leavesDrop4Chance")));
        woodcuttingInfo.add(Material.matchMaterial(config.getString("woodcutting.drops.leavesDrop5Name")));
        woodcuttingInfo.add(Integer.valueOf(config.getString("woodcutting.drops.leavesDrop5Amount")));
        woodcuttingInfo.add(Double.valueOf(config.getString("woodcutting.drops.leavesDrop5Chance")));

        fishingInfoBaseChances.add(Double.valueOf(config.getString("fishing.drops.tier1_baseChance")));
        fishingInfoBaseChances.add(Double.valueOf(config.getString("fishing.drops.tier2_baseChance")));
        fishingInfoBaseChances.add(Double.valueOf(config.getString("fishing.drops.tier3_baseChance")));
        fishingInfoBaseChances.add(Double.valueOf(config.getString("fishing.drops.tier4_baseChance")));
        fishingInfoBaseChances.add(Double.valueOf(config.getString("fishing.drops.tier5_baseChance")));
        fishingInfo.add(Material.matchMaterial(config.getString("fishing.drops.tier1_drop1Name")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier1_drop1Amount")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier1_drop1Random")));
        fishingInfo.add(Material.matchMaterial(config.getString("fishing.drops.tier1_drop2Name")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier1_drop2Amount")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier1_drop2Random")));
        fishingInfo.add(Material.matchMaterial(config.getString("fishing.drops.tier1_drop3Name")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier1_drop3Amount")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier1_drop3Random")));
        fishingInfo.add(Material.matchMaterial(config.getString("fishing.drops.tier1_drop4Name")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier1_drop4Amount")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier1_drop4Random")));
        fishingInfo.add(Material.matchMaterial(config.getString("fishing.drops.tier2_drop1Name")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier2_drop1Amount")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier2_drop1Random")));
        fishingInfo.add(Material.matchMaterial(config.getString("fishing.drops.tier2_drop2Name")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier2_drop2Amount")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier2_drop2Random")));
        fishingInfo.add(Material.matchMaterial(config.getString("fishing.drops.tier2_drop3Name")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier2_drop3Amount")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier2_drop3Random")));
        fishingInfo.add(Material.matchMaterial(config.getString("fishing.drops.tier2_drop4Name")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier2_drop4Amount")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier2_drop4Random")));
        fishingInfo.add(Material.matchMaterial(config.getString("fishing.drops.tier3_drop1Name")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier3_drop1Amount")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier3_drop1Random")));
        fishingInfo.add(Material.matchMaterial(config.getString("fishing.drops.tier3_drop2Name")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier3_drop2Amount")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier3_drop2Random")));
        fishingInfo.add(Material.matchMaterial(config.getString("fishing.drops.tier3_drop3Name")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier3_drop3Amount")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier3_drop3Random")));
        fishingInfo.add(Material.matchMaterial(config.getString("fishing.drops.tier3_drop4Name")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier3_drop4Amount")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier3_drop4Random")));
        fishingInfo.add(Material.matchMaterial(config.getString("fishing.drops.tier4_drop1Name")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier4_drop1Amount")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier4_drop1Random")));
        fishingInfo.add(Material.matchMaterial(config.getString("fishing.drops.tier4_drop2Name")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier4_drop2Amount")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier4_drop2Random")));
        fishingInfo.add(Material.matchMaterial(config.getString("fishing.drops.tier4_drop3Name")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier4_drop3Amount")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier4_drop3Random")));
        fishingInfo.add(Material.matchMaterial(config.getString("fishing.drops.tier4_drop4Name")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier4_drop4Amount")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier4_drop4Random")));
        fishingInfo.add(Material.matchMaterial(config.getString("fishing.drops.tier5_drop1Name")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier5_drop1Amount")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier5_drop1Random")));
        fishingInfo.add(Material.matchMaterial(config.getString("fishing.drops.tier5_drop2Name")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier5_drop2Amount")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier5_drop2Random")));
        fishingInfo.add(Material.matchMaterial(config.getString("fishing.drops.tier5_drop3Name")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier5_drop3Amount")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier5_drop3Random")));
        fishingInfo.add(Material.matchMaterial(config.getString("fishing.drops.tier5_drop4Name")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier5_drop4Amount")));
        fishingInfo.add(Integer.valueOf(config.getString("fishing.drops.tier5_drop4Random")));
        fishingInfoHotRod.add(Material.matchMaterial(config.getString("fishing.drops.tier1_drop1Name_HotRod")));
        fishingInfoHotRod.add(Integer.valueOf(config.getString("fishing.drops.tier1_drop1Amount_HotRod")));
        fishingInfoHotRod.add(Integer.valueOf(config.getString("fishing.drops.tier1_drop1Random_HotRod")));
        fishingInfoHotRod.add(Material.matchMaterial(config.getString("fishing.drops.tier1_drop2Name_HotRod")));
        fishingInfoHotRod.add(Integer.valueOf(config.getString("fishing.drops.tier1_drop2Amount_HotRod")));
        fishingInfoHotRod.add(Integer.valueOf(config.getString("fishing.drops.tier1_drop2Random_HotRod")));
        fishingInfoHotRod.add(Material.matchMaterial(config.getString("fishing.drops.tier2_drop1Name_HotRod")));
        fishingInfoHotRod.add(Integer.valueOf(config.getString("fishing.drops.tier2_drop1Amount_HotRod")));
        fishingInfoHotRod.add(Integer.valueOf(config.getString("fishing.drops.tier2_drop1Random_HotRod")));
        fishingInfoHotRod.add(Material.matchMaterial(config.getString("fishing.drops.tier2_drop3Name_HotRod")));
        fishingInfoHotRod.add(Integer.valueOf(config.getString("fishing.drops.tier2_drop3Amount_HotRod")));
        fishingInfoHotRod.add(Integer.valueOf(config.getString("fishing.drops.tier2_drop3Random_HotRod")));
        fishingInfoHotRod.add(Material.matchMaterial(config.getString("fishing.drops.tier2_drop4Name_HotRod")));
        fishingInfoHotRod.add(Integer.valueOf(config.getString("fishing.drops.tier2_drop4Amount_HotRod")));
        fishingInfoHotRod.add(Integer.valueOf(config.getString("fishing.drops.tier2_drop4Random_HotRod")));
        fishingInfoEnchants.add(Integer.valueOf(config.getString("fishing.drops.tier1_enchantedArmor")));
        fishingInfoEnchants.add(Integer.valueOf(config.getString("fishing.drops.tier2_enchantedArmor")));
        fishingInfoEnchants.add(Integer.valueOf(config.getString("fishing.drops.tier3_enchantedArmor")));
        fishingInfoEnchants.add(Integer.valueOf(config.getString("fishing.drops.tier4_enchantedArmor")));
        fishingInfoEnchants.add(Integer.valueOf(config.getString("fishing.drops.tier5_enchantedArmor")));
    }

    public int getSaveStatsTimer() {return  saveStatsTimer;}
    public String getDefaultLanguage() {return  defaultLanguage;}
    public boolean isAllowExplosions() {return allowExplosions;}
    public boolean isAllowBuild() {return allowBuild;}
    public boolean isAllowPvP() {return allowPvP;}
    public boolean isAllowHurtAnimals() {return allowHurtAnimals;}
    public ArrayList<Integer> getMaxLevels(){
        return maxLevels;
    }
    public ArrayList<Integer> getSoulsInfo(){
        return soulsInfo;
    }
    public ArrayList<Double> getMultipliers(){
        return multipliers;
    }
    public ArrayList<Double> getTokensInfo(){
        return tokensInfo;
    }
    public ArrayList<Double> getLevelingInfo(){
        return levelingInfo;
    }
    public ArrayList<Object> getDiggingInfo(){
        return diggingInfo;
    }
    public ArrayList<Object> getWoodcuttingInfo(){
        return woodcuttingInfo;
    }
    public ArrayList<Object> getFishingInfo1(){
        return fishingInfo;
    }
    public ArrayList<Object> getFishingInfo2(){
        return fishingInfoBaseChances;
    }
    public ArrayList<Object> getFishingInfo3(){
        return fishingInfoHotRod;
    }
    public ArrayList<Object> getFishingInfo4(){
        return fishingInfoEnchants;
    }
}
