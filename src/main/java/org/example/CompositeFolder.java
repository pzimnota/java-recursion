package org.example;

import java.util.List;

public class CompositeFolder implements MultiFolder {
    private final String name;
    private final String size;
    private final List<Folder> folders;

    public CompositeFolder(String name, String size, List<Folder> folders) {
        this.name = name;
        this.size = size;
        this.folders = (folders == null) ? List.of() : List.copyOf(folders);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSize() {
        return size;
    }

    @Override
    public List<Folder> getFolders() {
        return folders;
    }
}
