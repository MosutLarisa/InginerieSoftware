package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FolderTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void getSize() {
        // Arrange
        var folder = new Folder("TestFolder");
        var fileContent = "Content 123";
        folder.add(new File("Folder item",fileContent));

        // Act
        var folderSize = folder.getSize();

        // Assert
        assertEquals(fileContent.length(), folderSize);
    }

    @Test
    void getName() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    void setName() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    void getContent() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    void add() {
        // Arrange
        var subFolderName = "SubFolder";
        var subFolder =new Folder(subFolderName);
        var folder = new Folder(subFolderName);

        // Act
        folder.add(subFolder);

        // Assert
        assertTrue(folder.getContent().contains(subFolderName));
    }

    @Test
    void remove() {

        // Arrange

        // Act

        // Assert
    }
}