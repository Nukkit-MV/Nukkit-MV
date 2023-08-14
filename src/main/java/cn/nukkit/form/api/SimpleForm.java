package cn.nukkit.form.api;

import cn.nukkit.Player;
import cn.nukkit.api.NukkitMVOnly;
import cn.nukkit.form.api.response.SimpleFormResponse;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;

import java.util.ArrayList;
import java.util.List;

@NukkitMVOnly
public class SimpleForm extends Form {

    private final String type = "form";

    private String content = "";
    private List<ElementButton> buttons;

    public SimpleForm() {
        this.buttons = new ArrayList<>();
    }
    public SimpleForm(SimpleFormResponse handler) {
        this.buttons = new ArrayList<>();
        this.setHandler(handler);
    }

    public SimpleForm(String title) {
        this.title = title;
        this.buttons = new ArrayList<>();
    }

    public SimpleForm(String title, SimpleFormResponse handler) {
        this.title = title;
        this.buttons = new ArrayList<>();
        this.setHandler(handler);
    }

    public SimpleForm(String title, String content, SimpleFormResponse handler) {
        this.title = title;
        this.content = content;
        this.buttons = new ArrayList<>();
        this.setHandler(handler);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ElementButton> getButtons() {
        return buttons;
    }

    public void addButton(ElementButton button) {
        this.buttons.add(button);
    }

    public void addButton(ElementButton button, String label) {
        dataLabels.put(this.buttons.size(), label);
        buttons.add(button);
    }

    public SimpleForm addContent(String value) {
        this.setContent(this.getContent() + value);
        return this;
    }

    public SimpleForm addContentLine(String value) {
        return addContent(value + "\n");
    }

    public SimpleForm addContentOnNextLine(String value) {
        return addContent("\n" + value);
    }

    public SimpleForm addButton(String text, String label) {
        this.addButton(new ElementButton(text), label);
        return this;
    }

    public SimpleForm addButton(String text) {
        this.addButton(new ElementButton(text));
        return this;
    }

    public SimpleForm addButton(String text, ElementButtonImageData image) {
        this.addButton(new ElementButton(text,image));
        return this;
    }

    public SimpleForm addButton(String text, ElementButtonImageData image, String label) {
        this.addButton(new ElementButton(text, image), label);
        return this;
    }

    public SimpleForm addButton(String text, ImageType type, String ico) {
        ElementButton button = new ElementButton(text);
        button.addImage(new ElementButtonImageData(type.getType(), ico));
        this.addButton(button);
        return this;
    }

    public SimpleForm addButton(String text, ImageType type, String ico, String label) {
        ElementButton button = new ElementButton(text);
        button.addImage(new ElementButtonImageData(type.getType(), ico));
        this.addButton(button, label);
        return this;
    }

    public SimpleForm setHandler(SimpleFormResponse handler) {
        this.handler = handler;
        return this;
    }

    @Override
    public void preHandle(Player player, String data) {
        if (this.getHandler() == null) return;
        if (data.equals("null")) {
            this.closed = true;
            this.getHandler().handle(player, null);
            return;
        }

        int id = Integer.parseInt(data);

        if (id > this.buttons.size()) {
            this.getHandler().handle(player, null);
            return;
        }

        if (!this.dataLabels.containsKey(id)) {
            this.getHandler().handle(player, data);
            return;
        }

        this.getHandler().handle(player, this.dataLabels.get(id));
    }

    @Override
    public SimpleFormResponse getHandler() {
        return (SimpleFormResponse) super.getHandler();
    }
}
