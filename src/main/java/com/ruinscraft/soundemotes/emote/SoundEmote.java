package com.ruinscraft.soundemotes.emote;

public class SoundEmote {

    private final String name;
    private final String url;

    public SoundEmote(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

}
