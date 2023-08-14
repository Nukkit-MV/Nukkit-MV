package cn.nukkit.form.api;

import cn.nukkit.Player;
import cn.nukkit.api.NukkitMVOnly;
import cn.nukkit.form.api.response.CustomFormResponse;
import cn.nukkit.form.element.*;
import com.google.common.reflect.TypeToken;

import java.util.*;

@NukkitMVOnly
public class CustomForm extends Form {

    public final String type = "custom_form";

    private ElementButtonImageData icon;
    private List<Element> content;

    public CustomForm(String title) {
        this(title, null);
    }

    public CustomForm(CustomFormResponse response) {
        this("", response);
    }

    public CustomForm(String title, CustomFormResponse responseHandler) {
        this.title = title;
        this.content = new ArrayList<>();
        this.setHandler(responseHandler);
    }

    public void addElement(Element element) {
        this.content.add(element);
    }

    public void addElement(Element element, String label) {
        dataLabels.put(content.size(), label);
        content.add(element);
    }

    /**
     * Adds a new label element to the form window
     *
     * @param value The text for the element
     * @return the form window
     */
    public CustomForm addLabel(String value) {
        this.addElement(new ElementLabel(value), value);
        return this;
    }

    /**
     * Adds a new label element to the form window with its label and text
     *
     * @param value The text for the label
     * @param label the label
     * @return the form window
     */
    public CustomForm addLabel(String value, String label) {
        this.addElement(new ElementLabel(value), label);
        return this;
    }

    public CustomForm setIcon(ElementButtonImageData data) {
        this.icon = data;
        return this;
    }

    public CustomForm setIcon(ImageType type, String path) {
        this.icon = new ElementButtonImageData(type.getType(), path);
        return this;
    }

    public CustomForm addInput() {
        ElementInput element = new ElementInput("");
        this.addElement(element);
        return this;
    }

    public CustomForm addInput(String name) {
        ElementInput element = new ElementInput(name);
        this.addElement(element);
        return this;
    }

    public CustomForm addInput(String name, String placeholder) {
        ElementInput element = new ElementInput(name, placeholder);
        this.addElement(element);
        return this;
    }

    public CustomForm addInput(String name, String placeholder, String defaultText) {
        ElementInput element = new ElementInput(name, placeholder, defaultText);
        this.addElement(element);
        return this;
    }

    public CustomForm addInput(ElementInput elementInput, String label) {
        this.addElement(elementInput, label);
        return this;
    }

    public CustomForm addToggle() {
        ElementToggle element = new ElementToggle("");
        this.addElement(element);
        return this;
    }

    public CustomForm addToggle(String name) {
        ElementToggle element = new ElementToggle(name);
        this.addElement(element);
        return this;
    }

    public CustomForm addToggle(String name, boolean defaultValue) {
        ElementToggle element = new ElementToggle(name, defaultValue);
        this.addElement(element);
        return this;
    }

    public CustomForm addToggle(ElementToggle elementToggle, String label) {
        this.addElement(elementToggle, label);
        return this;
    }

    public CustomForm addDropDown(String name, List<String> list) {
        ElementDropdown element = new ElementDropdown(name, list);
        this.addElement(element);
        return this;
    }


    public CustomForm addDropDown(String name, List<String> list, int defaultValue) {
        ElementDropdown element = new ElementDropdown(name, list, defaultValue);
        this.addElement(element);
        return this;
    }

    public CustomForm addToggle(ElementDropdown elementDropdown, String label) {
        this.addElement(elementDropdown, label);
        return this;
    }

    public CustomForm addSlider(String name, float min, float max) {
        ElementSlider element = new ElementSlider(name, min, max);
        this.addElement(element);
        return this;
    }

    public CustomForm addSlider(String name, float min, float max, int step) {
        ElementSlider element = new ElementSlider(name, min, max, step, 3);
        this.addElement(element);
        return this;
    }

    public CustomForm addSlider(String name, float min, float max, int step, float defaultValue, String label) {
        return this.addSlider(new ElementSlider(name, min, max, step, defaultValue), label);
    }

    public CustomForm addSlider(String name, float min, float max, int step, float defaultValue) {
        this.addElement(new ElementSlider(name, min, max, step, defaultValue));
        return this;
    }

    public CustomForm addSlider(ElementSlider elementSlider, String label) {
        this.addElement(elementSlider,label);
        return this;
    }

    public CustomForm addStepSlider(String name, List<String> list) {
        ElementStepSlider element = new ElementStepSlider(name, list);
        this.addElement(element);
        return this;
    }

    public CustomForm addStepSlider(String name, List<String> list, int defaultStep) {
        ElementStepSlider element = new ElementStepSlider(name, list, defaultStep);
        this.addElement(element);
        return this;
    }

    public CustomForm addStepSlider(String name, List<String> list, int defaultStep, String label) {
        this.addElement(new ElementStepSlider(name, list, defaultStep), label);
        return this;
    }

    public CustomForm addStepSlider(ElementStepSlider elementStepSlider, String label) {
        this.addElement(elementStepSlider, label);
        return this;
    }

    public List<Element> getElements() {
        return content;
    }

    @Override
    public CustomFormResponse getHandler() {
        return (CustomFormResponse) super.getHandler();
    }

    @Override
    public void preHandle(Player player, String data) {
        if (this.getHandler() == null) return;
        if (data.equals("null")) {
            this.closed = true;
            this.getHandler().handle(player, null);
            return;
        }

        List<String> elementResponses = gson.fromJson(data, new TypeToken<List<String>>(){}.getType());
        int i = 0;

        HashMap<Object, Object> responses = new HashMap<>();
        boolean corrupted = false;

        for (String elementData : elementResponses) {
            Object id = dataLabels.containsKey(i) ? dataLabels.get(i) : i;
            Element e = content.get(i);

            if (e == null) {
                corrupted = true;
                continue;
            }

            if (e instanceof ElementLabel elementLabel) {
                responses.put(id, elementLabel.getText());
            } else if (e instanceof ElementDropdown elementDropdown) {
                responses.put(id, elementDropdown.getOptions().get(Integer.parseInt(elementData)));
            } else if (e instanceof ElementInput) {
                responses.put(id, elementData);
            } else if (e instanceof ElementSlider) {
                responses.put(id, Float.parseFloat(elementData));
            } else if (e instanceof ElementStepSlider elementStepSlider) {
                responses.put(id, elementStepSlider.getSteps().get(Integer.parseInt(elementData)));
            } else if (e instanceof ElementToggle) {
                responses.put(id, Boolean.parseBoolean(elementData));
            }

            i++;
        }

        if (corrupted) {
            this.getHandler().handle(player, responses);
        }
    }
}
