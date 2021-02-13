package com.ruinscraft.soundemotes;

import org.bukkit.plugin.java.JavaPlugin;

public class SoundEmotesPlugin extends JavaPlugin {

    private SoundEmoteManager soundEmoteManager;

    public SoundEmoteManager getSoundEmoteManager() {
        return soundEmoteManager;
    }

    @Override
    public void onEnable() {
        soundEmoteManager = new SoundEmoteManager();
        soundEmoteManager.load();
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        NetworkUtil.registerChannels(this);

        SoundEmoteCommand soundEmoteCommand = new SoundEmoteCommand(this);
        getCommand("soundemote").setExecutor(soundEmoteCommand);
        getCommand("soundemote").setTabCompleter(soundEmoteCommand);
    }

}
