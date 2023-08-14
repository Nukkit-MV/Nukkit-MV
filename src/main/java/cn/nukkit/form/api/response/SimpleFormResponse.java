package cn.nukkit.form.api.response;

import cn.nukkit.Player;
import cn.nukkit.api.NukkitMVOnly;
import cn.nukkit.form.api.FormResponse;

@NukkitMVOnly
public interface SimpleFormResponse extends FormResponse {
    void handle(Player player, Object data);
}
