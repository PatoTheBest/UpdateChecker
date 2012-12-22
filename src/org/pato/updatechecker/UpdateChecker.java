
package org.pato.updatechecker;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class UpdateChecker extends JavaPlugin{

    public static final Logger logger = Logger.getLogger("Minecraft");

    protected Logger log;
    protected UpdateChecker1 updateChecker1;
    
    
    
    @Override
    public void onDisable() {
      PluginDescriptionFile pdfFile = this.getDescription();
      org.pato.updatechecker.UpdateChecker.logger.log(Level.INFO, "[{0}] Has Been Disabled!", pdfFile.getName());
    }
    
    @Override
    public void onEnable() {
      this.log = this.getLogger();
      PluginDescriptionFile pdfFile = this.getDescription();
      this.logger.log(Level.INFO, "[{0}] Version {1} Has Been Enabled!", new Object[]{pdfFile.getName(), pdfFile.getVersion()});
      this.updateChecker1 = new UpdateChecker1(this, "http://dev.bukkit.org/server-mods/serverinformation/files.rss");
      if (this.updateChecker1.updateNeeded()){
          this.log.info("============================================");
          this.log.info("A New Version Is Available:" + this.updateChecker1.getVersion());
          this.log.info("Get It From: " + this.updateChecker1.getLink());
          this.log.info("============================================");
      }
      getConfig().options().copyDefaults(true);
      saveConfig();
    }
}
