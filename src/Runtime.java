
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.PrintWriter;

import java.util.ArrayList;


import reinforcement.QLRuntime;
import reinforcement.QLearning;

import algo.AlgoRuntime;
import algo.Algorithm;
import mutation.*;
import fitnessFunc.*;


public class Runtime {
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
	private static void Printer(String path, ArrayList<Integer> sum) throws FileNotFoundException {
	
		PrintWriter xxx = new PrintWriter(new FileOutputStream(path));
		int rounds = sum.size();
		if (rounds == 0) {
			xxx.println("inf");
			
		}
		else {
			double ans = 0;
			for (int i = 0; i < rounds; ++i) {
				ans += sum.get(i);		
			}
			double av = (double)ans/rounds;
			xxx.print(av + " ");
			ans = 0;
			for (int j = 0; j < rounds; ++j) {
				ans += (sum.get(j) - av) * (sum.get(j) - av);
			}
			ans /= rounds - 1;
			ans = Math.sqrt(ans);
			xxx.println(ans + " " + rounds + "%");
			
		}
		
		xxx.close();		
	}
	public static void main(int SIZE, int critNum, int steps, int k, int d, int al, String[] mut, String problem) throws FileNotFoundException {		
			
		Fitness fitness;		
		
		AlgoRuntime algo = new AlgoRuntime();	
		QLRuntime ql = new QLRuntime();	
		
		int rounds = 100;
		
		double alpha = 0.7;
		double gamma = 0.7;
		double eps = 0.0;
		

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
		String experimentRoot = "results/runtime"
				+ "/" + fitness.getName() + "/" + critNum + "/" + SIZE + "/" + steps + "/" + al;			
		
		if (problem.equals("Xdivk")){
			experimentRoot += "/" + k;			
		}
		if (problem.equals("MinMax")){
			experimentRoot += "/" + k + "/" + d;
		}
		new File(experimentRoot).mkdirs();
		PrintWriter xxx = new PrintWriter(new FileOutputStream(experimentRoot + "/ans"));
		for (int i = 0; i < mut.length; ++i) {
			ArrayList<Integer> sum = new ArrayList<Integer>();
			ArrayList<Integer> sum1 = new ArrayList<Integer>();
			for (int j = 0; j < rounds; ++j) {	
				if (al >= 1) {
					ArrayList<Integer> fit =  ql.run(d, k, critNum, SIZE, steps, fitness, mutation[i], alpha, gamma, eps);	
					if (fit.get(fit.size() - 1) == SIZE/k)
						sum.add(fit.size());
				}
				if (al != 1) {
					ArrayList<Integer> fit1 = algo.run(k, SIZE, steps, fitness, mutation[i]);
					if (fit1.get(fit1.size() - 1) == SIZE/k)
						//System.out.println(i);
						sum1.add(fit1.size() - 1);
				}			
				
			}
			xxx.print(mut[i] + " ");
			int r = 0;
			if (al >= 1) {
				r = sum.size();
				if (r == 0) {
					xxx.println("inf");					
				}
				else {
					double ans = 0;
					for (int j = 0; j < r; ++j) {
						ans += sum.get(i);		
					}
					double av = (double)ans/r;
					xxx.print(av + " ");
					ans = 0;
					for (int j = 0; j < r; ++j) {
						ans += (sum.get(j) - av) * (sum.get(j) - av);
					}
					ans /= r - 1;
					ans = Math.sqrt(ans);
					xxx.println(ans + " " + r + "%");
					
				}
			}
			if (al != 1) {
				r = sum1.size();
				if (r == 0) {
					xxx.println("inf");
					
				}
				else {
					double ans = 0;
					for (int j = 0; j < r; ++j) {
						ans += sum1.get(i);		
					}
					double av = (double)ans/r;
					xxx.print(av + " ");
					ans = 0;
					for (int j = 0; j < r; ++j) {
						ans += (sum1.get(j) - av) * (sum1.get(j) - av);
					}
					ans /= r - 1;
					ans = Math.sqrt(ans);
					xxx.println(ans + " " + r + "%");
				
				}
					
			}			
				
		}
		xxx.close();	
	
	}
}


