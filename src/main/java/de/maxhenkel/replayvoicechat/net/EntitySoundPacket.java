package de.maxhenkel.replayvoicechat.net;

import de.maxhenkel.replayvoicechat.ReplayVoicechat;
import de.maxhenkel.replayvoicechat.ReplayVoicechatPlugin;
import de.maxhenkel.replayvoicechat.playback.AudioPlaybackManager;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class EntitySoundPacket extends AbstractSoundPacket<EntitySoundPacket>
{

	public static final CustomPayload.Id<EntitySoundPacket> TYPE =
		new CustomPayload.Id(
			Identifier.of(ReplayVoicechat.MOD_ID, "entity_sound"));

	private boolean whispering;
	private float distance;

	public EntitySoundPacket(UUID id, short[] rawAudio, boolean whispering,
		float distance)
	{
		super(id, rawAudio);
		this.whispering = whispering;
		this.distance = distance;
	}

	public EntitySoundPacket()
	{

	}

	public boolean isWhispering()
	{
		return whispering;
	}

	public float getDistance()
	{
		return distance;
	}

	@Override
	public EntitySoundPacket fromBytes(PacketByteBuf buf)
		throws VersionCompatibilityException
	{
		super.fromBytes(buf);
		whispering = buf.readBoolean();
		if(version >= 1)
		{
			distance = buf.readFloat();
		}else
		{
			distance =
				(float)ReplayVoicechatPlugin.CLIENT_API.getVoiceChatDistance();
		}
		return this;
	}

	@Override
	public void toBytes(PacketByteBuf buf)
	{
		super.toBytes(buf);
		buf.writeBoolean(whispering);
		buf.writeFloat(distance);
	}

	@Override
	public void onPacket()
	{
		AudioPlaybackManager.INSTANCE.onEntitySound(this);
	}

	@Override
	public CustomPayload.Id<EntitySoundPacket> getId()
	{
		return TYPE;
	}
}
