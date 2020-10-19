package cz.muni.fi.pb162.find.impl.filters;

import cz.muni.fi.pb162.find.filesystem.SearchEntry;
import cz.muni.fi.pb162.find.filters.BasicFilter;

import java.util.List;

/**
 * This class represents a filter based on file name.
 *
 * @author Pavol Baran
 */
public class FileNameFilter extends BasicFilter {
    private String regex;

    /**
     * File Name Filter.
     *
     * @param paths list of filtered entries.
     * @param regex to be matched.
     */
    public FileNameFilter(List<SearchEntry> paths, String regex) {
        super(paths);
        this.regex = regex;
    }

    @Override
    public boolean filter(SearchEntry path) {
        return path.getFileName().toString().matches(regex);
    }
}
