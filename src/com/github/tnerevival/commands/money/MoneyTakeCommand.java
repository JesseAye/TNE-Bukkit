package com.github.tnerevival.commands.money;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.github.tnerevival.TNE;
import com.github.tnerevival.commands.TNECommand;
import com.github.tnerevival.core.Message;
import com.github.tnerevival.utils.AccountUtils;
import com.github.tnerevival.utils.MISCUtils;

public class MoneyTakeCommand extends TNECommand {

	public MoneyTakeCommand(TNE plugin) {
		super(plugin);
	}

	@Override
	public String getName() {
		return "take";
	}

	@Override
	public String[] getAliases() {
		return new String[0];
	}

	@Override
	public String getNode() {
		return "tne.money.take";
	}

	@Override
	public boolean console() {
		return true;
	}
	
	@Override
	public boolean execute(CommandSender sender, String[] arguments) {
		if(arguments.length == 2) {
			if(Double.valueOf(arguments[1]) < 0) {
				sender.sendMessage(new Message("Messages.Money.Negative").translate());
				return false;
			}
			if(getPlayer(sender, arguments[0]) != null && AccountUtils.takeMoney(MISCUtils.getID(getPlayer(sender, arguments[0])), MISCUtils.getID(getPlayer(sender)), AccountUtils.round(Double.valueOf(arguments[1])))) {
				Message took = new Message("Messages.Money.Took");
				took.addVariable("$amount", MISCUtils.formatBalance(getPlayer(sender, arguments[0]).getWorld().getName(), AccountUtils.round(Double.valueOf(arguments[1]))));
				took.addVariable("$player", arguments[0]);
				sender.sendMessage(took.translate());
				return true;
			}
		} else {
			help(sender);
		}
		return false;
	}

	@Override
	public void help(CommandSender sender) {
		sender.sendMessage(ChatColor.GOLD + "/money take <player> <amount> - make some of <player>'s money vanish into thin air");
	}
	
}