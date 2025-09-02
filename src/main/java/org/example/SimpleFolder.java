package org.example;

public class SimpleFolder implements Folder {
    private final String name;
    private final String size;

    public SimpleFolder(String name, String size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getSize() {
        return size;
    }

    @Override
    public String getName() {
        return name;
    }
}
