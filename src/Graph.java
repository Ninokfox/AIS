import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import reinforcement.QLearning;

import algo.Algorithm;
import mutation.*;
import fitnessFunc.*;


public class Graph {	
	public static void main(String[] args) throws FileNotFoundException {
			Algorithm algo = new Algorithm();
			QLearning ql = new QLearning();
			
			Fitness fitness = new Xdivk();
			Fitness fitness2 = new MinMax();
			Mutation mutation = new MutRLS();
			Mutation mutation2 = new MutBCA();
			String experimentRoot = (args.length == 0 ? "D:/ArtificialIS/results" : args[0])
					+ "/" + fitness2.getName() + "_GRAPH1";
			new File(experimentRoot).mkdirs();
			try {
				int k = 10;
			    int SIZE = 400;
			    int d = 0;
			    PrintWriter zzz = new PrintWriter(new FileOutputStream(experimentRoot + "/RLS.txt"));
				PrintWriter yyy = new PrintWriter(new FileOutputStream(experimentRoot + "/BCA.txt"));
				PrintWriter xxx = new PrintWriter(new FileOutputStream(experimentRoot + "/RLS+QL.txt"));
				PrintWriter uuu = new PrintWriter(new FileOutputStream(experimentRoot + "/BCA+QL.txt"));
			    ArrayList<ArrayList<Integer>> sum = new ArrayList<ArrayList<Integer>>();
			    ArrayList<ArrayList<Integer>> sum2 = new ArrayList<ArrayList<Integer>>();
			    ArrayList<ArrayList<Integer>> sum3 = new ArrayList<ArrayList<Integer>>();
			    ArrayList<ArrayList<Integer>> sum4 = new ArrayList<ArrayList<Integer>>();
			    int rounds = 100;
			    int steps = 50000;
				for (int i = 0; i < rounds; ++i) {
					System.out.println(i);
					ArrayList<Integer> fit = algo.run(k, SIZE, steps, fitness, mutation);
					ArrayList<Integer> fit2 = algo.run(k, SIZE, steps, fitness, mutation2);
					ArrayList<Integer> fit3 = ql.run(d, k, 3, SIZE, steps, fitness2, mutation, 0.5, 0.5, 0.0);
					ArrayList<Integer> fit4 = ql.run(d, k, 3, SIZE, steps, fitness2, mutation2, 0.7, 0.7, 0.0);
					sum.add(fit);
					sum2.add(fit2);
					sum3.add(fit3);
					sum4.add(fit4);
					
				}
				for (int i = 0; i < steps; ++i) {
					int ans = 0;
					int ans2 = 0;
					int ans3 = 0;
					int ans4 = 0;
					for (int j = 0; j < rounds; ++j) {
						ans += sum.get(j).get(i);
						ans2 += sum2.get(j).get(i);
						ans3 += sum3.get(j).get(i);
						ans4 += sum4.get(j).get(i);
						
					}
					zzz.println(ans);
					yyy.println(ans2);
					xxx.println(ans3);
					uuu.println(ans4);
				}
				uuu.close();
				xxx.close();
				yyy.close();
				zzz.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}


	

