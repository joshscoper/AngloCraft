package net.anglocraft.player;

import net.anglocraft.Main;
import java.io.File;
import java.io.IOException;

import net.anglocraft.commands.Name;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PlayerManager {
   private Player player;
   private Main main = Main.getPlugin(Main.class);
   private File playerFile = new File(main.getDataFolder() + File.separator + "PlayerData");
   private File playerData;
   private YamlConfiguration playerConfig;


   public PlayerManager(Player player) {
      this.player = player;
      this.playerData = new File(playerFile, player.getUniqueId().toString() + ".yml");
      this.playerConfig = YamlConfiguration.loadConfiguration(playerData);
   }

   public void createFile() {
      //Creates Directory
      if (!playerFile.exists()){
         playerFile.mkdirs();
      }
      //Creates Player Files
      if (!playerData.exists()){
         try {
            playerData.createNewFile();
            playerConfig.createSection("Faction");
            playerConfig.createSection("Village");
            playerConfig.createSection("Name");
            playerConfig.createSection("RPName");
            playerConfig.set("Name", player.getName());
            playerConfig.save(playerData);
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   //Save File Manually
   public void saveFile(){
      try{
         playerConfig.save(playerData);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }


   //File Check
   public boolean hasFile(){
      if (playerData.exists()) {
         return true;
      } else {
         return false;
      }
   }

   //Faction
   public void setFaction(String faction) {
      try {
         playerConfig.load(playerData);
         playerConfig.set("Faction", faction);
         playerConfig.save(playerData);
      } catch (IOException | InvalidConfigurationException e){
         e.printStackTrace();
      }
   }

   public String getFaction() {
      try {
         playerConfig.load(playerData);
         String faction = playerConfig.getString("Faction");
         return faction;
      }catch (IOException | InvalidConfigurationException e){
         e.printStackTrace();
      }
      return getFaction();
   }

   public Boolean hasFaction(){
      if (playerConfig.get("Faction") != null){
         return true;
      } else {
         return false;
      }
   }

   //Village
   public void setVillage(String village) {
      try{
         playerConfig.load(playerData);
         playerConfig.set("Village", village);
         playerConfig.save(playerData);
      } catch (IOException | InvalidConfigurationException e) {
         e.printStackTrace();
      }
   }

   public String getVillage() {
      try {
         playerConfig.load(playerData);
         String village = playerConfig.getString("Village");
         return village;
      }catch (IOException | InvalidConfigurationException e){
         e.printStackTrace();
      }
      return getVillage();
   }

   public Boolean hasVillage(){
      if (playerConfig.get("Village") != null){
         return true;
      } else {
         return false;
      }
   }

   //RPName
   public void setRPName(String name){
      try {
         playerConfig.load(playerData);
         playerConfig.set("RPName", name);
         playerConfig.save(playerData);
      } catch (IOException | InvalidConfigurationException e) {
         e.printStackTrace();
      }
   }

   public String getRPName(){
      try {
         playerConfig.load(playerData);
         String name = playerConfig.getString("RPName");
         return name;
      }catch (IOException | InvalidConfigurationException e){
         e.printStackTrace();
      }
      return getRPName();
   }

   public boolean hasRPName() {
      if (playerConfig.get("RPName") != null){
         return true;
      } else {
         return false;
      }
   }
}
