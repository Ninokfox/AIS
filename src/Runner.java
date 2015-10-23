import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JPanel;

import mutation.MutBCA;
import mutation.MutCLONALG;
import mutation.MutEA;
import mutation.MutHYBRID1;
import mutation.MutHYBRID2;
import mutation.MutHYBRID3;
import mutation.MutHYBRID4;
import mutation.MutHYBRID5;
import mutation.MutRLS;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

import fitnessFunc.HIFF;
import fitnessFunc.LeadingOnes;
import fitnessFunc.MinMax;
import fitnessFunc.OneMax;
import fitnessFunc.Xdivk;

public class Runner {
	
    public static void main(String args[]) throws IOException {
    	int alnum = 0;
    	String[] mut = {"RLS","CLONALG", "EA", "BCA"};//, "HYBRID1", "HYBRID2", "HYBRID3", "HYBRID4", "HYBRID5"};//"EA"};
    	String problem = "HIFF";
    	//!!! HIFF critNum !!!
    	int critNum = 3;
    	int steps = 100000;
    	
    	int SIZE = 400;    	
    	int k = 1;
    	int d = 0;
    	switch (problem) {
			case "HIFF":
				SIZE = 64;	
				k = 1;
				d = 0;
				break;
			case "MinMax":
				SIZE = 400;	
				k = 10;
				d = 266;
				critNum = 3;
			    break;
			case "OneMax":
				SIZE = 100;	
				k = 1;
				d = 0;
				critNum = 2;
			    break;
			case "Xdivk":
				SIZE = 32;	
				k = 4;
				d = 0;
				critNum = 2;
			    break;
			case "LeadingOnes":
				SIZE = 50;	
				k = 1;
				d = 0;	
				critNum = 2;
			    break;
			default:
				throw new IllegalArgumentException("Invalid problem name: " + problem);	
		}

    	//PlotValuesCalc.main(SIZE, critNum, steps, k, d, alnum, mut, problem);
    	Runtime.main(SIZE, critNum, steps, k, d, alnum, mut, problem);

    }
}
