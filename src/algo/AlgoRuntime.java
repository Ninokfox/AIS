package algo;

import java.util.ArrayList;
import java.util.Random;


import mutation.Mutation;

import fitnessFunc.Fitness;

public class AlgoRuntime {
	
	public String getName() {
        return "AlgoRuntime";
    }
	
	public ArrayList<Integer> run(int k, int individLen, int evSize, Fitness fitness, Mutation mutation) {
		Random random = new Random();
		BitString ind = Init.run(individLen, random);
		ArrayList<Integer> ff = new ArrayList<Integer>();
		ff.add(fitness.fitness(ind, k));
		//System.out.println(ind);
		for (int t = 0; t < evSize; t++){
			int f = fitness.maxValue(individLen, k);
			if (ff.get(ff.size() - 1) != f) {
				//System.out.println(ff.get(ff.size() - 1));
				BitString mutated = mutation.mutate(ind, random, fitness, k);
				//System.out.println(mutated);
				int lastF = ff.get(ff.size() - 1);
				int currF = fitness.fitness(mutated, k);
				if (currF >= lastF) {
					ind = mutated;					
					ff.add(currF);
				} else {
					ff.add(lastF);
				}
				
			}
			else 
				break;
		}
		return ff;
	}
}
