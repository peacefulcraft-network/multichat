package xyz.olivermartin.multichat.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import xyz.olivermartin.multichat.bungee.ChatControl;
import xyz.olivermartin.multichat.bungee.MessageManager;

/**
 * 'Help Me' Command
 * <p>Allows players to request help from all online staff members</p>
 * 
 * @author Oliver Martin (Revilo410)
 *
 */
public class HelpMeCommand extends Command {

	private static String[] aliases = new String[] {};

	public HelpMeCommand() {
		super("helpme", "multichat.chat.helpme", aliases);
	}

	public void execute(CommandSender sender, String[] args) {

		if ( sender instanceof ProxiedPlayer ) {

			if (args.length < 1) {

				MessageManager.sendMessage(sender, "command_helpme_desc");
				MessageManager.sendMessage(sender, "command_helpme_usage");

			} else { 

				String message = "";
				for (String arg : args) {
					message = message + arg + " ";
				}

				sendMessage(sender.getName() + ": " + message);
				MessageManager.sendMessage(sender, "command_helpme_sent");

			}

		} else {
			MessageManager.sendMessage(sender, "command_helpme_only_players");
		}
	}

	public static void sendMessage(String message) {

		message = ChatControl.applyChatRules(message, "helpme");

		for (ProxiedPlayer onlineplayer : ProxyServer.getInstance().getPlayers()) {
			if (onlineplayer.hasPermission("multichat.staff")) {
				MessageManager.sendSpecialMessage(onlineplayer, "command_helpme_format", message);
			}
		}

		System.out.println("\033[31m[MultiChat][HELPME] " + message);

	}
}
