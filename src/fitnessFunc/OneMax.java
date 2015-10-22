package fitnessFunc;

import algo.BitString;

public class OneMax implements Fitness {
   

	public int fitness(BitString a, int k) {
		return a.bitCount();
	}
	public int maxValue(int len, int k) {
		return len;
	}
	
    public String getName() {
        return "OneMax";
    }

    public int fitness(BitString individual, int criterion, int k, int d) {
    	if (criterion == 0) {
    		return individual.bitCount();
    	}
    	return individual.size() - individual.bitCount();
	}

	@Override
	public int maxValue(int len, int criterion, int k, int d) {
		
		return len;
	}
}
