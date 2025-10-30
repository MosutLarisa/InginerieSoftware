package org.example;

public interface IFileSystemComponent {
    String getName();
    void setName(String name);
    String getContent(int indentLevel);
}
