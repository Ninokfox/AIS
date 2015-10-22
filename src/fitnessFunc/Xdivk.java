package fitnessFunc;

import algo.BitString;

public class Xdivk implements Fitness {
    public String getName() {
        return "Xdivk";
    }

	public int fitness(BitString a, int k) {

		return a.bitCount()/k;
	}
	public int maxValue(int len, int k) {
		return len/k;
	}
	
    public int fitness(BitString individual, int criterion, int k, int d) {
    	if (criterion == 0) {    		
    		return individual.bitCount()/k;
    	}
    	return individual.bitCount();
    }

	@Override
	public int maxValue(int len, int criterion, int k, int d) {
		if (criterion == 0) {    		
    		return len/k;
    	}
    	return len;
	}
}
