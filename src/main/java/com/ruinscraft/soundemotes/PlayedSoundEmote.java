package com.ruinscraft.soundemotes;

import java.util.UUID;

public class PlayedSoundEmote {

    private SoundEmote soundEmote;
    private UUID entityId;

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
