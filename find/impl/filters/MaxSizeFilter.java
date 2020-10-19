package cz.muni.fi.pb162.find.impl.filters;

import cz.muni.fi.pb162.find.filesystem.SearchEntry;
import cz.muni.fi.pb162.find.filters.BasicFilter;

import java.util.List;

/**
 * This class represents a filter based on max size of a SearchEntry.
 *
 * @author Pavol Baran
 */
public class MaxSizeFilter extends BasicFilter {
    private long maxSize;

    /**
     * Max Size Filter.
     *
     * @param paths list of filtered entries.
     * @param maxSize maximum size of a SearchEntry.
     */
    public MaxSizeFilter(List<SearchEntry> paths, long maxSize) {
        super(paths);
        this.maxSize = maxSize;
    }

    @Override
    public boolean filter(SearchEntry path) {
        return path.getSize() <= maxSize;
    }
}
