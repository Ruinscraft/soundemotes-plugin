package com.ruinscraft.soundemotes;

import com.ruinscraft.soundemotes.emote.PlayedSoundEmote;
import com.ruinscraft.soundemotes.emote.SoundEmote;
import com.ruinscraft.soundemotes.net.NetworkUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class SoundEmoteChatListener implements Listener {

    private final SoundEmotesPlugin emotesPlugin;

    public SoundEmoteChatListener(SoundEmotesPlugin emotesPlugin) {
        this.emotesPlugin = emotesPlugin;
    }

    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent event) {
        if (event.isCancelled()) return;

        emotesPlugin.getSpamHandler().addMessage(event.getPlayer().getUniqueId());

        if (!emotesPlugin.getSpamHandler().canSendMessage(event.getPlayer().getUniqueId())) {
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
