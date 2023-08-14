package cn.nukkit.scoreboard.manager;

import cn.nukkit.Player;
import cn.nukkit.entity.EntityLiving;
import cn.nukkit.scoreboard.data.DisplaySlot;
import cn.nukkit.scoreboard.displayer.IScoreboardViewer;
import cn.nukkit.scoreboard.scoreboard.IScoreboard;
import cn.nukkit.scoreboard.storage.IScoreboardStorage;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Set;

public interface IScoreboardManager {
    boolean addScoreboard(IScoreboard scoreboard);

    boolean removeScoreboard(IScoreboard scoreboard);

    boolean removeScoreboard(String objectiveName);

    @Nullable IScoreboard getScoreboard(String objectiveName);

    Map<String, IScoreboard> getScoreboards();

    boolean containScoreboard(IScoreboard scoreboard);

    boolean containScoreboard(String name);

    Map<DisplaySlot, IScoreboard> getDisplay();

    @Nullable IScoreboard getDisplaySlot(DisplaySlot slot);

    void setDisplay(DisplaySlot slot, @Nullable IScoreboard scoreboard);

    Set<IScoreboardViewer> getViewers();

    boolean addViewer(IScoreboardViewer viewer);

    boolean removeViewer(IScoreboardViewer viewer);

    void onPlayerJoin(Player player);

    void beforePlayerQuit(Player player);

    void onEntityDead(EntityLiving entity);

    IScoreboardStorage getStorage();

    void save();

    void read();
}
