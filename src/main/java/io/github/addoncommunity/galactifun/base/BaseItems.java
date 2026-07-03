package io.github.addoncommunity.galactifun.base;


import org.bukkit.Material;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import io.github.addoncommunity.galactifun.util.MaterialCompat;
import org.bukkit.inventory.ItemStack;

import io.github.addoncommunity.galactifun.Galactifun;
import io.github.addoncommunity.galactifun.api.items.Relic;
import io.github.addoncommunity.galactifun.api.items.spacesuit.SpaceSuit;
import io.github.addoncommunity.galactifun.api.items.spacesuit.SpaceSuitHelmet;
import io.github.addoncommunity.galactifun.api.items.spacesuit.SpaceSuitStat;
import io.github.addoncommunity.galactifun.api.items.spacesuit.SpaceSuitUpgrade;
import io.github.addoncommunity.galactifun.api.universe.attributes.atmosphere.Gas;
import io.github.addoncommunity.galactifun.api.worlds.AlienWorld;
import io.github.addoncommunity.galactifun.base.items.AssemblyTable;
import io.github.addoncommunity.galactifun.base.items.AtmosphericHarvester;
import io.github.addoncommunity.galactifun.base.items.AutomaticDoor;
import io.github.addoncommunity.galactifun.base.items.CircuitPress;
import io.github.addoncommunity.galactifun.base.items.DiamondAnvil;
import io.github.addoncommunity.galactifun.base.items.Electrolyzer;
import io.github.addoncommunity.galactifun.base.items.FusionReactor;
import io.github.addoncommunity.galactifun.base.items.LaunchPadCore;
import io.github.addoncommunity.galactifun.base.items.LaunchPadFloor;
import io.github.addoncommunity.galactifun.base.items.OxygenFiller;
import io.github.addoncommunity.galactifun.base.items.SpaceSuitUpgrader;
import io.github.addoncommunity.galactifun.base.items.StargateController;
import io.github.addoncommunity.galactifun.base.items.StargateRing;
import io.github.addoncommunity.galactifun.base.items.TechnologicalSalvager;
import io.github.addoncommunity.galactifun.base.items.knowledge.Observatory;
import io.github.addoncommunity.galactifun.base.items.knowledge.PlanetaryAnalyzer;
import io.github.addoncommunity.galactifun.base.items.protection.CoolingUnit;
import io.github.addoncommunity.galactifun.base.items.protection.ForcefieldGenerator;
import io.github.addoncommunity.galactifun.base.items.protection.IonDisperser;
import io.github.addoncommunity.galactifun.base.items.protection.OxygenSealer;
import io.github.addoncommunity.galactifun.base.items.protection.SpaceHeater;
import io.github.addoncommunity.galactifun.base.items.rockets.ChemicalRocket;
import io.github.addoncommunity.galactifun.base.items.rockets.IonRocket;
import io.github.addoncommunity.galactifun.core.CoreItemGroup;
import io.github.mooy1.infinitylib.machines.MachineBlock;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun5.utils.HeadTexture;

/**
 * Holds the base machines and setup
 */
public final class BaseItems {

    private BaseItems() {}

    //<editor-fold desc="Space Suits, Oxygen, Upgrades" defaultstate="collapsed">
    public static final SlimefunItemStack OXYGEN_FILLER = new SlimefunItemStack(
            "OXYGEN_FILLER",
            new ItemStack(MaterialCompat.safe(XMaterial.QUARTZ_BLOCK))
    );
    public static final SlimefunItemStack SPACE_SUIT_UPGRADER = new SlimefunItemStack(
            "SPACE_SUIT_UPGRADER",
            new ItemStack(MaterialCompat.safe(XMaterial.IRON_BLOCK))
    );
    public static final SlimefunItemStack HEAT_RESISTANCE_UPGRADE = new SlimefunItemStack(
            "HEAT_RESISTANCE_UPGRADE",
            MaterialCompat.safe(XMaterial.IRON_BLOCK)
    );
    public static final SlimefunItemStack COLD_RESISTANCE_UPGRADE = new SlimefunItemStack(
            "COLD_RESISTANCE_UPGRADE",
            MaterialCompat.safe(XMaterial.IRON_BLOCK)
    );
    public static final SlimefunItemStack RADIATION_RESISTANCE_UPGRADE = new SlimefunItemStack(
            "RADIATION_RESISTANCE_UPGRADE",
            MaterialCompat.safe(XMaterial.IRON_BLOCK)
    );
    public static final SlimefunItemStack SPACE_SUIT_HELMET = new SlimefunItemStack(
            "SPACE_SUIT_HELMET",
            MaterialCompat.safe(XMaterial.GLASS)
    );
    // NOTE: kept as name/lore constructor - the trailing SpaceSuit.oxygenLore(0, 3600) line is a functional
    // marker that SpaceSuit#setOxygen finds (by prefix) and live-rewrites as the suit's oxygen changes;
    // dropping it would remove the only lore line the live-update code can match against.
    public static final SlimefunItemStack SPACE_SUIT_CHEST = new SlimefunItemStack(
            "SPACE_SUIT_CHEST",
            MaterialCompat.safe(XMaterial.IRON_CHESTPLATE),
            "&fSpace Suit Chest",
            "",
            "&7A basic space suit chest",
            "",
            SpaceSuit.oxygenLore(0, 3600)
    );
    public static final SlimefunItemStack SPACE_SUIT_PANTS = new SlimefunItemStack(
            "SPACE_SUIT_PANTS",
            MaterialCompat.safe(XMaterial.IRON_LEGGINGS)
    );
    public static final SlimefunItemStack SPACE_SUIT_BOOTS = new SlimefunItemStack(
            "SPACE_SUIT_BOOTS",
            MaterialCompat.safe(XMaterial.IRON_BOOTS)
    );
    //</editor-fold>
    //<editor-fold desc="Random Stuff" defaultstate="collapsed">
    public static final SlimefunItemStack LAUNCH_PAD_CORE = new SlimefunItemStack(
            "LAUNCH_PAD_CORE",
            MaterialCompat.safe(XMaterial.STONE)
    );

    public static final SlimefunItemStack LAUNCH_PAD_FLOOR = new SlimefunItemStack(
            "LAUNCH_PAD_FLOOR",
            MaterialCompat.safe(XMaterial.STONE_SLAB)
    );

    public static final SlimefunItemStack CIRCUIT_PRESS = new SlimefunItemStack(
            "CIRCUIT_PRESS",
            MaterialCompat.safe(XMaterial.PISTON)
    );
    public static final SlimefunItemStack ASSEMBLY_TABLE = new SlimefunItemStack(
            "ASSEMBLY_TABLE",
            MaterialCompat.safe(XMaterial.SMITHING_TABLE)
    );
    public static final SlimefunItemStack STARGATE_RING = new SlimefunItemStack(
            "STARGATE_RING",
            MaterialCompat.safe(XMaterial.QUARTZ_BLOCK)
    );
    public static final SlimefunItemStack STARGATE_CONTROLLER = new SlimefunItemStack(
            "STARGATE_CONTROLLER",
            MaterialCompat.safe(XMaterial.CHISELED_QUARTZ_BLOCK)
    );
    public static final SlimefunItemStack OBSERVATORY = new SlimefunItemStack(
            "OBSERVATORY",
            MaterialCompat.safe(XMaterial.GLASS)
    );
    public static final SlimefunItemStack PLANETARY_ANALYZER = new SlimefunItemStack(
            "PLANETARY_ANALYZER",
            MaterialCompat.safe(XMaterial.SEA_LANTERN)
    );
    public static final SlimefunItemStack DIAMOND_ANVIL = new SlimefunItemStack(
            "DIAMOND_ANVIL",
            MaterialCompat.safe(XMaterial.PISTON)
    );
    public static final SlimefunItemStack FUSION_REACTOR = new SlimefunItemStack(
            "FUSION_REACTOR",
            HeadTexture.NUCLEAR_REACTOR.getAsItemStack()
    );
    public static final SlimefunItemStack ATMOSPHERIC_HARVESTER = new SlimefunItemStack(
            "ATMOSPHERIC_HARVESTER",
            GalactifunHead.ATMOSPHERIC_HARVESTER
    );
    public static final SlimefunItemStack CHEMICAL_REACTOR = new SlimefunItemStack(
            "CHEMICAL_REACTOR",
            MaterialCompat.safe(XMaterial.SEA_LANTERN)
    );
    public static final SlimefunItemStack ELECTROLYZER = new SlimefunItemStack(
            "ELECTROLYZER",
            MaterialCompat.safe(XMaterial.LANTERN)
    );
    //</editor-fold>
    //<editor-fold desc="Protecting Blocks" defaultstate="collapsed">
    public static final SlimefunItemStack COOLING_UNIT_1 = new SlimefunItemStack(
            "COOLING_UNIT_1",
            HeadTexture.COOLING_UNIT.getTexture()
    );
    public static final SlimefunItemStack COOLING_UNIT_2 = new SlimefunItemStack(
            "COOLING_UNIT_2",
            HeadTexture.COOLING_UNIT.getTexture()
    );
    public static final SlimefunItemStack COOLING_UNIT_3 = new SlimefunItemStack(
            "COOLING_UNIT_3",
            HeadTexture.COOLING_UNIT.getTexture()
    );
    public static final SlimefunItemStack SPACE_HEATER_1 = new SlimefunItemStack(
            "SPACE_HEATER_1",
            MaterialCompat.safe(XMaterial.SHROOMLIGHT)
    );
    public static final SlimefunItemStack SPACE_HEATER_2 = new SlimefunItemStack(
            "SPACE_HEATER_2",
            MaterialCompat.safe(XMaterial.SHROOMLIGHT)
    );
    public static final SlimefunItemStack SPACE_HEATER_3 = new SlimefunItemStack(
            "SPACE_HEATER_3",
            MaterialCompat.safe(XMaterial.SHROOMLIGHT)
    );
    public static final SlimefunItemStack ION_DISPERSER_1 = new SlimefunItemStack(
            "ION_DISPERSER_1",
            MaterialCompat.safe(XMaterial.PRISMARINE)
    );
    public static final SlimefunItemStack ION_DISPERSER_2 = new SlimefunItemStack(
            "ION_DISPERSER_2",
            MaterialCompat.safe(XMaterial.PRISMARINE)
    );
    public static final SlimefunItemStack OXYGEN_SEALER = new SlimefunItemStack(
            "OXYGEN_SEALER",
            MaterialCompat.safe(XMaterial.FURNACE)
    );
    public static final SlimefunItemStack LANDING_HATCH = new SlimefunItemStack(
            "LANDING_HATCH",
            MaterialCompat.safe(XMaterial.IRON_TRAPDOOR)
    );
    public static final SlimefunItemStack SUPER_FAN = new SlimefunItemStack(
            "SUPER_FAN",
            MaterialCompat.safe(XMaterial.WHITE_WOOL)
    );
    public static final SlimefunItemStack AUTOMATIC_DOOR = new SlimefunItemStack(
            "AUTOMATIC_DOOR",
            MaterialCompat.safe(XMaterial.OBSERVER)
    );
    public static final SlimefunItemStack ENVIRONMENTAL_FORCEFIELD_GENERATOR = new SlimefunItemStack(
            "ENVIRONMENTAL_FORCEFIELD_GENERATOR",
            MaterialCompat.safe(XMaterial.DISPENSER)
    );
    //</editor-fold>
    //<editor-fold desc="Relics" defaultstate="collapsed">
    public static final SlimefunItemStack ENGINE_RELIC = new SlimefunItemStack(
            "ENGINE_RELIC",
            MaterialCompat.safe(XMaterial.SEA_LANTERN)
    );
    public static final SlimefunItemStack REACTOR_RELIC = new SlimefunItemStack(
            "REACTOR_RELIC",
            MaterialCompat.safe(XMaterial.BEACON)
    );
    public static final SlimefunItemStack COMPUTER_RELIC = new SlimefunItemStack(
            "COMPUTER_RELIC",
            GalactifunHead.CORE
    );
    public static final SlimefunItemStack BROKEN_SOLAR_PANEL_RELIC = new SlimefunItemStack(
            "BROKEN_SOLAR_PANEL_RELIC",
            MaterialCompat.safe(XMaterial.DAYLIGHT_DETECTOR)
    );
    public static final SlimefunItemStack FALLEN_SATELLITE_RELIC = new SlimefunItemStack(
            "FALLEN_SATELLITE_RELIC",
            MaterialCompat.safe(XMaterial.CHISELED_QUARTZ_BLOCK)
    );
    public static final SlimefunItemStack TECHNOLOGICAL_SALVAGER = new SlimefunItemStack(
            "TECHNOLOGICAL_SALVAGER",
            GalactifunHead.CORE
    );
    //</editor-fold>
    //<editor-fold desc="Rock It" defaultstate="collapsed">
    private static final int TIER_ONE_FUEL = 10;
    private static final int TIER_ONE_STORAGE = 9;
    public static final SlimefunItemStack TIER_ONE = new SlimefunItemStack(
            "ROCKET_TIER_ONE",
            GalactifunHead.ROCKET
    );
    private static final int TIER_TWO_FUEL = 100;
    private static final int TIER_TWO_STORAGE = 18;
    public static final SlimefunItemStack TIER_TWO = new SlimefunItemStack(
            "ROCKET_TIER_TWO",
            GalactifunHead.ROCKET
    );
    private static final int TIER_THREE_FUEL = 500;
    private static final int TIER_THREE_STORAGE = 36;
    public static final SlimefunItemStack TIER_THREE = new SlimefunItemStack(
            "ROCKET_TIER_THREE",
            GalactifunHead.ROCKET
    );
    public static final SlimefunItemStack ION_ROCKET = new SlimefunItemStack(
            "ION_ROCKET",
            GalactifunHead.ION_ROCKET
    );
    //</editor-fold>

    public static void setup(Galactifun galactifun) {
        new OxygenFiller(CoreItemGroup.MACHINES, OXYGEN_FILLER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                BaseMats.ALUMINUM_COMPOSITE.item(), BaseMats.REINFORCED_CHANNEL.item(), BaseMats.ALUMINUM_COMPOSITE.item(),
                BaseMats.ALUMINUM_COMPOSITE.item(), BaseMats.FAN_BLADE.item(), BaseMats.ALUMINUM_COMPOSITE.item(),
                BaseMats.ALUMINUM_COMPOSITE.item(), null, BaseMats.ALUMINUM_COMPOSITE.item()
        }).setCapacity(200).setEnergyConsumption(100).setProcessingSpeed(1).register(galactifun);
        new SpaceSuitUpgrader(CoreItemGroup.MACHINES, SPACE_SUIT_UPGRADER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                BaseMats.ALUMINUM_COMPOSITE.item(), SlimefunItems.HARDENED_GLASS.item(), BaseMats.ALUMINUM_COMPOSITE.item(),
                BaseMats.ALUMINUM_COMPOSITE.item(), null, BaseMats.ALUMINUM_COMPOSITE.item(),
                BaseMats.ALUMINUM_COMPOSITE.item(), SlimefunItems.STEEL_INGOT.item(), BaseMats.ALUMINUM_COMPOSITE.item()
        }).setCapacity(400).setEnergyConsumption(200).setProcessingSpeed(1).register(galactifun);

        new SpaceSuitUpgrade(CoreItemGroup.EQUIPMENT, HEAT_RESISTANCE_UPGRADE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.COOLING_UNIT.item(), SlimefunItems.COOLING_UNIT.item(), SlimefunItems.COOLING_UNIT.item(),
                SlimefunItems.COOLING_UNIT.item(), new ItemStack(MaterialCompat.safe(XMaterial.IRON_BLOCK)), SlimefunItems.COOLING_UNIT.item(),
                SlimefunItems.COOLING_UNIT.item(), SlimefunItems.COOLING_UNIT.item(), SlimefunItems.COOLING_UNIT.item()
        }, SpaceSuitStat.HEAT_RESISTANCE, 1).register(galactifun);
        new SpaceSuitUpgrade(CoreItemGroup.EQUIPMENT, COLD_RESISTANCE_UPGRADE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.HEATING_COIL.item(), SlimefunItems.HEATING_COIL.item(), SlimefunItems.HEATING_COIL.item(),
                SlimefunItems.HEATING_COIL.item(), new ItemStack(MaterialCompat.safe(XMaterial.IRON_BLOCK)), SlimefunItems.HEATING_COIL.item(),
                SlimefunItems.HEATING_COIL.item(), SlimefunItems.HEATING_COIL.item(), SlimefunItems.HEATING_COIL.item()
        }, SpaceSuitStat.COLD_RESISTANCE, 1).register(galactifun);
        new SpaceSuitUpgrade(CoreItemGroup.EQUIPMENT, RADIATION_RESISTANCE_UPGRADE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.LEAD_INGOT.item(), SlimefunItems.LEAD_INGOT.item(), SlimefunItems.LEAD_INGOT.item(),
                SlimefunItems.LEAD_INGOT.item(), new ItemStack(MaterialCompat.safe(XMaterial.IRON_BLOCK)), SlimefunItems.LEAD_INGOT.item(),
                SlimefunItems.LEAD_INGOT.item(), SlimefunItems.LEAD_INGOT.item(), SlimefunItems.LEAD_INGOT.item()
        }, SpaceSuitStat.RADIATION_RESISTANCE, 1).register(galactifun);

        new SpaceSuitHelmet(CoreItemGroup.EQUIPMENT, SPACE_SUIT_HELMET, RecipeType.ARMOR_FORGE, new ItemStack[] {
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), BaseMats.GOLD_FOIL.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item(),
                SlimefunItems.REINFORCED_CLOTH.item(), SlimefunItems.SCUBA_HELMET.item(), SlimefunItems.REINFORCED_CLOTH.item(),
                BaseMats.REINFORCED_CHANNEL.item(), BaseMats.OXYGEN_REGENERATOR.item(), BaseMats.FAN_BLADE.item()
        }, 3, 0).register(galactifun);
        new SpaceSuit(CoreItemGroup.EQUIPMENT, SPACE_SUIT_CHEST, RecipeType.ARMOR_FORGE, new ItemStack[] {
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), SlimefunItems.REINFORCED_CLOTH.item(), BaseMats.REINFORCED_CHANNEL.item(),
                BaseMats.FAN_BLADE.item(), SlimefunItems.HAZMAT_CHESTPLATE.item(), BaseMats.FAN_BLADE.item(),
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item()
        }, 3, 3600).register(galactifun);
        new SpaceSuit(CoreItemGroup.EQUIPMENT, SPACE_SUIT_PANTS, RecipeType.ARMOR_FORGE, new ItemStack[] {
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), SlimefunItems.HAZMAT_LEGGINGS.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item(),
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), null, BaseMats.ALUMINUM_COMPOSITE_SHEET.item(),
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), null, BaseMats.ALUMINUM_COMPOSITE_SHEET.item()
        }, 2, 0).register(galactifun);
        new SpaceSuit(CoreItemGroup.EQUIPMENT, SPACE_SUIT_BOOTS, RecipeType.ARMOR_FORGE, new ItemStack[] {
                null, null, null,
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), null, BaseMats.ALUMINUM_COMPOSITE_SHEET.item(),
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), SlimefunItems.HAZMAT_BOOTS.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item()
        }, 2, 0).register(galactifun);

        new CircuitPress(CoreItemGroup.MACHINES, CIRCUIT_PRESS, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.HEATING_COIL.item(), new ItemStack(MaterialCompat.safe(XMaterial.PISTON)), SlimefunItems.HEATING_COIL.item(),
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), null, BaseMats.ALUMINUM_COMPOSITE_SHEET.item(),
                SlimefunItems.HEATING_COIL.item(), new ItemStack(MaterialCompat.safe(XMaterial.PISTON)), SlimefunItems.HEATING_COIL.item()
        }).setCapacity(512).setEnergyConsumption(128).setProcessingSpeed(1).register(galactifun);

        new AssemblyTable(ASSEMBLY_TABLE, new ItemStack[] {
                SlimefunItems.STEEL_PLATE.item(), SlimefunItems.ENHANCED_AUTO_CRAFTER.item(), SlimefunItems.STEEL_PLATE.item(),
                SlimefunItems.CARGO_MOTOR.item(), BaseMats.ADVANCED_PROCESSING_UNIT.item(), SlimefunItems.CARGO_MOTOR.item(),
                SlimefunItems.REINFORCED_PLATE.item(), SlimefunItems.REINFORCED_PLATE.item(), SlimefunItems.REINFORCED_PLATE.item()
        }, 2048).register(galactifun);

        new StargateRing(CoreItemGroup.COMPONENTS, STARGATE_RING, AssemblyTable.TYPE, new ItemStack[] {
                BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ULTRA_DUTY_SHEET.item(),
                BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ENDER_BLOCK.item(), BaseMats.ENDER_BLOCK.item(), BaseMats.ENDER_BLOCK.item(), BaseMats.ENDER_BLOCK.item(), BaseMats.ULTRA_DUTY_SHEET.item(),
                BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ENDER_BLOCK.item(), BaseMats.ENDER_BLOCK.item(), BaseMats.ENDER_BLOCK.item(), BaseMats.ENDER_BLOCK.item(), BaseMats.ULTRA_DUTY_SHEET.item(),
                BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ENDER_BLOCK.item(), BaseMats.ENDER_BLOCK.item(), BaseMats.ENDER_BLOCK.item(), BaseMats.ENDER_BLOCK.item(), BaseMats.ULTRA_DUTY_SHEET.item(),
                BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ENDER_BLOCK.item(), BaseMats.ENDER_BLOCK.item(), BaseMats.ENDER_BLOCK.item(), BaseMats.ENDER_BLOCK.item(), BaseMats.ULTRA_DUTY_SHEET.item(),
                BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ULTRA_DUTY_SHEET.item()
        }).register(galactifun);
        new StargateController(CoreItemGroup.COMPONENTS, STARGATE_CONTROLLER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                STARGATE_RING.item(), BaseMats.GLOWSTONE_CIRCUIT.item(), STARGATE_RING.item(),
                BaseMats.REDSTONE_CIRCUIT.item(), BaseMats.DIAMOND_CIRCUIT.item(), BaseMats.REDSTONE_CIRCUIT.item(),
                STARGATE_RING.item(), BaseMats.LAPIS_CIRCUIT.item(), STARGATE_RING.item()
        }).register(galactifun);

        new ChemicalRocket(CoreItemGroup.ITEMS, TIER_ONE, AssemblyTable.TYPE, new ItemStack[] {
                null, null, BaseMats.NOSE_CONE.item(), BaseMats.NOSE_CONE.item(), null, null,
                null, null, BaseMats.HEAVY_DUTY_SHEET.item(), BaseMats.HEAVY_DUTY_SHEET.item(), null, null,
                null, BaseMats.HEAVY_DUTY_SHEET.item(), BaseMats.ADVANCED_PROCESSING_UNIT.item(), BaseMats.ADVANCED_PROCESSING_UNIT.item(), BaseMats.HEAVY_DUTY_SHEET.item(), null,
                BaseMats.HEAVY_DUTY_SHEET.item(), BaseMats.FUEL_TANK.item(), BaseMats.LIFE_SUPPORT_MODULE.item(), BaseMats.LIFE_SUPPORT_MODULE.item(), BaseMats.FUEL_TANK.item(), BaseMats.HEAVY_DUTY_SHEET.item(),
                BaseMats.HEAVY_DUTY_SHEET.item(), BaseMats.FUEL_TANK.item(), BaseMats.FUEL_TANK.item(), BaseMats.FUEL_TANK.item(), BaseMats.FUEL_TANK.item(), BaseMats.HEAVY_DUTY_SHEET.item(),
                BaseMats.HEAVY_DUTY_SHEET.item(), null, BaseMats.ROCKET_ENGINE.item(), BaseMats.ROCKET_ENGINE.item(), null, BaseMats.HEAVY_DUTY_SHEET.item()
        }, TIER_ONE_FUEL, TIER_ONE_STORAGE).register(galactifun);
        new ChemicalRocket(CoreItemGroup.ITEMS, TIER_TWO, AssemblyTable.TYPE, new ItemStack[] {
                null, null, BaseMats.NOSE_CONE.item(), BaseMats.NOSE_CONE.item(), null, null,
                null, null, BaseMats.HEAVY_DUTY_SHEET.item(), BaseMats.HEAVY_DUTY_SHEET.item(), null, null,
                null, BaseMats.HEAVY_DUTY_SHEET.item(), BaseMats.ADVANCED_PROCESSING_UNIT.item(), BaseMats.ADVANCED_PROCESSING_UNIT.item(), BaseMats.HEAVY_DUTY_SHEET.item(), null,
                BaseMats.HEAVY_DUTY_SHEET.item(), BaseMats.FUEL_TANK.item(), BaseMats.LIFE_SUPPORT_MODULE.item(), BaseMats.LIFE_SUPPORT_MODULE.item(), BaseMats.FUEL_TANK.item(), BaseMats.HEAVY_DUTY_SHEET.item(),
                BaseMats.HEAVY_DUTY_SHEET.item(), BaseMats.FUEL_TANK.item(), BaseMats.FUEL_TANK.item(), BaseMats.FUEL_TANK.item(), BaseMats.FUEL_TANK.item(), BaseMats.HEAVY_DUTY_SHEET.item(),
                BaseMats.HEAVY_DUTY_SHEET.item(), null, BaseMats.ROCKET_ENGINE_2.item(), BaseMats.ROCKET_ENGINE_2.item(), null, BaseMats.HEAVY_DUTY_SHEET.item()
        }, TIER_TWO_FUEL, TIER_TWO_STORAGE).register(galactifun);
        new ChemicalRocket(CoreItemGroup.ITEMS, TIER_THREE, AssemblyTable.TYPE, new ItemStack[] {
                null, null, BaseMats.NOSE_CONE.item(), BaseMats.NOSE_CONE.item(), null, null,
                null, null, BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ULTRA_DUTY_SHEET.item(), null, null,
                null, BaseMats.HEAVY_DUTY_SHEET.item(), BaseMats.ADVANCED_PROCESSING_UNIT.item(), BaseMats.ADVANCED_PROCESSING_UNIT.item(), BaseMats.ULTRA_DUTY_SHEET.item(), null,
                BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.FUEL_TANK_2.item(), BaseMats.LIFE_SUPPORT_MODULE.item(), BaseMats.LIFE_SUPPORT_MODULE.item(), BaseMats.FUEL_TANK_2.item(), BaseMats.ULTRA_DUTY_SHEET.item(),
                BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.FUEL_TANK_2.item(), BaseMats.FUEL_TANK.item(), BaseMats.FUEL_TANK.item(), BaseMats.FUEL_TANK_2.item(), BaseMats.ULTRA_DUTY_SHEET.item(),
                BaseMats.ULTRA_DUTY_SHEET.item(), null, BaseMats.ROCKET_ENGINE_3.item(), BaseMats.ROCKET_ENGINE_3.item(), null, BaseMats.ULTRA_DUTY_SHEET.item()
        }, TIER_THREE_FUEL, TIER_THREE_STORAGE).register(galactifun);

        new SlimefunItem(CoreItemGroup.ITEMS, LANDING_HATCH, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                BaseMats.HEAVY_DUTY_SHEET.item(), new ItemStack(MaterialCompat.safe(XMaterial.IRON_TRAPDOOR)), BaseMats.HEAVY_DUTY_SHEET.item(),
                BaseMats.SPACE_GRADE_PLATE.item(), null, BaseMats.SPACE_GRADE_PLATE.item(),
                BaseMats.SPACE_GRADE_PLATE.item(), null, BaseMats.SPACE_GRADE_PLATE.item()
        }).register(galactifun);

        new LaunchPadFloor(CoreItemGroup.ITEMS, BaseItems.LAUNCH_PAD_FLOOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                null, null, null,
                BaseMats.HEAVY_DUTY_SHEET.item(), BaseMats.HEAVY_DUTY_SHEET.item(), BaseMats.HEAVY_DUTY_SHEET.item(),
                BaseMats.HEAVY_DUTY_SHEET.item(), BaseMats.HEAVY_DUTY_SHEET.item(), BaseMats.HEAVY_DUTY_SHEET.item(),
        }).register(galactifun);

        new LaunchPadCore(CoreItemGroup.ITEMS, BaseItems.LAUNCH_PAD_CORE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.REINFORCED_PLATE.item(), BaseMats.NOZZLE.item(), SlimefunItems.REINFORCED_PLATE.item(),
                SlimefunItems.CARGO_MOTOR.item(), SlimefunItems.OIL_PUMP.item(), SlimefunItems.CARGO_MOTOR.item(),
                SlimefunItems.REINFORCED_PLATE.item(), BaseMats.ADVANCED_PROCESSING_UNIT.item(), SlimefunItems.REINFORCED_PLATE.item(),
        }).register(galactifun);

        new CoolingUnit(COOLING_UNIT_1, new ItemStack[] {
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), SlimefunItems.COOLING_UNIT.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item(),
                SlimefunItems.COOLING_UNIT.item(), BaseMats.FAN_BLADE.item(), SlimefunItems.COOLING_UNIT.item(),
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), SlimefunItems.COOLING_UNIT.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item()
        }, 1).register(galactifun);

        new CoolingUnit(COOLING_UNIT_2, new ItemStack[] {
                BaseMats.SPACE_GRADE_PLATE.item(), BaseMats.DRY_ICE.item(), BaseMats.SPACE_GRADE_PLATE.item(),
                BaseMats.DRY_ICE.item(), COOLING_UNIT_1.item(), BaseMats.DRY_ICE.item(),
                BaseMats.SPACE_GRADE_PLATE.item(), BaseMats.DRY_ICE.item(), BaseMats.SPACE_GRADE_PLATE.item()
        }, 2).register(galactifun);

        new CoolingUnit(COOLING_UNIT_3, new ItemStack[] {
                BaseMats.HEAVY_DUTY_SHEET.item(), BaseMats.DRY_ICE.item(), BaseMats.HEAVY_DUTY_SHEET.item(),
                BaseMats.DRY_ICE.item(), COOLING_UNIT_2.item(), BaseMats.DRY_ICE.item(),
                BaseMats.HEAVY_DUTY_SHEET.item(), BaseMats.DRY_ICE.item(), BaseMats.HEAVY_DUTY_SHEET.item()
        }, 3).register(galactifun);

        new SpaceHeater(SPACE_HEATER_1, new ItemStack[] {
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), SlimefunItems.HEATING_COIL.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item(),
                SlimefunItems.HEATING_COIL.item(), BaseMats.FAN_BLADE.item(), SlimefunItems.HEATING_COIL.item(),
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), SlimefunItems.HEATING_COIL.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item()
        }, 1).register(galactifun);

        new SpaceHeater(SPACE_HEATER_2, new ItemStack[] {
                BaseMats.SPACE_GRADE_PLATE.item(), new ItemStack(MaterialCompat.safe(XMaterial.LAVA_BUCKET)), BaseMats.SPACE_GRADE_PLATE.item(),
                BaseMats.VENTSTONE.item(), SPACE_HEATER_1.item(), BaseMats.VENTSTONE.item(),
                BaseMats.SPACE_GRADE_PLATE.item(), SlimefunItems.HEATING_COIL.item(), BaseMats.SPACE_GRADE_PLATE.item()
        }, 2).register(galactifun);

        new SpaceHeater(SPACE_HEATER_3, new ItemStack[] {
                BaseMats.HEAVY_DUTY_SHEET.item(), BaseMats.VOLCANIC_INGOT.item(), BaseMats.HEAVY_DUTY_SHEET.item(),
                BaseMats.VOLCANIC_INGOT.item(), SPACE_HEATER_2.item(), BaseMats.VOLCANIC_INGOT.item(),
                BaseMats.HEAVY_DUTY_SHEET.item(), BaseMats.BLISTERING_VOLCANIC_INGOT.item(), BaseMats.HEAVY_DUTY_SHEET.item()
        }, 3).register(galactifun);

        new IonDisperser(ION_DISPERSER_1, new ItemStack[] {
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), BaseMats.FAN_BLADE.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item(),
                new ItemStack(MaterialCompat.safe(XMaterial.PRISMARINE_CRYSTALS)), BaseMats.SULFUR_BLOCK.item(), new ItemStack(MaterialCompat.safe(XMaterial.PRISMARINE_CRYSTALS)),
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), BaseMats.VENTSTONE.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item()
        }, 1).register(galactifun);

        new IonDisperser(ION_DISPERSER_2, new ItemStack[] {
                BaseMats.SPACE_GRADE_PLATE.item(), BaseMats.FAN_BLADE.item(), BaseMats.SPACE_GRADE_PLATE.item(),
                BaseMats.SULFUR_BLOCK.item(), ION_DISPERSER_1.item(), BaseMats.SULFUR_BLOCK.item(),
                BaseMats.SPACE_GRADE_PLATE.item(), BaseMats.BLISTERING_VOLCANIC_INGOT.item(), BaseMats.SPACE_GRADE_PLATE.item()
        }, 2).register(galactifun);

        new Observatory(OBSERVATORY, new ItemStack[] {
                new ItemStack(MaterialCompat.safe(XMaterial.IRON_BLOCK)), new ItemStack(MaterialCompat.safe(XMaterial.PISTON)), new ItemStack(MaterialCompat.safe(XMaterial.IRON_BLOCK)),
                new ItemStack(MaterialCompat.safe(XMaterial.PISTON)), BaseMats.LUNAR_GLASS.item(), new ItemStack(MaterialCompat.safe(XMaterial.PISTON)),
                new ItemStack(MaterialCompat.safe(XMaterial.IRON_BLOCK)), new ItemStack(MaterialCompat.safe(XMaterial.PISTON)), new ItemStack(MaterialCompat.safe(XMaterial.IRON_BLOCK))
        }).register(galactifun);
        new PlanetaryAnalyzer(PLANETARY_ANALYZER, new ItemStack[] {
                BaseMats.TUNGSTEN_INGOT.item(), SlimefunItems.GPS_TRANSMITTER_4.item(), BaseMats.TUNGSTEN_INGOT.item(),
                BaseMats.SPACE_GRADE_PLATE.item(), SlimefunItems.ENERGIZED_CAPACITOR.item(), BaseMats.SPACE_GRADE_PLATE.item(),
                BaseMats.TUNGSTEN_INGOT.item(), BaseMats.VOLCANIC_INGOT.item(), BaseMats.TUNGSTEN_INGOT.item()
        }).register(galactifun);
        new DiamondAnvil(DIAMOND_ANVIL, new ItemStack[] {
                BaseMats.ULTRA_DUTY_SHEET.item(), new ItemStack(MaterialCompat.safe(XMaterial.GLASS)), BaseMats.ULTRA_DUTY_SHEET.item(),
                BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.DIAMOND_ANVIL_CELL.item(), BaseMats.ULTRA_DUTY_SHEET.item(),
                BaseMats.ULTRA_DUTY_SHEET.item(), new ItemStack(MaterialCompat.safe(XMaterial.ANVIL)), BaseMats.ULTRA_DUTY_SHEET.item()
        }).setCapacity(2048).setEnergyConsumption(512).setProcessingSpeed(1).register(galactifun);

        new OxygenSealer(OXYGEN_SEALER, new ItemStack[] {
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), BaseMats.FAN_BLADE.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item(),
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), BaseMats.REINFORCED_CHANNEL.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item(),
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), BaseMats.OXYGEN_REGENERATOR.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item()
        }, 1000).register(galactifun);

        new SlimefunItem(CoreItemGroup.ITEMS, SUPER_FAN, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item(),
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), BaseMats.FAN_BLADE.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item(),
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), BaseMats.VENTSTONE.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item()
        }).register(galactifun);

        new AutomaticDoor(AUTOMATIC_DOOR, new ItemStack[] {
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), new ItemStack(MaterialCompat.safe(XMaterial.OBSERVER)), BaseMats.ALUMINUM_COMPOSITE_SHEET.item(),
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), SlimefunItems.PROGRAMMABLE_ANDROID_MINER.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item(),
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), SlimefunItems.BLOCK_PLACER.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item()
        }).register(galactifun);

        new ForcefieldGenerator(ENVIRONMENTAL_FORCEFIELD_GENERATOR, new ItemStack[] {
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), BaseMats.ENDER_BLOCK.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item(),
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), ION_DISPERSER_1.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item(),
                BaseMats.ALUMINUM_COMPOSITE_SHEET.item(), BaseMats.SPACE_GRADE_PLATE.item(), BaseMats.ALUMINUM_COMPOSITE_SHEET.item()
        }).register(galactifun);

        new FusionReactor(FUSION_REACTOR, new ItemStack[] {
                BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ADVANCED_PROCESSING_UNIT.item(), BaseMats.ADVANCED_PROCESSING_UNIT.item(), BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ULTRA_DUTY_SHEET.item(),
                BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.BLISTERING_VOLCANIC_INGOT.item(), BaseMats.LASERITE.item(), BaseMats.LASERITE.item(), BaseMats.BLISTERING_VOLCANIC_INGOT.item(), BaseMats.ULTRA_DUTY_SHEET.item(),
                BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.LASERITE.item(), BaseMats.FUSION_PELLET.item(), BaseMats.FUSION_PELLET.item(), BaseMats.LASERITE.item(), BaseMats.ULTRA_DUTY_SHEET.item(),
                BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.LASERITE.item(), BaseMats.FUSION_PELLET.item(), BaseMats.FUSION_PELLET.item(), BaseMats.LASERITE.item(), BaseMats.ULTRA_DUTY_SHEET.item(),
                BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.BLISTERING_VOLCANIC_INGOT.item(), BaseMats.LASERITE.item(), BaseMats.LASERITE.item(), BaseMats.BLISTERING_VOLCANIC_INGOT.item(), BaseMats.ULTRA_DUTY_SHEET.item(),
                BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ULTRA_DUTY_SHEET.item(),
        }).register(galactifun);

        new AtmosphericHarvester(ATMOSPHERIC_HARVESTER, new ItemStack[] {
                BaseMats.SPACE_GRADE_PLATE.item(), BaseMats.FAN_BLADE.item(), BaseMats.SPACE_GRADE_PLATE.item(),
                BaseMats.SPACE_GRADE_PLATE.item(), null, BaseMats.SPACE_GRADE_PLATE.item(),
                BaseMats.SPACE_GRADE_PLATE.item(), BaseMats.SPACE_GRADE_PLATE.item(), BaseMats.SPACE_GRADE_PLATE.item()
        }).register(galactifun);

        new IonRocket(ION_ROCKET, new ItemStack[] {
                null, null, BaseMats.NOSE_CONE.item(), BaseMats.NOSE_CONE.item(), null, null,
                null, null, BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ULTRA_DUTY_SHEET.item(), null, null,
                null, BaseMats.HEAVY_DUTY_SHEET.item(), BaseMats.ADVANCED_PROCESSING_UNIT.item(), BaseMats.ADVANCED_PROCESSING_UNIT.item(), BaseMats.ULTRA_DUTY_SHEET.item(), null,
                BaseMats.ULTRA_DUTY_SHEET.item(), FUSION_REACTOR.item(), BaseMats.LIFE_SUPPORT_MODULE.item(), BaseMats.LIFE_SUPPORT_MODULE.item(), SlimefunItems.ENERGIZED_CAPACITOR.item(), BaseMats.ULTRA_DUTY_SHEET.item(),
                BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.FUEL_TANK_2.item(), BaseMats.FUEL_TANK.item(), BaseMats.FUEL_TANK.item(), BaseMats.FUEL_TANK_2.item(), BaseMats.ULTRA_DUTY_SHEET.item(),
                BaseMats.ULTRA_DUTY_SHEET.item(), null, BaseMats.ION_ENGINE.item(), BaseMats.ION_ENGINE.item(), null, BaseMats.ULTRA_DUTY_SHEET.item()
        }, 500, 18).register(galactifun);

        MachineBlock chemicalReactor = new MachineBlock(CoreItemGroup.MACHINES, CHEMICAL_REACTOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                BaseMats.SPACE_GRADE_PLATE.item(), null, BaseMats.SPACE_GRADE_PLATE.item(),
                BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.BLISTERING_VOLCANIC_INGOT.item(), BaseMats.ULTRA_DUTY_SHEET.item(),
                BaseMats.ADVANCED_PROCESSING_UNIT.item(), BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ADVANCED_PROCESSING_UNIT.item()
        });

        chemicalReactor.addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.WATER_BUCKET)), Gas.WATER.item().item(), new ItemStack(MaterialCompat.safe(XMaterial.BUCKET)));
        chemicalReactor.addRecipe(Gas.WATER.item().item(), Gas.OXYGEN.item().item(), Gas.HYDROGEN.item().asQuantity(2));

        chemicalReactor.addRecipe(Gas.CARBON_DIOXIDE.item().item(), SlimefunItems.CARBON.item(), Gas.OXYGEN.item().asQuantity(2));
        chemicalReactor.addRecipe(Gas.METHANE.item().item(), SlimefunItems.CARBON.item(), Gas.HYDROGEN.item().asQuantity(4));
        chemicalReactor.addRecipe(Gas.HYDROCARBONS.item().item(), Gas.METHANE.item().asQuantity(6));
        chemicalReactor.addRecipe(SlimefunItems.OIL_BUCKET.item(), Gas.HYDROCARBONS.item().item(), new ItemStack(MaterialCompat.safe(XMaterial.BUCKET)));

        chemicalReactor.addRecipe(Gas.AMMONIA.item().item(), Gas.NITROGEN.item().item(), Gas.HYDROGEN.item().asQuantity(3));

        chemicalReactor.energyCapacity(512);
        chemicalReactor.energyPerTick(128);
        chemicalReactor.ticksPerOutput(20);
        chemicalReactor.register(galactifun);

        new Electrolyzer(ELECTROLYZER, new ItemStack[] {
                BaseMats.SPACE_GRADE_PLATE.item(), null, BaseMats.SPACE_GRADE_PLATE.item(),
                SlimefunItems.SILVER_INGOT.item(), BaseMats.BLISTERING_VOLCANIC_INGOT.item(), SlimefunItems.SILVER_INGOT.item(),
                BaseMats.ADVANCED_PROCESSING_UNIT.item(), BaseMats.ULTRA_DUTY_SHEET.item(), BaseMats.ADVANCED_PROCESSING_UNIT.item()
        }).register(galactifun);

        // relics
        new Relic(BROKEN_SOLAR_PANEL_RELIC, new Relic.RelicSettings()
                .addRequired(SlimefunItems.SILICON.item(), 2, 5)
                .addRequired(SlimefunItems.SOLAR_PANEL.item(), 1, 3)
                .addOptional(SlimefunItems.SOLAR_GENERATOR.item(), 0.20f)
                .addOptional(SlimefunItems.SOLAR_GENERATOR_2.item(), 0.10f)
                .addOptional(SlimefunItems.SOLAR_GENERATOR_3.item(), 0.02f),
                Galactifun.worldManager().alienWorlds().stream()
                        .filter(a -> a.getSetting("generate-fallen-satellites", Boolean.class, true))
                        .toArray(AlienWorld[]::new)).register(galactifun);

        new Relic(FALLEN_SATELLITE_RELIC, new Relic.RelicSettings()
                .addRequired(BaseMats.HEAVY_DUTY_SHEET.item(), 3, 4)
                .addRequired(BaseMats.SPACE_GRADE_PLATE.item(), 1, 3)
                .addRequired(SlimefunItems.BASIC_CIRCUIT_BOARD.item(), 0, 2)
                .addOptional(SlimefunItems.ADVANCED_CIRCUIT_BOARD.item(), 0.15f)
                .addOptional(BaseMats.ADVANCED_PROCESSING_UNIT.item(), 0.10f),
                Galactifun.worldManager().alienWorlds().stream()
                        .filter(a -> a.getSetting("generate-fallen-satellites", Boolean.class, true))
                        .toArray(AlienWorld[]::new)).register(galactifun);

        new TechnologicalSalvager(TECHNOLOGICAL_SALVAGER, new ItemStack[] {
                BaseMats.ULTRA_DUTY_SHEET.item(), null, BaseMats.ULTRA_DUTY_SHEET.item(),
                BaseMats.ADVANCED_PROCESSING_UNIT.item(), null, BaseMats.ADVANCED_PROCESSING_UNIT.item(),
                BaseMats.ULTRA_DUTY_SHEET.item(), new ItemStack(MaterialCompat.safe(XMaterial.STICKY_PISTON)), BaseMats.ULTRA_DUTY_SHEET.item()
        }).register(galactifun);
    }

}

