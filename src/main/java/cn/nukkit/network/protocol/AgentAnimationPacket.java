package cn.nukkit.network.protocol;

public class AgentAnimationPacket extends DataPacket {

    public byte animation;
    public long runtimeEntityId;

    @Override
    public byte pid() {
        return (byte) ProtocolInfo.AGENT_ANIMATION;
    }

    @Override
    public int packetId() {
        return ProtocolInfo.AGENT_ANIMATION;
    }

    @Override
    public void decode() {
        this.animation = (byte) getByte();
        this.runtimeEntityId = getEntityRuntimeId();
    }

    @Override
    public void encode() {
        putByte(this.animation);
        putEntityRuntimeId(this.runtimeEntityId);
    }
}
