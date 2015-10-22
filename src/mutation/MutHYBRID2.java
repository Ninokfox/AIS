package mutation;

import java.util.Random;

import algo.BitString;
import fitnessFunc.Fitness;

public  class MutHYBRID2 implements Mutation{
	public String getName() {
        //return "HYBRID2";
        return "BCA+RLS(ns)";
    }
	
	public static boolean getRandom(double prob, Random random) {
		double rr = random.nextDouble();
		
        return (rr < prob);
    }
	
	public BitString mutate(BitString a, Random random, Fitness fit, int k) {
		
		BitString mutated = a.clone();
		int size = mutated.size();
		int from = random.nextInt(size);
		int l = random.nextInt(size + 1);
		int until = from + l;
		double v = (double)fit.fitness(mutated, k) / fit.maxValue(mutated.size(), k);
		double prob = Math.pow(mutated.size(), -v);		
		if (getRandom(prob, random)) {
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
		double v = (double)fit.fitness(mutated, k) / fit.maxValue(mutated.size(), k);
		double prob = Math.pow(mutated.size(), -v);		
		if (getRandom(prob, random)) {
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
