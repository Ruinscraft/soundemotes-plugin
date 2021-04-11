package com.ruinscraft.soundemotes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class SoundEmoteManager {

    private static final String EMOTE_URL = "https://cdn.ruinscraft.com/media/soundemotes/%s";
    private static final String EMOTE_LIST_URL = "https://cdn.ruinscraft.com/media/soundemotes/emotelist.php";
    private static final JsonParser JSON_PARSER = new JsonParser();

    private Map<String, SoundEmote> soundEmotes;

    public SoundEmoteManager() {
        soundEmotes = new HashMap<>();
    }

    public Map<String, SoundEmote> getSoundEmotes() {
        return soundEmotes;
    }

    public Set<String> getSoundEmoteNames() {
        return soundEmotes.keySet();
    }

    public SoundEmote match(String text) {
        for (String emoteName : getSoundEmoteNames()) {
            if (text.trim().equalsIgnoreCase(emoteName)) {
                return soundEmotes.get(emoteName);
            }
        }

        return null;
    }

    public synchronized CompletableFuture<Void> load() {
        return CompletableFuture.runAsync(() -> {
            try {
                URL url = new URL(EMOTE_LIST_URL);

                try (InputStreamReader reader = new InputStreamReader(url.openStream())) {
                    JsonElement emotesElement = JSON_PARSER.parse(reader);

                    if (emotesElement instanceof JsonArray) {
                        JsonArray emotesArray = emotesElement.getAsJsonArray();

                        for (JsonElement emoteElement : emotesArray) {
                            String emoteFile = emoteElement.getAsString();
                            String emoteName = emoteFile.replace(".ogg", "").trim();
                            String emoteUrl = String.format(EMOTE_URL, emoteFile).replace(" ", "%20").trim();
                            soundEmotes.put(emoteName, new SoundEmote(emoteName, emoteUrl));
                        }
                    }
                }

                System.out.println("Sound emotes loaded! (" + soundEmotes.size() + ")");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
