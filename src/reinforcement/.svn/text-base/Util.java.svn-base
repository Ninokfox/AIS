package reinforcement;

import java.util.Random;

public class Util {
	
	public static boolean getRandom(double len, Random random) {
		if (len != 0) {
			int a = (int)(1/len);
	        return random.nextInt(a) == 0;
		}
		else
			return false;
    }
	
	public static double max(double[] qq, int critNum) {
		double max = qq[0];
		for (int i = 1; i < critNum; ++i) {
			if (max < qq[i]) {
				max = qq[i];
			}
		}
		return max;
	}
	public static int ffchooser(double[] qq, int critNum, Random random) {
		double max = qq[0];
		int ans = 0;
		for (int i = 1; i < critNum; ++i) {
			if (max == qq[i]) {
				if (random.nextBoolean()) {
					ans = i;
				}
			}
			if (max < qq[i]) {
				max = qq[i];
				ans = i;
			}
		}
		return ans;
	}

}
