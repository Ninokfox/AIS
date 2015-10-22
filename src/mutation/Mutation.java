package mutation;

import algo.BitString;

import java.util.Random;
import fitnessFunc.*;
public interface Mutation {
    public BitString mutate(BitString individual, Random random, Fitness fit, int k);
    public BitString mutateM(BitString individual, Random random, Fitness fit, int k, int d, int critNum);
    public String getName();
  
}
