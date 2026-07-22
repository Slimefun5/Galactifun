package io.github.addoncommunity.galactifun.core;


import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import io.github.addoncommunity.galactifun.util.MaterialCompat;

import io.github.addoncommunity.galactifun.Galactifun;
import io.github.addoncommunity.galactifun.base.GalactifunHead;
import io.github.addoncommunity.galactifun.core.categories.AssemblyItemGroup;
import io.github.thebusybiscuit.slimefun5.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun5.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun5.core.guide.widgets.GuideWidget;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun5.libraries.dough.items.CustomItemStack;

/**
 * Slimefun item categories
 *
 * @author Mooy1
 */
public final class CoreItemGroup {

    private CoreItemGroup() {}

    public static final ItemGroup EQUIPMENT = new ItemGroup(
            Galactifun.createKey("equipment"), CustomItemStack.create(MaterialCompat.safe(XMaterial.IRON_HELMET), "&fEquipment")
    ).setTheme("armor");
    public static final ItemGroup ITEMS = new ItemGroup(
            Galactifun.createKey("items"), CustomItemStack.create(GalactifunHead.ROCKET, "&fGalactifun")
    ).setTheme("misc");
    public static final ItemGroup COMPONENTS = new ItemGroup(
            Galactifun.createKey("components"), CustomItemStack.create(MaterialCompat.safe(XMaterial.IRON_INGOT), "&fGalactifun Components")
    ).setTheme("resources");
    public static final ItemGroup MACHINES = new ItemGroup(
            Galactifun.createKey("machines"), CustomItemStack.create(MaterialCompat.safe(XMaterial.REDSTONE_LAMP), "&fGalactifun Machines")
    ).setTheme("machines");
    public static final ItemGroup BLOCKS = new ItemGroup(
            Galactifun.createKey("blocks"), CustomItemStack.create(MaterialCompat.safe(XMaterial.COBBLESTONE), "&fGalactifun Blocks")
    ).setTheme("misc");
    public static final ItemGroup RELICS = new ItemGroup(
            Galactifun.createKey("relics"), CustomItemStack.create(MaterialCompat.safe(XMaterial.CHISELED_POLISHED_BLACKSTONE), "&fGalactifun Relics")
    ).setTheme("misc");

    // Recipe browser opened directly by the Assembly Table machine, not a guide entry
    public static final AssemblyItemGroup ASSEMBLY_CATEGORY = new AssemblyItemGroup(
            Galactifun.createKey("assembly_flex"),
            CustomItemStack.create(MaterialCompat.safe(XMaterial.SMITHING_TABLE), "&fAssembly Table Recipes"));

    public static void setup(Galactifun galactifun) {
        EQUIPMENT.register(galactifun);
        ITEMS.register(galactifun);
        COMPONENTS.register(galactifun);
        MACHINES.register(galactifun);
        BLOCKS.register(galactifun);
        RELICS.register(galactifun);

        // The universe map is a navigable screen, not an item list, so expose it as a guide widget
        Slimefun.getGuideWidgets().register(new GuideWidget(
                "galactifun_universe", "&bThe Universe", XMaterial.END_STONE, 10,
                (player, profile) -> new WorldSelector((p, slot, item, action) -> {
                    profile.getGuideHistory().goBack(Slimefun.getRegistry().getSlimefunGuide(SlimefunGuideMode.SURVIVAL_MODE));
                    return false;
                }).open(player)));
    }

}
