package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class FileCabinetTest {
    private FileCabinet fileCabinet;
    private SimpleFolder firstFolder, secondFolder, thirdFolder;

    @BeforeEach
    void setupBeforeEach() {
        firstFolder = new SimpleFolder("first", "MEDIUM");
        secondFolder = new SimpleFolder("second", "SMALL");
        SimpleFolder duplicateFolder = new SimpleFolder("second", "SMALL");
        CompositeFolder firstComposite = new CompositeFolder("firstComposite", "LARGE", List.of(firstFolder, secondFolder));
        CompositeFolder topComposite = new CompositeFolder("topComposite", "LARGE", List.of(firstComposite, duplicateFolder));
        thirdFolder = new SimpleFolder("third", "MEDIUM");
        fileCabinet = new FileCabinet(List.of(topComposite, thirdFolder));
    }

    @Test
    void findFolderByName_returnsEmpty_whenNameIsNull() {
        FileCabinet emptyCabinet = new FileCabinet(null);
        Optional<Folder> result = emptyCabinet.findFolderByName(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void findFolderByName_returnsEmpty_whenNameIsBlank() {
        FileCabinet emptyCabinet = new FileCabinet(null);
        Optional<Folder> result = emptyCabinet.findFolderByName("       ");
        assertTrue(result.isEmpty());
    }

    @Test
    void findFolderByName_returnsName_whenFolderExists() {
        Optional<Folder> result = fileCabinet.findFolderByName("third");
        assertTrue(result.isPresent());
        assertSame(thirdFolder, result.get());
    }

    @Test
    void findFolderByName_returnsFirstMatch_whenFolderHasDuplicate() {
        Optional<Folder> result = fileCabinet.findFolderByName("second");
        assertTrue(result.isPresent());
        assertSame(secondFolder, result.get());
    }

    @Test
    void findFoldersBySize_returnsAllMediumFolders_whenFoldersWithSameSizeExist() {
        List<Folder> result = fileCabinet.findFoldersBySize("MEDIUM");
        assertTrue(result.contains(firstFolder));
        assertTrue(result.contains(thirdFolder));
        assertEquals(2, result.size());
    }

    @Test
    void count_returnsZero_forEmptyCabinet() {
        FileCabinet emptyCabinet = new FileCabinet(null);
        int result = emptyCabinet.count();

        assertEquals(0, result);
    }

    @Test
    void count_returnsNumberOfFolders() {
        int result = fileCabinet.count();
        assertEquals(6, result);
    }
}
