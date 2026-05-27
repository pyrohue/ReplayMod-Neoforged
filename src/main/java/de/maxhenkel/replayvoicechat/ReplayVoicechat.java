package de.maxhenkel.replayvoicechat;

import com.replaymod.core.Module;
import de.maxhenkel.replayvoicechat.playback.NetManager;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod("replayvoicechat")
public class ReplayVoicechat implements Module
{

	public static final String MOD_ID = "replayvoicechat";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void initClient()
	{
		NetManager.init();
	}
}
