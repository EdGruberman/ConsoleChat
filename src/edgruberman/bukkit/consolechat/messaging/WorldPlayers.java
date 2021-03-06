package edgruberman.bukkit.consolechat.messaging;

import java.util.List;
import java.util.logging.Level;

import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * players in a world at message delivery time
 *
 * @author EdGruberman (ed@rjump.com)
 * @version 2.1.0
 */
public class WorldPlayers extends Recipients {

    protected final World world;

    public WorldPlayers(final World world) {
        this.world = world;
    }

    public World getWorld() {
        return this.world;
    }

    @Override
    public Confirmation deliver(final Message message) {
        final List<Player> players = this.world.getPlayers();
        for (final Player player : players)
                player.sendMessage(message.format(player).toString());

        final int count = players.size();
        return new Confirmation(Level.FINE, count, "[WORLD%{1}({2})] {0}", message, WorldPlayers.this.world.getName(), count);
    }

}
