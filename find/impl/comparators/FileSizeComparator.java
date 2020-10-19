package cz.muni.fi.pb162.find.impl.comparators;

import cz.muni.fi.pb162.find.comparators.BasicComparator;
import cz.muni.fi.pb162.find.filesystem.SearchEntry;

/**
 * This class represents comparator based on file size.
 *
 * @author Pavol Baran
 */
public class FileSizeComparator implements BasicComparator {
    private BasicComparator nextComparator;

    /**
     * Constructs FileSizeComparator with given nextComparator.
     *
     * @param nextComparator BasicComparator used when entries are equal.
     */
    public FileSizeComparator(BasicComparator nextComparator){
        this.nextComparator = nextComparator;
    }

    @Override
    public BasicComparator getNextComparator() {
        return nextComparator;
    }

    @Override
    public int compare(SearchEntry t1, SearchEntry t2) {
        int compareVal = Long.compare(t1.getSize(), t2.getSize());
        return compareVal == 0 ? nextComparator.compare(t1 ,t2) : compareVal;
    }
}
