package fitnessFunc;

import algo.BitString;

public interface Fitness {
    public int fitness(BitString individual, int k);
    public String getName();
    public int maxValue(int len, int k);
    public int fitness(BitString individual, int criterion, int k, int d);
    public int maxValue(int len, int criterion, int k, int d);
}
