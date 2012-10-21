/*
 * Copyright (C) 2012 BangL <henno.rickowski@googlemail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.bangl.wgpdf;

import com.mewin.WGCustomFlags.WGCustomFlagsPlugin;
import com.mewin.WGCustomFlags.flags.CustomSetFlag;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.EnumFlag;
import de.bangl.wgpdf.listener.PlayerDamageListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author BangL <henno.rickowski@googlemail.com>
 */
public class WGPlayerDamageFlagsPlugin extends JavaPlugin {
    public static final EnumFlag DAMAGE_CAUSE_FLAG = new EnumFlag("damage-cause", DmgCause.class);
    public static final CustomSetFlag ALLOW_DAMAGE_FLAG = new CustomSetFlag("allow-damage", DAMAGE_CAUSE_FLAG);
    public static final CustomSetFlag DENY_DAMAGE_FLAG = new CustomSetFlag("deny-damage", DAMAGE_CAUSE_FLAG);

    private WGCustomFlagsPlugin pluginWGCustomFlags;
    private WorldGuardPlugin pluginWorldGuard;
    private PlayerDamageListener listenerplayerDamage;

    public WorldGuardPlugin getWGP() {
        return pluginWorldGuard;
    }

    public WGCustomFlagsPlugin getWGCFP() {
        return pluginWGCustomFlags;
    }

    @Override
    public void onEnable() {
        pluginWorldGuard = Utils.getWorldGuard(this);
        pluginWGCustomFlags = Utils.getWGCustomFlags(this);
        listenerplayerDamage = new PlayerDamageListener(this);

        pluginWGCustomFlags.addCustomFlag(ALLOW_DAMAGE_FLAG);
        pluginWGCustomFlags.addCustomFlag(DENY_DAMAGE_FLAG);
    }

    @Override
    public void onDisable() {
        pluginWGCustomFlags = null;
        pluginWorldGuard = null;
        listenerplayerDamage = null;
    }
}
