package mutation;

import algo.BitString;

import java.util.Random;

import fitnessFunc.Fitness;
import fitnessFunc.LeadingOnes;

public class MutCLONALG implements Mutation{
	
    public String getName() {
        return "CLONALG";
    }
	
	public static boolean getRandom(double prob, Random random) {
		double rr = random.nextDouble();
		
        return (rr < prob);
    }
	
	public BitString mutate(BitString a, Random random, Fitness fit, int k) {
		
		BitString mutated = a.clone();		
		double v = (double)fit.fitness(mutated, k) / fit.maxValue(mutated.size(), k);
		double prob = Math.pow(mutated.size(), -v);		
		for (int i = 0; i < mutated.size(); ++i) {
			if (getRandom(prob, random)) {
				mutated.flip(i);
			}
		}
		return mutated;
	}

	@Override
	public BitString mutateM(BitString a, Random random, Fitness fit, int k, int d, int critNum) {
		BitString mutated = a.clone();		
		double v = (double)fit.fitness(mutated, critNum, k, d) / fit.maxValue(mutated.size(), critNum, k, d);		
		double prob = Math.pow(mutated.size(), -v);		
		for (int i = 0; i < mutated.size(); ++i) {
			if (getRandom(prob, random)) {
				mutated.flip(i);
			}
		}
		return mutated;
	}
}


