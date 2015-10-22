package fitnessFunc;

import algo.BitString;

public class MinMax implements Fitness{
    public String getName() {
        return "MinMax";
    }

    public int fitness(BitString individual, int criterion, int k, int d) {    	
    	if (criterion == 1) {
    		return Math.min(individual.bitCount(), d);
    	}
    	if (criterion == 2) {
    		return Math.max(individual.bitCount(), d);
    	}
    	return individual.bitCount()/k;
	}

	
	public int maxValue(int len, int criterion, int k, int d) {		
		if (criterion == 1) {
			return d;
		}
		if (criterion == 2) {
			return len;
		}
		return len/k;
	}

	
	public int fitness(BitString a, int k) {

		return a.bitCount()/k;
	}
	public int maxValue(int len, int k) {
		return len/k;
	}

}
