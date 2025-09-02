package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        FileCabinet fileCabinet = getFileCabinet();

        Optional<Folder> foundFolder = fileCabinet.findFolderByName("third");
        foundFolder.ifPresent(folder -> System.out.println("Found folder: " + folder.getName()));
        System.out.println("Present? " + fileCabinet.findFolderByName("missing").isPresent());

        List<Folder> smallFolders = fileCabinet.findFoldersBySize("SMALL       ");
        System.out.println("Small folders: " + smallFolders);
        System.out.println(fileCabinet.findFoldersBySize("tiny"));


        System.out.println("Number of all folders: " + fileCabinet.count());


        List<Folder> emptyList = new ArrayList<>();
        FileCabinet empty = new FileCabinet(emptyList);
        System.out.println("Number of folders in empty: " + empty.count());
        System.out.println(empty.findFolderByName("Tenth"));
        System.out.println(empty.findFoldersBySize("LARGE"));


    }


    private static FileCabinet getFileCabinet() {
        SimpleFolder simpleFolder1 = new SimpleFolder("first", "MEDIUM");
        SimpleFolder simpleFolder2 = new SimpleFolder("second", "LARGE");
        SimpleFolder simpleFolder3 = new SimpleFolder("third", "SMALL");
        SimpleFolder simpleFolder4 = new SimpleFolder("forth", "MEDIUM");

        List<Folder> folders = new ArrayList<>();
        folders.add(simpleFolder1);
        folders.add(simpleFolder2);
        CompositeFolder compositeFolder = new CompositeFolder("firstComposite", "SMALL", folders);

        List<Folder> folders2 = new ArrayList<>();
        folders2.add(simpleFolder4);
        CompositeFolder compositeFolderInside = new CompositeFolder("compositeInside", "LARGE", folders2);

        List<Folder> folders3 = new ArrayList<>();
        folders3.add(compositeFolderInside);

        CompositeFolder compositeFolderTop = new CompositeFolder("compositeTop", "LARGE", folders3);

        List<Folder> topLevelFolders = new ArrayList<>();
        topLevelFolders.add(compositeFolder);
        topLevelFolders.add(simpleFolder3);
        topLevelFolders.add(compositeFolderTop);

        return new FileCabinet(topLevelFolders);
    }
}