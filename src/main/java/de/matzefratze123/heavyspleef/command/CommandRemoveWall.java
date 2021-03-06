/**
 *   HeavySpleef - Advanced spleef plugin for bukkit
 *   
 *   Copyright (C) 2013 matzefratze123
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package de.matzefratze123.heavyspleef.command;

import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.matzefratze123.heavyspleef.command.handler.HSCommand;
import de.matzefratze123.heavyspleef.command.handler.Help;
import de.matzefratze123.heavyspleef.command.handler.UserType;
import de.matzefratze123.heavyspleef.command.handler.UserType.Type;
import de.matzefratze123.heavyspleef.core.GameManager;
import de.matzefratze123.heavyspleef.core.Game;
import de.matzefratze123.heavyspleef.core.SignWall;
import de.matzefratze123.heavyspleef.util.Permissions;

@UserType(Type.ADMIN)
public class CommandRemoveWall extends HSCommand {

	public CommandRemoveWall() {
		setOnlyIngame(true);
		setPermission(Permissions.REMOVE_WALL);
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		Player p = (Player)sender;
		
		Block blockLocation = p.getTargetBlock(null, 50);
		for (Game game : GameManager.getGames()) {
			for (SignWall wall : game.getComponents().getSignWalls()) {
				if (!wall.contains(blockLocation.getLocation()))
					continue;
				game.getComponents().removeSignWall(wall.getId());
				p.sendMessage(_("wallRemoved"));
				return;
			}
		}
			
		p.sendMessage(_("notLookingAtWall"));
	}

	@Override
	public Help getHelp(Help help) {
		help.setUsage("/spleef removewall");
		help.addHelp("Removes the wall on which you're currently looking");
		
		return help;
	}
	
}
