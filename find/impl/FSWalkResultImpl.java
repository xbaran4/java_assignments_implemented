package cz.muni.fi.pb162.find.impl;

import cz.muni.fi.pb162.find.ApplicationOptions;
import cz.muni.fi.pb162.find.filesystem.DirEntry;
import cz.muni.fi.pb162.find.filesystem.FSWalkResult;
import cz.muni.fi.pb162.find.filesystem.FileEntry;
import cz.muni.fi.pb162.find.filesystem.SearchEntry;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents implementation of a File System Walk Result.
 *
 * @author Pavol Baran
 */
public class FSWalkResultImpl extends FSWalkResult {

    private List<SearchEntry> directories;
    private List<SearchEntry> files;

    /**
     * Constructor for class FSWalkResult
     *
     * @param options application options
     */
    public FSWalkResultImpl(ApplicationOptions options) {
        super(options);
        directories = new ArrayList<>();
        files = new ArrayList<>();
    }

    @Override
    public List<SearchEntry> getDirectories() {
        return directories;

    }

    @Override
    public List<SearchEntry> getFiles() {
        return files;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        SearchEntry fileEntry = new FileEntry(file);
        fileEntry.setSize(attrs.size());
        files.add(fileEntry);
        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        SearchEntry dirEntry = new DirEntry(dir);
        dirEntry.setSize(0);
        directories.add(dirEntry);
        return super.postVisitDirectory(dir, exc);
    }
}
