package org.pks;

import java.util.Random;

public class Utils {
    Random random = new Random();

    public String generateString(int maxThread) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'

        return random.ints(leftLimit, rightLimit + 1)
                .limit(stringLength(maxThread))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private int stringLength(int maxThread) {
        int max = 0;
        for (int i = 0; i < maxThread; i++) {
            if (i * i >= maxThread) {
                max = i;
                break;
            }
        }
        return max;
    }
}
