import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Parser {
	public static void main(String[] args) throws IOException {    	
    	
    	BufferedReader reader = new BufferedReader(new FileReader("MinMaxRandomTestBCABIG40.txt"));
    	PrintWriter out = new PrintWriter(new FileOutputStream("MinMaxOut.out"));
        String s;        
        s = reader.readLine();
        s = reader.readLine();
        while (!(s == null)) {        	
            StringTokenizer st = new StringTokenizer(s);
            st.nextToken();
            out.print(st.nextToken() + "	");             
            s = reader.readLine();
            StringTokenizer stst = new StringTokenizer(s);
            stst.nextToken();
            out.println(stst.nextToken());  
            s = reader.readLine();
        }   	
        out.close();        
    }
}
