package mutation;

import algo.BitString;

import java.util.Random;

import fitnessFunc.Fitness;

public class MutBCA implements Mutation {
    public String getName() {
        return "BCA";
    }
	public BitString mutate(BitString a, Random random, Fitness fit, int k) {
		BitString mutated = a.clone();
		int size = mutated.size();
		int from = random.nextInt(size);
		int l = random.nextInt(size + 1);
		int until = from + l;
		if (until > size) {
			mutated.flip(from, size);
			mutated.flip(0, until - size);
		} else {
			mutated.flip(from, until);
		}
		return mutated;
	}
	@Override
	public BitString mutateM(BitString a, Random random, Fitness fit,
			int k, int d, int critNum) {
		BitString mutated = a.clone();
		int size = mutated.size();
		int from = random.nextInt(size);
		int l = random.nextInt(size + 1);
		int until = from + l;
		if (until > size) {
			mutated.flip(from, size);
			mutated.flip(0, until - size);
		} else {
			mutated.flip(from, until);
		}
		return mutated;
	}	

}
