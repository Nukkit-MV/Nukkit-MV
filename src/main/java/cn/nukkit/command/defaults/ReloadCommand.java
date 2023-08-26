package cn.nukkit.command.defaults;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.utils.TextFormat;

/**
 * @author MagicDroidX
 * Nukkit Project
 */
public class ReloadCommand extends VanillaCommand {

    public ReloadCommand(String name) {
        super(name, "%nukkit.command.reload.description", "%commands.reload.usage");
        this.setPermission("nukkit.command.reload");
        this.commandParameters.clear();
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!this.testPermission(sender)) {
            return true;
        }

        if (args.length > 0 && args[0].equals("confirm")) {
            Command.broadcastCommandMessage(sender, new TranslationContainer(TextFormat.YELLOW + "%nukkit.command.reload.reloading" + TextFormat.WHITE));

            sender.getServer().reload();

            Command.broadcastCommandMessage(sender, new TranslationContainer(TextFormat.YELLOW + "%nukkit.command.reload.reloaded" + TextFormat.WHITE));
        } else {
            sender.sendMessage(TextFormat.RED + "Reloading can cause problems for some plugins! You can force to reload by using: /reload confirm");
        }

        return true;
    }
}
