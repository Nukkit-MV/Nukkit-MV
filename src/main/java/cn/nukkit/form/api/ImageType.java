package cn.nukkit.form.api;

import cn.nukkit.api.NukkitMVOnly;

@NukkitMVOnly
public enum ImageType {
    PATH("path"),
    URL("url");

    final String type;

    ImageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
