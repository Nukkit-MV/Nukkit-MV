package cn.nukkit.form.api;

import cn.nukkit.Player;
import cn.nukkit.api.NukkitMVOnly;
import cn.nukkit.form.api.response.ModalFormResponse;

@NukkitMVOnly
public class ModalForm extends Form {

    private final String type = "modal";

    private String content = "";
    private String button1 = "Yes";
    private String button2 = "No";

    public ModalForm(ModalFormResponse responseHandler) {
        this.setHandler(responseHandler);
        this.title = "";
    }

    public ModalForm(String title, ModalFormResponse responseHandler) {
        this.title = title;
        this.setHandler(responseHandler);
    }

    public ModalForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ModalForm(String title, String content, ModalFormResponse responseHandler) {
        this.title = title;
        this.content = content;
        this.setHandler(responseHandler);
    }

    public ModalForm(String title, String content, String buttonFirst) {
        this.title = title;
        this.content = content;
        button1 = buttonFirst;
    }

    public ModalForm(String title, String content, String buttonFirst, ModalFormResponse responseHandler) {
        this.title = title;
        this.content = content;
        button1 = buttonFirst;
        this.setHandler(responseHandler);
    }

    public ModalForm(String title, String content, String buttonFirst, String buttonSecond) {
        this.title = title;
        this.content = content;
        button1 = buttonFirst;
        button2 = buttonSecond;
    }

    public ModalForm(String title, String content, String buttonFirst, String buttonSecond, ModalFormResponse responseHandler) {
        this.title = title;
        this.content = content;
        button1 = buttonFirst;
        button2 = buttonSecond;
        this.setHandler(responseHandler);
    }

    public ModalForm setContent(String value) {
        this.content = value;
        return this;
    }

    public ModalForm setButtonFirst(String value) {
        button1 = value;
        return this;
    }

    public ModalForm setButtonSecond(String value) {
        button2 = value;
        return this;
    }

    @Override
    public ModalFormResponse getHandler() {
        return (ModalFormResponse) super.getHandler();
    }

    @Override
    public void preHandle(Player player, String data) {
        if (this.getHandler() == null) return;
        if (data.equals("null")) {
            closed = true;
            this.getHandler().handle(player, null);
            return;
        }
        this.getHandler().handle(player, data.equals("true"));
    }
}
