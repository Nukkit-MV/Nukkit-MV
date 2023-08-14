package cn.nukkit.command.data;

import cn.nukkit.Server;
import cn.nukkit.api.NukkitMVOnly;
import cn.nukkit.api.Since;
import cn.nukkit.network.protocol.UpdateSoftEnumPacket;
import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author CreeperFace
 */
public class CommandEnum {

    public static final CommandEnum SCOREBOARD_OBJECTIVES = new CommandEnum("ScoreboardObjectives", () -> Server.getInstance().getScoreboardManager().getScoreboards().keySet());
    public static final CommandEnum ENUM_BOOLEAN = new CommandEnum("Boolean", ImmutableList.of("true", "false"));
    public static final CommandEnum ENUM_GAMEMODE = new CommandEnum("GameMode",
            ImmutableList.of("survival", "creative", "s", "c", "adventure", "a", "spectator", "view", "v", "spc"));
    public static final CommandEnum ENUM_BLOCK;
    public static final CommandEnum ENUM_ITEM;

    static {
        /*ImmutableList.Builder<String> blocks = ImmutableList.builder();
        for (Field field : BlockID.class.getDeclaredFields()) {
            blocks.add(field.getName().toLowerCase());
        }*/
        ENUM_BLOCK = new CommandEnum("Block", /*blocks.build()*/ Collections.emptyList());

        /*ImmutableList.Builder<String> items = ImmutableList.builder();
        for (Field field : ItemID.class.getDeclaredFields()) {
            items.add(field.getName().toLowerCase());
        }
        items.addAll(ENUM_BLOCK.getValues());*/
        ENUM_ITEM = new CommandEnum("Item", /*items.build()*/ Collections.emptyList());
    }

    private final String name;
    private final List<String> values;

    @NukkitMVOnly
    @Since("1.0.0-NMV")
    private final boolean isSoft; //softEnum

    @NukkitMVOnly
    @Since("1.0.0-NMV")
    private final Supplier<Collection<String>> strListSupplier;

    public CommandEnum(String name, String... values) {
        this(name, Arrays.asList(values));
    }

    public CommandEnum(String name, List<String> values) {
        this.name = name;
        this.values = values;
        this.isSoft = true;
        this.strListSupplier = null;
    }

    @NukkitMVOnly
    @Since("1.0.0-NMV")
    public CommandEnum(String name, List<String> values, boolean isSoft) {
        this.name = name;
        this.values = values;
        this.isSoft = isSoft;
        this.strListSupplier = null;
    }

    /**
     * Instantiates a new Soft Command enum.
     *
     * @param name            the name
     * @param strListSupplier the str list supplier
     */
    @NukkitMVOnly
    @Since("1.0.0-NMV")
    public CommandEnum(String name, Supplier<Collection<String>> strListSupplier) {
        this.name = name;
        this.values = null;
        this.isSoft = true;
        this.strListSupplier = strListSupplier;
    }

    @NukkitMVOnly
    @Since("1.0.0-NMV")
    public void updateSoftEnum(UpdateSoftEnumPacket.Type mode, String... value) {
        if (!this.isSoft) return;
        UpdateSoftEnumPacket pk = new UpdateSoftEnumPacket();
        pk.name = this.getName();
        pk.values = Arrays.stream(value).toList();
        pk.type = mode;
        Server.broadcastPacket(Server.getInstance().getOnlinePlayers().values(), pk);
    }

    @NukkitMVOnly
    @Since("1.0.0-NMV")
    public void updateSoftEnum() {
        if (!this.isSoft && this.strListSupplier == null) return;
        UpdateSoftEnumPacket pk = new UpdateSoftEnumPacket();
        pk.name = this.getName();
        pk.values = this.getValues();
        pk.type = UpdateSoftEnumPacket.Type.SET;
        Server.broadcastPacket(Server.getInstance().getOnlinePlayers().values(), pk);
    }

    public String getName() {
        return name;
    }

    public List<String> getValues() {
        if (this.strListSupplier == null) return values;
        else return strListSupplier.get().stream().toList();
    }

    public boolean isSoft() {
        return isSoft;
    }

    public int hashCode() {
        return name.hashCode();
    }
}
