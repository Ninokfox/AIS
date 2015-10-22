package reinforcement;

import java.util.ArrayList;
import java.util.Random;

import algo.BitString;
import mutation.Mutation;
import algo.Init;

import fitnessFunc.Fitness;
import fitnessFunc.OneMax;


public class Hist {
	public String getName() {
        return "HistQL";
    }

	public long[][] run(int d, int k, int critNum, int individLen, int evSize, Fitness fitness, Mutation mutation, double alpha, double gamma, double e) {
		Random random = new Random();
		Fitness one = new OneMax();
		BitString ind = Init.run(individLen, random);
		ArrayList<Integer> ff = new ArrayList<Integer>();
		long[][]matrix = new long[individLen][critNum];
		 
		double[] Q = new double[critNum];
		for (int i = 0; i < critNum; ++i) {
			Q[i] = 0;
			for (int j = 0; j < individLen; ++j) {
				matrix[j][i] = 0;
			}
		}
		
		ff.add(fitness.fitness(ind, 0, k, d));  	
		for (int t = 0; t < evSize; t++){
			if (ff.get(ff.size() - 1) != individLen/k) {
				BitString prev = ind;
				int f;
				if (Util.getRandom(e, random)) {
					f = random.nextInt(critNum);
					
				} else {
					f = Util.ffchooser(Q, critNum, random);	
				}
				matrix[one.fitness(ind, k) - 1][f]++;
								
				BitString y = mutation.mutate(ind, random, null, 1);
				
				if (fitness.fitness(y, f, k, d) >= fitness.fitness(ind, f, k, d)) {
					ind = y;					
				}				
				ff.add(fitness.fitness(ind, 0, k, d));
				double r = Reward.reward(d, k, ind, prev, 0, fitness);
				double m = Util.max(Q, critNum);
				Q[f] = Q[f] + alpha * (r + gamma * m - Q[f]);		
			}
			else break;
		}
		return matrix;
	}
}
