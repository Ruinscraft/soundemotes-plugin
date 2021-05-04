package com.ruinscraft.soundemotes;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private SoundEmotesPlugin emotesPlugin;

    public ChatListener(SoundEmotesPlugin emotesPlugin) {
        this.emotesPlugin = emotesPlugin;
    }

    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent event) {
        if (event.isCancelled()) {
            return;
        }

        Player player = event.getPlayer();
        String message = event.getMessage();
        SoundEmote soundEmote = emotesPlugin.getSoundEmoteManager().match(message);

        if (soundEmote != null) {
            PlayedSoundEmote playedSoundEmote = new PlayedSoundEmote(soundEmote, player.getUniqueId());

            for (Player onlinePlayer : emotesPlugin.getServer().getOnlinePlayers()) {
                NetworkUtil.sendPlayedSoundEmotePacket(emotesPlugin, onlinePlayer, playedSoundEmote);
            }
        }
    }

}
