package io.github.addoncommunity.galactifun.core.commands;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Nonnull;

import io.github.addoncommunity.galactifun.Galactifun;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.addoncommunity.galactifun.util.Messages;
import io.github.addoncommunity.galactifun.util.Util;
import io.github.mooy1.infinitylib.commands.SubCommand;
import io.github.thebusybiscuit.slimefun5.libraries.dough.blocks.BlockPosition;

public final class SealedCommand extends SubCommand {

    public SealedCommand() {
        super("sealed", "Checks if the area is sealed", false);
    }

    @Override
    public void execute(@Nonnull CommandSender commandSender, @Nonnull String[] strings) {
        if (!(commandSender instanceof Player)) return;
        Player p = (Player) commandSender;
        if (strings.length != 1) {
            Messages.send(p, ChatColor.RED, "sealed.usage", "Usage: /galactifun sealed <range>");
            return;
        }

        int maxRange = Galactifun.instance().getConfig().getInt("other.sealed-command-max-range");
        int range;

        try {
            range = Integer.parseInt(strings[0]);
        } catch (NumberFormatException e) {
            sendRangeError(p, maxRange);
            return;
        }

        if (range < 1 || range > maxRange) {
            sendRangeError(p, maxRange);
            return;
        }

        double time = System.nanoTime();
        Optional<Set<BlockPosition>> filled = Util.floodFill(p.getLocation(), range);

        if (filled.isPresent()) {
            Messages.send(p, "sealed.sealed", "Sealed");
        } else {
            Messages.send(p, "sealed.not-sealed", "Not sealed");
        }

        p.sendMessage(Util.timeSince(time));
    }

    private static void sendRangeError(@Nonnull Player p, int maxRange) {
        Messages.send(p, ChatColor.RED, "sealed.range-out-of-bounds", "Range must be an integer between 1 and %max%",
                line -> line.replace("%max%", String.valueOf(maxRange)));
    }

    @Override
    public void complete(@Nonnull CommandSender commandSender, @Nonnull String[] strings, @Nonnull List<String> list) {

    }

}

