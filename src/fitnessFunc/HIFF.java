package fitnessFunc;

import algo.BitString;

public class HIFF implements Fitness {
    public String getName() {
        return "HIFF";
    }

	public int fitness(BitString a, int k) {
		return a.hiffComponent(false) + a.hiffComponent(true);
	}
	public int maxValue(int len, int k) {
		return len*(int)(Math.log(len) / Math.log(2) + 1);
	}
	
	public int fitness(BitString individual, int criterion, int k, int d) {
		int rv = 0;	
		if (criterion == 3) {			// mask 1010101010....
			for (int i = 0; i < individual.size(); i++) {
				if (individual.get(i) == (i % 2 == 0)) {
					rv++;
				}
			}			
		}
		else {
			if (criterion != 2) {
				rv += individual.hiffComponent(false);
			}
			if (criterion != 1) {
				rv += individual.hiffComponent(true);
			}
		}
		return rv;
	}

	@Override
	public int maxValue(int len, int criterion, int k, int d) {
		if (criterion == 3) {
			return len;
		}
		else return len*(int)(Math.log(len) / Math.log(2) + 1);
	}
}
