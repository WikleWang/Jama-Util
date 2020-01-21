package com.amt.jama.util.string;

/**
 * @author WangWei
 */
public class CompareUtil {
    public static boolean closeEnough(String one, String two) {
        return one.equals(two)
                || one.toLowerCase().equals(two.toLowerCase())
                || one.toLowerCase().replaceAll(" ", "_").equals(two.toLowerCase().replaceAll(" ", "_"));

    }
}
