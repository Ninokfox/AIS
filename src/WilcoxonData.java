import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import reinforcement.QLearning;
import reinforcement.Vinny;

import mutation.MutBCA;
import mutation.MutEA;
import mutation.Mutation;
import algo.Algorithm;
import fitnessFunc.Fitness;
import fitnessFunc.HIFF;
import fitnessFunc.LeadingOnes;
import fitnessFunc.OneMax;
import fitnessFunc.Xdivk;


public class WilcoxonData {
	public static void main(String[] args) throws FileNotFoundException {
		QLearning ql = new QLearning();
		Algorithm algo = new Algorithm();
		Fitness fitness = new OneMax();
		Fitness fitness2 = new OneMax();
		
		Mutation mutation = new MutBCA();
		//Mutation mutation2 = new MutEA();
		String experimentRoot = (args.length == 0 ? "C:/Documents and Settings/�������������/��� ���������" : args[0]);		    
		new File(experimentRoot).mkdirs();
		int SIZE = 90;
		double alpha = 0.7;
		double gamma = 0.7;
		double e = 0;
		int k = 1;
		int d = 0;
		try {					
			int critNum = 2;			
			PrintWriter zzz = new PrintWriter(new FileOutputStream(experimentRoot + "/OneMaxZeroMax" + SIZE+".txt"));
			zzz.println("group" + "\t" + "cond");
			for (int i = 0; i < 100; ++i) {
				System.out.println(i);
				
				//ArrayList<Integer> fit = ql.run(2, SIZE, 30000, fitness2, mutation, alpha, gamma, e);
				ArrayList<Integer> fit = algo.run(k, SIZE, 1000000, fitness, mutation);
				ArrayList<Integer> fit2 = ql.run(d, k, critNum, SIZE, 1000000, fitness2, mutation, alpha, gamma, e);
				
				if (fit.get(fit.size() - 1) == SIZE/k) {
					zzz.println("0" + "\t" + fit.size());
				}
				if (fit2.get(fit2.size() - 1) == SIZE/k) {
					zzz.println("1" + "\t" + fit2.size());
				}				
			}			
			zzz.close();			
		}
		catch (FileNotFoundException m) {
			m.printStackTrace();
		}
	}
}