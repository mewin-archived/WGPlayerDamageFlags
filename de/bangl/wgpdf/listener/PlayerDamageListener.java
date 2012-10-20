package de.bangl.wgpdf.listener;

import de.bangl.wgpdf.Utils;
import de.bangl.wgpdf.WGPlayerDamageFlagsPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 *
 * @author BangL
 */
public class PlayerDamageListener implements Listener {
    WGPlayerDamageFlagsPlugin plugin;

    public PlayerDamageListener(WGPlayerDamageFlagsPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        // Only handle if player and dmg cause is not null.
        if(event.getEntity() instanceof Player
                && event.getCause() != null) {
            // Cancel if dmg cause is denied here.
            if (!Utils.dmgAllowedAtLocation(plugin.getWGP(), event.getCause(), event.getEntity().getLocation())) {
                event.setCancelled(true);
            }
        }
    }
}
