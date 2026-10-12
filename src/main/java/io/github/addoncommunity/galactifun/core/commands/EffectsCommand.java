package io.github.addoncommunity.galactifun.core.commands;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.addoncommunity.galactifun.Galactifun;
import io.github.addoncommunity.galactifun.api.universe.attributes.atmosphere.AtmosphericEffect;
import io.github.addoncommunity.galactifun.api.worlds.PlanetaryWorld;
import io.github.addoncommunity.galactifun.util.Messages;
import io.github.mooy1.infinitylib.commands.SubCommand;

public final class EffectsCommand extends SubCommand {

    public EffectsCommand() {
        super("effects", "Gets all the effects of the current block (taking in protections)", false);
    }

    @Override
    public void execute(@Nonnull CommandSender commandSender, @Nonnull String[] strings) {
        if (!(commandSender instanceof Player)) return;
        Player p = (Player) commandSender;

        PlanetaryWorld world = Galactifun.worldManager().getWorld(p.getWorld());
        if (world == null) {
            Messages.send(p, ChatColor.RED, "commands.not-in-galactifun-world", "You must be in a Galactifun world to execute this command");
            return;
        }

        for (Map.Entry<AtmosphericEffect, Integer> entry : Galactifun.protectionManager().getEffectsAt(p.getLocation()).entrySet()) {
            Messages.send(p, ChatColor.YELLOW, "commands.effect-level", "Effect: %effect%, Level: %level%",
                    line -> line.replace("%effect%", entry.getKey().name(p))
                            .replace("%level%", String.valueOf(entry.getValue())));
        }
    }

    @Override
    public void complete(@Nonnull CommandSender commandSender, @Nonnull String[] strings, @Nonnull List<String> list) {

    }

}
