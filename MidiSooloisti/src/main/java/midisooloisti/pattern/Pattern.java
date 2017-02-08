package midisooloisti.pattern;

import java.util.ArrayList;
import java.util.Random;

public interface Pattern {

    public ArrayList<Integer> getNotes(Scale scale, int currentPitch);

    default int direction(Random random) {
        int value = random.nextInt(100);

        if (value >= 50) {
            return 1;
        }

        return -1;
    }

}