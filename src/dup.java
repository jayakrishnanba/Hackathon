import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;


public class dup {
	public static void duplicate(String BasDir){
		try{
			BufferedReader reader = new BufferedReader(new FileReader(BasDir + "/temp.txt"));
    			Set<String> lines = new HashSet<String>(10000); // maybe should be bigger
   			String line;
    			while ((line = reader.readLine()) != null) {
    				if(line.length() <= 2)
    	                continue;
        			lines.add(line);
    			}
    			reader.close();
    			BufferedWriter writer = new BufferedWriter(new FileWriter(BasDir + "/tfidf.txt"));
    			for (String unique : lines) {
        			writer.write(unique);
        			writer.newLine();
    			}
    			writer.close();
		}

		catch(Exception e){
			System.err.println("Error: "+e.getMessage());
			e.printStackTrace();
		}
	}

}