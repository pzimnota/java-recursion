package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

public class FileCabinet implements Cabinet {
    private final List<Folder> folders;


    public FileCabinet(List<Folder> folders) {
        this.folders = (folders == null) ? new ArrayList<>() : List.copyOf(folders);
    }

    public boolean traverseActionFolders(List<Folder> folders, Predicate<Folder> predicate) {
        if (folders == null) return false;

        for (Folder folder : folders) {
            if (predicate.test(folder)) {
                return true;
            }
            if (folder instanceof MultiFolder mf) {
                List<Folder> children = mf.getFolders() == null ? List.of() : mf.getFolders();
                if (traverseActionFolders(children, predicate)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Optional<Folder> findFolderByName(String name) {
        if (name == null ) return Optional.empty();
        String finalName = name.trim();
        if(finalName.isEmpty()) return Optional.empty();

        AtomicReference<Folder> found = new AtomicReference<>(null);
        traverseActionFolders(folders, folder -> {
            if (finalName.equals(folder.getName())) {
                found.set(folder);
                return true;
            }
            return false;
        });
        return Optional.ofNullable(found.get());
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        if (size == null) return List.of();
        String normalizedSize = size.trim();
        if (normalizedSize.isEmpty()) return List.of();
        List<Folder> folderList = new ArrayList<>();
        traverseActionFolders(folders, folder -> {
            if (normalizedSize.equalsIgnoreCase(folder.getSize())) {
                folderList.add(folder);
            }
            return false;
        });
        return folderList;
    }

    @Override
    public int count() {
        AtomicInteger count = new AtomicInteger();
        traverseActionFolders(folders, _ -> {
            count.getAndIncrement();
            return false;
        });
        return count.get();
    }

}
