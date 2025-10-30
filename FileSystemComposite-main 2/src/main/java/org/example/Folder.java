package org.example;

import java.util.ArrayList;

public class Folder implements IFileSystemComposite {
    private String name;
    private final ArrayList<IFileSystemComponent> elements = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void add(IFileSystemComponent component) {
        elements.add(component);
    }


    @Override
    public void remove(IFileSystemComponent component) {
        elements.remove(component);
    }


    @Override
    public String getContent(int indentLevel) {
        String indent = "    ".repeat(indentLevel);
        StringBuilder html = new StringBuilder();

        html.append(indent).append("<").append(name).append(">\n");
        for (IFileSystemComponent item : elements) {
            html.append(item.getContent(indentLevel + 1));
        }
        html.append(indent).append("</").append(name).append(">\n");

        return html.toString();
    }
}
