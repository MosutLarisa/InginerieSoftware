package org.example.htmlbuilder;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class HtmlTag implements IHtmlComponent {
    protected String tagName;
    protected Map<String, String> attributes = new LinkedHashMap<>();

    public HtmlTag(String tagName) {
        this.tagName = tagName;
    }

    public void setAttribute(String key, String value) {
        attributes.put(key, value);
    }

    protected String buildOpeningTag() {
        StringBuilder sb = new StringBuilder("<" + tagName);
        for (var e : attributes.entrySet()) {
            sb.append(" ").append(e.getKey()).append("=\"").append(e.getValue()).append("\"");
        }
        sb.append(">");
        return sb.toString();
    }

    protected String buildClosingTag() {
        return "</" + tagName + ">";
    }
}
