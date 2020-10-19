package cz.muni.fi.pb162.find.impl.filters;

import cz.muni.fi.pb162.find.filesystem.SearchEntry;
import cz.muni.fi.pb162.find.filters.BasicFilter;

import java.util.List;

/**
 * This class represents a filter based on min size of a SearchEntry.
 *
 * @author Pavol Baran
 */
public class MinSizeFilter extends BasicFilter {
    private long minSize;

    /**
     * Min Size Filter.
     *
     * @param paths list of filtered entries.
     * @param minSize minimum size of a SearchEntry.
     */
    public MinSizeFilter(List<SearchEntry> paths, long minSize) {
        super(paths);
        this.minSize = minSize;
    }

    @Override
    public boolean filter(SearchEntry path) {
        return path.getSize() >= minSize;
    }
}
