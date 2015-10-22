package mutation;

import algo.BitString;

import java.util.Random;

import fitnessFunc.Fitness;

public  class MutRLS implements Mutation {
    public String getName() {
        return "RLS";
    }
    public BitString mutate(BitString a, Random random, Fitness fit, int k) {
		BitString mutated = a.clone();
		int i = random.nextInt(mutated.size());
		mutated.flip(i);
		return mutated;
	}
	@Override
	public BitString mutateM(BitString a, Random random, Fitness fit,
			int k, int d, int critNum) {
		BitString mutated = a.clone();
		int i = random.nextInt(mutated.size());
		mutated.flip(i);
		return mutated;
	}
}
