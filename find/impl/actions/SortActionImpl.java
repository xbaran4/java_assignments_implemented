package cz.muni.fi.pb162.find.impl.actions;

import cz.muni.fi.pb162.find.ApplicationOptions;
import cz.muni.fi.pb162.find.actions.SortAction;
import cz.muni.fi.pb162.find.filesystem.SearchEntry;
import cz.muni.fi.pb162.find.tools.SortFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a sorting action.
 *
 * @author Pavol Baran
 */
public class SortActionImpl implements SortAction {
    private ApplicationOptions options;

    /**
     * Constructs SortActionImpl with given sorting options.
     *
     * @param options used in sorting.
     */
    public SortActionImpl(ApplicationOptions options) {
        this.options = options;
    }

    @Override
    public List<SearchEntry> sorted(List<SearchEntry> entries) {
        List<SearchEntry> entriesCopy = new ArrayList<>(entries);
        entriesCopy.sort(SortFactory.create(options.getSort()));
        return entriesCopy;
    }
}
