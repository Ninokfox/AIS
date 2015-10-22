package reinforcement;

import java.util.ArrayList;
import java.util.Random;

import algo.BitString;
import mutation.Mutation;
import algo.Init;

import fitnessFunc.Fitness;


public class QLearning {
	public String getName() {
        return "QLearning";
    }

	public ArrayList<Integer> run(int d, int k, int critNum, int individLen, int evSize, Fitness fitness, Mutation mutation, double alpha, double gamma, double e) {
		Random random = new Random();		
		BitString ind = Init.run(individLen, random);
		ArrayList<Integer> ff = new ArrayList<Integer>();
		
		double[] Q = new double[critNum];
		for (int i = 0; i < critNum; ++i) {
			Q[i] = 0;
		}
		
		ff.add(fitness.fitness(ind, 0, k, d));  	
		for (int t = 0; t < evSize; t++){
			//if (ff.get(ff.size() - 1) != individLen/k) {
				BitString prev = ind;
				int f;
				if (Util.getRandom(e, random)) {
					f = random.nextInt(critNum);
					
				} else {
					f = Util.ffchooser(Q, critNum, random);	
				}
				
								
				BitString y = mutation.mutateM(ind, random, fitness, k, d, f); //если вместо f взять critNum, то CLONALG решает LO успешнее.
				
				if (fitness.fitness(y, f, k, d) >= fitness.fitness(ind, f, k, d)) { //если брать >, то EA и CLONALG улучшаются. 
					ind = y;
				}	
				
				ff.add(fitness.fitness(ind, 0, k, d));
				double r = Reward.reward(d, k, ind, prev, 0, fitness);
				
				double m = Util.max(Q, critNum);
				Q[f] = Q[f] + alpha * (r + gamma * m - Q[f]);	
				
		}
		
		return ff;
	}
}
