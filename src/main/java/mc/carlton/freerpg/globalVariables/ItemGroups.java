package mc.carlton.freerpg.globalVariables;

import mc.carlton.freerpg.playerAndServerInfo.ConfigLoad;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ItemGroups {
    static List<Material> newIngredients = new ArrayList<>();
    static List<Material> oldIngredients = new ArrayList<>();
    static ItemStack hastePotion;
    static ItemStack fatiguePotion;
    static ItemStack heroPotion;
    static ItemStack decayPotion;
    static ItemStack resistancePotion;
    static List<Material> leftClickItems;
    static List<Material> pickaxes;
    static List<Material> axes;
    static List<Material> shovels;
    static List<Material> hoes;
    static List<Material> swords;
    static List<Material> noRightClick;
    static List<Material> actionableBlocks;
    static List<PotionEffectType> harmfulEffects;
    static List<Material> logs;
    static List<Material> tallCrops;
    static Map<Material, Boolean> trackedBlocks = new ConcurrentHashMap<>();
    static Map<Enchantment, Integer> enchantmentLevelMap = new HashMap<>();
    static List<Material> crops;
    static Map<Material, Integer> farmFood = new HashMap<Material, Integer>();
    static Map<Material, Integer> meatFood = new HashMap<Material, Integer>();
    static Map<Material, Double> farmFoodSaturation = new HashMap<Material, Double>();
    static Map<Material, Double> meatFoodSaturation = new HashMap<Material, Double>();
    static Map<Material, Integer> fishFood = new HashMap<Material, Integer>();
    static Map<Material, Double> fishFoodSaturation = new HashMap<Material, Double>();
    static List<Material> valuableItems;
    static List<Material> ores;
    static Map<Material, Material> repairItems = new HashMap<>();
    static Map<Material, Integer> repairItemsAmount = new HashMap<>();
    static List<Material> strippedLogs;
    static List<Material> leaves;


    public void initializeItemGroups(){
        initializeIngredients();
        initializeCustomPostions();
        initializeLeftClickItems();
        initializeTools();
        initializeActionItems();
        initializeHarmfulPotions();
        initializeBlocks();
        initializeTrackedBlocks();
        initializeEnchantmentLevelMap();
        initializeFoodMaps();
        initializeValuableItems();
    }

    public void initializeEnchantmentLevelMap() {
        enchantmentLevelMap.put(Enchantment.ARROW_KNOCKBACK, 2);
        enchantmentLevelMap.put(Enchantment.ARROW_DAMAGE, 5);
        enchantmentLevelMap.put(Enchantment.ARROW_FIRE, 1);
        enchantmentLevelMap.put(Enchantment.ARROW_INFINITE, 1);
        enchantmentLevelMap.put(Enchantment.BINDING_CURSE, 1);
        enchantmentLevelMap.put(Enchantment.CHANNELING, 1);
        enchantmentLevelMap.put(Enchantment.DAMAGE_ALL, 4);
        enchantmentLevelMap.put(Enchantment.DAMAGE_ARTHROPODS, 4);
        enchantmentLevelMap.put(Enchantment.DAMAGE_UNDEAD, 4);
        enchantmentLevelMap.put(Enchantment.DEPTH_STRIDER, 2);
        enchantmentLevelMap.put(Enchantment.DIG_SPEED, 4);
        enchantmentLevelMap.put(Enchantment.DURABILITY, 3);
        enchantmentLevelMap.put(Enchantment.FIRE_ASPECT, 2);
        enchantmentLevelMap.put(Enchantment.FROST_WALKER, 2);
        enchantmentLevelMap.put(Enchantment.IMPALING, 4);
        enchantmentLevelMap.put(Enchantment.KNOCKBACK, 2);
        enchantmentLevelMap.put(Enchantment.LOOT_BONUS_BLOCKS, 3);
        enchantmentLevelMap.put(Enchantment.LUCK, 3);
        enchantmentLevelMap.put(Enchantment.LOOT_BONUS_MOBS, 3);
        enchantmentLevelMap.put(Enchantment.LOYALTY, 3);
        enchantmentLevelMap.put(Enchantment.LURE, 3);
        enchantmentLevelMap.put(Enchantment.MENDING, 1);
        enchantmentLevelMap.put(Enchantment.MULTISHOT, 1);
        enchantmentLevelMap.put(Enchantment.OXYGEN, 3);
        enchantmentLevelMap.put(Enchantment.PIERCING, 4);
        enchantmentLevelMap.put(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        enchantmentLevelMap.put(Enchantment.PROTECTION_EXPLOSIONS, 4);
        enchantmentLevelMap.put(Enchantment.PROTECTION_FALL, 4);
        enchantmentLevelMap.put(Enchantment.PROTECTION_FIRE, 4);
        enchantmentLevelMap.put(Enchantment.PROTECTION_PROJECTILE, 4);
        enchantmentLevelMap.put(Enchantment.QUICK_CHARGE, 3);
        enchantmentLevelMap.put(Enchantment.RIPTIDE, 3);
        enchantmentLevelMap.put(Enchantment.SILK_TOUCH, 1);
        enchantmentLevelMap.put(Enchantment.SWEEPING_EDGE, 3);
        enchantmentLevelMap.put(Enchantment.THORNS, 3);
        enchantmentLevelMap.put(Enchantment.VANISHING_CURSE, 1);
        enchantmentLevelMap.put(Enchantment.WATER_WORKER, 1);
    }

    public void initializeTrackedBlocks() {
        Material[] trackedBlocks0 = new Material[]{Material.ACACIA_LOG, Material.ACACIA_LEAVES, Material.BIRCH_LOG, Material.BIRCH_LEAVES,
                Material.DARK_OAK_LOG, Material.DARK_OAK_LEAVES, Material.JUNGLE_LOG, Material.JUNGLE_LEAVES,
                Material.OAK_LOG, Material.OAK_LEAVES, Material.SPRUCE_LOG, Material.SPRUCE_LEAVES,
                Material.COAL_ORE, Material.DIAMOND_ORE, Material.EMERALD_ORE, Material.GOLD_ORE,
                Material.IRON_ORE, Material.LAPIS_ORE, Material.NETHER_QUARTZ_ORE, Material.REDSTONE_ORE,
                Material.SUGAR_CANE, Material.MELON, Material.PUMPKIN, Material.RED_MUSHROOM, Material.BROWN_MUSHROOM,
                Material.BAMBOO, Material.CACTUS, Material.SPAWNER};
        for (Material mat : trackedBlocks0) {
            trackedBlocks.putIfAbsent(mat,true);
        }
    }

    public void initializeBlocks() {
        Material[] tallCrops0 = {Material.SUGAR_CANE, Material.BAMBOO, Material.CACTUS, Material.KELP, Material.KELP_PLANT};
        tallCrops = Arrays.asList(tallCrops0);
        Material[] logs0 = {Material.ACACIA_LOG, Material.BIRCH_LOG, Material.DARK_OAK_LOG, Material.OAK_LOG, Material.SPRUCE_LOG, Material.JUNGLE_LOG,
                Material.STRIPPED_ACACIA_LOG, Material.STRIPPED_BIRCH_LOG, Material.STRIPPED_DARK_OAK_LOG, Material.STRIPPED_JUNGLE_LOG,
                Material.STRIPPED_OAK_LOG, Material.STRIPPED_SPRUCE_LOG};
        logs = Arrays.asList(logs0);
        Material[] crops0 = {Material.WHEAT, Material.BEETROOTS, Material.CARROTS, Material.CHORUS_FLOWER, Material.MELON_STEM, Material.MELON,
                Material.NETHER_WART, Material.POTATOES, Material.PUMPKIN_STEM, Material.PUMPKIN, Material.SWEET_BERRY_BUSH, Material.COCOA};
        crops = Arrays.asList(crops0);
        Material[] ores0 = {Material.REDSTONE_ORE, Material.NETHER_QUARTZ_ORE, Material.LAPIS_ORE, Material.IRON_ORE, Material.GOLD_ORE,
                Material.EMERALD_ORE, Material.DIAMOND_ORE, Material.COAL_ORE};
        ores = Arrays.asList(ores0);
        Material[] strippedLogs0 = {Material.STRIPPED_SPRUCE_LOG, Material.STRIPPED_OAK_LOG, Material.STRIPPED_JUNGLE_LOG, Material.STRIPPED_DARK_OAK_LOG, Material.STRIPPED_BIRCH_LOG, Material.STRIPPED_ACACIA_LOG};
        strippedLogs = Arrays.asList(strippedLogs0);
        Material[] leaves0 = {Material.ACACIA_LEAVES, Material.BIRCH_LEAVES, Material.DARK_OAK_LEAVES, Material.OAK_LEAVES, Material.SPRUCE_LEAVES, Material.JUNGLE_LEAVES};
        leaves = Arrays.asList(leaves0);
    }

    public void initializeHarmfulPotions(){
        PotionEffectType[] harmfulEffects0 = {PotionEffectType.WEAKNESS, PotionEffectType.POISON, PotionEffectType.BLINDNESS, PotionEffectType.HUNGER,
                PotionEffectType.HARM, PotionEffectType.SLOW_DIGGING, PotionEffectType.SLOW, PotionEffectType.WEAKNESS, PotionEffectType.WITHER};
        harmfulEffects = Arrays.asList(harmfulEffects0);
    }

    public void initializeActionItems() {
        Material[] noRightClick0 = {Material.AIR, Material.ARROW, Material.SPECTRAL_ARROW, Material.TIPPED_ARROW, Material.BLAZE_POWDER,
                Material.BLAZE_ROD, Material.BONE, Material.BOOK, Material.BOWL, Material.CHARCOAL,
                Material.COAL, Material.CLAY, Material.BRICK, Material.COMPASS, Material.DIAMOND_HORSE_ARMOR, Material.DRAGON_BREATH,
                Material.MAP, Material.ENCHANTED_BOOK, Material.FEATHER, Material.FERMENTED_SPIDER_EYE, Material.FIREWORK_STAR,
                Material.FLINT, Material.GHAST_TEAR, Material.GLISTERING_MELON_SLICE, Material.GLOWSTONE_DUST, Material.GOLDEN_HORSE_ARMOR,
                Material.GOLD_NUGGET, Material.GOLD_INGOT, Material.GUNPOWDER, Material.INK_SAC, Material.IRON_HORSE_ARMOR,
                Material.MAGMA_CREAM, Material.NETHER_BRICK, Material.NETHER_WART, Material.PAPER, Material.PRISMARINE_SHARD, Material.PRISMARINE_CRYSTALS,
                Material.RABBIT_HIDE, Material.LEATHER, Material.LEATHER_HORSE_ARMOR, Material.RABBIT_FOOT, Material.SADDLE, Material.SHEARS,
                Material.SLIME_BALL, Material.SHULKER_SHELL, Material.SPIDER_EYE, Material.STICK, Material.STRING, Material.TOTEM_OF_UNDYING,
                Material.CLOCK, Material.WRITTEN_BOOK};
        noRightClick = Arrays.asList(noRightClick0);
        Material[] actionableBlocks0 = {Material.ANVIL, Material.BLACK_BED, Material.BLUE_BED, Material.BROWN_BED, Material.CYAN_BED, Material.GRAY_BED,
                Material.GREEN_BED, Material.LIGHT_BLUE_BED, Material.LIGHT_GRAY_BED, Material.LIME_BED, Material.MAGENTA_BED,
                Material.ORANGE_BED, Material.PINK_BED, Material.PURPLE_BED, Material.RED_BED, Material.WHITE_BED, Material.YELLOW_BED,
                Material.BELL, Material.BLAST_FURNACE, Material.BREWING_STAND, Material.CARTOGRAPHY_TABLE, Material.CHEST, Material.COMPOSTER,
                Material.CRAFTING_TABLE, Material.ACACIA_DOOR, Material.BIRCH_DOOR, Material.DARK_OAK_DOOR, Material.IRON_DOOR, Material.JUNGLE_DOOR,
                Material.OAK_DOOR, Material.SPRUCE_DOOR, Material.ENCHANTING_TABLE, Material.ACACIA_FENCE_GATE, Material.BIRCH_FENCE_GATE,
                Material.DARK_OAK_FENCE_GATE, Material.JUNGLE_FENCE_GATE, Material.OAK_FENCE_GATE, Material.SPRUCE_FENCE_GATE, Material.FURNACE,
                Material.GRINDSTONE, Material.JUKEBOX, Material.LECTERN, Material.LOOM, Material.NOTE_BLOCK, Material.SMOKER, Material.STONECUTTER,
                Material.TRAPPED_CHEST, Material.ACACIA_TRAPDOOR, Material.BIRCH_TRAPDOOR, Material.DARK_OAK_TRAPDOOR, Material.IRON_TRAPDOOR,
                Material.JUNGLE_TRAPDOOR, Material.OAK_TRAPDOOR, Material.SPRUCE_TRAPDOOR, Material.BARREL, Material.ACACIA_BUTTON, Material.BIRCH_BUTTON,
                Material.DARK_OAK_BUTTON, Material.OAK_BUTTON, Material.JUNGLE_BUTTON, Material.SPRUCE_BUTTON, Material.STONE_BUTTON, Material.REPEATER,
                Material.COMPARATOR, Material.HOPPER, Material.HOPPER_MINECART, Material.DAYLIGHT_DETECTOR, Material.LEVER, Material.FURNACE_MINECART,
                Material.DISPENSER, Material.DROPPER, Material.CHIPPED_ANVIL, Material.DAMAGED_ANVIL, Material.SHULKER_BOX, Material.ENDER_CHEST, Material.BLACK_SHULKER_BOX,
                Material.BLUE_SHULKER_BOX, Material.BROWN_SHULKER_BOX, Material.CYAN_SHULKER_BOX, Material.GRAY_SHULKER_BOX, Material.GREEN_SHULKER_BOX, Material.LIGHT_BLUE_SHULKER_BOX,
                Material.LIGHT_GRAY_SHULKER_BOX, Material.LIME_SHULKER_BOX, Material.MAGENTA_SHULKER_BOX, Material.RED_SHULKER_BOX, Material.WHITE_SHULKER_BOX, Material.YELLOW_SHULKER_BOX,
                Material.ORANGE_SHULKER_BOX, Material.PURPLE_SHULKER_BOX, Material.PINK_SHULKER_BOX};
        actionableBlocks = Arrays.asList(actionableBlocks0);
    }

    public void initializeTools() {
        Material[] pickaxes0 = {Material.DIAMOND_PICKAXE, Material.GOLDEN_PICKAXE, Material.IRON_PICKAXE, Material.STONE_PICKAXE, Material.WOODEN_PICKAXE};
        pickaxes = Arrays.asList(pickaxes0);
        Material[] axes0 = {Material.DIAMOND_AXE, Material.GOLDEN_AXE, Material.IRON_AXE, Material.STONE_AXE, Material.WOODEN_AXE};
        axes = Arrays.asList(axes0);
        Material[] shovels0 = {Material.DIAMOND_SHOVEL, Material.GOLDEN_SHOVEL, Material.IRON_SHOVEL, Material.STONE_SHOVEL, Material.WOODEN_SHOVEL};
        shovels = Arrays.asList(shovels0);
        Material[] hoes0 = {Material.DIAMOND_HOE, Material.GOLDEN_HOE, Material.IRON_HOE, Material.STONE_HOE, Material.WOODEN_HOE};
        hoes = Arrays.asList(hoes0);
        Material[] swords0 = {Material.WOODEN_SWORD, Material.STONE_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.IRON_SWORD};
        swords = Arrays.asList(swords0);
    }

    public void initializeLeftClickItems(){
        Material[] leftClickItems0 = {Material.IRON_SWORD, Material.DIAMOND_SWORD, Material.GOLDEN_SWORD, Material.STONE_SWORD, Material.WOODEN_SWORD,
                Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE, Material.STONE_PICKAXE, Material.GOLDEN_PICKAXE, Material.WOODEN_PICKAXE,
                Material.IRON_SHOVEL, Material.DIAMOND_SHOVEL, Material.GOLDEN_SHOVEL, Material.STONE_SHOVEL, Material.WOODEN_SHOVEL,
                Material.IRON_AXE, Material.DIAMOND_AXE, Material.GOLDEN_AXE, Material.STONE_AXE, Material.WOODEN_AXE,
                Material.BOW, Material.CROSSBOW};
        leftClickItems = Arrays.asList(leftClickItems0);
    }

    public void initializeIngredients() {
        ConfigLoad configLoad = new ConfigLoad();
        ArrayList<Object> alchemyInfo = configLoad.getAlchemyInfo();
        Material[] newIngredients0 = {(Material)alchemyInfo.get(1),(Material)alchemyInfo.get(5),(Material)alchemyInfo.get(9),(Material)alchemyInfo.get(13),(Material)alchemyInfo.get(17)};
        newIngredients = Arrays.asList(newIngredients0);
        Material[] oldIngredients0 = {Material.NETHER_WART, Material.GUNPOWDER, Material.GLOWSTONE_DUST, Material.SPIDER_EYE, Material.GHAST_TEAR,
                Material.RABBIT_FOOT, Material.BLAZE_POWDER, Material.GLISTERING_MELON_SLICE, Material.SUGAR, Material.MAGMA_CREAM,
                Material.REDSTONE, Material.PUFFERFISH, Material.GOLDEN_CARROT, Material.TURTLE_HELMET, Material.PHANTOM_MEMBRANE,
                Material.FERMENTED_SPIDER_EYE};
        oldIngredients = Arrays.asList(oldIngredients0);
    }

    public void initializeCustomPostions() {
        ConfigLoad configLoad = new ConfigLoad();
        ArrayList<Object> alchemyInfo = configLoad.getAlchemyInfo();
        StringsAndOtherData stringsAndOtherData = new StringsAndOtherData();
        String potion1Name = stringsAndOtherData.getPotionNameFromEffect((PotionEffectType) alchemyInfo.get(0));
        String potion2Name = stringsAndOtherData.getPotionNameFromEffect((PotionEffectType) alchemyInfo.get(4));
        String potion3Name = stringsAndOtherData.getPotionNameFromEffect((PotionEffectType) alchemyInfo.get(8));
        String potion4Name = stringsAndOtherData.getPotionNameFromEffect((PotionEffectType) alchemyInfo.get(12));
        String potion5Name = stringsAndOtherData.getPotionNameFromEffect((PotionEffectType) alchemyInfo.get(16));

        //Hero Potion
        heroPotion = new ItemStack(Material.POTION,1);
        heroPotion.addUnsafeEnchantment(Enchantment.LOYALTY,1);
        PotionMeta heroMeta = (PotionMeta) heroPotion.getItemMeta();
        heroMeta.addCustomEffect(new PotionEffect((PotionEffectType) alchemyInfo.get(0),20*(int)alchemyInfo.get(2),0),true);
        heroMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        heroMeta.setColor((Color) alchemyInfo.get(3));
        heroMeta.setDisplayName(ChatColor.RESET + potion1Name);
        heroPotion.setItemMeta(heroMeta);

        //Mining Fatigue Potion
        fatiguePotion = new ItemStack(Material.POTION,1);
        fatiguePotion.addUnsafeEnchantment(Enchantment.LOYALTY,1);
        PotionMeta fatigueMeta = (PotionMeta) fatiguePotion.getItemMeta();
        fatigueMeta.addCustomEffect(new PotionEffect((PotionEffectType) alchemyInfo.get(4),20*(int)alchemyInfo.get(6),0),true);
        fatigueMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        fatigueMeta.setColor((Color) alchemyInfo.get(7));
        fatigueMeta.setDisplayName(ChatColor.RESET + potion2Name);
        fatiguePotion.setItemMeta(fatigueMeta);

        //Haste Potion
        hastePotion = new ItemStack(Material.POTION,1);
        hastePotion.addUnsafeEnchantment(Enchantment.LOYALTY,1);
        PotionMeta hasteMeta = (PotionMeta) hastePotion.getItemMeta();
        hasteMeta.addCustomEffect(new PotionEffect((PotionEffectType) alchemyInfo.get(8),20*(int)alchemyInfo.get(10),0),true);
        hasteMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        hasteMeta.setColor((Color) alchemyInfo.get(11));
        hasteMeta.setDisplayName(ChatColor.RESET + potion3Name);
        hastePotion.setItemMeta(hasteMeta);

        //decay Potion
        decayPotion = new ItemStack(Material.POTION,1);
        decayPotion.addUnsafeEnchantment(Enchantment.LOYALTY,1);
        PotionMeta decayMeta = (PotionMeta) decayPotion.getItemMeta();
        decayMeta.addCustomEffect(new PotionEffect((PotionEffectType) alchemyInfo.get(12),20*(int)alchemyInfo.get(14),0),true);
        decayMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        decayMeta.setColor((Color) alchemyInfo.get(15));
        decayMeta.setDisplayName(ChatColor.RESET + potion4Name);
        decayPotion.setItemMeta(decayMeta);

        //resistance Potion
        resistancePotion = new ItemStack(Material.POTION,1);
        resistancePotion.addUnsafeEnchantment(Enchantment.LOYALTY,1);
        PotionMeta resistanceMeta = (PotionMeta) resistancePotion.getItemMeta();
        resistanceMeta.addCustomEffect(new PotionEffect((PotionEffectType) alchemyInfo.get(16),20*(int)alchemyInfo.get(18),0),true);
        resistanceMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        resistanceMeta.setColor((Color) alchemyInfo.get(19));
        resistanceMeta.setDisplayName(ChatColor.RESET + potion5Name);
        resistancePotion.setItemMeta(resistanceMeta);
    }

    public void initializeFoodMaps(){
        farmFood.put(Material.GOLDEN_APPLE,4);
        farmFoodSaturation.put(Material.GOLDEN_APPLE,13.6);
        farmFood.put(Material.GOLDEN_CARROT,6);
        farmFoodSaturation.put(Material.GOLDEN_CARROT,14.4);
        farmFood.put(Material.BAKED_POTATO,5);
        farmFoodSaturation.put(Material.BAKED_POTATO,6.0);
        farmFood.put(Material.BEETROOT,1);
        farmFoodSaturation.put(Material.BEETROOT,1.2);
        farmFood.put(Material.BEETROOT_SOUP,6);
        farmFoodSaturation.put(Material.BEETROOT_SOUP,7.2);
        farmFood.put(Material.BREAD,5);
        farmFoodSaturation.put(Material.BREAD,6.0);
        farmFood.put(Material.CARROT,3);
        farmFoodSaturation.put(Material.CARROT,3.6);
        farmFood.put(Material.MUSHROOM_STEW,6);
        farmFoodSaturation.put(Material.MUSHROOM_STEW,13.2);
        farmFood.put(Material.APPLE,4);
        farmFoodSaturation.put(Material.APPLE,2.4);
        farmFood.put(Material.CHORUS_FRUIT,4);
        farmFoodSaturation.put(Material.CHORUS_FRUIT,2.4);
        farmFood.put(Material.DRIED_KELP,1);
        farmFoodSaturation.put(Material.DRIED_KELP,0.6);
        farmFood.put(Material.MELON_SLICE,2);
        farmFoodSaturation.put(Material.MELON_SLICE,1.2);
        farmFood.put(Material.POTATO,1);
        farmFoodSaturation.put(Material.POTATO,0.6);
        farmFood.put(Material.PUMPKIN_PIE,8);
        farmFoodSaturation.put(Material.PUMPKIN_PIE,4.8);
        farmFood.put(Material.CAKE,2);
        farmFoodSaturation.put(Material.CAKE,0.4);
        farmFood.put(Material.COOKIE,2);
        farmFoodSaturation.put(Material.COOKIE,0.4);
        farmFood.put(Material.HONEY_BOTTLE,6);
        farmFoodSaturation.put(Material.HONEY_BOTTLE,1.2);
        farmFood.put(Material.SWEET_BERRIES,2);
        farmFoodSaturation.put(Material.SWEET_BERRIES,0.4);

        meatFood.put(Material.COOKED_MUTTON,6);
        meatFoodSaturation.put(Material.COOKED_MUTTON,9.6);
        meatFood.put(Material.COOKED_PORKCHOP,8);
        meatFoodSaturation.put(Material.COOKED_PORKCHOP,12.8);
        meatFood.put(Material.COOKED_BEEF,8);
        meatFoodSaturation.put(Material.COOKED_BEEF,12.8);
        meatFood.put(Material.COOKED_CHICKEN,6);
        meatFoodSaturation.put(Material.COOKED_CHICKEN,7.2);
        meatFood.put(Material.COOKED_RABBIT,5);
        meatFoodSaturation.put(Material.COOKED_RABBIT,6.0);
        meatFood.put(Material.RABBIT_STEW,10);
        meatFoodSaturation.put(Material.RABBIT_STEW,12.0);
        meatFood.put(Material.BEEF,3);
        meatFoodSaturation.put(Material.BEEF,1.8);
        meatFood.put(Material.MUTTON,2);
        meatFoodSaturation.put(Material.MUTTON,1.2);
        meatFood.put(Material.PORKCHOP,3);
        meatFoodSaturation.put(Material.PORKCHOP,1.8);
        meatFood.put(Material.RABBIT,3);
        meatFoodSaturation.put(Material.RABBIT,1.8);
        meatFood.put(Material.CHICKEN,2);
        meatFoodSaturation.put(Material.CHICKEN,1.2);
        meatFood.put(Material.ROTTEN_FLESH,4);
        meatFoodSaturation.put(Material.ROTTEN_FLESH,0.8);

        fishFood.put(Material.COOKED_SALMON,6);
        fishFoodSaturation.put(Material.COOKED_SALMON,1.6);
        fishFood.put(Material.COOKED_COD,5);
        fishFoodSaturation.put(Material.COOKED_COD,1.2);
        fishFood.put(Material.COD,2);
        fishFoodSaturation.put(Material.COD,0.4);
        fishFood.put(Material.SALMON,2);
        fishFoodSaturation.put(Material.SALMON,0.4);
        fishFood.put(Material.TROPICAL_FISH,1);
        fishFoodSaturation.put(Material.TROPICAL_FISH,0.2);
        fishFood.put(Material.PUFFERFISH,1);
        fishFoodSaturation.put(Material.PUFFERFISH,0.2);
        fishFood.put(Material.DRIED_KELP,1);
        fishFoodSaturation.put(Material.DRIED_KELP,0.6);
    }

    public void initializeValuableItems() {
        Material[] valuableItems0 = {Material.IRON_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE, Material.EMERALD_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE,
                Material.IRON_BLOCK, Material.GOLD_BLOCK, Material.DIAMOND_BLOCK, Material.EMERALD_BLOCK, Material.REDSTONE_BLOCK, Material.LAPIS_BLOCK,
                Material.SLIME_BLOCK, Material.SPONGE, Material.NETHER_QUARTZ_ORE, Material.NETHER_WART_BLOCK, Material.DRAGON_EGG, Material.SHULKER_BOX,
                Material.ENCHANTING_TABLE, Material.ANVIL, Material.BEACON, Material.BREWING_STAND, Material.CAKE, Material.JUKEBOX,
                Material.TNT, Material.CREEPER_HEAD, Material.DRAGON_HEAD, Material.PLAYER_HEAD, Material.ZOMBIE_HEAD, Material.SKELETON_SKULL, Material.WITHER_SKELETON_SKULL,
                Material.ENDER_EYE, Material.ENDER_PEARL, Material.FIREWORK_ROCKET, Material.FIRE_CHARGE, Material.POTION, Material.SPLASH_POTION, Material.LINGERING_POTION,
                Material.NETHER_WART, Material.REDSTONE, Material.TRIDENT, Material.DIAMOND_AXE, Material.DIAMOND_BOOTS, Material.DIAMOND_CHESTPLATE,
                Material.DIAMOND_HELMET, Material.DIAMOND_HOE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_PICKAXE, Material.DIAMOND_SHOVEL, Material.DIAMOND_SWORD,
                Material.ELYTRA, Material.ENCHANTED_BOOK, Material.ENCHANTED_GOLDEN_APPLE, Material.GOLDEN_APPLE, Material.IRON_INGOT,
                Material.MUSIC_DISC_11, Material.MUSIC_DISC_13, Material.MUSIC_DISC_BLOCKS, Material.MUSIC_DISC_CAT,
                Material.MUSIC_DISC_CHIRP, Material.MUSIC_DISC_FAR, Material.MUSIC_DISC_MALL, Material.MUSIC_DISC_MELLOHI,
                Material.MUSIC_DISC_STAL, Material.MUSIC_DISC_STRAD, Material.MUSIC_DISC_WAIT, Material.MUSIC_DISC_WARD,
                Material.NAME_TAG, Material.TIPPED_ARROW, Material.TOTEM_OF_UNDYING, Material.SPECTRAL_ARROW, Material.DIAMOND, Material.GOLD_INGOT,
                Material.HEART_OF_THE_SEA, Material.DRAGON_BREATH, Material.EMERALD, Material.NAUTILUS_SHELL, Material.NETHER_STAR, Material.SLIME_BALL,
                Material.RABBIT_FOOT};
        valuableItems = Arrays.asList(valuableItems0);
    }

    public void initializeRepairItems() {
        repairItems.put(Material.WOODEN_AXE, Material.STICK);
        repairItems.put(Material.WOODEN_HOE, Material.STICK);
        repairItems.put(Material.WOODEN_PICKAXE, Material.STICK);
        repairItems.put(Material.WOODEN_SWORD, Material.STICK);
        repairItems.put(Material.WOODEN_SHOVEL, Material.STICK);
        repairItems.put(Material.LEATHER_HELMET, Material.LEATHER);
        repairItems.put(Material.LEATHER_CHESTPLATE, Material.LEATHER);
        repairItems.put(Material.LEATHER_LEGGINGS, Material.LEATHER);
        repairItems.put(Material.LEATHER_BOOTS, Material.LEATHER);
        repairItems.put(Material.IRON_AXE, Material.IRON_INGOT);
        repairItems.put(Material.IRON_HOE, Material.IRON_INGOT);
        repairItems.put(Material.IRON_PICKAXE, Material.IRON_INGOT);
        repairItems.put(Material.IRON_SWORD, Material.IRON_INGOT);
        repairItems.put(Material.IRON_SHOVEL, Material.IRON_INGOT);
        repairItems.put(Material.IRON_HELMET, Material.IRON_INGOT);
        repairItems.put(Material.IRON_CHESTPLATE, Material.IRON_INGOT);
        repairItems.put(Material.IRON_LEGGINGS, Material.IRON_INGOT);
        repairItems.put(Material.IRON_BOOTS, Material.IRON_INGOT);
        repairItems.put(Material.GOLDEN_AXE, Material.GOLD_INGOT);
        repairItems.put(Material.GOLDEN_HOE, Material.GOLD_INGOT);
        repairItems.put(Material.GOLDEN_PICKAXE, Material.GOLD_INGOT);
        repairItems.put(Material.GOLDEN_SWORD, Material.GOLD_INGOT);
        repairItems.put(Material.GOLDEN_SHOVEL, Material.GOLD_INGOT);
        repairItems.put(Material.GOLDEN_HELMET, Material.GOLD_INGOT);
        repairItems.put(Material.GOLDEN_CHESTPLATE, Material.GOLD_INGOT);
        repairItems.put(Material.GOLDEN_LEGGINGS, Material.GOLD_INGOT);
        repairItems.put(Material.GOLDEN_BOOTS, Material.GOLD_INGOT);
        repairItems.put(Material.STONE_AXE, Material.COBBLESTONE);
        repairItems.put(Material.STONE_HOE, Material.COBBLESTONE);
        repairItems.put(Material.STONE_PICKAXE, Material.COBBLESTONE);
        repairItems.put(Material.STONE_SWORD, Material.COBBLESTONE);
        repairItems.put(Material.STONE_SHOVEL, Material.COBBLESTONE);
        repairItems.put(Material.CHAINMAIL_HELMET, Material.IRON_INGOT);
        repairItems.put(Material.CHAINMAIL_CHESTPLATE, Material.IRON_INGOT);
        repairItems.put(Material.CHAINMAIL_LEGGINGS, Material.IRON_INGOT);
        repairItems.put(Material.CHAINMAIL_BOOTS, Material.IRON_INGOT);
        repairItems.put(Material.DIAMOND_AXE, Material.DIAMOND);
        repairItems.put(Material.DIAMOND_HOE, Material.DIAMOND);
        repairItems.put(Material.DIAMOND_PICKAXE, Material.DIAMOND);
        repairItems.put(Material.DIAMOND_SWORD, Material.DIAMOND);
        repairItems.put(Material.DIAMOND_SHOVEL, Material.DIAMOND);
        repairItems.put(Material.DIAMOND_HELMET, Material.DIAMOND);
        repairItems.put(Material.DIAMOND_CHESTPLATE, Material.DIAMOND);
        repairItems.put(Material.DIAMOND_LEGGINGS, Material.DIAMOND);
        repairItems.put(Material.DIAMOND_BOOTS, Material.DIAMOND);
        repairItems.put(Material.SHEARS, Material.IRON_INGOT);
        repairItems.put(Material.FISHING_ROD, Material.STRING);
        repairItems.put(Material.CARROT_ON_A_STICK, Material.CARROT);
        repairItems.put(Material.FLINT_AND_STEEL, Material.FLINT);
        repairItems.put(Material.BOW, Material.STRING);
        repairItems.put(Material.TRIDENT, Material.PRISMARINE_BRICKS);
        repairItems.put(Material.ELYTRA, Material.PHANTOM_MEMBRANE);
        repairItems.put(Material.SHIELD, Material.IRON_INGOT);
        repairItems.put(Material.CROSSBOW, Material.STRING);

        repairItemsAmount.put(Material.WOODEN_AXE,3);
        repairItemsAmount.put(Material.WOODEN_HOE,2);
        repairItemsAmount.put(Material.WOODEN_PICKAXE,3);
        repairItemsAmount.put(Material.WOODEN_SWORD,2);
        repairItemsAmount.put(Material.WOODEN_SHOVEL,1);
        repairItemsAmount.put(Material.LEATHER_HELMET,5);
        repairItemsAmount.put(Material.LEATHER_CHESTPLATE,8);
        repairItemsAmount.put(Material.LEATHER_LEGGINGS,7);
        repairItemsAmount.put(Material.LEATHER_BOOTS,4);
        repairItemsAmount.put(Material.IRON_AXE,3);
        repairItemsAmount.put(Material.IRON_HOE,2);
        repairItemsAmount.put(Material.IRON_PICKAXE,3);
        repairItemsAmount.put(Material.IRON_SWORD,2);
        repairItemsAmount.put(Material.IRON_SHOVEL,1);
        repairItemsAmount.put(Material.IRON_HELMET,5);
        repairItemsAmount.put(Material.IRON_CHESTPLATE,8);
        repairItemsAmount.put(Material.IRON_LEGGINGS,7);
        repairItemsAmount.put(Material.IRON_BOOTS,4);
        repairItemsAmount.put(Material.GOLDEN_AXE,3);
        repairItemsAmount.put(Material.GOLDEN_HOE,2);
        repairItemsAmount.put(Material.GOLDEN_PICKAXE,3);
        repairItemsAmount.put(Material.GOLDEN_SWORD,2);
        repairItemsAmount.put(Material.GOLDEN_SHOVEL,1);
        repairItemsAmount.put(Material.GOLDEN_HELMET,5);
        repairItemsAmount.put(Material.GOLDEN_CHESTPLATE,8);
        repairItemsAmount.put(Material.GOLDEN_LEGGINGS,7);
        repairItemsAmount.put(Material.GOLDEN_BOOTS,4);
        repairItemsAmount.put(Material.STONE_AXE,3);
        repairItemsAmount.put(Material.STONE_HOE,3);
        repairItemsAmount.put(Material.STONE_PICKAXE,3);
        repairItemsAmount.put(Material.STONE_SWORD,2);
        repairItemsAmount.put(Material.STONE_SHOVEL,1);
        repairItemsAmount.put(Material.CHAINMAIL_HELMET,5);
        repairItemsAmount.put(Material.CHAINMAIL_CHESTPLATE,8);
        repairItemsAmount.put(Material.CHAINMAIL_LEGGINGS,7);
        repairItemsAmount.put(Material.CHAINMAIL_BOOTS,4);
        repairItemsAmount.put(Material.DIAMOND_AXE,3);
        repairItemsAmount.put(Material.DIAMOND_HOE,2);
        repairItemsAmount.put(Material.DIAMOND_PICKAXE,3);
        repairItemsAmount.put(Material.DIAMOND_SWORD,2);
        repairItemsAmount.put(Material.DIAMOND_SHOVEL,1);
        repairItemsAmount.put(Material.DIAMOND_HELMET,5);
        repairItemsAmount.put(Material.DIAMOND_CHESTPLATE,8);
        repairItemsAmount.put(Material.DIAMOND_LEGGINGS,7);
        repairItemsAmount.put(Material.DIAMOND_BOOTS,4);
        repairItemsAmount.put(Material.SHEARS,2);
        repairItemsAmount.put(Material.FISHING_ROD,2);

        repairItemsAmount.put(Material.BOW,3);

        repairItemsAmount.put(Material.ELYTRA,10);
        repairItemsAmount.put(Material.SHIELD,1);
        repairItemsAmount.put(Material.CROSSBOW,2);
    }



    //Getters
    public List<Material> getNewIngredients(){
        return newIngredients;
    }
    public List<Material> getOldIngredients(){
        return oldIngredients;
    }
    public ItemStack getHastePotion(){
        return hastePotion;
    }
    public ItemStack getFatiguePotion(){
        return fatiguePotion;
    }
    public ItemStack getHeroPotion(){
        return heroPotion;
    }
    public ItemStack getDecayPotion(){
        return decayPotion;
    }
    public ItemStack getResistancePotion(){
        return resistancePotion;
    }
    public List<Material> getLeftClickItems() {
        return leftClickItems;
    }
    public List<Material> getPickaxes() {
        return pickaxes;
    }
    public List<Material> getAxes() {
        return axes;
    }
    public List<Material> getShovels() {
        return shovels;
    }
    public List<Material> getHoes() {
        return hoes;
    }
    public List<Material> getSwords() {
        return swords;
    }
    public List<Material> getNoRightClick() {
        return noRightClick;
    }
    public List<Material> getActionableBlocks() {
        return actionableBlocks;
    }
    public List<PotionEffectType> getHarmfulEffects() {return harmfulEffects;}
    public List<Material> getLogs() {return logs;}
    public List<Material> getTallCrops() {return tallCrops;}
    public Map<Material, Boolean> getTrackedBlocks() {return trackedBlocks;}
    public Map<Enchantment, Integer> getEnchantmentLevelMap() {
        return enchantmentLevelMap;
    }

    public List<Material> getCrops() {
        return crops;
    }

    public Map<Material, Double> getFarmFoodSaturation() {
        return farmFoodSaturation;
    }

    public Map<Material, Double> getMeatFoodSaturation() {
        return meatFoodSaturation;
    }

    public Map<Material, Integer> getFarmFood() {
        return farmFood;
    }

    public Map<Material, Integer> getMeatFood() {
        return meatFood;
    }

    public Map<Material, Double> getFishFoodSaturation() {
        return fishFoodSaturation;
    }

    public Map<Material, Integer> getFishFood() {
        return fishFood;
    }

    public List<Material> getValuableItems() {
        return valuableItems;
    }
    public List<Material> getOres() { return ores;}

    public Map<Material, Integer> getRepairItemsAmount() {
        return repairItemsAmount;
    }

    public Map<Material, Material> getRepairItems() {
        return repairItems;
    }

    public List<Material> getLeaves() {
        return leaves;
    }

    public List<Material> getStrippedLogs() {
        return strippedLogs;
    }
}
