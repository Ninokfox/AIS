import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import reinforcement.QLearning;
import reinforcement.Vinny;

import mutation.MutBCA;
import mutation.MutEA;
import mutation.MutRLS;
import mutation.Mutation;
import algo.Algorithm;
import fitnessFunc.Fitness;
import fitnessFunc.HIFF;
import fitnessFunc.LeadingOnes;
import fitnessFunc.MinMax;
import fitnessFunc.OneMax;
import fitnessFunc.Xdivk;


public class WilcoxTest {
	public static void main(String[] args) throws FileNotFoundException {
		Random random = new Random();
		QLearning ql = new QLearning();
		Algorithm algo = new Algorithm();
		Fitness fitness = new Xdivk();
		Fitness fitness2 = new MinMax();
		
		Mutation mutation = new MutBCA();
		//Mutation mutation = new MutRLS();
		String experimentRoot = (args.length == 0 ? "C:/Documents and Settings/�������������/��� ���������" : args[0]);		    
		new File(experimentRoot).mkdirs();
		
		double alpha = 0.7;
		double gamma = 0.7;
		double e = 0;
		int critNum = 3;	
		
		PrintWriter zzz = new PrintWriter(new FileOutputStream(experimentRoot + "/MinMaxRandomTestBCALarge40.txt"));
		zzz.println("group" + "\t" + "cond");
		for (int j = 0; j < 40; ++j) {
			System.out.print(j + " : ");			
			//int k = 5 * (random.nextInt(3) + 1);
			int k = (random.nextInt(14) + 2);
			//int m = 10 * (random.nextInt(4) + 3);
			int m = (random.nextInt(55) + 6);
			int SIZE = k * m;
			double p = SIZE * 0.83;
			int d = (int)p;	
			System.out.println(k + "*" + m + "=" + SIZE + " " + d);
			int sum = 0;
			int sum2 = 0;
			for (int i = 0; i < 100; ++i) {
				System.out.print(i );				
				//ArrayList<Integer> fit = ql.run(2, SIZE, 30000, fitness2, mutation, alpha, gamma, e);
				ArrayList<Integer> fit = algo.run(k, SIZE, 1000000, fitness, mutation);
				ArrayList<Integer> fit2 = ql.run(d, k, critNum, SIZE, 1000000, fitness2, mutation, alpha, gamma, e);
				sum += fit.get(fit.size() - 1);
				sum2 += fit2.get(fit2.size() - 1);					
			}
			double av = sum/100.0;	
			zzz.println("0" + "\t" + av);
			av = sum2/100.0;	
			zzz.println("1" + "\t" + av);				
		}
		zzz.close();
	}
}