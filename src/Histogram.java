
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import reinforcement.QLearning;

import mutation.MutBCA;
import mutation.Mutation;

import fitnessFunc.Fitness;

import fitnessFunc.MinMax;



public class Histogram {
	public static void main(String[] args) throws IOException {
		QLearning algo = new QLearning();		
		//FitnessM fitness = new LeadingOnesM();		
		double alpha = 0.7;
		double gamma = 0.7;
		double e = 0;
		Fitness fitness = new MinMax();
		//Algorithm algo = new Algorithm();		
		//Fitness fitness = new Xdivk();
		Mutation mutation = new MutBCA();
		int k = 3;	
		int critNum = 3;		
		int SIZE = 18;//k*4;
		int d = 17;
		String experimentRoot = (args.length == 0 ? "D:/ArtificialIS/results/Histmillion" : args[0])
				+ "/" + fitness.getName() + "_" + mutation.getName() + "/" + k + "/"+ SIZE;
		new File(experimentRoot).mkdirs();
		try {		    
			PrintWriter yyy = new PrintWriter(new FileOutputStream(experimentRoot + "/step17.txt"));	
			PrintWriter xxx = new PrintWriter(new FileOutputStream(experimentRoot + "/occurrence17.txt"));
			Map<Integer, Integer> index = new TreeMap<Integer, Integer>();   		    
			for (int i = 0; i < 50000; ++i) {
				System.out.println(i);
				//ArrayList<Integer> fit = algo.run(d, k, SIZE, 1000000, fitness, mutation);
				ArrayList<Integer> fit = algo.run(d, k, critNum, SIZE, 1000000, fitness, mutation, alpha, gamma, e);
				int ans = fit.get(fit.size() - 1);
				if (ans == SIZE/k) {					
					if (index.containsKey(fit.size())) {
						index.put(fit.size(), index.get(fit.size()) + 1);
		            } else {
		            	index.put(fit.size(), 1);
		            }				
				}				
			}			
			System.out.println(index.keySet().size());						
			for (Integer in : index.keySet()) { 				
				yyy.println(in);
				xxx.println(index.get(in));				
			}		
			
			xxx.close();
			yyy.close();	
			
		} catch (FileNotFoundException x) {
			x.printStackTrace();
		}	
	}

}
