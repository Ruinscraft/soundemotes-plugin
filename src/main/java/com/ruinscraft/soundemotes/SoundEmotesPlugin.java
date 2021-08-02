package com.ruinscraft.soundemotes;

import com.ruinscraft.soundemotes.emote.SoundEmoteManager;
import com.ruinscraft.soundemotes.net.NetworkUtil;
import org.bukkit.plugin.java.JavaPlugin;

public class SoundEmotesPlugin extends JavaPlugin {

    private SoundEmoteManager soundEmoteManager;
    private SpamHandler spamHandler;

    public SoundEmoteManager getSoundEmoteManager() {
        return soundEmoteManager;
    }

    public SpamHandler getSpamHandler() {
        return spamHandler;
    }

    @Override
    public void onEnable() {
        soundEmoteManager = new SoundEmoteManager();
        soundEmoteManager.load();

        spamHandler = new SpamHandler(this);

        getServer().getPluginManager().registerEvents(new SoundEmoteChatListener(this), this);

        NetworkUtil.registerChannels(this);

        SoundEmoteCommand soundEmoteCommand = new SoundEmoteCommand(this);
        getCommand("soundemote").setExecutor(soundEmoteCommand);
        getCommand("soundemote").setTabCompleter(soundEmoteCommand);
    }

}
