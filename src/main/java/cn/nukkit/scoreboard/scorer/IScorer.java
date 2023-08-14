package cn.nukkit.scoreboard.scorer;

import cn.nukkit.network.protocol.SetScorePacket;
import cn.nukkit.scoreboard.data.ScorerType;
import cn.nukkit.scoreboard.scoreboard.IScoreboard;
import cn.nukkit.scoreboard.scoreboard.IScoreboardLine;

public interface IScorer {

    ScorerType getScorerType();

    String getName();

    SetScorePacket.ScoreInfo toNetworkInfo(IScoreboard scoreboard, IScoreboardLine line);
}
