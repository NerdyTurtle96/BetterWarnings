package me.nerdyturtle96.betterwarnings;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BetterWarnings extends JavaPlugin 
{
	@Override
	public void onDisable() 
	{
		this.getLogger().info("Disabled.");
	}
	
	@Override
	public void onEnable() 
	{
		this.getLogger().info("Enabled.");
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command chatCommand, String commandLabel, String[] commandArguments) 
	{
		Player commandTarget = Bukkit.getServer().getPlayer(commandArguments[1]);
		
		if (chatCommand.getName().equalsIgnoreCase("warn")) 
		{
			if (commandSender.hasPermission("betterwarnings.command.warn")) 
			{
				if (commandArguments.length < 2) 
				{
					commandSender.sendMessage(ChatColor.RED + "Invalid arguments.");
					
					return true;
				}
				
				if (commandTarget == null) 
				{
					commandSender.sendMessage(ChatColor.RED + "That player couldn't be found.");
					
					return true;
				}
			}
		}
		
		return false;
	}
}
