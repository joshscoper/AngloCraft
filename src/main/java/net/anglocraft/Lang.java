package net.anglocraft;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

public enum Lang {
   PREFIX("admin-prefix", "&6&lAngloCraft &e>>"),
   DEBUG("debug-prefix", "&e&lDEBUG &f>>"),
   WELCOME_MESSAGE("welcome-message", "&aWelcome &e%player% &ato &6&lAngloCraft!"),
   INVALID_ARGS("invalid-args", "&cInvalid arguments!"),
   PLAYER_ONLY("player-only", "&cSorry but that can only be run by a player!"),
   MUST_BE_NUMBER("must-be-number", "&cYou need to specify a number, not a word."),
   NO_PERMS("no-permissions", "&cYou don''t have permission for that!"),
   JOIN("join-message", "&7[&a+&7] &f%player%"),
   CHOOSE_CLASS("choose-class", "&cYou must choose a class!"),
   QUIT("quit-message", "&7[&c-&7] &f%player%"),
   DANE_JOIN("dane-join", "&eYou have joined the &6&lDanes&e,"),
   SAXON_JOIN("saxon-join", "&eYou have joined the &6&lSaxons&e,"),
   SKIN_SELECT_LOADING("skin-select-loading","&eSkin selector loading please be patient..."),
   SKIN_SELECT("skin-select", "&eTalk to the %guide% &eto continue.");

   private String path;
   private String def;
   private static YamlConfiguration LANG;

   private Lang(String path, String start) {
      this.path = path;
      this.def = start;
   }

   public static void setFile(YamlConfiguration config) {
      LANG = config;
   }

   public String toString() {
      return this == PREFIX ? ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, this.def)) + " " : ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, this.def));
   }

   public String getDefault() {
      return this.def;
   }

   public String getPath() {
      return this.path;
   }
}
