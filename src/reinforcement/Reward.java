package reinforcement;

import algo.BitString;
import fitnessFunc.Fitness;

public class Reward {
	public static double reward(int d, int k, BitString x, BitString prev, int criterion, Fitness fitness){
		return fitness.fitness(x, criterion, k, d) - fitness.fitness(prev, criterion, k, d);
	}
}
