package org.example.htmlbuilder;

import java.util.ArrayList;
import java.util.List;

public class HtmlElement extends HtmlTag {
    private List<IHtmlComponent> children = new ArrayList<>();

    public HtmlElement(String tagName) {
        super(tagName);
    }

    public void add(IHtmlComponent child) {
        children.add(child);
    }

    @Override
    public String render(int indent) {
        String spaces = " ".repeat(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(spaces).append(buildOpeningTag()).append("\n");
        for (IHtmlComponent child : children) {
            sb.append(child.render(indent + 2));
        }
        sb.append(spaces).append(buildClosingTag()).append("\n");
        return sb.toString();
    }
}
