package algo;

import java.util.ArrayList;
import java.util.Random;


import mutation.Mutation;

import fitnessFunc.Fitness;

public class MagicRandom {
	public String getName() {
        return "Magic";
    }
	public ArrayList<Integer> run(int k, int individLen, int evSize, Fitness fitness1, Fitness fitness2, Mutation mutation) {
		Random random = new Random();
		BitString ind = Init.run(individLen, random);
		ArrayList<Integer> ff = new ArrayList<Integer>();
		if (random.nextBoolean()) {
			ff.add(fitness1.fitness(ind, k));
		}
		else {
			ff.add(fitness2.fitness(ind, k));
		}
		
		for (int t = 0; t < evSize; t++){
			BitString mutated = mutation.mutate(ind, random, null, 1);
			
			int lastF = ff.get(ff.size() - 1);
			
			int currF;
			if (random.nextBoolean()) {
				currF = fitness1.fitness(mutated, k);
				if (currF == individLen/k) {
					ff.add(currF);
					break;
				}
			}
			else {
				currF = fitness2.fitness(mutated, k);
				if (currF == individLen) {
					ff.add(currF);
					break;
				}				
			}		
			
			if (currF >= lastF) {
				ind = mutated;
				
				ff.add(currF);
			} else {
				ff.add(lastF);
			}			
		}
		
		return ff;
	}	
}
