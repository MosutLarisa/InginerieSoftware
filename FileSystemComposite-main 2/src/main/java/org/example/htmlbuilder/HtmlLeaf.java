package org.example.htmlbuilder;

public class HtmlLeaf extends HtmlTag {
    private String textContent;

    public HtmlLeaf(String tagName, String textContent) {
        super(tagName);
        this.textContent = textContent;
    }

    @Override
    public String render(int indent) {
        String spaces = " ".repeat(indent);
        return spaces + buildOpeningTag() + textContent + buildClosingTag() + "\n";
    }
}
