package io.husayn.paging_library_sample.utilities;


public class TextUtility {

    private static final String TWO_ZEROS = "00";
    private static final String ONE_ZERO = "0";
    
    public static String toThreeDigitNumber(int n) {
        if (n >= 1 && n <= 9)
            return TWO_ZEROS + n;
        if (n <= 99)
            return ONE_ZERO + n;
        return String.valueOf(n);
    }
}
