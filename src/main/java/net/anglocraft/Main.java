package net.anglocraft;

import net.anglocraft.character.Interaction;
import net.anglocraft.character.InventoryInteraction;
import net.anglocraft.claim.WorldClaimManager;
import net.anglocraft.commands.Name;
import net.anglocraft.events.Join;
import net.anglocraft.serverlist.MOTD;
import net.anglocraft.serverlist.PlayerCount;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
   private File config;
   private FileConfiguration c;
   // TODO 2020/06/20: AHHHH EVERYTHING IS STATIC
   public static YamlConfiguration langConfig;
   public static File langFile;
   private static final Logger log = Logger.getLogger("Minecraft");
   private static Economy econ = null;
   private static Permission perms = null;
   private static Chat chat = null;
   private static Map<World, WorldClaimManager> claimManagerMap = new HashMap<>();

   public void onEnable() {
      this.loadLang();
      this.config = new File(this.getDataFolder(), "config.yml");
      if (!this.config.exists()) {
         this.config.getParentFile().mkdirs();
         this.saveResource("config.yml", false);
      }

      this.c = new YamlConfiguration();

      try {
         this.c.load(this.config);
      } catch (InvalidConfigurationException | IOException var2) {
         var2.printStackTrace();
      }

      this.registerEvents();
      this.registerCommands();
      if (!this.setupEconomy()) {
         log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", this.getDescription().getName()));
         this.getServer().getPluginManager().disablePlugin(this);
      } else {
         this.setupPermissions();
         this.setupChat();
      }

      for(World world : getServer().getWorlds())
         claimManagerMap.put(world, new WorldClaimManager(world));
   }
   public void onDisable() {
   }

   public void registerEvents() {
      Bukkit.getServer().getPluginManager().registerEvents(new MOTD(), this);
      Bukkit.getServer().getPluginManager().registerEvents(new PlayerCount(), this);
      Bukkit.getServer().getPluginManager().registerEvents(new Interaction(), this);
      Bukkit.getServer().getPluginManager().registerEvents(new Join(), this);
      Bukkit.getServer().getPluginManager().registerEvents(new InventoryInteraction(), this);
      Bukkit.getServer().getPluginManager().registerEvents(new net.anglocraft.events.Chat(), this);
   }

   public void registerCommands() {
      this.getCommand("name").setExecutor(new Name());
   }

   public YamlConfiguration getLang() {
      return langConfig;
   }

   public File getLangFile() {
      return langFile;
   }

   public YamlConfiguration loadLang() {
      File lang = new File(this.getDataFolder(), "lang.yml");
      if (!lang.exists()) {
         try {
            this.getDataFolder().mkdir();
            lang.createNewFile();
            InputStream defConfigStream = this.getResource("lang.yml");
            if (defConfigStream != null) {
               YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new File("lang.yml"));
               defConfig.save(lang);
               Lang.setFile(defConfig);
               return defConfig;
            }
         } catch (IOException var8) {
            var8.printStackTrace();
            log.severe("[PluginName] Couldn't create language file.");
            log.severe("[PluginName] This is a fatal error. Now disabling");
            this.setEnabled(false);
         }
      }

      YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
      Lang[] var3 = Lang.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Lang item = var3[var5];
         if (conf.getString(item.getPath()) == null) {
            conf.set(item.getPath(), item.getDefault());
         }
      }

      Lang.setFile(conf);
      langConfig = conf;
      langFile = lang;

      try {
         conf.save(this.getLangFile());
      } catch (IOException var7) {
         log.log(Level.WARNING, "ANGLOCRAFT: Failed to save lang.yml.");
         log.log(Level.WARNING, "ANGLOCRAFT: Report this stack trace to JoshScoper.");
         var7.printStackTrace();
      }

      return conf;
   }

   public FileConfiguration config() {
      return this.c;
   }

   public void saveFile(FileConfiguration fileconfig, File file) {
      try {
         fileconfig.save(file);
      } catch (IOException var4) {
         var4.printStackTrace();
      }

   }

   private boolean setupEconomy() {
      if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
         return false;
      } else {
         RegisteredServiceProvider rsp = this.getServer().getServicesManager().getRegistration(Economy.class);
         if (rsp == null) {
            return false;
         } else {
            econ = (Economy)rsp.getProvider();
            return econ != null;
         }
      }
   }

   private boolean setupChat() {
      RegisteredServiceProvider rsp = this.getServer().getServicesManager().getRegistration(Chat.class);
      chat = (Chat)rsp.getProvider();
      return chat != null;
   }

   private boolean setupPermissions() {
      RegisteredServiceProvider rsp = this.getServer().getServicesManager().getRegistration(Permission.class);
      perms = (Permission)rsp.getProvider();
      return perms != null;
   }

   public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
      if (!(sender instanceof Player)) {
         log.info("Only players are supported for this Example Plugin, but you should not do this!!!");
         return true;
      } else {
         Player player = (Player)sender;
         if (command.getLabel().equals("test-economy")) {
            sender.sendMessage(String.format("You have %s", econ.format(econ.getBalance(player.getName()))));
            EconomyResponse r = econ.depositPlayer(player, 1.05D);
            if (r.transactionSuccess()) {
               sender.sendMessage(String.format("You were given %s and now have %s", econ.format(r.amount), econ.format(r.balance)));
            } else {
               sender.sendMessage(String.format("An error occured: %s", r.errorMessage));
            }

            return true;
         } else if (command.getLabel().equals("test-permission")) {
            if (perms.has(player, "example.plugin.awesome")) {
               sender.sendMessage("You are awesome!");
            } else {
               sender.sendMessage("You suck!");
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public static Economy getEconomy() {
      return econ;
   }

   public static Permission getPermissions() {
      return perms;
   }

   public static Chat getChat() {
      return chat;
   }
}
