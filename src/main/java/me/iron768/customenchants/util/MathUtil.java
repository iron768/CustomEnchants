package me.iron768.customenchants.util;

import java.util.Random;

public class MathUtil {

    public static boolean hasChance(int percent) {
        Random random = new Random();

        return random.nextInt(99) + 1 <= percent;
    }

    public static int randomInteger(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("Max needs to be higher than 0.");
        }

        Random random = new Random();

        return random.nextInt(max) + 1;
    }

}
