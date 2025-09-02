package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        SimpleFolder simpleFolder1 = new SimpleFolder("first", "MEDIUM");
        SimpleFolder simpleFolder2 = new SimpleFolder("second", "LARGE");
        SimpleFolder simpleFolder3 = new SimpleFolder("third", "SMALL");

        List<Folder> folders = new ArrayList<>();
        folders.add(simpleFolder1);
        folders.add(simpleFolder2);

        CompositeFolder compositeFolder = new CompositeFolder("firstComposite", "SMALL", folders);
        List<Folder> topLevelFolders = new ArrayList<>();
        topLevelFolders.add(compositeFolder);

        topLevelFolders.add(simpleFolder3);
        FileCabinet fileCabinet = new FileCabinet(topLevelFolders);

        fileCabinet.traverseActionFolders(topLevelFolders, folder -> System.out.println(folder.getName()));
        Optional<Folder> foundFolder = fileCabinet.findFolderByName("third");

        foundFolder.ifPresent(folder -> System.out.println("Found folder: " + folder.getName()));
        System.out.println(fileCabinet.findFoldersBySize("SMALL"));
        System.out.println("Number of all folders: " + fileCabinet.count());


        List<Folder> emptyList = new ArrayList<>();
        FileCabinet empty = new FileCabinet(emptyList);
        System.out.println("Number of folders in empty: " + empty.count());
        System.out.println(empty.findFolderByName("Tenth"));
        System.out.println(empty.findFoldersBySize("LARGE"));


    }
}