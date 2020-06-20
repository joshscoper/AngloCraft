package anglocraft.net.PlayerManager;

import anglocraft.net.Main;
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class PlayerManager {
   private final Main main = (Main)Main.getPlugin(Main.class);
   private final Player player;
   private File playerFile;
   private FileConfiguration playerData;

   public PlayerManager(Player player) {
      this.player = player;
   }

   public boolean hasFile() {
      return this.playerFile.exists();
   }

   public FileConfiguration getPlayerData() {
      return this.playerData;
   }

   public void setRPName(String name) {
      this.getPlayerData().set("RP_Name", name);
   }

   public String getRPName() {
      return this.getPlayerData().getString("RP_Name");
   }

   public String getFaction() {
      return this.getPlayerData().getString("Faction");
   }

   public void savePlayerData() {
      String UUID = this.player.getUniqueId().toString();
      this.playerFile = new File(this.main.getDataFolder() + File.separator + "PlayerData", UUID + ".yml");

      try {
         this.getPlayerData().save(this.playerFile);
      } catch (IOException var3) {
         var3.printStackTrace();
      }

   }

   public void createFile() {
      this.playerFile = new File(this.main.getDataFolder() + File.separator + "PlayerData", this.player.getUniqueId().toString() + ".yml");

      try {
         this.playerFile.createNewFile();
      } catch (IOException var2) {
         var2.printStackTrace();
      }

   }

   public void registerPlayer() {
      String UUID = this.player.getUniqueId().toString();
      this.playerFile = new File(this.main.getDataFolder() + File.separator + "PlayerData", UUID + ".yml");

      try {
         this.createFile();
         this.playerData.load(this.playerFile);
         this.playerData.createSection("Name");
         this.playerData.set("Name", this.player.getDisplayName());
         this.playerData.createSection("RP_Name");
         this.playerData.createSection("Faction");
         this.playerData.save(this.playerFile);
      } catch (InvalidConfigurationException | IOException var3) {
         var3.printStackTrace();
      }

   }
}
