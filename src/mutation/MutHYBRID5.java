package mutation;

import java.util.Random;

import algo.BitString;
import fitnessFunc.Fitness;

public  class MutHYBRID5 implements Mutation{
	public String getName() {
        //return "HYBRID4";
		return "CLONALG+RLS(ns+target)";
    }
	
	public static boolean getRandom(double prob, Random random) {
		double rr = random.nextDouble();
		
        return (rr < prob);
    }
	
	public BitString mutate(BitString a, Random random, Fitness fit, int k) {
		
		BitString mutated = a.clone();
		int size = mutated.size();
		int from = random.nextInt(size);
		double v = (double)fit.fitness(mutated, k) / fit.maxValue(mutated.size(), k);
		double prob = Math.pow(mutated.size(), -v);		
		if (getRandom(prob, random)) {				
			for (int i = 0; i < mutated.size(); ++i) {
				if (getRandom(prob, random)) {
					mutated.flip(i);
				}
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
		double v = (double)fit.fitness(mutated, 0, k, d) / fit.maxValue(mutated.size(), 0, k, d);	
		double prob = Math.pow(mutated.size(), -v);		
		if (getRandom(prob, random)) {				
			for (int i = 0; i < mutated.size(); ++i) {
				double v1 = (double)fit.fitness(mutated, critNum, k, d) / fit.maxValue(mutated.size(), critNum, k, d);	
				double prob1 = Math.pow(mutated.size(), -v1);		
				if (getRandom(prob1, random)) {
					mutated.flip(i);
				}
			}
		}
		else {
			mutated.flip(from);
		}
		
		return mutated;
	}

}
