package cn.nukkit.scoreboard.displayer;

import cn.nukkit.scoreboard.data.DisplaySlot;
import cn.nukkit.scoreboard.scoreboard.IScoreboard;
import cn.nukkit.scoreboard.scoreboard.IScoreboardLine;

public interface IScoreboardViewer {
    void display(IScoreboard scoreboard, DisplaySlot slot);

    void hide(DisplaySlot slot);

    void removeScoreboard(IScoreboard scoreboard);

    void removeLine(IScoreboardLine line);

    void updateScore(IScoreboardLine line);
}
