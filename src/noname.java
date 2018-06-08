import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;


public class noname {
	public static void name(String BasDir){
		try{
			File TxtDir = new File(BasDir + "/NameFiles");
			File files[] = TxtDir.listFiles();

       

       File NameDir = new File(BasDir + "/NoNameFiles");
		if(NameDir.exists() == false){//If the directory TextFiles doesn't exists 
			NameDir.mkdir();								
		}

       

       for(int i = 0; i < files.length; i++){
			String filename = files[i].getName().toString();
			//System.out.println(filename);
			
			if(files[i].isFile()){
				String filepath = files[i].getPath();
				BufferedReader in = new BufferedReader(new FileReader(filepath));
				//System.out.println("Indexing " + filename);
				String line;
				//String str ="";
				while ((line = in.readLine()) != null) {						
					//str = str + temp.toLowerCase();	
					//System.out.println(str);
					//temp = temp.replaceAll("[^0-9a-zA-Z,.-_ ]", "");
					StringTokenizer st = new StringTokenizer(line," ");
					while(st.hasMoreTokens()){
						String str = st.nextToken();
						if(str.indexOf("<") == -1){
							File TextFile = new File(BasDir + "/NoNameFiles/" + filename); //Create Text Files
							FileWriter out = new FileWriter(TextFile,true);  //Write into the Text Files
							out.write(str + " ");
							out.close();
						}
					}

        
				/* String s1 = "My name is Jenny Rose.  I go to Stanford University, which is in California.";
         String s2 = "I study computer science.  I grew up in New Jersey.";
         System.out.println(classifier.testString(s1));
         System.out.println(classifier.testStringInlineXML(s2));*/
         
				}
	
			}
       	}
	}
	
	catch(Exception e){
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	}
}
