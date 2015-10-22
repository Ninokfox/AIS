import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

import algo.MagicRandom;
import algo.Randomized;

import mutation.*;
import fitnessFunc.Fitness;
import fitnessFunc.LeadingOnes;
import fitnessFunc.OneMax;
import fitnessFunc.Xdivk;


public class RandomAIS {
	public static void main(String[] args) throws ParseException, FileNotFoundException {
		
		//FitnessM fitness = new XdivkM();
		Fitness fitness = new OneMax();
		Mutation mutation = new MutBCA();
		Randomized algo = new Randomized();
		//MagicRandom algo = new MagicRandom();
		int k = 1;	
		String experimentRoot1 = (args.length == 0 ? "D:/ArtificialIS/results/Random/" : args[0])
			    + fitness.getName() + "_+_" + algo.getName() + "_" + mutation.getName()+ "/" + k;
		new File(experimentRoot1).mkdirs();
		int d = 1;
		PrintWriter lll = new PrintWriter(new FileOutputStream(experimentRoot1 + "/" +algo.getName()+ d+ ".txt"));
		PrintWriter bbb = new PrintWriter(new FileOutputStream(experimentRoot1 + "/percent"+ d+ ".txt"));
		int SIZE = 10;//k*4;
		for (int j = 0; j < 8; ++j) {
			SIZE += 10;//k*2;						
			String experimentRoot = (args.length == 0 ? "D:/ArtificialIS/results/Random/" : args[0])
			    + fitness.getName() + "_+_" + algo.getName() + "_" + mutation.getName() + "/" + k+  "/" + SIZE;
			new File(experimentRoot).mkdirs();		
			
			try {	
				
				PrintWriter aaa = new PrintWriter(new FileOutputStream(experimentRoot + "/ans" + d+".txt"));
				
				ArrayList<Integer> matrix = new ArrayList<Integer>();
			    int summ = 0;
			    int steps = 1000000;
			    int rounds = 100;
				for (int i = 0; i < rounds; ++i) {		
					ArrayList<Integer> fit = algo.run(d, k, SIZE, steps, fitness, mutation);
					if (fit.get(fit.size() - 1) == SIZE/k) {
						matrix.add(fit.size());
						summ += fit.size();
						aaa.println(fit.size());						
					}				
				}
				aaa.close();
				double av = summ/(double)matrix.size();
				lll.print(new DecimalFormat("#0.0").format(av));
				aaa.println(matrix.size());
				System.out.println(matrix.size());
				double ans = 0;
				for (int i = 0; i < matrix.size(); ++i) {
					ans += (matrix.get(i) - av) * (matrix.get(i) - av);
				}
				ans /= matrix.size() - 1;
				ans = Math.sqrt(ans);
				lll.println("("+ new DecimalFormat("#0.0").format(ans) + "))");				
			}
			catch (FileNotFoundException x) {
				x.printStackTrace();
			}
		}
		bbb.close();
		lll.close();	
	}
}
