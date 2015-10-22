import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import reinforcement.QLearning;
import reinforcement.Vinny;

import algo.Algorithm;
import mutation.*;
import fitnessFunc.*;


public class AIS {
	public static void main(String[] args) throws FileNotFoundException {
		QLearning algo = new QLearning();
		Fitness fitness = new MinMax();
		//Mutation mutation = new MutBCA();
		double alpha = 0.7;
		double gamma = 0.7;
		double e = 0;
		int critNum = 3;
		int k = 4;
		int d = 16;
		//FitnessM fitness = new OneMax();
		//Fitness fitness = new LeadingOnes();
		//Algorithm algo = new Algorithm();		
		//FitnessM fitness = new MinMAX();
		Mutation mutation = new MutBCA();
		
		String experimentRoot1 = (args.length == 0 ? "D:/ArtificialIS/results/Table" : args[0])
				+ "/" + fitness.getName() + "_" + mutation.getName() + "/" + k;
		new File(experimentRoot1).mkdirs();
		
		//PrintWriter yyy = new PrintWriter(new FileOutputStream(experimentRoot1 + "/ans"+ d+ ".txt"));
		PrintWriter aaa = new PrintWriter(new FileOutputStream(experimentRoot1 + "/percent"+ d+ ".txt"));
		int SIZE = 24;//k*4;
		//for (int j = 0; j < 3; ++j) {
			//SIZE += k*2;
		
			String experimentRoot = (args.length == 0 ? "D:/ArtificialIS/results/Table" : args[0])
					+ "/" + fitness.getName() + "_" + mutation.getName() + "/" + k + "/"+ SIZE;
			new File(experimentRoot).mkdirs();
			try {
				PrintWriter yyy = new PrintWriter(new FileOutputStream(experimentRoot + "/ans"+ d+ ".txt"));
			    PrintWriter zzz = new PrintWriter(new FileOutputStream(experimentRoot + "/best"+ d+ ".txt"));
				ArrayList<Integer> best = new ArrayList<Integer>();
				ArrayList<Integer> matrix = new ArrayList<Integer>();
			    int summ = 0;
			    int rounds = 100;
			    int steps = 1000000;
				for (int i = 0; i < rounds; ++i) {
					
					//ArrayList<Integer> fit = algo.run(k, SIZE, steps, fitness, mutation);
					ArrayList<Integer> fit = algo.run(d, k, critNum, SIZE, steps, fitness, mutation, alpha, gamma, e);
					int ans = fit.get(fit.size() - 1);
					if (fit.get(fit.size() - 1) == SIZE/k) {
						matrix.add(fit.size());
						summ += fit.size();
						zzz.println(fit.size());
					}
					best.add(ans);					
				}
				//System.out.println(d + " - " + matrix.size());
				double av = summ/(double)matrix.size();
				aaa.println(matrix.size());
				yyy.print(new DecimalFormat("#0.0").format(av));
				double ans = 0;
				for (int i = 0; i < matrix.size(); ++i) {
					ans += (matrix.get(i) - av) * (matrix.get(i) - av);
				}
				ans /= matrix.size() - 1;
				ans = Math.sqrt(ans);
				yyy.println("("+ new DecimalFormat("#0.0").format(ans) + ")");
				zzz.println(best);
				zzz.close();		
				yyy.close();
			} catch (FileNotFoundException x) {
				x.printStackTrace();
			}
	//}
			
	//yyy.close();
	aaa.close();
	}
}
