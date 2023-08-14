package cn.nukkit.form.api.response;

import cn.nukkit.Player;
import cn.nukkit.api.NukkitMVOnly;
import cn.nukkit.form.api.FormResponse;

import java.util.Map;

@NukkitMVOnly
public interface CustomFormResponse extends FormResponse {
    void handle(Player player, Map<Object, Object> data);
}
