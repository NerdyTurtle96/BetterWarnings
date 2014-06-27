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
		this.getConfig();
		
		this.getLogger().info("Enabled.");
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command chatCommand, String commandLabel, String[] commandArguments) 
	{
		Player commandTarget = Bukkit.getServer().getPlayer(commandArguments[0]);
		
		if (chatCommand.getName().equalsIgnoreCase("warn")) 
		{
			String warnMessage = "";
			
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
				
				for (int i = 1; i < commandArguments.length; i++) 
				{
					warnMessage = commandArguments[1];
				}
				
				Object warnCount = this.getConfig().get(commandTarget.getName());
				
				if (warnCount == null) 
				{
					commandTarget.sendMessage(ChatColor.DARK_GREEN + "You've been warned by " + ChatColor.GREEN + commandSender.getName() + ChatColor.DARK_GREEN + " for " + ChatColor.GREEN + warnMessage + ChatColor.DARK_GREEN + ".");
					
					this.getConfig().set(commandTarget.getName(), 1);
					
					this.saveConfig();
					
					return true;
				}
				
				int l = Integer.parseInt(warnCount.toString());
				
				if (l == 1) 
				{
					commandTarget.kickPlayer(ChatColor.DARK_GREEN + "You've been warned by " + ChatColor.GREEN + commandSender.getName() + ChatColor.DARK_GREEN + " for " + ChatColor.GREEN + warnMessage + ChatColor.DARK_GREEN + ".");
					
					this.getConfig().set(commandTarget.getName(), 2);
					
					this.saveConfig();
					
					return true;
				}
				
				if (l == 2) 
				{
					commandTarget.kickPlayer(ChatColor.DARK_GREEN + "You've been warned by " + ChatColor.GREEN + commandSender.getName() + ChatColor.DARK_GREEN + " for " + ChatColor.GREEN + warnMessage + ChatColor.DARK_GREEN + ".");
					
					commandTarget.setBanned(true);
					
					this.getConfig().set(commandTarget.getName(), 3);
					
					this.saveConfig();
					
					return true;
				}
			}
		}
		
		return false;
	}
}
