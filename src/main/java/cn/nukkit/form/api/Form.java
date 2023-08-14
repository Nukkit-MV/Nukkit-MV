package cn.nukkit.form.api;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.api.Internal;
import cn.nukkit.api.NukkitMVOnly;
import cn.nukkit.network.protocol.ModalFormRequestPacket;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

@NukkitMVOnly
public abstract class Form {

    public static int maxWindows = 10;

    protected static final Gson gson = new Gson();

    protected transient boolean closed = false;
    protected transient boolean isSetting = false;

    public static WeakHashMap<Player, Integer> formIds = new WeakHashMap<>();
    public static WeakHashMap<Player, Map<Integer, Form>> playerForms = new WeakHashMap<>();

    public String title;

    public transient Map<Integer, String> dataLabels = new WeakHashMap<>();
    public transient FormResponse handler;

    @Internal("called when a player is joining the server")
    public static void join(Player player) {
        formIds.put(player, 0);
        playerForms.put(player, new HashMap<>());
    }

    @Internal("called when a player is leaving the server")
    public static void quit(Player player) {
        formIds.remove(player);
        playerForms.remove(player);
    }

    /**
     * Shows the player the form window
     */
    public void send(Player player) {
        if (!formIds.containsKey(player)) formIds.put(player, 0);
        show(player, formIds.get(player) + 1);
    }

    /**
     * Shows the window form to all online players
     */
    public void sendAll() {
        for (Player player : Server.getInstance().getOnlinePlayers().values()) {
            send(player);
        }
    }

    /**
     * shows the window form using its id
     *
     * @param player The player who is getting the form window
     * @param id The form window id
     */
    protected void show(Player player, int id) {
        Map<Integer, Form> forms = playerForms.get(player);

        if (forms.size() > maxWindows) {
            player.kick("§cToo many form windows open! Kicked to prevent spam.");
            return;
        }

        ModalFormRequestPacket packet = new ModalFormRequestPacket();
        packet.formId = id;
        packet.data = getData();

        forms.put(id, this);
        player.dataPacket(packet);
    }

    public abstract void preHandle(Player player, String data);

    public String getData() {
        return gson.toJson(this);
    }

    public FormResponse getHandler() {
        return handler;
    }

    public Form setHandler(FormResponse handler) {
        this.handler = handler;
        return this;
    }

    public Form setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            this.title = null;
            this.dataLabels = null;
            this.handler = null;
        } finally {
            super.finalize();
        }
    }
}
