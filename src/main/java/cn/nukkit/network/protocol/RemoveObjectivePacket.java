package cn.nukkit.network.protocol;

import cn.nukkit.api.NukkitMVOnly;
import cn.nukkit.api.Since;

@NukkitMVOnly
@Since("1.0.0-NMV")
public class RemoveObjectivePacket extends DataPacket {

    public String objectiveName;

    @Override
    public byte pid() {
        return ProtocolInfo.REMOVE_OBJECTIVE_PACKET;
    }

    @Override
    public void decode() {
        //only server -> client
    }

    @Override
    public void encode() {
        this.reset();
        this.putString(this.objectiveName);
    }
}