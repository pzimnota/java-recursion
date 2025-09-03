package org.example;

import java.util.List;

public record CompositeFolder(String name, String size, List<Folder> folders) implements MultiFolder {
    public CompositeFolder(String name, String size, List<Folder> folders) {
        this.name = name;
        this.size = size;
        this.folders = (folders == null) ? List.of() : List.copyOf(folders);
    }
}
