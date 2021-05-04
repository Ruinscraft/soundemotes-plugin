package com.ruinscraft.soundemotes;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.google.gson.JsonObject;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class NetworkUtil {

    private static String CHANNEL_PLAYED_SOUND_EMOTE = "soundemotes:played_sound_emote";

    public static void registerChannels(JavaPlugin javaPlugin) {
        javaPlugin.getServer().getMessenger().registerOutgoingPluginChannel(javaPlugin, CHANNEL_PLAYED_SOUND_EMOTE);
    }

    public static void sendPlayedSoundEmotePacket(JavaPlugin javaPlugin, Player player, PlayedSoundEmote playedSoundEmote) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        JsonObject serializedPlayedSoundEmote = serializePlayedSoundEmote(playedSoundEmote);
        out.writeUTF(serializedPlayedSoundEmote.toString());
        player.sendPluginMessage(javaPlugin, CHANNEL_PLAYED_SOUND_EMOTE, out.toByteArray());
    }

    private static JsonObject serializedSoundEmote(SoundEmote soundEmote) {
        JsonObject soundEmoteJson = new JsonObject();
        soundEmoteJson.addProperty("name", soundEmote.getName());
        soundEmoteJson.addProperty("url", soundEmote.getUrl());
        return soundEmoteJson;
    }

    private static JsonObject serializePlayedSoundEmote(PlayedSoundEmote playedSoundEmote) {
        JsonObject playedSoundEmoteJson = new JsonObject();
        playedSoundEmoteJson.add("sound_emote", serializedSoundEmote(playedSoundEmote.getSoundEmote()));
        playedSoundEmoteJson.addProperty("entity_id", playedSoundEmote.getEntityId().toString());
        return playedSoundEmoteJson;
    }

}
