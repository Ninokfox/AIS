package algo;

import java.util.ArrayList;
import java.util.Random;


import mutation.Mutation;

import fitnessFunc.Fitness;


public class Randomized {
	public String getName() {
        return "Randomized";
    }
	public ArrayList<Integer> run(int d, int k, int individLen, int evSize, Fitness fitness, Mutation mutation) {
		Random random = new Random();
		BitString ind = Init.run(individLen, random);
		ArrayList<Integer> ff = new ArrayList<Integer>();
		ff.add(fitness.fitness(ind, random.nextInt(2), k, d));	
		
		for (int t = 0; t < evSize; t++){
			if (ff.get(ff.size() - 1) != individLen/k) {
				BitString mutated = mutation.mutate(ind, random, null, 1);
				int foo = random.nextInt(2);
				int lastF = fitness.fitness(ind, foo, k, d);				
				int currF = fitness.fitness(mutated, foo, k, d);				
				if (currF >= lastF) {
					ind = mutated;						
				} 
				ff.add(fitness.fitness(ind, 0, k, d));
			}
			else
				break;			
		}
		return ff;
	}	
}
