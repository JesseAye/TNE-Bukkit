package net.tnemc.core.commands.admin;

import net.tnemc.core.TNE;
import net.tnemc.core.commands.TNECommand;
import net.tnemc.core.common.EconomyManager;
import net.tnemc.core.common.api.IDFinder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.sql.SQLException;
import java.util.UUID;

/**
 * The New Economy Minecraft Server Plugin
 *
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/ or send a letter to
 * Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
 * Created by Daniel on 2/8/2018.
 */
public class AdminResetCommand extends TNECommand {

  public AdminResetCommand(TNE plugin) {
    super(plugin);
  }

  @Override
  public String getName() {
    return "reset";
  }

  @Override
  public String[] getAliases() {
    return new String[0];
  }

  @Override
  public String getNode() {
    return "tne.admin.reset";
  }

  @Override
  public boolean console() {
    return true;
  }

  @Override
  public String getHelp() {
    return "Messages.Commands.Admin.Reset";
  }

  @Override
  public boolean execute(CommandSender sender, String command, String[] arguments) {
    try {
      final UUID id = IDFinder.getID(sender);
      if(EconomyManager.reset.contains(id)) {
        TNE.saveManager().getTNEManager().getTNEProvider().delete(TNE.instance().currentSaveVersion);
        sender.sendMessage("All data has been reset.");
      } else {
        EconomyManager.reset.add(id);
        sender.sendMessage(ChatColor.RED + "Are you sure you wish to do this? Have you done /tne extract first? This is not reversible. Type /eco reset again to confirm.");
        Bukkit.getScheduler().runTaskLaterAsynchronously(TNE.instance(), ()->{
          if(EconomyManager.reset.contains(id)) {
            EconomyManager.reset.remove(id);
          }
        }, 600);
      }
    } catch (SQLException e) {
      TNE.debug(e);
    }
    return true;
  }
}