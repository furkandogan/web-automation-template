package test.tools.selenium.util;

import java.util.Comparator;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;

public class VersionComparator implements Comparator<String> {
    @Override
    public int compare(String v1, String v2) {
        String[] v1split = v1.split("\\.");
        String[] v2split = v2.split("\\.");
        int length = max(v1split.length, v2split.length);
        for (int i = 0; i < length; i++) {
            int v1Part = i < v1split.length ? parseInt(v1split[i]) : 0;
            int v2Part = i < v2split.length ? parseInt(v2split[i]) : 0;
            if (v1Part < v2Part) {
                return -1;
            }
            if (v1Part > v2Part) {
                return 1;
            }
        }
        return 0;
    }
}
