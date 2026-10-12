package io.github.addoncommunity.galactifun.base.items.knowledge;

import javax.annotation.Nonnull;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.addoncommunity.galactifun.Galactifun;
import io.github.addoncommunity.galactifun.api.worlds.PlanetaryWorld;
import io.github.addoncommunity.galactifun.core.CoreItemGroup;
import io.github.addoncommunity.galactifun.util.Messages;
import io.github.mooy1.infinitylib.common.Scheduler;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun5.implementation.items.SimpleSlimefunItem;
import io.github.addoncommunity.galactifun.compat.Pdc;

public final class PlanetaryAnalyzer extends SimpleSlimefunItem<BlockUseHandler> {

    public PlanetaryAnalyzer(SlimefunItemStack item, ItemStack[] recipe) {
        super(CoreItemGroup.MACHINES, item, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);
    }

    @Nonnull
    @Override
    public BlockUseHandler getItemHandler() {
        return e -> {
            Player p = e.getPlayer();
            NamespacedKey key = new NamespacedKey("galactifun", "analyzing_" + p.getUniqueId());

            PlanetaryWorld world = Galactifun.worldManager().getWorld(p.getWorld());
            if (world == null) {
                Messages.send(p, ChatColor.RED, "knowledge.must-be-on-planet", "You must be on a planet to use this!");
                return;
            }

            if (Pdc.getBoolean(world.worldStorage(), key)) {
                Messages.send(p, ChatColor.RED, "knowledge.already-analyzing", "Already analyzing!");
                return;
            }

            Messages.send(p, ChatColor.GREEN, "knowledge.analyzing", "Analyzing planet %planet%",
                    line -> line.replace("%planet%", world.name()));
            Pdc.setBoolean(world.worldStorage(), key, true);
            Scheduler.run(30 * 60 * 20, () -> {
                Pdc.setBoolean(world.worldStorage(), key, false);
                KnowledgeLevel.BASIC.set(p, world);
            });
        };
    }

}

