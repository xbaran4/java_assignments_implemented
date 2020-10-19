package cz.muni.fi.pb162.find.impl.actions;

import cz.muni.fi.pb162.find.ApplicationOptions;
import cz.muni.fi.pb162.find.actions.FilterAction;
import cz.muni.fi.pb162.find.filesystem.SearchEntry;
import cz.muni.fi.pb162.find.impl.filters.FileContentFilter;
import cz.muni.fi.pb162.find.impl.filters.FileNameFilter;
import cz.muni.fi.pb162.find.impl.filters.MaxSizeFilter;
import cz.muni.fi.pb162.find.impl.filters.MinSizeFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a sorting action.
 *
 * @author Pavol Baran
 */
public class FilterActionImpl implements FilterAction {
    private ApplicationOptions options;

    /**
     * Constructs FilterActionImpl with given sorting options.
     *
     * @param options used in filtering.
     */
    public FilterActionImpl(ApplicationOptions options) {
        this.options = options;
    }

    @Override
    public List<SearchEntry> filter(List<SearchEntry> entries) {
        List<SearchEntry> entriesCopy = new ArrayList<>(entries);

        if (options.getNameRegex() != null) {
            entriesCopy = new FileNameFilter(entriesCopy, options.getNameRegex()).filtered();
        }
        if (options.getTextRegex() != null) {
            entriesCopy = new FileContentFilter(entriesCopy, options.getTextRegex()).filtered();
        }
        if (options.getSizeMax() != null) {
            entriesCopy = new MaxSizeFilter(entriesCopy, options.getSizeMax()).filtered();
        }
        if (options.getSizeMin() != null) {
            entriesCopy = new MinSizeFilter(entriesCopy, options.getSizeMin()).filtered();
        }
        return entriesCopy;
    }
}
