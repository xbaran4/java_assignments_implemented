package cz.muni.fi.pb162.find.impl.comparators;

import cz.muni.fi.pb162.find.comparators.BasicComparator;
import cz.muni.fi.pb162.find.filesystem.SearchEntry;

/**
 * This class represents comparator based on file path.
 *
 * @author Pavol Baran
 */
public class FilePathComparator implements BasicComparator {
    @Override
    public BasicComparator getNextComparator() {
        return null;
    }

    @Override
    public int compare(SearchEntry t1, SearchEntry t2) {
        return t1.getPath().toString().compareTo(t2.getPath().toString());
    }
}
