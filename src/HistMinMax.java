import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import reinforcement.Hist;
import mutation.*;
import fitnessFunc.Fitness;
import fitnessFunc.MinMax;



public class HistMinMax {
	public static void main(String[] args) throws FileNotFoundException {
		Hist algo = new Hist();
		Fitness fitness = new MinMax();		
		double alpha = 0.7;
		double gamma = 0.7;
		double e = 0;
		int critNum = 3;
		int k = 5;
		int d = 165;
		int SIZE = 200;		
		Mutation mutation = new MutBCA();		
		
		//for (int j = 0; j < 3; ++j) {
			//SIZE += k*2;
			String experimentRoot = (args.length == 0 ? "D:/ArtificialIS/results/HistMinMax" : args[0])
					+ "/" + fitness.getName() + "_" + mutation.getName() + "/" + k + "/"+ SIZE;
			new File(experimentRoot).mkdirs();
			try {
				long[][]matrix = new long[SIZE][critNum];
			    PrintWriter zzz = new PrintWriter(new FileOutputStream(experimentRoot + "/ans"+ d+ ".txt"));
				int rounds = 100;
			    int steps = 1000000;
				for (int i = 0; i < rounds; ++i) {
					System.out.println(i);
					long [][] m = algo.run(d, k, critNum, SIZE, steps, fitness, mutation, alpha, gamma, e);					
					for (int j = 0; j < SIZE; ++j) {
						for (int h = 0; h < critNum; ++h) {							
							matrix[j][h] += m[j][h];
						}
					}
				}
				for (int i = 0; i < SIZE; ++i) {
					int sum = 0;
					int last = 100;
					for (int h = 0; h < critNum; ++h){
						
						sum += matrix[i][h];
					}
					if (sum != 0){
						for (int h = 0; h < critNum - 1; ++h) {
							double part = 100 * matrix[i][h] / sum;
							matrix[i][h] = (int)part;
							zzz.print(matrix[i][h] + "	");							
							last -= matrix[i][h];
						}
						matrix[i][critNum - 1] = last;
						zzz.println(matrix[i][critNum - 1]);
					}
					else zzz.println("33	33	34");
				}
				zzz.close();
				
			} catch (FileNotFoundException x) {
				x.printStackTrace();
			}
	//}	
	}
}
