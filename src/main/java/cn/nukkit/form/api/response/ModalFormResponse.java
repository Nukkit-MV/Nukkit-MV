package cn.nukkit.form.api.response;

import cn.nukkit.Player;
import cn.nukkit.api.NukkitMVOnly;
import cn.nukkit.form.api.FormResponse;

@NukkitMVOnly
public interface ModalFormResponse extends FormResponse {
    void handle(Player player, Boolean data);
}
