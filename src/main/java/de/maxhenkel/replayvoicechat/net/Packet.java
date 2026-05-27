package de.maxhenkel.replayvoicechat.net;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;

public interface Packet<T extends Packet<T>> extends CustomPayload
{

	T fromBytes(PacketByteBuf buf) throws VersionCompatibilityException;

	void toBytes(PacketByteBuf buf);

	void onPacket();

	@Override
	CustomPayload.Id<T> getId();

}
