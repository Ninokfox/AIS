import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
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

import reinforcement.QLearning;
import algo.Algorithm;

public class DeviationRendSaved extends ApplicationFrame {
	
    private static final long serialVersionUID = 1L;

    public DeviationRendSaved(String s, String path, String problem, int al, ArrayList<String>mut) throws IOException {
        super(s);
        JPanel jpanel = createDemoPanel(path, problem, al, mut);
        jpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(jpanel);
    }
    
    private static Color[] coloring() {
    	Color a[] = new Color[10];
    	a[0] = new Color(255, 150, 150);
    	a[1] = new Color(150, 150, 255);
    	a[2] = new Color(150, 255, 150);
    	a[3] = new Color(255, 255, 150); //yellow
    	a[4] = new Color(255, 150, 255); //magenta
    	a[5] = new Color(150, 255, 255); // turquoise
    	a[6] = Color.pink.brighter();
    	a[7] = Color.cyan.brighter();
    	a[8] = Color.black.brighter();
    	a[9] = Color.gray.brighter();
		return a;    	
    }
    
    private static XYDataset createDataset(String path, String problem, int al, ArrayList<String>mut) throws IOException {
    	YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
    	
    	File f = new File(path);
		String[] str = f.list();
		
		for (int i = 0; i < str.length; ++i) { 
			if (str[i].contains("~")) {
				break;
			}
			if (mut.contains(str[i])) {
				YIntervalSeries series = new YIntervalSeries(str[i]);
				
				BufferedReader reader = new BufferedReader(new FileReader(path + "/" + str[i]));	    	
		        String s;        
		        s = reader.readLine();
		        int j = 0;
		        while (!(s == null)) {        	
		        	String a1[] = s.split("\\s");
			        double value = Double.valueOf(a1[0]);
			        double dev = Double.valueOf(a1[1]);
			        series.add(j, value, value - dev, value + dev);            
		            s = reader.readLine();
		            j++;
		        }
		        dataset.addSeries(series);
		        reader.close();	
			}			
       
    	}
		return dataset;         
    }

    private static JFreeChart createChart(XYDataset xydataset, String pName) {
    	Color[] a = coloring();
        JFreeChart chart = ChartFactory.createXYLineChart(pName, "number of function evaluations", "average function value", xydataset, PlotOrientation.VERTICAL, true, true, false);
        chart.setBackgroundPaint(Color.white);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        DeviationRenderer renderer = new DeviationRenderer(true, false);
        for (int  i = 0; i < xydataset.getSeriesCount(); ++i) {        
	        renderer.setSeriesStroke(i, new BasicStroke(3F, 1, 1));	 
	        
	        renderer.setSeriesFillPaint(i, a[i]);
        }  

        plot.setRenderer(renderer);
        //
        NumberAxis valueAxis = (NumberAxis) plot.getRangeAxis();
        valueAxis.setAutoRangeIncludesZero(false);
        valueAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return chart;
    }

    public static JPanel createDemoPanel(String path, String problem, int al, ArrayList<String>mut) throws IOException {	
        JFreeChart jfreechart = createChart(createDataset(path, problem, al, mut), problem);
        return new ChartPanel(jfreechart);
    }

    public static void main(String args[]) throws IOException {
    	//int al = 0;
    	String hybrid1 = "BCA+RLS(50%)";
    	String hybrid2 = "BCA+RLS(ns)";
    	String hybrid3 = "CLONALG+RLS(50%)";
    	String hybrid4 = "CLONALG+RLS(ns)";
    	String hybrid5 = "CLONALG+RLS(ns+target)";
    	String[] a = {"RLS", "CLONALG", hybrid3, "m" + hybrid3, "BCA", hybrid4, "m" + hybrid4, "m"+hybrid5};//, hybrid4, hybrid3, "m" + hybrid4, "m" + hybrid5 };      
    	//a += {"mRLS", "mCLONALG", "mEA", "mBCA"};
    	String problem = "Xdivk";
    	int critNum = 2;
    	int steps = 50000;
    	
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
				SIZE = 1000;	
				k = 1;
				d = 0;
				critNum = 2;
			    break;
			case "Xdivk":
				SIZE = 100;	
				k = 5;
				d = 0;
				critNum = 2;
			    break;
			case "LeadingOnes":
				SIZE = 1000;	
				k = 1;
				d = 0;	
				critNum = 2;
			    break;
			default:
				throw new IllegalArgumentException("Invalid problem name: " + problem);	
		}
			
		
		ArrayList<String> mut = new ArrayList<String>();
    	for (int i = 0; i < a.length; ++i) {
    		mut.add(a[i]);
    	}    	
    	String path = "results/" + problem + "/" + critNum + "/" + SIZE + "/" + steps + "/2";//al;
		if (problem.equals("Xdivk")){
			path += "/" + k;			
		}
		if (problem.equals("MinMax")){
			path += "/" + k + "/" + d;
		}
    	DeviationRendSaved deviationrendererdemo1 = new DeviationRendSaved("JFreeChart : DeviationRenderer", path, problem, 2, mut);
        deviationrendererdemo1.pack();
        RefineryUtilities.centerFrameOnScreen(deviationrendererdemo1);
        deviationrendererdemo1.setVisible(true);
    }
}
