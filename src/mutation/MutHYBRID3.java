package mutation;

import java.util.Random;

import algo.BitString;
import fitnessFunc.Fitness;

public  class MutHYBRID3 implements Mutation{
	public String getName() {
        //return "HYBRID3";
		return "CLONALG+RLS(50%)";
    }
	
	public static boolean getRandom(double prob, Random random) {
		double rr = random.nextDouble();
		
        return (rr < prob);
    }
	
	public BitString mutate(BitString a, Random random, Fitness fit, int k) {
		
		BitString mutated = a.clone();
		int size = mutated.size();
		int from = random.nextInt(size);
		if (random.nextInt(2) == 0) {
			double v = (double)fit.fitness(mutated, k) / fit.maxValue(mutated.size(), k);
			double prob = Math.pow(mutated.size(), -v);		
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

		if (random.nextInt(2) == 0) {
			double v = (double)fit.fitness(mutated, critNum, k, d) / fit.maxValue(mutated.size(), critNum, k, d);	
			double prob = Math.pow(mutated.size(), -v);		
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

}
