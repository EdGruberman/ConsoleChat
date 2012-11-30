package edgruberman.bukkit.consolechat;

import java.text.MessageFormat;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.Plugin;

public final class InputFilter implements Listener {

    private final Plugin plugin;
    private final String chat;

    private boolean enabled = false;

    InputFilter(final Plugin plugin, final String chat) {
        this.plugin = plugin;
        this.chat = chat;
    }

    @EventHandler
    public void onServerCommand(final ServerCommandEvent event) {
        String command = event.getCommand();
        if (command.startsWith("/")) {
            command = command.substring(1);
        } else {
            command = MessageFormat.format(this.chat, command);
        }
        event.setCommand(command);
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void enable() {
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
        this.enabled = true;
    }

    public void disable() {
        HandlerList.unregisterAll(this);
        this.enabled = false;
    }

}
