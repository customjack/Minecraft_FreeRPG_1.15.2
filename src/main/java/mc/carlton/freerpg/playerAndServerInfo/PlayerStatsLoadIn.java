package mc.carlton.freerpg.playerAndServerInfo;

import mc.carlton.freerpg.FreeRPG;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerStatsLoadIn {
    private Player p;
    Map<String, ArrayList<Number>> statsMap = new HashMap<String, ArrayList<Number>>();
    File f;
    UUID pUUID;
    FileConfiguration playerData;

    public PlayerStatsLoadIn(Player player) {
        Plugin plugin = FreeRPG.getPlugin(FreeRPG.class);
        this.p = player;
        this.pUUID = p.getUniqueId();
        this.f = new File(plugin.getDataFolder(), File.separator + "PlayerDatabase" + File.separator + pUUID.toString() + ".yml");
        this.playerData = YamlConfiguration.loadConfiguration(f);
    }

    public long getLoginTime() {
        long loginTime = 0;
        if (f.exists()) {
            loginTime = Long.parseLong(playerData.get("general.lastLogin").toString());
            return loginTime;
        }
        return Instant.now().getEpochSecond();
    }

    public long getPlayTime() {
        long playTime = 0;
        if (f.exists()) {
            playTime = Long.parseLong(playerData.get("general.playTime").toString());
            return playTime;
        }
        return Instant.now().getEpochSecond();
    }

    public String getPlayerLanguage() {
        String language = "enUs";
        if (f.exists()) {
            language = playerData.get("general.language").toString();
            return language;
        }
        return language;
    }

    public Map<String, Integer> getSkillExpBarToggles(){
        Map<String, Integer> skillExpBarToggleMap = new HashMap<>();
        String[] labels = {"digging","woodcutting","mining","farming","fishing","archery","beastMastery","swordsmanship","defense","axeMastery","repair","agility","alchemy","smelting","enchanting"};
        for (String label : labels) {
            skillExpBarToggleMap.put(label,playerData.getInt(label + ".showEXPBarToggle"));
        }
        return skillExpBarToggleMap;
    }
    public Map<String, Integer> getSkillAbilityToggles(){
        Map<String, Integer> skillAbilityToggleMap = new HashMap<>();
        String[] labels = {"digging","woodcutting","mining","farming","fishing","archery","beastMastery","swordsmanship","defense","axeMastery","repair","agility","alchemy","smelting","enchanting"};
        for (String label : labels) {
            skillAbilityToggleMap.put(label,playerData.getInt(label + ".triggerAbilityToggle"));
        }
        return skillAbilityToggleMap;
    }

    public Map<String, ArrayList<Number>> getPlayerStatsMap() {
        String[] labels = {"digging","woodcutting","mining","farming","fishing","archery","beastMastery","swordsmanship","defense","axeMastery","repair","agility","alchemy","smelting","enchanting"};
        ArrayList<Number> stats = new ArrayList<Number>();
        if(f.exists()) {
            stats.add(Integer.valueOf((playerData.get("globalStats.totalLevel").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.globalTokens").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.skill_1a").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.skill_1b").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.skill_1c").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.skill_2a").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.skill_2b").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.skill_2c").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.skill_3a").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.skill_3b").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.skill_3c").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.skill_M").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.flintToggle").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.oreToggle").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.speedToggle").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.potionToggle").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.grappleToggle").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.hotRodToggle").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.veinMinerToggle").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.megaDigToggle").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.souls").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.levelUpMessageToggle").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.abilityPrepareMessageToggle").toString())));
            stats.add(Double.valueOf((playerData.get("globalStats.personalEXPMultiplier").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.triggerAbilitiesToggle").toString())));
            stats.add(Integer.valueOf((playerData.get("globalStats.showEXPBarToggle").toString())));
            statsMap.put("global", stats);

            for (int i = 0; i < labels.length; i++) {
                String skillName = labels[i];
                ArrayList<Number> statsi = new ArrayList<Number>();
                statsi.add(Integer.valueOf((playerData.get(labels[i] + ".level").toString())));
                statsi.add(Integer.valueOf((playerData.get(labels[i] + ".experience").toString())));
                statsi.add(Integer.valueOf((playerData.get(labels[i] + ".passiveTokens").toString())));
                statsi.add(Integer.valueOf((playerData.get(labels[i] + ".skillTokens").toString())));
                statsi.add(Integer.valueOf((playerData.get(labels[i] + ".passive1").toString())));
                statsi.add(Integer.valueOf((playerData.get(labels[i] + ".passive2").toString())));
                statsi.add(Integer.valueOf((playerData.get(labels[i] + ".passive3").toString())));
                statsi.add(Integer.valueOf((playerData.get(labels[i] + ".skill_1a").toString())));
                statsi.add(Integer.valueOf((playerData.get(labels[i] + ".skill_1b").toString())));
                statsi.add(Integer.valueOf((playerData.get(labels[i] + ".skill_2a").toString())));
                statsi.add(Integer.valueOf((playerData.get(labels[i] + ".skill_2b").toString())));
                statsi.add(Integer.valueOf((playerData.get(labels[i] + ".skill_3a").toString())));
                statsi.add(Integer.valueOf((playerData.get(labels[i] + ".skill_3b").toString())));
                statsi.add(Integer.valueOf((playerData.get(labels[i] + ".skill_M").toString())));
                statsMap.put(skillName, statsi);
            }

        }
    return statsMap;
    }
    public void setPlayerStatsMap() throws IOException {
        PlayerStats pStatClass = new PlayerStats(p);
        Map<String, ArrayList<Number>> pStatAll = pStatClass.getPlayerData();
        Map<String, Integer> expBarToggles = pStatClass.getSkillToggleExpBar();
        Map<String, Integer> abilityToggles = pStatClass.getSkillToggleAbility();
        String pName = p.getName();
        long unixTime = Instant.now().getEpochSecond();
        if(f.exists()) {
            playerData.set("general.username", pName);

            //Setting playTime in seconds
            playerData.set("general.lastLogout",unixTime);
            long lastLoginTime = (long) Long.parseLong(playerData.get("general.lastLogin").toString());
            long playTime = unixTime-lastLoginTime;
            playerData.set("general.playTime",playTime);

            //Setting player Language
            String playerLanguage = pStatClass.getPlayerLanguage();
            playerData.set("general.language",playerLanguage);

            for (String i : pStatAll.keySet()) {
                if (i.equalsIgnoreCase("global")) {
                    playerData.set("globalStats.totalLevel",pStatAll.get(i).get(0));
                    playerData.set("globalStats.globalTokens",pStatAll.get(i).get(1));
                    playerData.set("globalStats.skill_1a",pStatAll.get(i).get(2));
                    playerData.set("globalStats.skill_1b",pStatAll.get(i).get(3));
                    playerData.set("globalStats.skill_1c",pStatAll.get(i).get(4));
                    playerData.set("globalStats.skill_2a",pStatAll.get(i).get(5));
                    playerData.set("globalStats.skill_2b",pStatAll.get(i).get(6));
                    playerData.set("globalStats.skill_2c",pStatAll.get(i).get(7));
                    playerData.set("globalStats.skill_3a",pStatAll.get(i).get(8));
                    playerData.set("globalStats.skill_3b",pStatAll.get(i).get(9));
                    playerData.set("globalStats.skill_3c",pStatAll.get(i).get(10));
                    playerData.set("globalStats.skill_M",pStatAll.get(i).get(11));
                    playerData.set("globalStats.flintToggle",pStatAll.get(i).get(12));
                    playerData.set("globalStats.oreToggle",pStatAll.get(i).get(13));
                    playerData.set("globalStats.speedToggle",pStatAll.get(i).get(14));
                    playerData.set("globalStats.potionToggle",pStatAll.get(i).get(15));
                    playerData.set("globalStats.grappleToggle",pStatAll.get(i).get(16));
                    playerData.set("globalStats.hotRodToggle",pStatAll.get(i).get(17));
                    playerData.set("globalStats.veinMinerToggle",pStatAll.get(i).get(18));
                    playerData.set("globalStats.megaDigToggle",pStatAll.get(i).get(19));
                    playerData.set("globalStats.souls",pStatAll.get(i).get(20));
                    playerData.set("globalStats.levelUpMessageToggle",pStatAll.get(i).get(21));
                    playerData.set("globalStats.abilityPrepareMessageToggle",pStatAll.get(i).get(22));
                    playerData.set("globalStats.personalEXPMultiplier",pStatAll.get(i).get(23));
                    playerData.set("globalStats.triggerAbilitiesToggle",pStatAll.get(i).get(24));
                    playerData.set("globalStats.showEXPBarToggle",pStatAll.get(i).get(25));
                }
                else {
                    playerData.set(i+".level",pStatAll.get(i).get(0));
                    playerData.set(i+".experience",pStatAll.get(i).get(1));
                    playerData.set(i+".passiveTokens",pStatAll.get(i).get(2));
                    playerData.set(i+".skillTokens",pStatAll.get(i).get(3));
                    playerData.set(i+".passive1",pStatAll.get(i).get(4));
                    playerData.set(i+".passive2",pStatAll.get(i).get(5));
                    playerData.set(i+".passive3",pStatAll.get(i).get(6));
                    playerData.set(i+".skill_1a",pStatAll.get(i).get(7));
                    playerData.set(i+".skill_1b",pStatAll.get(i).get(8));
                    playerData.set(i+".skill_2a",pStatAll.get(i).get(9));
                    playerData.set(i+".skill_2b",pStatAll.get(i).get(10));
                    playerData.set(i+".skill_3a",pStatAll.get(i).get(11));
                    playerData.set(i+".skill_3b",pStatAll.get(i).get(12));
                    playerData.set(i+".skill_M",pStatAll.get(i).get(13));
                    playerData.set(i+".triggerAbilityToggle",expBarToggles.get(i));
                    playerData.set(i+".showEXPBarToggle",abilityToggles.get(i));
                }
                }
            playerData.save(f);
            System.out.println("[FreeRPG] Saved " + pName + " stats successfully");
            }
    }
}
