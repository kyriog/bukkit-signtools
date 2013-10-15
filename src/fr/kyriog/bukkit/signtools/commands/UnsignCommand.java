package fr.kyriog.bukkit.signtools.commands;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class UnsignCommand implements CommandExecutor {
	private final Server server;
	private final Logger logger;

	public UnsignCommand(Server server, Logger logger) {
		this.server = server;
		this.logger = logger;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof ConsoleCommandSender)) {
			sender.sendMessage("This command can only be run in console.");
			return true;
		}
		if(args.length < 3)
			return false;

		World world;
		if(args.length >= 4)
			world = server.getWorld(args[3]);
		else
			world = server.getWorlds().get(0);

		if(world == null)
			sender.sendMessage("The world can't be found.");
		else {
			int x = Integer.valueOf(args[0]);
			int y = Integer.valueOf(args[1]);
			int z = Integer.valueOf(args[2]);
			Block block = world.getBlockAt(x, y, z);
			switch(block.getType()) {
			case SIGN:
			case SIGN_POST:
			case WALL_SIGN:
				Sign sign = (Sign) block.getState();
				StringBuilder log = new StringBuilder();
				log.append("[Destroying] (");
				log.append(x);
				log.append(",");
				log.append(y);
				log.append(",");
				log.append(z);
				log.append(") in \"");
				log.append(world.getName());
				log.append("\": ");
				for(String line : sign.getLines()) {
					log.append(line);
					log.append('%');
				}

				logger.info(log.toString());
				block.setType(Material.AIR);
				break;
			default:
				sender.sendMessage("Selected block isn't a sign.");
			}
		}
		return true;
	}
}
