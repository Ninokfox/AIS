
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.PrintWriter;

import java.util.ArrayList;


import reinforcement.QLearning;

import algo.Algorithm;
import mutation.*;
import fitnessFunc.*;


public class PlotValuesCalc {
    public static void deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                File f = new File(dir, children[i]);
                deleteDirectory(f);
            }
            dir.delete();
        } else dir.delete();
    }
	private static void Printer(String path, ArrayList<ArrayList<Integer>> sum) throws FileNotFoundException {
	
		PrintWriter xxx = new PrintWriter(new FileOutputStream(path));
		int rounds = sum.size();
		for (int i = 0; i < sum.get(0).size(); ++i) {
			double ans = 0;			
			for (int j = 0; j < rounds; ++j) {
				ans += sum.get(j).get(i);				
			}
			double av = (double)ans/rounds;
			xxx.print(av + " ");
			ans = 0;
			for (int j = 0; j < rounds; ++j) {
				ans += (sum.get(j).get(i) - av) * (sum.get(j).get(i) - av);
			}
			ans /= rounds - 1;
			ans = Math.sqrt(ans);
			xxx.println(ans);
		}
		
		xxx.close();		
	}
	public static void main(int SIZE, int critNum, int steps, int k, int d, int al, String[] mut, String problem) throws FileNotFoundException {		
			
		Fitness fitness;		
		
		Algorithm algo = new Algorithm();	
		QLearning ql = new QLearning();	
		
		int rounds = 100;
		
		double alpha = 0.7;
		double gamma = 0.7;
		double eps = 0.0;
		ArrayList<ArrayList<Integer>> sum1 = new ArrayList<ArrayList<Integer>>();

		Mutation[] mutation = new Mutation[mut.length];
		
		for (int i = 0; i < mut.length; ++i) {
			switch (mut[i]) {
				case "EA":
					mutation[i] = new MutEA();
					break;
				case "RLS":
					mutation[i] = new MutRLS();
				    break;
				case "BCA":
					mutation[i] = new MutBCA();
				    break;
				case "CLONALG":
					mutation[i] = new MutCLONALG();
				    break;
				case "HYBRID1":
					mutation[i] = new MutHYBRID1();
				    break;
				case "HYBRID2":
					mutation[i] = new MutHYBRID2();
				    break;
				case "HYBRID3":
					mutation[i] = new MutHYBRID3();
				    break;
				case "HYBRID4":
					mutation[i] = new MutHYBRID4();
				    break;
				case "HYBRID5":
					mutation[i] = new MutHYBRID5();
				    break;
				default:
					throw new IllegalArgumentException("Invalid mutation name: " + mut[i]);	
			}
		}
		
		switch (problem) {
			case "HIFF":
				fitness = new HIFF();		
				break;
			case "MinMax":
				fitness = new MinMax();
			    break;
			case "OneMax":
				fitness = new OneMax();	
			    break;
			case "Xdivk":
				fitness = new Xdivk();
			    break;
			case "LeadingOnes":
				fitness = new LeadingOnes();			
			    break;
			default:
				throw new IllegalArgumentException("Invalid problem name: " + problem);	
		}
		if (al == 0) {
			critNum = 1;
		}
		String experimentRoot = "results"
				+ "/" + fitness.getName() + "/" + critNum + "/" + SIZE + "/" + steps + "/" + al;
		
			
		String experimentRoot2 = "results/Graph"
				+ "/" + fitness.getName() + "/" + al;
		
		if (problem.equals("Xdivk")){
			experimentRoot += "/" + k;			
		}
		if (problem.equals("MinMax")){
			experimentRoot += "/" + k + "/" + d;
		}
		new File(experimentRoot).mkdirs();
		new File(experimentRoot2).mkdirs();
		for (int i = 0; i < mut.length; ++i) {
			ArrayList<ArrayList<Integer>> sum = new ArrayList<ArrayList<Integer>>();
			for (int j = 0; j < rounds; ++j) {	
				if (al >= 1) {
					ArrayList<Integer> fit =  ql.run(d, k, critNum, SIZE, steps, fitness, mutation[i], alpha, gamma, eps);	
					sum.add(fit);
				}
				if (al != 1) {
					ArrayList<Integer> fit1 = algo.run(k, SIZE, steps, fitness, mutation[i]);
					sum1.add(fit1);
				}			
				
			}
			if (al == 1) {
				Printer(experimentRoot + "/m" + mutation[i].getName() , sum);
				Printer(experimentRoot2 + "/m" + mutation[i].getName() , sum);
			}
			if (al == 0) {
				Printer(experimentRoot + "/" + mutation[i].getName(), sum1);
				Printer(experimentRoot2 + "/" + mutation[i].getName() , sum1);
			}
			if (al == 2) {
				Printer(experimentRoot + "/m" + mutation[i].getName(), sum);
				Printer(experimentRoot2 + "/m" + mutation[i].getName() , sum);
				Printer(experimentRoot + "/" + mutation[i].getName(), sum1);
				Printer(experimentRoot2 + "/" + mutation[i].getName() , sum1);
			}
		}	
	
	}
	
}


