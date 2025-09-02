package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class FileCabinet implements Cabinet {
    private final List<Folder> folders;


    public FileCabinet(List<Folder> folders) {
        this.folders = (folders == null) ? new ArrayList<>() : List.copyOf(folders);
    }

    public void traverseActionFolders(List<Folder> folders, Consumer<Folder> action) {

        for (Folder folder : folders) {
            action.accept(folder);
            if (folder instanceof MultiFolder mf) {
                traverseActionFolders(mf.getFolders(), action);
                }
            }
        }


    @Override
    public Optional<Folder> findFolderByName(String name) {
        final Optional<Folder>[] result = new Optional[]{Optional.empty()};

        traverseActionFolders(folders, folder -> {
            if (result[0].isEmpty() && folder.getName().equals(name)) {
                result[0] = Optional.of(folder);
            }
        });

        return result[0];
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        List<Folder> folderList = new ArrayList<>();
        traverseActionFolders(folders, folder ->
        { if (folder.getSize().equals(size)){
            folderList.add(folder);
        } });
        return folderList; }

    @Override
    public int count() {
        List<Folder> all = new ArrayList<>();
        traverseActionFolders(folders, all::add);
        return all.size();
    }

}
