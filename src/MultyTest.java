

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import reinforcement.QLearning;
import reinforcement.Vinny;
import mutation.*;

import fitnessFunc.Fitness;
import fitnessFunc.LeadingOnes;


public class MultyTest {
	public static void main(String[] args) throws ParseException, FileNotFoundException {
		QLearning algo = new QLearning();
		Fitness fitness = new LeadingOnes();
		Mutation mutation = new MutBCA();
		double alpha = 0.0003;	
		String experimentRoot1 = (args.length == 0 ? "D:/ArtificialIS/results/new_test/helpers/" : args[0])
			    + fitness.getName() + "_+_" + algo.getName() + "_" + mutation.getName()+ alpha;
		new File(experimentRoot1).mkdirs();
		PrintWriter lll = new PrintWriter(new FileOutputStream(experimentRoot1 + "/step.txt"));
		double e = 0;
		int critNum = 2;
		int SIZE = 90;				
		for (int h = 0; h < 3; ++h) {
			alpha *= 10;
			double gamma = 0.0001;
			for (int j = 0; j< 3; ++j)	{
				gamma *= 10;
				lll.print(alpha + " " + gamma+ ": ");		
				ArrayList<Integer> d = new ArrayList<Integer>();
			    int summ = 0;
				for (int i = 0; i < 100; ++i) {
					
					ArrayList<Integer> fit = algo.run(1, 1, critNum, SIZE, 50000, fitness, mutation, alpha, gamma, e);
					d.add(fit.size());
					summ += fit.size();
				}
			
				double av = summ/100.0;
				lll.print(new DecimalFormat("#0.0").format(av));
				double ans = 0;
				for (int i = 0; i < 100; ++i) {
					ans += (d.get(i) - av) * (d.get(i) - av);
				}
				ans /= 99;
				ans = Math.sqrt(ans);
				lll.println("("+ new DecimalFormat("#0.0").format(ans) + ")");
			}
		}			
		lll.close();
	}

}
