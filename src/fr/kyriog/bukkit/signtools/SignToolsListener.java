package fr.kyriog.bukkit.signtools;

import java.util.logging.Logger;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignToolsListener implements Listener {
	private final Logger logger;
	private final World defaultWorld;

	public SignToolsListener(Logger logger, World defaultWorld) {
		this.logger = logger;
		this.defaultWorld = defaultWorld;
	}

	@EventHandler
	public void placedSignHandler(SignChangeEvent e) {
		StringBuilder log = new StringBuilder();
		log.append('[');
		log.append(e.getBlock().getX());
		log.append(' ');
		log.append(e.getBlock().getY());
		log.append(' ');
		log.append(e.getBlock().getZ());
		if(defaultWorld != e.getBlock().getWorld()) {
			log.append(' ');
			log.append(e.getBlock().getWorld().getName());
		}
		log.append("] <");
		log.append(e.getPlayer().getName());
		log.append("> ");
		for(String line : e.getLines()) {
			log.append(line);
			log.append('%');
		}

		logger.info(log.toString());
	}
}
