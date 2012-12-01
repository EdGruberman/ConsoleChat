package edgruberman.bukkit.consolechat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import edgruberman.bukkit.consolechat.InputFilter;
import edgruberman.bukkit.consolechat.Main;

public final class Toggle implements CommandExecutor {

    private final InputFilter filter;

    public Toggle(final InputFilter filter) {
        this.filter = filter;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof ConsoleCommandSender)) {
            Main.courier.send(sender, "requires-console", label);
            return true;
        }

        if (this.filter.isEnabled()) { this.filter.disable(); } else { this.filter.enable(); }
        Main.courier.send(sender, ( this.filter.isEnabled() ? "enabled" : "disabled" ));
        return true;
    }

}
