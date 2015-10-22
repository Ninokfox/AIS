package mutation;

import algo.BitString;

import java.util.Random;

import fitnessFunc.Fitness;

public  class MutEA implements Mutation {
    public String getName() {
        return "EA";
    }
	
	public static boolean getRandom(int len, Random random) {	    
        return random.nextInt(len) == 0;
    }
	
	public static boolean getRandom2(int len, Random random) {	    
        return random.nextInt(len) <= 1;
    }

	public BitString mutate(BitString a, Random random, Fitness fit, int k) {
		BitString mutated = a.clone();
		for (int i = 0; i < mutated.size(); ++i) {
			if (getRandom(mutated.size(), random)) {
				mutated.flip(i);
			}
		}
		return mutated;
	}

	@Override
	public BitString mutateM(BitString a, Random random, Fitness fit,
			int k, int d, int critNum) {
		BitString mutated = a.clone();
		for (int i = 0; i < mutated.size(); ++i) {
			if (getRandom(mutated.size(), random)) {
				mutated.flip(i);
			}
		}
		return mutated;
	}
}
