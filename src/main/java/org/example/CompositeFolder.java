package org.example;

import java.util.List;

public record CompositeFolder(String getName, String getSize, List<Folder> getFolders) implements MultiFolder {
    public CompositeFolder(String getName, String getSize, List<Folder> getFolders) {
        this.getName = getName;
        this.getSize = getSize;
        this.getFolders = (getFolders == null) ? List.of() : List.copyOf(getFolders);
    }
}
