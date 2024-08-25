package XD.XDDOS.utils.helper;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public final class RandomUtils {
    public static String randomUTF16String1(int length) {
        Random rnd = new Random();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < length; i++)
            s.append((char)(-128 + rnd.nextInt(255)));
        return new String(s.toString().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_16);
    }

    public static int nextInt(int startInclusive, int endExclusive) {
        if (endExclusive - startInclusive <= 0)
            return startInclusive;
        return startInclusive + (new Random()).nextInt(endExclusive - startInclusive);
    }

    public static double nextDouble(double startInclusive, double endInclusive) {
        if (startInclusive == endInclusive || endInclusive - startInclusive <= 0.0D)
            return startInclusive;
        return startInclusive + (endInclusive - startInclusive) * Math.random();
    }

    public static float nextFloat(float startInclusive, float endInclusive) {
        if (startInclusive == endInclusive || endInclusive - startInclusive <= 0.0F)
            return startInclusive;
        return (float)(startInclusive + (endInclusive - startInclusive) * Math.random());
    }

    public static String randomNumber(int length) {
        return random(length, "123456789");
    }

    public static String randomString(int length) {
        return random(length, "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
    }

    public static String random(int length, String chars) {
        return random(length, chars.toCharArray());
    }

    public static String random(int length, char[] chars) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (i < length) {
            stringBuilder.append(chars[(new Random()).nextInt(chars.length)]);
            i++;
        }
        return stringBuilder.toString();
    }
}
