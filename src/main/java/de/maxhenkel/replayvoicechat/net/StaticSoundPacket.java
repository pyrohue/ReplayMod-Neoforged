package de.maxhenkel.replayvoicechat.net;

import de.maxhenkel.replayvoicechat.ReplayVoicechat;
import de.maxhenkel.replayvoicechat.playback.AudioPlaybackManager;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class StaticSoundPacket extends AbstractSoundPacket<StaticSoundPacket>
{

	public static final CustomPayload.Id<StaticSoundPacket> TYPE =
		new CustomPayload.Id<>(
			Identifier.of(ReplayVoicechat.MOD_ID, "static_sound"));

	public StaticSoundPacket(UUID id, short[] rawAudio)
	{
		super(id, rawAudio);
	}

	public StaticSoundPacket()
	{

	}

	@Override
	public void onPacket()
	{
		AudioPlaybackManager.INSTANCE.onStaticSound(this);
	}

	@Override
	public CustomPayload.Id<StaticSoundPacket> getId()
	{
		return TYPE;
	}
}
