package com.ruinscraft.soundemotes.net;

import com.ruinscraft.soundemotes.emote.PlayedSoundEmote;
import io.netty.buffer.Unpooled;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class NetworkUtil {

    private static final String CHANNEL_PLAYED_SOUND_EMOTE = "soundemotes:played_sound_emote";

    public static void registerChannels(JavaPlugin javaPlugin) {
        javaPlugin.getServer().getMessenger().registerOutgoingPluginChannel(javaPlugin, CHANNEL_PLAYED_SOUND_EMOTE);
    }

    public static void sendPlayedSoundEmotePacket(JavaPlugin javaPlugin, Player player, PlayedSoundEmote playedSoundEmote) {
        PacketByteBufReimpl buf = new PacketByteBufReimpl(Unpooled.buffer());
        buf.writeString(playedSoundEmote.getSoundEmote().getName());
        buf.writeString(playedSoundEmote.getSoundEmote().getUrl());
        buf.writeUUID(playedSoundEmote.getEntityId());
        player.sendPluginMessage(javaPlugin, CHANNEL_PLAYED_SOUND_EMOTE, buf.array());
    }

}
