package cz.muni.fi.pb162.find.impl.comparators;

import cz.muni.fi.pb162.find.comparators.BasicComparator;
import cz.muni.fi.pb162.find.filesystem.SearchEntry;

/**
 * This class represents comparator based on file extension.
 *
 * @author Pavol Baran
 */
public class FileExtensionComparator implements BasicComparator {
    private BasicComparator nextComparator;

    /**
     * Constructs FileExtensionComparator with given nextComparator.
     *
     * @param nextComparator BasicComparator used when entries are equal.
     */
    public FileExtensionComparator(BasicComparator nextComparator){
        this.nextComparator = nextComparator;
    }

    @Override
    public BasicComparator getNextComparator() {
        return nextComparator;
    }

    @Override
    public int compare(SearchEntry t1, SearchEntry t2) {
        String t1FileName = t1.getFileName().toString();
        String t2FileName = t2.getFileName().toString();
        String t1Extension = "";
        String t2Extension = "";
        int t1IndexOfDot = t1FileName.lastIndexOf(".");
        int t2IndexOfDot = t2FileName.lastIndexOf(".");

        if (t1IndexOfDot > -1 && t1IndexOfDot < t1FileName.length() -1) {
            t1Extension = t1FileName.substring(t1IndexOfDot + 1);
        }
        if (t2IndexOfDot > -1 && t2IndexOfDot < t2FileName.length() -1) {
            t2Extension = t2FileName.substring(t2IndexOfDot + 1);
        }
        int compareVal = t1Extension.compareTo(t2Extension);
        return compareVal == 0 ? nextComparator.compare(t1 ,t2) : compareVal;
    }
}
