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

public class BIG{	
	public static void main(String[] args) throws FileNotFoundException {
			Algorithm algo = new Algorithm();
			QLearning ql = new QLearning();
			
			Fitness fitness = new Xdivk();
			Fitness fitness2 = new MinMax();
			Mutation mutation = new MutRLS();
			Mutation mutation2 = new MutBCA();
			int k = 10;
		    int SIZE = 600;
		    int d = 495;
			String experimentRoot = (args.length == 0 ? "D:/ArtificialIS/results/BIG" : args[0])
					+ "/" + fitness2.getName()+"/"+ SIZE+"/"+k;
			new File(experimentRoot).mkdirs();
			try {
				
			    PrintWriter zzz = new PrintWriter(new FileOutputStream(experimentRoot + "/ans"+d+".txt"));
				//PrintWriter yyy = new PrintWriter(new FileOutputStream(experimentRoot + "/BCA.txt"));
				//PrintWriter xxx = new PrintWriter(new FileOutputStream(experimentRoot + "/RLS+QL.txt"));
				//PrintWriter uuu = new PrintWriter(new FileOutputStream(experimentRoot + "/BCA+QL.txt"));
			    int[] sum = new int[4];
			    			    
			    int rounds = 100;
			    int steps = 1000000;
			    int[][] res = new int[rounds][4];
			    
			    
				for (int i = 0; i < rounds; ++i) {
					System.out.println(i);
					ArrayList<Integer> fit = algo.run(k, SIZE, steps, fitness, mutation);
					ArrayList<Integer> fit3 = algo.run(k, SIZE, steps, fitness, mutation2);
					ArrayList<Integer> fit2 = ql.run(d, k, 3, SIZE, steps, fitness2, mutation, 0.5, 0.5, 0.0);
					ArrayList<Integer> fit4 = ql.run(d, k, 3, SIZE, steps, fitness2, mutation2, 0.7, 0.7, 0.0);
					sum[0] += fit.get(fit.size() - 1);
					sum[1] += fit2.get(fit2.size() - 1);
					sum[2] += fit3.get(fit3.size() - 1);
					sum[3] += fit4.get(fit4.size() - 1);
					res[i][0] = fit.get(fit.size() - 1);
					res[i][1] = fit2.get(fit2.size() - 1);
					res[i][2] = fit3.get(fit3.size() - 1);
					res[i][3] = fit4.get(fit4.size() - 1);				
					
				}					
				
				for (int i = 0; i < 4; ++i) {
					double ans = 0;
					double av = sum[i]/100.0;	
					zzz.print(new DecimalFormat("#0.0").format(av));
					for (int j = 0; j < 100; ++j) {							
						ans += (res[j][i]- av) * (res[j][i] - av);
					}
					ans /= 99.0;
					ans = Math.sqrt(ans);
					zzz.println("("+ new DecimalFormat("#0.0").format(ans) + ")	");
				}			
		
				zzz.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}


		


