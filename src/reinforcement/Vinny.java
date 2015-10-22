package reinforcement;

import java.util.ArrayList;
import java.util.Random;

import algo.BitString;
import mutation.Mutation;
import algo.Init;
import fitnessFunc.Fitness;


public class Vinny {
	public String getName() {
        return "Vinny";
    }
	public ArrayList<Integer> run(int d, int k, int critNum, int individLen, int evSize, Fitness fitness, Mutation mutation, double alpha, double gamma) {
		Random random = new Random();
		BitString ind = Init.run(individLen, random);
		ArrayList<Integer> ff = new ArrayList<Integer>();
		double[] Q = {0, 0, 0, 0};		
		double[] Q2 = {0, 0, 0, 0};	
		ff.add(fitness.fitness(ind, 0, k, d));
		
		for (int t = 0; t < evSize; t++) {
			if (ff.get(ff.size() - 1) != individLen/k) {
				boolean[] mut = {false, false, false, false};
				BitString x;
				BitString prev = ind;
				BitString y = mutation.mutate(ind, random, null, 1);
				double m = Util.max(Q2, critNum);
				for (int i = 0; i < critNum; ++i) {
					x = ind;
					if (fitness.fitness(y, i, k, d) >= fitness.fitness(x, i, k, d)) {
						mut[i] = true;
						x = y;
					}
					double r = Reward.reward(d, k, x, prev, 0, fitness);				
					Q[i] = Q[i] + alpha * (r + gamma * m - Q[i]);
				}			
				
				int f = Util.ffchooser(Q, critNum, random);
				
				Q2[f] = Q[f];
				
				if (mut[f]) {
					ind = y;
					ff.add(fitness.fitness(y, 0, k, d));
				} else {
					ind = prev;
					ff.add(fitness.fitness(prev, 0, k, d));
				}			
				Q = Q2;
			}
			else break;
		}
		return ff;
	}
}
