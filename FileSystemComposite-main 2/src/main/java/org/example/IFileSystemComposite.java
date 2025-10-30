package org.example;

public interface IFileSystemComposite extends IFileSystemComponent {
    void add(IFileSystemComponent component);
    void remove(IFileSystemComponent component);
}
