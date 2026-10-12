package io.github.addoncommunity.galactifun.util;

import java.util.List;
import java.util.function.UnaryOperator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.addoncommunity.galactifun.Galactifun;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;

/**
 * Resolves this addon's chat text for one viewer, from {@code languages/<lang>/messages.yml}.
 *
 * @implNote The {@link ChatColor} deliberately stays at the call site rather than moving into the
 *           translated value: a translator never edits a colour code they cannot see the effect of.
 *           A console recipient is handled by the service, which applies the same substitutions to
 *           the English literal and strips the colour.
 */
public final class Messages {

    private Messages() {}

    @Nonnull
    public static String get(@Nullable Player player, @Nonnull String key, @Nonnull String english) {
        return Slimefun.getAddonMessageService().getMessage(player, Galactifun.instance(), key, english);
    }

    @Nonnull
    public static List<String> getList(@Nullable Player player, @Nonnull String key, @Nonnull List<String> english) {
        return Slimefun.getAddonMessageService().getMessages(player, Galactifun.instance(), key, english);
    }

    public static void send(@Nonnull CommandSender recipient, @Nonnull String key, @Nonnull String english) {
        Slimefun.getAddonMessageService().sendMessage(recipient, Galactifun.instance(), key, english);
    }

    public static void send(@Nonnull CommandSender recipient, @Nonnull ChatColor colour, @Nonnull String key, @Nonnull String english) {
        send(recipient, colour, key, english, UnaryOperator.identity());
    }

    public static void send(@Nonnull CommandSender recipient, @Nonnull ChatColor colour, @Nonnull String key, @Nonnull String english, @Nonnull UnaryOperator<String> placeholders) {
        Slimefun.getAddonMessageService().sendMessage(recipient, Galactifun.instance(), key, english,
                line -> colour + placeholders.apply(line));
    }

    /**
     * The viewer's own word for a yes/no answer, so a prompt the player must answer in chat can be
     * answered in the language the prompt was asked in.
     *
     * @implNote The English word stays accepted alongside it: a server running a translated prompt
     *           still has players and scripts that type {@code yes}.
     */
    public static boolean isAffirmative(@Nullable Player player, @Nonnull String answer) {
        return answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase(get(player, "world.affirmative", "yes"));
    }
}
