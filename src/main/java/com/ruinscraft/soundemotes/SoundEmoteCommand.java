package com.ruinscraft.soundemotes;

import com.ruinscraft.soundemotes.emote.SoundEmote;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class SoundEmoteCommand implements CommandExecutor, TabCompleter {

    private final SoundEmotesPlugin emotesPlugin;

    public SoundEmoteCommand(SoundEmotesPlugin emotesPlugin) {
        this.emotesPlugin = emotesPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "/" + label + " <sound emote>");
            return true;
        }

        String joined = String.join(" ", args);
        SoundEmote soundEmote = emotesPlugin.getSoundEmoteManager().match(joined);

        if (soundEmote == null) {
            sender.sendMessage(ChatColor.RED + "No sound emote found.");
        } else {
            player.chat(soundEmote.getName());
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        StringUtil.copyPartialMatches(args[0], new ArrayList<>(emotesPlugin.getSoundEmoteManager().getSoundEmoteNames()), completions);
        return completions;
    }

}
