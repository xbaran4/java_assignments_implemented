package cz.muni.fi.pb162.find.impl.filters;

import cz.muni.fi.pb162.find.filesystem.SearchEntry;
import cz.muni.fi.pb162.find.filters.BasicFilter;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * This class represents a filter based on file content.
 *
 * @author Pavol Baran
 */
public class FileContentFilter extends BasicFilter {
    private String regex;

    /**
     * File Content Filter.
     *
     * @param paths list of filtered entries.
     * @param regex to be matched.
     */
    public FileContentFilter(List<SearchEntry> paths, String regex) {
        super(paths);
        this.regex = regex;
    }

    @Override
    public boolean filter(SearchEntry path) {
        try {
            return Files.readString(path.getPath()).matches(regex);
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
