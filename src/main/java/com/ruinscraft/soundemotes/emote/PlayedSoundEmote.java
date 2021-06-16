package com.ruinscraft.soundemotes.emote;

import java.util.UUID;

public class PlayedSoundEmote {

    private final SoundEmote soundEmote;
    private final UUID entityId;

    public PlayedSoundEmote(SoundEmote soundEmote, UUID entityId) {
        this.soundEmote = soundEmote;
        this.entityId = entityId;
    }

    public SoundEmote getSoundEmote() {
        return soundEmote;
    }

    public UUID getEntityId() {
        return entityId;
    }

}
