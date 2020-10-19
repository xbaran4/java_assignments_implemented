package cz.muni.fi.pb162.find.impl.comparators;

import cz.muni.fi.pb162.find.comparators.BasicComparator;
import cz.muni.fi.pb162.find.filesystem.SearchEntry;

/**
 * This class represents comparator based on file name.
 *
 * @author Pavol Baran
 */
public class FileNameComparator implements BasicComparator {
    private BasicComparator nextComparator;

    /**
     * Constructs FileNameComparator with given nextComparator.
     *
     * @param nextComparator BasicComparator used when entries are equal.
     */
    public FileNameComparator(BasicComparator nextComparator){
        this.nextComparator = nextComparator;
    }

    @Override
    public BasicComparator getNextComparator() {
        return nextComparator;
    }

    @Override
    public int compare(SearchEntry t1, SearchEntry t2) {
        int compareVal = t1.getFileName().toString().compareTo(t2.getFileName().toString());
        return compareVal == 0 ? nextComparator.compare(t1 ,t2) : compareVal;
    }
}
