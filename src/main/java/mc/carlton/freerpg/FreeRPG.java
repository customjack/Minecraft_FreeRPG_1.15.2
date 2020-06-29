package mc.carlton.freerpg;

import mc.carlton.freerpg.brewingEvents.BrewingInventoryClick;
import mc.carlton.freerpg.brewingEvents.FinishedBrewing;
import mc.carlton.freerpg.clickEvents.PlayerLeftClick;
import mc.carlton.freerpg.clickEvents.PlayerRightClick;
import mc.carlton.freerpg.clickEvents.PlayerRightClickEntity;
import mc.carlton.freerpg.combatEvents.*;
import mc.carlton.freerpg.commands.*;
import mc.carlton.freerpg.enchantingEvents.*;
import mc.carlton.freerpg.furnaceEvents.FurnaceBurn;
import mc.carlton.freerpg.furnaceEvents.FurnaceInventoryClick;
import mc.carlton.freerpg.furnaceEvents.FurnaceSmelt;
import mc.carlton.freerpg.guiCommands.*;
import mc.carlton.freerpg.guiEvents.*;
import mc.carlton.freerpg.leaveAndJoin.LoginProcedure;
import mc.carlton.freerpg.leaveAndJoin.LogoutProcedure;
import mc.carlton.freerpg.miscEvents.*;
import mc.carlton.freerpg.gameTools.ArrowTypes;
import mc.carlton.freerpg.leaveAndJoin.PlayerJoin;
import mc.carlton.freerpg.leaveAndJoin.PlayerLeave;
import mc.carlton.freerpg.pistonEvents.PistonExtend;
import mc.carlton.freerpg.pistonEvents.PistonRetract;
import mc.carlton.freerpg.playerAndServerInfo.*;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.ArrayList;

public final class FreeRPG extends JavaPlugin implements Listener {
    public String version = "Beta 1.0";

    @Override
    public void onEnable() {
        // Plugin startup logic
        Plugin plugin = FreeRPG.getPlugin(FreeRPG.class);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        PlacedBlockManager mayCreateFile = new PlacedBlockManager();
        try {
            mayCreateFile.startConditions();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PlacedBlocksLoadIn loadInBlocks = new PlacedBlocksLoadIn();
        try {
            ArrayList<Location> blocks = loadInBlocks.getPlacedBlocks();
            PlacedBlocks blockStorageClass = new PlacedBlocks();
            blockStorageClass.setBlocks(blocks);
        } catch (IOException e) {
            e.printStackTrace();
        }


        new BukkitRunnable() {
            @Override
            public void run() {
                ArrowTypes getTippedArrows = new ArrowTypes();
                getTippedArrows.getArrows(0);
            }
        }.runTaskLater(plugin, 20);

        //config Load
        ConfigLoad loadConfig = new ConfigLoad();
        loadConfig.setConfigData();

        System.out.println("FreeRPG loaded sucessfully...");
        System.out.println("Running FreeRPG version " + version);

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
        getServer().getPluginManager().registerEvents(new MainGUIclick(), this);
        getServer().getPluginManager().registerEvents(new SkillsGUIclick(), this);
        getServer().getPluginManager().registerEvents(new CraftingGUIclick(), this);
        getServer().getPluginManager().registerEvents(new ConfirmationGUIClick(), this);
        getServer().getPluginManager().registerEvents(new ConfigurationGUIClick(), this);
        getServer().getPluginManager().registerEvents(new PlayerBlockBreak(), this);
        getServer().getPluginManager().registerEvents(new PlayerBlockPlace(), this);
        getServer().getPluginManager().registerEvents(new PlayerRightClick(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeftClick(), this);
        getServer().getPluginManager().registerEvents(new PlayerTakeDamage(), this);
        getServer().getPluginManager().registerEvents(new PlayerCraft(), this);
        getServer().getPluginManager().registerEvents(new PlayerPrepareCrafting(), this);
        getServer().getPluginManager().registerEvents(new EntityHitEntity(), this);
        getServer().getPluginManager().registerEvents(new PistonExtend(), this);
        getServer().getPluginManager().registerEvents(new PistonRetract(), this);
        getServer().getPluginManager().registerEvents(new PlayerKillEntity(), this);
        getServer().getPluginManager().registerEvents(new PlayerConsumeItem(), this);
        getServer().getPluginManager().registerEvents(new PlayerRightClickEntity(), this);
        getServer().getPluginManager().registerEvents(new PlayerShear(), this);
        getServer().getPluginManager().registerEvents(new PlayerFish(), this);
        getServer().getPluginManager().registerEvents(new PlayerShootBow(), this);
        getServer().getPluginManager().registerEvents(new ArrowLand(), this);
        getServer().getPluginManager().registerEvents(new EntityGetDamaged(), this);
        getServer().getPluginManager().registerEvents(new TameEntityEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerMount(), this);
        getServer().getPluginManager().registerEvents(new PlayerDismount(), this);
        getServer().getPluginManager().registerEvents(new PlayerBreedEntity(), this);
        getServer().getPluginManager().registerEvents(new PlayerToggleSprint(), this);
        getServer().getPluginManager().registerEvents(new BrewingInventoryClick(), this);
        getServer().getPluginManager().registerEvents(new PotionSplash(), this);
        getServer().getPluginManager().registerEvents(new LingeringPotionSplash(), this);
        getServer().getPluginManager().registerEvents(new FinishedBrewing(), this);
        getServer().getPluginManager().registerEvents(new PlayerGetExperience(), this);
        getServer().getPluginManager().registerEvents(new PrepareEnchanting(), this);
        getServer().getPluginManager().registerEvents(new PrepareRepair(), this);
        getServer().getPluginManager().registerEvents(new AnvilClick(), this);
        getServer().getPluginManager().registerEvents(new PlayerEnchant(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new FurnaceSmelt(), this);
        getServer().getPluginManager().registerEvents(new FurnaceInventoryClick(), this);
        getServer().getPluginManager().registerEvents(new FurnaceBurn(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
        getServer().getPluginManager().registerEvents(new PlayerDropItem(), this);

        getCommand("skills").setExecutor(new MainGUI());
        getCommand("spite").setExecutor(new SpiteQuote());
        getCommand("skillTreeGUI").setExecutor(new SkillTreeGUI());
        getCommand("craftingGUI").setExecutor(new CraftingGUI());
        getCommand("confirmationGUI").setExecutor(new ConfirmationGUI());
        getCommand("configurationGUI").setExecutor(new ConfigurationGUI());
        getCommand("giveEXP").setExecutor(new GiveEXP());
        getCommand("setStatLevel").setExecutor(new SetLevel());
        getCommand("statReset").setExecutor(new StatReset());
        //getCommand("god").setExecutor(new god());
        getCommand("flintToggle").setExecutor(new FlintToggle());
        getCommand("enchantItem").setExecutor(new EnchantItem());
        getCommand("speedToggle").setExecutor(new SpeedToggle());
        getCommand("potionToggle").setExecutor(new PotionToggle());
        getCommand("flamePickToggle").setExecutor(new FlamePickToggle());
        getCommand("grappleToggle").setExecutor(new GrappleToggle());
        getCommand("hotRodToggle").setExecutor(new HotRodToggle());
        getCommand("veinMinerToggle").setExecutor(new VeinMinerToggle());
        getCommand("megaDigToggle").setExecutor(new MegaDigToggle());
        getCommand("statLeaders").setExecutor(new Leaderboard());

        //Recipes
        cowEgg();
        beeEgg();
        mooshroomEgg1();
        mooshroomEgg2();
        horseEgg();
        slimeEgg();
        dragonLessArrows();
        powerBook();
        efficiencyBook();
        sharpnessBook();
        protectionBook();
        luckBook();
        lureBook();
        frostBook();
        depthBook();
        mendingBook();
        fortuneBook();
        waterBreathingPotion();
        speedPotion();
        fireResistancePotion();
        healingPotion();
        strengthPotion();

        //If the plugin starts with players online
        for (Player p : Bukkit.getOnlinePlayers()) {
            PlayerStats pStatClass = new PlayerStats(p);
            if (pStatClass.getData().keySet().contains(p.getDisplayName())) {
                LogoutProcedure logout = new LogoutProcedure(p);
                try {
                    logout.playerLogout();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            LoginProcedure login = new LoginProcedure(p);
            login.playerLogin();
        }

        //Repeating task to save plugin data
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    PlayerStatsLoadIn saveStats = new PlayerStatsLoadIn(p);
                    try {
                        saveStats.setPlayerStatsMap(p);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                PlacedBlocksLoadIn saveBlocks = new PlacedBlocksLoadIn();
                try {
                    saveBlocks.setPlacedBlocks();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskTimer(plugin, 20*600,20*600);

    }

    public void onDisable() {

        //Does everything that would normally be done if a player were to log out
        for (Player p : Bukkit.getOnlinePlayers()) {
            LogoutProcedure logout = new LogoutProcedure(p);
            try {
                logout.playerLogout();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        PlacedBlocksLoadIn saveBlocks = new PlacedBlocksLoadIn();
        try {
            saveBlocks.setPlacedBlocks();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Recipe methods
    private void cowEgg() {
        ItemStack item = new ItemStack(Material.COW_SPAWN_EGG, 1);
        NamespacedKey key = new NamespacedKey(this, "CowSpawnEgg");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("LBL", "BbB", "LBL");
        recipe.setIngredient('L', Material.LEATHER);
        recipe.setIngredient('B', Material.BEEF);
        recipe.setIngredient('b', Material.BONE);
        Bukkit.addRecipe(recipe);
    }
    private void beeEgg() {
        ItemStack item = new ItemStack(Material.BEE_SPAWN_EGG, 1);
        NamespacedKey key = new NamespacedKey(this, "BeeSpawnEgg");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" O ", "DHR", " A ");
        recipe.setIngredient('O', Material.OXEYE_DAISY);
        recipe.setIngredient('D', Material.DANDELION);
        recipe.setIngredient('H', Material.HONEY_BOTTLE);
        recipe.setIngredient('R', Material.POPPY);
        recipe.setIngredient('A', Material.AZURE_BLUET);
        Bukkit.addRecipe(recipe);
    }
    private void mooshroomEgg1() {
        ItemStack item = new ItemStack(Material.MOOSHROOM_SPAWN_EGG, 1);
        NamespacedKey key = new NamespacedKey(this, "MooshroomSpawnEgg1");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("LML", "BbB", "LBL");
        recipe.setIngredient('L', Material.LEATHER);
        recipe.setIngredient('B', Material.BEEF);
        recipe.setIngredient('b', Material.BONE);
        recipe.setIngredient('M', Material.RED_MUSHROOM);
        Bukkit.addRecipe(recipe);
    }
    private void mooshroomEgg2() {
        ItemStack item = new ItemStack(Material.MOOSHROOM_SPAWN_EGG, 1);
        NamespacedKey key = new NamespacedKey(this, "MooshroomSpawnEgg2");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("LML", "BbB", "LBL");
        recipe.setIngredient('L', Material.LEATHER);
        recipe.setIngredient('B', Material.BEEF);
        recipe.setIngredient('b', Material.BONE);
        recipe.setIngredient('M', Material.BROWN_MUSHROOM);
        Bukkit.addRecipe(recipe);
    }
    private void horseEgg() {
        ItemStack item = new ItemStack(Material.HORSE_SPAWN_EGG, 1);
        NamespacedKey key = new NamespacedKey(this, "HorseSpawnEgg");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("LSL", "LbL", "HHH");
        recipe.setIngredient('L', Material.LEATHER);
        recipe.setIngredient('S', Material.SADDLE);
        recipe.setIngredient('b', Material.BONE);
        recipe.setIngredient('H', Material.HAY_BLOCK);
        Bukkit.addRecipe(recipe);
    }
    private void slimeEgg() {
        ItemStack item = new ItemStack(Material.SLIME_SPAWN_EGG, 1);
        NamespacedKey key = new NamespacedKey(this, "SlimeSpawnEgg");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   ", " SS", " SS");
        recipe.setIngredient('S', Material.SLIME_BALL);
        Bukkit.addRecipe(recipe);
    }

    private void dragonLessArrows() {
        ItemStack item = new ItemStack(Material.TIPPED_ARROW, 1);
        NamespacedKey key = new NamespacedKey(this, "tippedArrows");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("AAA", "APA", "AAA");
        recipe.setIngredient('A', Material.ARROW);
        recipe.setIngredient('P', Material.POTION);
        Bukkit.addRecipe(recipe);
    }

    private void powerBook() {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(Enchantment.ARROW_DAMAGE,1,true);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "powerBook");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   ", " PP", " P*");
        recipe.setIngredient('*', Material.BOW);
        recipe.setIngredient('P', Material.PAPER);
        Bukkit.addRecipe(recipe);
    }

    private void efficiencyBook() {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(Enchantment.DIG_SPEED,1,true);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "efficiencyBook");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   ", " PP", " P*");
        recipe.setIngredient('*', Material.IRON_PICKAXE);
        recipe.setIngredient('P', Material.PAPER);
        Bukkit.addRecipe(recipe);
    }

    private void sharpnessBook() {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(Enchantment.DAMAGE_ALL,1,true);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "sharpnessBook");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("I  ", " PP", " P*");
        recipe.setIngredient('*', Material.IRON_SWORD);
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('P', Material.PAPER);
        Bukkit.addRecipe(recipe);
    }

    private void protectionBook() {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1,true);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "protectionBook");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" * ", "*PP", " P*");
        recipe.setIngredient('*', Material.IRON_INGOT);
        recipe.setIngredient('P', Material.PAPER);
        Bukkit.addRecipe(recipe);
    }

    private void luckBook() {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(Enchantment.LUCK,1,true);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "luckBook");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("R  ", " PP", " P*");
        recipe.setIngredient('*', Material.FISHING_ROD);
        recipe.setIngredient('R', Material.RABBIT_FOOT);
        recipe.setIngredient('P', Material.PAPER);
        Bukkit.addRecipe(recipe);
    }

    private void lureBook() {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(Enchantment.LURE,1,true);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "lureBook");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("R  ", " PP", " P*");
        recipe.setIngredient('*', Material.FISHING_ROD);
        recipe.setIngredient('R', Material.COD_BUCKET);
        recipe.setIngredient('P', Material.PAPER);
        Bukkit.addRecipe(recipe);
    }

    private void frostBook() {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(Enchantment.FROST_WALKER,1,true);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "frostBook");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   ", " PP", " P*");
        recipe.setIngredient('*', Material.BLUE_ICE);
        recipe.setIngredient('P', Material.PAPER);
        Bukkit.addRecipe(recipe);
    }

    private void depthBook() {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(Enchantment.DEPTH_STRIDER,1,true);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "depthBook");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   ", " PP", " P*");
        recipe.setIngredient('*', Material.NAUTILUS_SHELL);
        recipe.setIngredient('P', Material.PAPER);
        Bukkit.addRecipe(recipe);
    }

    private void mendingBook() {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(Enchantment.MENDING,1,true);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "mendingBook");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   ", " PP", " P*");
        recipe.setIngredient('*', Material.DIAMOND_BLOCK);
        recipe.setIngredient('P', Material.PAPER);
        Bukkit.addRecipe(recipe);
    }

    private void fortuneBook() {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(Enchantment.LOOT_BONUS_BLOCKS,1,true);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "fortuneBook");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   ", " PP", " P*");
        recipe.setIngredient('*', Material.GOLD_BLOCK);
        recipe.setIngredient('P', Material.PAPER);
        Bukkit.addRecipe(recipe);
    }

    private void waterBreathingPotion() {
        ItemStack item = new ItemStack(Material.POTION, 1);
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        meta.setBasePotionData(new PotionData(PotionType.WATER_BREATHING,false,false));
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "waterBreathingPotion");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" I ", " W ", "   ");
        recipe.setIngredient('I', Material.PUFFERFISH);
        recipe.setIngredient('W', Material.GLASS_BOTTLE);
        Bukkit.addRecipe(recipe);
    }

    private void speedPotion() {
        ItemStack item = new ItemStack(Material.POTION, 1);
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        meta.setBasePotionData(new PotionData(PotionType.SPEED,false,false));
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "speedPotion");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" I ", " W ", "   ");
        recipe.setIngredient('I', Material.SUGAR);
        recipe.setIngredient('W', Material.GLASS_BOTTLE);
        Bukkit.addRecipe(recipe);
    }

    private void fireResistancePotion() {
        ItemStack item = new ItemStack(Material.POTION, 1);
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        meta.setBasePotionData(new PotionData(PotionType.FIRE_RESISTANCE,false,false));
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "fireResistancePotion");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" I ", " W ", "   ");
        recipe.setIngredient('I', Material.MAGMA_CREAM);
        recipe.setIngredient('W', Material.GLASS_BOTTLE);
        Bukkit.addRecipe(recipe);
    }

    private void healingPotion() {
        ItemStack item = new ItemStack(Material.POTION, 1);
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        meta.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL,false,false));
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "healingPotion");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" I ", " W ", "   ");
        recipe.setIngredient('I', Material.GLISTERING_MELON_SLICE);
        recipe.setIngredient('W', Material.GLASS_BOTTLE);
        Bukkit.addRecipe(recipe);
    }

    private void strengthPotion() {
        ItemStack item = new ItemStack(Material.POTION, 1);
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        meta.setBasePotionData(new PotionData(PotionType.STRENGTH,false,false));
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "strengthPotion");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" I ", " W ", "   ");
        recipe.setIngredient('I', Material.BLAZE_POWDER);
        recipe.setIngredient('W', Material.GLASS_BOTTLE);
        Bukkit.addRecipe(recipe);
    }




}



/*
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("levelsSet")){
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if(args.length == 0){
                    p.sendMessage("Suicide is badass");
                    p.setHealth(0);
                }else if(args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target instanceof Player){
                        target.sendMessage("You were killed by " + p.getDisplayName());
                        target.setHealth(0);
                        p.sendMessage("You just killed my nigga " + target.getDisplayName() + "! Oh LAWDDD!");
                    }else{
                        p.sendMessage("That player is not on the server");
                    }
                }
                else{
                    p.sendMessage(ChatColor.RED + "Too many arguments");
                    p.sendMessage(ChatColor.RED + "/kill [player]");
                }
            }else{
                System.out.println("You need to be a player to cast this command");
            }
        }
        return true;

    }
}
@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("guiCommand")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                Inventory gui = Bukkit.createInventory(player, 45, ChatColor.AQUA + "Custom GUI");

                //Menu Options(Items)
                ItemStack suicide = new ItemStack(Material.TNT); //Kills the player
                ItemStack feed = new ItemStack(Material.BREAD); //Fills the hunger bar
                ItemStack sword = new ItemStack(Material.DIAMOND_SWORD); //Gives the player a weapon

                //Edit the items
                ItemMeta suicide_meta = suicide.getItemMeta();
                suicide_meta.setDisplayName(ChatColor.RED + "Suicide");
                ArrayList<String> suicide_lore = new ArrayList<>();
                suicide_lore.add(ChatColor.GOLD + "Kill yourself. ;)");
                suicide_meta.setLore(suicide_lore);
                suicide.setItemMeta(suicide_meta);

                ItemMeta feed_meta = feed.getItemMeta();
                feed_meta.setDisplayName(ChatColor.DARK_GREEN + "Feed");
                ArrayList<String> feed_lore = new ArrayList<>();
                feed_lore.add(ChatColor.GOLD + "Hunger no more.");
                feed_meta.setLore(feed_lore);
                feed.setItemMeta(feed_meta);

                ItemMeta sword_meta = sword.getItemMeta();
                sword_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Sword");
                ArrayList<String> sword_lore = new ArrayList<>();
                sword_lore.add(ChatColor.GOLD + "Get a sword.");
                sword_meta.setLore(sword_lore);
                sword.setItemMeta(sword_meta);

                //Put the items in the inventory
                ItemStack[] menu_items = {suicide, feed, sword};
                gui.setContents(menu_items);
                player.openInventory(gui);
            } else {
                System.out.println("You need to be a player to cast this command");
            }

        }
        return true;

    }


public final class SpiteMC extends JavaPlugin implements Listener {
    String version = "inDev1.0";
    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        System.out.println("SpiteMC loaded sucessfully...");
        System.out.println("Running SpiteMC version " + version);
        getServer().getPluginManager().registerEvents(this,this);
        getServer().getPluginManager().registerEvents(new onDeath(), this);
        getServer().getPluginManager().registerEvents(new playerJoin(), this);
        getServer().getPluginManager().registerEvents(new playerLeave(), this);
        getServer().getPluginManager().registerEvents(new playerMove(), this);
        getServer().getPluginManager().registerEvents(new guiItemMove(), this);
        getCommand("god").setExecutor(new god());



    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("die")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                p.sendMessage("Have a nice death.");
                p.setHealth(0);
            } else {
                System.out.println("You need to be a player to cast this command");
            }
        }
        else if (command.getName().equals("heal")){
            if(sender instanceof Player) {
                Player p = (Player) sender;
                p.sendMessage("Have a nice life.");
                p.setHealth(Math.min(p.getHealth()+4,20));
            }else{
                System.out.println("You need to be a player to cast this command");
            }
        }
        else if (command.getName().equals("printMessageFromConfig")){
            if(sender instanceof Player) {
                Player p = (Player) sender;
                String food = getConfig().getString("Food");
                p.sendMessage(ChatColor.AQUA + food);
            }else{
                System.out.println("You need to be a player to cast this command");
            }
        }
        else if (command.getName().equals("sendWord")){
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if(args.length > 0){
                    if(args[0].equalsIgnoreCase("test")){
                        p.sendMessage("Test completed");
                    }else if(args[0].equalsIgnoreCase("build") && args[1].equalsIgnoreCase("tree")){
                        p.sendMessage("Another time friend.");
                    }
                }
                else{
                    p.sendMessage(ChatColor.RED + "You need to give the command an argument");
                    p.sendMessage(ChatColor.RED + "/sendWord [word]");
                }
            }else{
                System.out.println("You need to be a player to cast this command");
            }
        }
        else if (command.getName().equals("kill")){
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if(args.length == 0){
                    p.sendMessage("Suicide is badass");
                    p.setHealth(0);
                }else if(args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target instanceof Player){
                        target.sendMessage("You were killed by " + p.getDisplayName());
                        target.setHealth(0);
                        p.sendMessage("You just killed my nigga " + target.getDisplayName() + "! Oh LAWDDD!");
                    }else{
                        p.sendMessage("That player is not on the server");
                    }
                }
                else{
                    p.sendMessage(ChatColor.RED + "Too many arguments");
                    p.sendMessage(ChatColor.RED + "/kill [player]");
                }
            }else{
                System.out.println("You need to be a player to cast this command");
            }
        }
        else if (command.getName().equals("vault")){
            if(sender instanceof Player) {
                Player p = (Player) sender;
                Inventory vault = Bukkit.createInventory(p,9,"Your vault");
                p.openInventory(vault);
                ItemStack item1 = new ItemStack(Material.CLAY_BALL,2);
                vault.setItem(2,item1);
                vault.addItem(item1);

                vault.clear();
                item1.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK,1000);
                ItemMeta meta1 = item1.getItemMeta();
                meta1.setUnbreakable(true);
                ArrayList<String> lore = new ArrayList<>();
                lore.add(ChatColor.GOLD + "some lore");
                lore.add(ChatColor.DARK_PURPLE + "some more lore");
                meta1.setLore(lore);
                item1.setItemMeta(meta1);
                ItemStack item2 = new ItemStack(Material.BEEF,3);
                ItemStack[] items = {item1,item2};

                vault.setContents(items);

                p.openInventory(vault);
                ;            }else{
                System.out.println("You need to be a player to cast this command");
            }
        }
        else if (command.getName().equals("guiCommand")){
            if(sender instanceof Player) {
                Player player = (Player) sender;
                Inventory gui = Bukkit.createInventory(player, 9, ChatColor.AQUA + "Custom GUI");

                //Menu Options(Items)
                ItemStack suicide = new ItemStack(Material.TNT); //Kills the player
                ItemStack feed =  new ItemStack(Material.BREAD); //Fills the hunger bar
                ItemStack sword = new ItemStack(Material.DIAMOND_SWORD); //Gives the player a weapon

                //Edit the items
                ItemMeta suicide_meta = suicide.getItemMeta();
                suicide_meta.setDisplayName(ChatColor.RED + "Suicide");
                ArrayList<String> suicide_lore = new ArrayList<>();
                suicide_lore.add(ChatColor.GOLD + "Kill yourself. ;)");
                suicide_meta.setLore(suicide_lore);
                suicide.setItemMeta(suicide_meta);

                ItemMeta feed_meta = feed.getItemMeta();
                feed_meta.setDisplayName(ChatColor.DARK_GREEN + "Feed");
                ArrayList<String> feed_lore = new ArrayList<>();
                feed_lore.add(ChatColor.GOLD + "Hunger no more.");
                feed_meta.setLore(feed_lore);
                feed.setItemMeta(feed_meta);

                ItemMeta sword_meta = sword.getItemMeta();
                sword_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Sword");
                ArrayList<String> sword_lore = new ArrayList<>();
                sword_lore.add(ChatColor.GOLD + "Get a sword.");
                sword_meta.setLore(sword_lore);
                sword.setItemMeta(sword_meta);

                //Put the items in the inventory
                ItemStack[] menu_items = {suicide, feed, sword};
                gui.setContents(menu_items);
                player.openInventory(gui);
            }else{
                System.out.println("You need to be a player to cast this command");
            }
        }
        return true;
    }

}
*/