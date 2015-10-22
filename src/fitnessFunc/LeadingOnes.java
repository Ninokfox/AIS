package fitnessFunc;

import algo.BitString;

public class LeadingOnes implements Fitness{
	
	    public String getName() {
	        return "LeadingOnes";
	    }

		public int fitness(BitString a, int k) {
			//System.out.println(a);
			int b = a.leadingOnes();
			//System.out.println(b);
			return b;
		}
		public int maxValue(int len, int k) {
			return len;
		}
		
	    public int fitness(BitString individual, int criterion, int k, int d) {
	    	if (criterion == 0) {
	    		return individual.leadingOnes();
	    	}
	    	return individual.bitCount();
	    }

		@Override
		public int maxValue(int len, int criterion, int k, int d) {
	    	return len;
		}
	}



