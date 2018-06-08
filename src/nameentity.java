/** This is a demo of calling CRFClassifier programmatically.
  *  <p>
  *  Usage: <code> java -cp stanford-ner.jar:. -mx1000m NERDemo  
{serializedClassifier} [fileName]</code>
  *  <p>
  *  If arguments aren't specified, they default to ner-eng-ie.crf-3- 
all2006.ser.gz  and
  *  some hardcoded sample text.
  *  <p> *
  *  To use CRFClassifier from the command line:
  *  java -mx1000m edu.stanford.nlp.ie.crf.CRFClassifier -loadClassifier
  *      [classifier] -textFile [file]
  *  Or if the file is already tokenized and one word per line, use the
  *  version below (note the 's' instead of the 'x'):
  *  java -mx1000m edu.stanford.nlp.ie.crf.CRFClassifier -loadClassifier
  *      [classifier] -testFile [file]
  *
  *  @author Jenny Finkel
  */

import edu.stanford.nlp.ie.crf.*;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.AnswerAnnotation;
import edu.stanford.nlp.util.StringUtils;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class nameentity {

	public static void name(String BasDir){
		try{
			File TxtDir = new File(BasDir + "/TextFiles");
			File files[] = TxtDir.listFiles();

       String serializedClassifier = "classifiers/ner-eng-ie.crf-3-all2008.ser.gz";

       File NameDir = new File(BasDir + "/NameFiles");
		if(NameDir.exists() == false){//If the directory TextFiles doesn't exists 
			NameDir.mkdir();								
		}

       AbstractSequenceClassifier classifier =  CRFClassifier.getClassifierNoExceptions(serializedClassifier);

       for(int i = 0; i < files.length; i++){
			String filename = files[i].getName().toString();
			//System.out.println(filename);
			
			if(files[i].isFile()){
				String filepath = files[i].getPath();
				BufferedReader in = new BufferedReader(new FileReader(filepath));
				//System.out.println("Indexing " + filename);
				String temp;
				String str ="";
				while ((temp = in.readLine()) != null) {						
					//str = str + temp.toLowerCase();	
					//System.out.println(str);
					temp = temp.replaceAll("[^0-9a-zA-Z,.-_ ]", "");
					str = str +" "+ temp;
				}

        
				/* String s1 = "My name is Jenny Rose.  I go to Stanford University, which is in California.";
         String s2 = "I study computer science.  I grew up in New Jersey.";
         System.out.println(classifier.testString(s1));
         System.out.println(classifier.testStringInlineXML(s2));*/
         File TextFile = new File(BasDir + "/NameFiles/" + filename); //Create Text Files
			FileWriter out = new FileWriter(TextFile,true);  //Write into the Text Files
			out.write(classifier.testStringInlineXML(str));
			out.close();
				
	
			}
       	}
	}
	
	catch(Exception e){
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	}

}
