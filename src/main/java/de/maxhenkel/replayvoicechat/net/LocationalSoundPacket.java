package de.maxhenkel.replayvoicechat.net;

import de.maxhenkel.replayvoicechat.ReplayVoicechat;
import de.maxhenkel.replayvoicechat.ReplayVoicechatPlugin;
import de.maxhenkel.replayvoicechat.playback.AudioPlaybackManager;
import de.maxhenkel.voicechat.api.Position;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class LocationalSoundPacket
	extends AbstractSoundPacket<LocationalSoundPacket>
{

	public static final CustomPayload.Id<LocationalSoundPacket> TYPE =
		new CustomPayload.Id(
			Identifier.of(ReplayVoicechat.MOD_ID, "locational_sound"));

	private Position location;
	private float distance;

	public LocationalSoundPacket(UUID id, short[] rawAudio, Position location,
		float distance)
	{
		super(id, rawAudio);
		this.location = location;
		this.distance = distance;
	}

	public LocationalSoundPacket()
	{

	}

	public Position getLocation()
	{
		return location;
	}

	public float getDistance()
	{
		return distance;
	}

	@Override
	public LocationalSoundPacket fromBytes(PacketByteBuf buf)
		throws VersionCompatibilityException
	{
		super.fromBytes(buf);
		location = ReplayVoicechatPlugin.CLIENT_API.createPosition(
			buf.readDouble(), buf.readDouble(), buf.readDouble());
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
		buf.writeDouble(location.getX());
		buf.writeDouble(location.getY());
		buf.writeDouble(location.getZ());
		buf.writeFloat(distance);
	}

	@Override
	public void onPacket()
	{
		AudioPlaybackManager.INSTANCE.onLocationalSound(this);
	}

	@Override
	public CustomPayload.Id<LocationalSoundPacket> getId()
	{
		return TYPE;
	}
}
