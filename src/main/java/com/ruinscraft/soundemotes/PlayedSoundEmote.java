package com.ruinscraft.soundemotes;

public class PlayedSoundEmote {

    private SoundEmote soundEmote;
    private String world;
    private int x;
    private int y;
    private int z;

    public PlayedSoundEmote(SoundEmote soundEmote, String world, int x, int y, int z) {
        this.soundEmote = soundEmote;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public SoundEmote getSoundEmote() {
        return soundEmote;
    }

    public String getWorld() {
        return world;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

}
