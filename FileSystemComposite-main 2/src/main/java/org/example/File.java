package org.example;

public class File implements IFileSystemComponent {
    private String name;
    private final String content;
    private final boolean selfClosing;

    public File(String name, String content) {
        this(name, content, false);
    }

    public File(String name, String content, boolean selfClosing) {
        this.name = name;
        this.content = content;
        this.selfClosing = selfClosing;
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
    public String getContent(int indentLevel) {
        String indent = "    ".repeat(indentLevel);
        if (selfClosing) {
            return indent + "<" + name + " " + content + " />\n";
        } else {
            return indent + "<" + name + ">" + content + "</" + name + ">\n";
        }
    }
}
