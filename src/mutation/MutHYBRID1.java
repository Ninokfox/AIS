package mutation;

import java.util.Random;

import algo.BitString;
import fitnessFunc.Fitness;

public  class MutHYBRID1 implements Mutation{
	public String getName() {
        //return "HYBRID1";
        return "BCA+RLS(50%)";
    }
	
	public BitString mutate(BitString a, Random random, Fitness fit, int k) {
		
		BitString mutated = a.clone();
		int size = mutated.size();
		int from = random.nextInt(size);
		int l = random.nextInt(size + 1);
		int until = from + l;
		if (random.nextInt(2) == 0) {
			if (until > size) {
				mutated.flip(from, size);
				mutated.flip(0, until - size);
			} else {
				mutated.flip(from, until);
			}
		}
		else {
			mutated.flip(from);
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
		if (random.nextInt(2) == 0) {
			if (until > size) {
				mutated.flip(from, size);
				mutated.flip(0, until - size);
			} else {
				mutated.flip(from, until);
			}
		}
		else {
			mutated.flip(from);
		}
		
		return mutated;
	}

}
