package cn.nukkit.command.defaults;

import cn.nukkit.Nukkit;
import cn.nukkit.Player;
import cn.nukkit.command.CommandMap;
import cn.nukkit.command.CommandSender;
import cn.nukkit.form.api.CustomForm;
import cn.nukkit.form.api.ImageType;
import cn.nukkit.form.api.ModalForm;
import cn.nukkit.form.api.SimpleForm;
import cn.nukkit.form.api.response.ModalFormResponse;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginDescription;
import cn.nukkit.utils.TextFormat;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * Created on 2015/11/12 by xtypr.
 * Package cn.nukkit.command.defaults in project Nukkit .
 */
public class VersionCommand extends VanillaCommand {

    public VersionCommand(String name) {
        super(name,
                "%nukkit.command.version.description",
                "%nukkit.command.version.usage",
                new String[]{"ver", "about"}
        );
        this.setPermission("nukkit.command.version");
        this.commandParameters.clear();
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length == 0 || !sender.hasPermission("nukkit.command.version.plugins")) {
            sender.sendMessage("This server is running " + Nukkit.NUKKIT + " version " + Nukkit.VERSION);
            sender.sendMessage("MC: " + ProtocolInfo.MINECRAFT_VERSION_NETWORK);
            sender.sendMessage("API: " + Nukkit.API_VERSION);
        } else {
            StringBuilder pluginName = new StringBuilder();
            for (String arg : args) pluginName.append(arg).append(' ');
            pluginName = new StringBuilder(pluginName.toString().trim());
            final boolean[] found = {false};
            final Plugin[] exactPlugin = {sender.getServer().getPluginManager().getPlugin(pluginName.toString())};

            if (exactPlugin[0] == null) {
                pluginName = new StringBuilder(pluginName.toString().toLowerCase());
                final String finalPluginName = pluginName.toString();
                sender.getServer().getPluginManager().getPlugins().forEach((s, p) -> {
                    exactPlugin[0] = p;
                    found[0] = true;
                    if (s.toLowerCase().contains(finalPluginName)) {
                    }
                });
            } else {
                found[0] = true;
            }

            if (found[0]) {
                PluginDescription desc = exactPlugin[0].getDescription();
                sender.sendMessage(TextFormat.DARK_GREEN + desc.getName() + TextFormat.WHITE + " version " + TextFormat.DARK_GREEN + desc.getVersion());
                if (desc.getDescription() != null) {
                    sender.sendMessage(desc.getDescription());
                }
                if (desc.getWebsite() != null) {
                    sender.sendMessage("Website: " + desc.getWebsite());
                }
                List<String> authors = desc.getAuthors();
                final String[] authorsString = {""};
                authors.forEach((s) -> authorsString[0] += s);
                if (authors.size() == 1) {
                    sender.sendMessage("Author: " + authorsString[0]);
                } else if (authors.size() >= 2) {
                    sender.sendMessage("Authors: " + authorsString[0]);
                }
            } else {
                sender.sendMessage(new TranslationContainer("nukkit.command.version.noSuchPlugin"));
            }
        }
        return true;
    }

    @Override
    public boolean unregister(CommandMap commandMap) {
        return false;
    }
}
