package io.github.addoncommunity.galactifun.core.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.apache.commons.codec.Charsets;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.addoncommunity.galactifun.Galactifun;
import io.github.addoncommunity.galactifun.api.structures.Structure;
import io.github.addoncommunity.galactifun.api.structures.StructureRotation;
import io.github.addoncommunity.galactifun.util.MaterialCompat;
import io.github.addoncommunity.galactifun.util.Messages;
import io.github.mooy1.infinitylib.commands.SubCommand;
import io.github.mooy1.infinitylib.common.PersistentType;

public final class StructureCommand extends SubCommand {

    private final Map<String, Structure> savedStructures = new HashMap<>();
    private final NamespacedKey pos1;
    private final NamespacedKey pos2;
    private final File saveFolder;

    public StructureCommand(Galactifun galactifun) {
        super("structure", "The command for structures", true);

        this.saveFolder = new File(galactifun.getDataFolder(), "saved_structures");
        this.pos1 = new NamespacedKey("galactifun", "pos1");
        this.pos2 = new NamespacedKey("galactifun", "pos2");
    }

    @Override
    public void execute(@Nonnull CommandSender sender, String[] args) {
        if (args.length == 1 || !(sender instanceof Player)) {
            return;
        }
        Player p = (Player) sender;

        if (args[0].equals("save")) {
            if (args.length != 2) {
                Messages.send(p, ChatColor.RED, "structure.save-usage", "Usage: /galactifun save <name>");
                return;
            }

            Location pos1 = p.getPersistentDataContainer().get(this.pos1, PersistentType.LOCATION);
            if (pos1 == null) {
                Messages.send(p, ChatColor.RED, "structure.pos1-not-set", "pos1 not set!");
                return;
            }

            Location pos2 = p.getPersistentDataContainer().get(this.pos2, PersistentType.LOCATION);
            if (pos2 == null) {
                Messages.send(p, ChatColor.RED, "structure.pos2-not-set", "pos2 not set!");
                return;
            }

            StructureRotation rotation = StructureRotation.fromFace(p.getFacing());
            Structure struct = Structure.create(rotation, pos1.getBlock(), pos2.getBlock());

            File file = new File(this.saveFolder, args[1] + ".gs");
            file.getParentFile().mkdirs();
            if (file.exists()) {
                try {
                    Files.write(file.toPath(), struct.saveToString().getBytes(Charsets.UTF_8));
                    this.savedStructures.put(args[1], struct);
                    Messages.send(p, ChatColor.GREEN, "structure.saved", "Saved as '%name%'!",
                            line -> line.replace("%name%", args[1]));
                } catch (IOException e) {
                    e.printStackTrace();
                    Messages.send(p, ChatColor.RED, "structure.save-failed", "Error saving file! Check the console!");
                }
            }
            return;
        }

        Block target = p.getTargetBlockExact(32);
        if (target == null || MaterialCompat.isAir(target.getType())) {
            Messages.send(p, ChatColor.RED, "structure.must-target-block", "You must target a block!");
            return;
        }

        if (args[0].equals("pos1")) {
            p.getPersistentDataContainer().set(this.pos1, PersistentType.LOCATION, target.getLocation());
            Messages.send(p, ChatColor.GREEN, "structure.pos1-set", "Set pos1 to %position%",
                    line -> line.replace("%position%", describe(p, target)));
            return;
        }

        if (args[0].equals("pos2")) {
            p.getPersistentDataContainer().set(this.pos2, PersistentType.LOCATION, target.getLocation());
            Messages.send(p, ChatColor.GREEN, "structure.pos2-set", "Set pos2 to %position%",
                    line -> line.replace("%position%", describe(p, target)));
            return;
        }

        if (args[0].equals("paste")) {
            if (args.length != 2) {
                Messages.send(p, ChatColor.RED, "structure.paste-usage", "Usage: /galactifun paste <name>");
                return;
            }

            Structure saved = this.savedStructures.get(args[1]);

            if (saved == null) {
                saved = Structure.getByKey(args[1]);
                if (saved == null) {
                    Messages.send(p, ChatColor.RED, "structure.unknown", "Unknown structure '%name%'!",
                            line -> line.replace("%name%", args[1]));
                    return;
                }
            }

            saved.paste(target, StructureRotation.fromFace(p.getFacing()));
            Messages.send(p, ChatColor.GREEN, "structure.pasted", "Pasted!");
        }
    }

    private static String describe(Player p, Block l) {
        String coords = l.getX() + "x" + l.getY() + "y" + l.getZ() + "z";
        return Messages.get(p, "structure.position", "%coords% in %world%")
                .replace("%coords%", coords)
                .replace("%world%", l.getWorld().getName());
    }

    @Override
    public void complete(@Nonnull CommandSender commandSender, String[] args, @Nonnull List<String> options) {
        if (args.length == 1) {
            options.addAll(Arrays.asList("pos1", "pos2", "save", "paste"));
        } else if (args.length == 2 && args[0].equals("paste")) {
            options.addAll(this.savedStructures.keySet());
            options.addAll(Structure.getLoadedKeys());
        }
    }

}
