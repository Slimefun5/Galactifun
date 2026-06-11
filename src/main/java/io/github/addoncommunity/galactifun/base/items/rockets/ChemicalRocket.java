package io.github.addoncommunity.galactifun.base.items.rockets;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.inventory.ItemStack;

import io.github.addoncommunity.galactifun.api.items.Rocket;
import io.github.addoncommunity.galactifun.api.universe.attributes.atmosphere.Gas;
import io.github.thebusybiscuit.slimefun5.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.implementation.SlimefunItems;

public final class ChemicalRocket extends Rocket {

    public ChemicalRocket(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int fuelCapacity, int storageCapacity) {
        super(category, item, recipeType, recipe, fuelCapacity, storageCapacity);
    }

    @Override
    protected Map<ItemStack, Double> getAllowedFuels() {
        Map<ItemStack, Double> fuels = new HashMap<>();
        fuels.put(SlimefunItems.OIL_BUCKET.item(), .5);
        fuels.put(Gas.HYDROCARBONS.item().item(), .5);
        fuels.put(SlimefunItems.FUEL_BUCKET.item(), 1.0);
        fuels.put(Gas.HYDROGEN.item().item(), 3.5);
        fuels.put(Gas.AMMONIA.item().item(), 4.0);
        fuels.put(Gas.METHANE.item().item(), 6.0);
        return fuels;
    }

}

