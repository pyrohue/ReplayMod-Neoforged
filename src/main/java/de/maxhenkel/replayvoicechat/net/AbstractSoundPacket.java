package de.maxhenkel.replayvoicechat.net;

import com.replaymod.core.utils.Utils;
import net.minecraft.network.PacketByteBuf;

import java.util.UUID;

public abstract class AbstractSoundPacket<T extends Packet<T>>
	implements Packet<T>
{

	public static final short CURRENT_VERSION = 1;

	protected short version;
	protected UUID id;
	protected short[] rawAudio;

	public AbstractSoundPacket(UUID id, short[] rawAudio)
	{
		version = CURRENT_VERSION;
		this.id = id;
		this.rawAudio = rawAudio;
	}

	public AbstractSoundPacket()
	{

	}

	/*public static final StreamCodec<PacketByteBuf, AbstractSoundPacket> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.STRING_UTF8,
			MyData::name,
			ByteBufCodecs.VAR_INT,
			MyData::age,
			MyData::new
	);*/

	public UUID getPacketId()
	{
		return id;
	}

	public short[] getRawAudio()
	{
		return rawAudio;
	}

	@Override
	public T fromBytes(PacketByteBuf buf) throws VersionCompatibilityException
	{
		version = buf.readShort();
		if(version != CURRENT_VERSION && version != 0)
		{
			throw new VersionCompatibilityException("Incompatible version");
		}
		id = buf.readUuid();
		rawAudio = Utils.bytesToShorts(buf.readByteArray());
		return (T)this;
	}

	@Override
	public void toBytes(PacketByteBuf buf)
	{
		buf.writeShort(version);
		buf.writeUuid(id);
		buf.writeByteArray(Utils.shortsToBytes(rawAudio));
	}
}
