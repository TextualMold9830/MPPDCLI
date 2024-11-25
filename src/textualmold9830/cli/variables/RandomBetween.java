package textualmold9830.cli.variables;

import java.util.Random;

public class RandomBetween implements Variable {
    int min;
    int max;
    @Override
    public String getValue() {
        return String.valueOf(getInt());
    }
    private Random random;

    public RandomBetween(int min, int max) {
        random = new Random();
        this.min = min;
        this.max = max;
    }

    private int getInt(){
        return random.nextInt(max-min) + min;
    }
}
