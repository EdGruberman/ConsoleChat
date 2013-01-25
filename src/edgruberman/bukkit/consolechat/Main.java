package edgruberman.bukkit.consolechat;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import edgruberman.bukkit.consolechat.commands.Reload;
import edgruberman.bukkit.consolechat.commands.Toggle;
import edgruberman.bukkit.consolechat.messaging.ConfigurationCourier;
import edgruberman.bukkit.consolechat.util.CustomPlugin;

public final class Main extends CustomPlugin implements Listener {

    public static ConfigurationCourier courier;

    @Override
    public void onLoad() {
        this.putConfigMinimum("1.1.0");
        this.putConfigMinimum("language.yml", "1.1.2");
    }

    @Override
    public void onEnable() {
        this.reloadConfig();
        Main.courier = ConfigurationCourier.Factory.create(this).setBase(this.loadConfig("language.yml")).setFormatCode("format-code").build();

        final InputFilter filter = new InputFilter(this, this.getConfig().getString("format-code").charAt(0), this.getConfig().getString("chat"));
        if (this.getConfig().getBoolean("default-enable")) {
            filter.enable();
            Main.courier.send(Bukkit.getConsoleSender(), "enabled");
        }

        this.getCommand("consolechat:toggle").setExecutor(new Toggle(filter));
        this.getCommand("consolechat:reload").setExecutor(new Reload(this));
    }

    @Override
    public void onDisable() {
        Main.courier = null;
    }

}
