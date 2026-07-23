package io.github.addoncommunity.galactifun;

import java.io.File;
import java.util.logging.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPluginLoader;

import io.github.addoncommunity.galactifun.api.worlds.AlienWorld;
import io.github.addoncommunity.galactifun.api.worlds.PlanetaryWorld;
import io.github.addoncommunity.galactifun.base.BaseAlien;
import io.github.addoncommunity.galactifun.base.BaseItems;
import io.github.addoncommunity.galactifun.base.BaseMats;
import io.github.addoncommunity.galactifun.base.BaseUniverse;
import io.github.addoncommunity.galactifun.core.CoreItemGroup;
import io.github.addoncommunity.galactifun.core.commands.AlienRemoveCommand;
import io.github.addoncommunity.galactifun.core.commands.AlienSpawnCommand;
import io.github.addoncommunity.galactifun.core.commands.EffectsCommand;
import io.github.addoncommunity.galactifun.core.commands.GalactiportCommand;
import io.github.addoncommunity.galactifun.core.commands.SealedCommand;
import io.github.addoncommunity.galactifun.core.commands.StructureCommand;
import io.github.addoncommunity.galactifun.core.managers.AlienManager;
import io.github.addoncommunity.galactifun.core.managers.ProtectionManager;
import io.github.addoncommunity.galactifun.core.managers.WorldManager;
import io.github.mooy1.infinitylib.common.Scheduler;
import io.github.mooy1.infinitylib.core.AbstractAddon;
import org.bstats.bukkit.Metrics;
import io.github.thebusybiscuit.slimefun5.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun5.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.core.guide.wiki.WikiText;
import io.github.thebusybiscuit.slimefun5.core.guide.wiki.WikiTopic;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun5.libraries.dough.updater.BlobBuildUpdater;
import io.github.thebusybiscuit.slimefun5.libraries.paperlib.PaperLib;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public final class Galactifun extends AbstractAddon {

    private static Galactifun instance;

    private boolean isTest = false;

    private AlienManager alienManager;
    private WorldManager worldManager;
    private ProtectionManager protectionManager;

    private boolean shouldDisable = false;

    public Galactifun() {
        super("Slimefun5", "Galactifun", "master", "auto-update");
    }

    public Galactifun(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file, "Slimefun5", "Galactifun", "master", "auto-update");
        isTest = true;
    }

    public static AlienManager alienManager() {
        return instance.alienManager;
    }

    public static WorldManager worldManager() {
        return instance.worldManager;
    }

    public static ProtectionManager protectionManager() {
        return instance.protectionManager;
    }

    @Override
    protected void enable() {
        instance = this;

        if (!isTest) {
            if (!PaperLib.isPaper()) {
                log(Level.SEVERE, "Galactifun only supports Paper and its forks (i.e. Airplane and Purpur)");
                log(Level.SEVERE, "Please use Paper or a fork of Paper");
                shouldDisable = true;
            }
            if (Slimefun.getMinecraftVersion().isBefore(MinecraftVersion.MINECRAFT_1_17)) {
                log(Level.SEVERE, "Galactifun only supports Minecraft 1.17 and above");
                log(Level.SEVERE, "Please use Minecraft 1.17 or above");
                shouldDisable = true;
            }
            if (Bukkit.getPluginManager().isPluginEnabled("ChatColor2")) {
                log(Level.SEVERE, "Galactifun will not work properly with ChatColor2");
                log(Level.SEVERE, "Please disable ChatColor2");
                shouldDisable = true;
            }

            if (shouldDisable) {
                Bukkit.getPluginManager().disablePlugin(this);
                return;
            }
        }

        // Consolidated metrics: only start our own bStats if the server opted out (metrics.disable-addon-metrics = false).
        if (Slimefun.getCfg().contains("metrics.disable-addon-metrics") && !Slimefun.getCfg().getBoolean("metrics.disable-addon-metrics")) {
            new Metrics(this, 11613);
        }

        if (!isTest && this.getConfig().getBoolean("auto-update") && !getPluginVersion().contains("MODIFIED")) {
            try {
                new BlobBuildUpdater(this, this.getFile(), "Galactifun").start();
            } catch (Exception e) {
                log(Level.WARNING, "Could not start the auto-updater, skipping it: " + e.getMessage());
            }
        }

        this.alienManager = new AlienManager(this);
        this.worldManager = new WorldManager(this);
        this.protectionManager = new ProtectionManager();

        BaseAlien.setup(this.alienManager);
        if (!isTest) {
            BaseUniverse.setup(this);
        }
        CoreItemGroup.setup(this);
        BaseMats.setup();
        BaseItems.setup(this);

        // Startup banner intentionally omitted: Slimefun core logs every installed addon uniformly.

        getAddonCommand()
                .addSub(new GalactiportCommand())
                .addSub(new AlienSpawnCommand())
                .addSub(new AlienRemoveCommand())
                .addSub(new StructureCommand(this))
                .addSub(new SealedCommand())
                .addSub(new EffectsCommand());

        // Contribute this addon's per-language item translations (languages/<lang>/items.yml).
        Slimefun.getItemTranslationService().registerTranslations(this);

        // Register this addon's own in-game wiki page (core does not auto-generate addon wikis).
        registerWiki();
    }

    /**
     * Builds a rich in-game wiki for Galactifun: one topic per ItemGroup (built
     * dynamically from this addon's enabled items) plus detailed per-item pages
     * covering rockets, space suits, machines, resources and key blocks.
     */
    private void registerWiki() {
        WikiText wiki = Slimefun.getWikiText();

        // Group this addon's items by their ItemGroup, preserving discovery order.
        Map<ItemGroup, List<String>> byGroup = new LinkedHashMap<>();
        for (SlimefunItem item : Slimefun.getRegistry().getEnabledSlimefunItems()) {
            try {
                if (item.getAddon() != this) {
                    continue;
                }
                byGroup.computeIfAbsent(item.getItemGroup(), key -> new ArrayList<>()).add(item.getId());
            } catch (Exception | LinkageError ignored) {
                // Skip items whose addon/group resolution fails on exotic builds.
            }
        }

        for (Map.Entry<ItemGroup, List<String>> entry : byGroup.entrySet()) {
            ItemGroup group = entry.getKey();
            String key = group.getKey().getKey();
            String topicId = "addon_galactifun_" + key;

            wiki.registerTopic(new WikiTopic(topicId, categoryName(key), categoryIcon(key), categoryTagline(key)));
            wiki.setMechanic(topicId, categoryMechanic(key));
            wiki.setTopicItems(topicId, entry.getValue());
        }

        registerItemPages(wiki);
    }

    private static String categoryName(String key) {
        switch (key) {
            case "equipment": return "Galactifun: Space Suits & Equipment";
            case "items": return "Galactifun: Rockets & Gear";
            case "components": return "Galactifun: Components & Materials";
            case "machines": return "Galactifun: Machines";
            case "blocks": return "Galactifun: Alien Resources";
            case "relics": return "Galactifun: Alien Relics";
            default: return "Galactifun";
        }
    }

    private static XMaterial categoryIcon(String key) {
        switch (key) {
            case "equipment": return XMaterial.IRON_CHESTPLATE;
            case "items": return XMaterial.FIREWORK_ROCKET;
            case "components": return XMaterial.IRON_INGOT;
            case "machines": return XMaterial.REDSTONE_LAMP;
            case "blocks": return XMaterial.RED_SAND;
            case "relics": return XMaterial.CHISELED_POLISHED_BLACKSTONE;
            default: return XMaterial.BEACON;
        }
    }

    private static String categoryTagline(String key) {
        switch (key) {
            case "equipment": return "&7Survive the airless void";
            case "items": return "&7Build rockets and reach orbit";
            case "components": return "&7The building blocks of spaceflight";
            case "machines": return "&7Refine gases, fuse atoms and more";
            case "blocks": return "&7Harvest the riches of other worlds";
            case "relics": return "&7Decode the technology of aliens";
            default: return "&7Explore space";
        }
    }

    private static List<String> categoryMechanic(String key) {
        switch (key) {
            case "equipment":
                return Arrays.asList(
                        "&7Space is lethally cold, scorchingly hot,", "&7irradiated and utterly without air.", "",
                        "&7A full &fSpace Suit &7(helmet, chest, pants", "&7and boots) shields you from these hazards", "&7and stores breathable oxygen.", "",
                        "&7The chestplate holds your oxygen supply -", "&7refill it at an &fOxygen Filler&7. Bolt on", "&7Heat, Cold and Radiation upgrades via the", "&fSpace Suit Upgrader &7for harsher worlds.");
            case "items":
                return Arrays.asList(
                        "&7Rockets are your ride between worlds.", "",
                        "&7Assemble one on an &fAssembly Table&7, then", "&7stand it on a &fLaunch Pad&7. Fuel it, open", "&7the GUI and pick a destination from the", "&7galaxy map.", "",
                        "&7Higher tiers carry more fuel and cargo;", "&7the &bIon Rocket &7sips fuel for long hauls.", "&7Bring a sealed cabin and enough oxygen for", "&7the journey - the void does not forgive.");
            case "components":
                return Arrays.asList(
                        "&7Every rocket, suit and machine is built", "&7from these intermediate parts.", "",
                        "&7Press sheets in a &fCompressor&7, forge", "&7circuits in a &fCircuit Press&7, and combine", "&7everything on the &fAssembly Table&7.", "",
                        "&7Engines, fuel tanks, life-support modules", "&7and processing units all start here.", "&7Stockpile them before a big build - rockets", "&7devour components by the dozen.");
            case "machines":
                return Arrays.asList(
                        "&7Galactifun's machines power the whole", "&7space program.", "",
                        "&7Fill suits with the &fOxygen Filler&7, refine", "&7and split gases in the &fChemical Reactor &7and", "&fElectrolyzer&7, and pull air straight from a", "&7planet with the &fAtmospheric Harvester&7.", "",
                        "&7End-game machines like the &fDiamond Anvil &7and", "&fFusion Reactor &7crush and fuse matter for", "&7staggering amounts of energy. Wire them to", "&7a strong power network first.");
            case "blocks":
                return Arrays.asList(
                        "&7Each world hides its own resources.", "",
                        "&7Mine &7Moon Dust and Rock on the Moon,", "&cMars Dust &7and &4Fallen Meteors &7on Mars,", "&6Sulfur &7and &6Ventstone &7on Venus, and", "&bMethane Ice &7on frigid Titan.", "",
                        "&7These raw materials grind, smelt and react", "&7into the alloys and gases your machines", "&7need. Bring the right suit upgrades - some", "&7worlds will cook or freeze you on arrival.");
            case "relics":
                return Arrays.asList(
                        "&7Scattered across alien worlds lie the", "&7ruins of a lost civilisation.", "",
                        "&7&fFallen Satellites &7and &7broken solar arrays", "&7can be dug up and fed to a", "&fTechnological Salvager&7, which strips them", "&7for usable parts and circuits.", "",
                        "&7Stranger &7relics hum with alien power and", "&7bear text no human can read. Study them -", "&7they may be the key to technology far", "&7beyond our own.");
            default:
                return Arrays.asList(
                        "&7Travel to planets and moons aboard rockets,", "&7suit up to survive the void, and harvest", "&7exotic alien resources across the galaxy.", "",
                        "&7Click an item below for its recipe.");
        }
    }

    /** Detailed lore pages for individual notable items. */
    private void registerItemPages(WikiText wiki) {
        // Rockets & launch infrastructure
        wiki.set("ROCKET_TIER_ONE", Arrays.asList(
                "&7The entry-level &4Chemical Rocket&7.",
                "&7Carries 10 fuel and 9 cargo slots - just",
                "&7enough to reach the Moon and back.",
                "&7Built on an Assembly Table, launched from",
                "&7a fuelled Launch Pad."));
        wiki.set("ROCKET_TIER_TWO", Arrays.asList(
                "&7A larger &4Chemical Rocket&7.",
                "&7100 fuel and 18 cargo slots open up the",
                "&7inner planets like Mars and Venus."));
        wiki.set("ROCKET_TIER_THREE", Arrays.asList(
                "&7The heavy-lift &4Chemical Rocket&7.",
                "&7500 fuel and 36 cargo slots for the long",
                "&7burns out to the outer worlds."));
        wiki.set("ION_ROCKET", Arrays.asList(
                "&bExpels ions at enormous speed for",
                "&7extreme fuel efficiency.",
                "&7Carries a Fusion Reactor onboard, so it",
                "&7needs only a fraction of the fuel of a",
                "&7chemical rocket for the same range."));
        wiki.set("LAUNCH_PAD_CORE", Arrays.asList(
                "&7The heart of a Launch Pad.",
                "&7Surround it with 8 &fLaunch Pad Floor&7s,",
                "&7then place a rocket on top to launch."));
        wiki.set("LAUNCH_PAD_FLOOR", Arrays.asList(
                "&7Reinforced plating for a Launch Pad.",
                "&7Place 8 of them around a Launch Pad Core."));
        wiki.set("LANDING_HATCH", Arrays.asList(
                "&7Rockets ignore this block on landing and",
                "&7touch down on the highest block below it.",
                "&7Seals spaces as it is impassable to air."));

        // Space suit & oxygen
        wiki.set("SPACE_SUIT_HELMET", Arrays.asList(
                "&7Part of the Space Suit set.",
                "&7Shields your head from the vacuum.",
                "&7Wear the full set to survive in space."));
        wiki.set("SPACE_SUIT_CHEST", Arrays.asList(
                "&7The core of the Space Suit.",
                "&7Stores your breathable oxygen supply",
                "&7(up to 3600 units).",
                "&7Refill it in an &fOxygen Filler&7."));
        wiki.set("SPACE_SUIT_PANTS", Arrays.asList(
                "&7Part of the Space Suit set.",
                "&7Wear the full set to survive the void."));
        wiki.set("SPACE_SUIT_BOOTS", Arrays.asList(
                "&7Part of the Space Suit set.",
                "&7Wear the full set to survive the void."));
        wiki.set("OXYGEN_FILLER", Arrays.asList(
                "&7Refills the oxygen stored in a",
                "&7Space Suit chestplate.",
                "&7Place the chest in the machine and",
                "&7supply it with power."));
        wiki.set("HEAT_RESISTANCE_UPGRADE", Arrays.asList(
                "&7Apply with the Space Suit Upgrader to",
                "&7protect against scorching worlds",
                "&7like Venus."));
        wiki.set("COLD_RESISTANCE_UPGRADE", Arrays.asList(
                "&7Apply with the Space Suit Upgrader to",
                "&7survive frozen worlds like Titan."));
        wiki.set("RADIATION_RESISTANCE_UPGRADE", Arrays.asList(
                "&7Apply with the Space Suit Upgrader to",
                "&7shrug off radiation on hostile worlds."));
        wiki.set("SPACE_SUIT_UPGRADER", Arrays.asList(
                "&7Installs resistance upgrades onto a",
                "&7Space Suit chestplate.",
                "&7Combine the suit with a Heat, Cold or",
                "&7Radiation upgrade here."));

        // Oxygen sealing & habitats
        wiki.set("OXYGEN_SEALER", Arrays.asList(
                "&7Floods a sealed room with breathable",
                "&7oxygen, up to 1000 blocks of space.",
                "&7Seal the area completely or the air",
                "&7leaks out into the void."));
        wiki.set("AUTOMATIC_DOOR", Arrays.asList(
                "&7Opens and closes a wall of blocks",
                "&7automatically as you approach.",
                "&7Handy for airlocks on sealed bases."));
        wiki.set("ENVIRONMENTAL_FORCEFIELD_GENERATOR", Arrays.asList(
                "&7Projects a forcefield that blocks air",
                "&7from escaping while still letting",
                "&7players and entities pass through."));
        wiki.set("SUPER_FAN", Arrays.asList(
                "&7Place next to a sealing block to extend",
                "&7its effective range by 15%."));

        // Machines
        wiki.set("ATMOSPHERIC_HARVESTER", Arrays.asList(
                "&7Sucks gases straight out of a planet's",
                "&7atmosphere - the composition depends on",
                "&7which world you are standing on."));
        wiki.set("CHEMICAL_REACTOR", Arrays.asList(
                "&7Reacts gases and chemicals together to",
                "&7synthesise new ones, such as turning",
                "&7water into oxygen and hydrogen."));
        wiki.set("ELECTROLYZER", Arrays.asList(
                "&7Uses electricity to split chemicals",
                "&7into their constituent elements."));
        wiki.set("CIRCUIT_PRESS", Arrays.asList(
                "&7Presses blocks and silicon into the",
                "&7specialised circuits used throughout",
                "&7Galactifun's recipes."));
        wiki.set("DIAMOND_ANVIL", Arrays.asList(
                "&7Compresses materials under colossal",
                "&7pressure until they become something",
                "&7else entirely."));
        wiki.set("FUSION_REACTOR", Arrays.asList(
                "&7Fires lasers at Helium-3 fusion pellets",
                "&7to release staggering amounts of energy.",
                "&7An end-game power source - and the heart",
                "&7of the Ion Rocket."));
        wiki.set("ASSEMBLY_TABLE", Arrays.asList(
                "&7The workbench for Galactifun's largest",
                "&7builds, including rockets and engines.",
                "&7Uses a wide multi-block crafting grid."));
        wiki.set("OBSERVATORY", Arrays.asList(
                "&7Scans nearby worlds remotely to reveal",
                "&7basic information without travelling",
                "&7there. Range about 0.25 light years."));
        wiki.set("PLANETARY_ANALYZER", Arrays.asList(
                "&7Reveals advanced details about the world",
                "&7you are currently standing on."));
        wiki.set("TECHNOLOGICAL_SALVAGER", Arrays.asList(
                "&7Breaks down alien relics and recovers",
                "&7whatever salvageable parts they contain."));

        // Stargates
        wiki.set("STARGATE_RING", Arrays.asList(
                "&9A segment of a Stargate.",
                "&7Assemble several rings into a full",
                "&7gateway for instant travel."));
        wiki.set("STARGATE_CONTROLLER", Arrays.asList(
                "&9Controls and dials a constructed",
                "&7Stargate."));

        // Key resources & components
        wiki.set("FALLEN_METEOR", Arrays.asList(
                "&4Crashed meteors found on Mars.",
                "&7Smelt them down to extract Tungsten."));
        wiki.set("TUNGSTEN_INGOT", Arrays.asList(
                "&bA tough metal from smelted meteors or",
                "&7traded from Martians for reinforced",
                "&7plates. Used in heat-grade alloys."));
        wiki.set("VOLCANIC_INGOT", Arrays.asList(
                "&4Forged from Ventstone in the hellish",
                "&7depths of Venus. A valuable commodity."));
        wiki.set("LASERITE", Arrays.asList(
                "&cRefined from Laserite Ore mined on Titan.",
                "&7A core ingredient of the Fusion Reactor."));
        wiki.set("FUSION_PELLET", Arrays.asList(
                "&7Compressed fuel pellets for the",
                "&7Fusion Reactor."));
        wiki.set("MOON_CHEESE", Arrays.asList(
                "&6It really is made of cheese.",
                "&7Harvested from gold ore on the Moon.",
                "&7Ew."));
        wiki.set("SPACE_GRADE_PLATE", Arrays.asList(
                "&7A heat-pressed plate of heavy-duty sheet",
                "&7and tungsten carbide.",
                "&7The backbone of rockets and machines."));
        wiki.set("LIFE_SUPPORT_MODULE", Arrays.asList(
                "&4Keeps a rocket cabin breathable.",
                "&7An essential part of every crewed rocket."));
        wiki.set("ROCKET_ENGINE", Arrays.asList(
                "&7The thrust source of a Chemical Rocket.",
                "&7Higher Mk versions give more power."));
        wiki.set("ION_ENGINE", Arrays.asList(
                "&bA hyper-efficient engine that drives the",
                "&7Ion Rocket on ions instead of fuel burn."));
        wiki.set("FUEL_TANK", Arrays.asList(
                "&6Stores rocket fuel.",
                "&7More tanks mean a greater travel range."));
    }

    @Override
    protected void disable() {
        if (shouldDisable) return;

        this.alienManager.onDisable();

        // Do this last
        instance = null;
    }

    @Override
    public void load() {
        if (!isTest) {
            // Default to not logging world settings
            Bukkit.spigot().getConfig().set("world-settings.default.verbose", false);
        }
    }

    @Nullable
    @Override
    public ChunkGenerator getDefaultWorldGenerator(@Nonnull String worldName, @Nullable String id) {
        World world = Bukkit.getWorld(worldName);
        if (world == null) return null;

        PlanetaryWorld planetaryWorld = this.worldManager.getWorld(world);
        if (planetaryWorld instanceof AlienWorld) {
            return planetaryWorld.world().getGenerator();
        }

        return null;
    }


    public static Galactifun instance() { return instance; }
}

