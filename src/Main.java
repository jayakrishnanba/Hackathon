/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import javax.swing.JFileChooser;
/**
 *
 * 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static String BaseDirectory;
    public static void main(String[] args) {
        // TODO code application logic here
        //Select the directory to work with
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("PDF File Chooser");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {//Open the diserd directory
			BaseDirectory = String.valueOf(chooser.getCurrentDirectory());
			System.out.println(BaseDirectory);
                        System.out.println("Finding the Dependency Graph...");
			//Create a Directory call TextFiles
            long startTime = System.currentTimeMillis();
			File TextDir = new File(BaseDirectory + "/TextFiles");
			if(TextDir.exists() == false){//If the directory TextFiles doesn't exists
                            System.out.println("Converting PDF to Text...");
				TextDir.mkdir();
				pdftotext.PdfTxt(BaseDirectory);


			}//Convert to Text
			
				
				
			

			//nowords.Words(BaseDirectory + "/TextFiles"); //Count the Number of words
                        File BiTextDir = new File(BaseDirectory + "/BiTextFiles");
                        if (BiTextDir.exists() == false) {//If the directory TextFiles doesn't exists
                        	//BiTextDir.mkdir();
                            System.out.println("Finding the Bigrams...");
                            ngrams.bigram(BaseDirectory);

                        }//Convert to Text

                        File TriTextDir = new File(BaseDirectory + "/TriTextFiles");
                        if (TriTextDir.exists() == false) {//If the directory TextFiles doesn't exists
                            System.out.println("Finding the Trigrams...");
                            ngrams.trigram(BaseDirectory);
                        }//Convert to Text

                        File ForTextDir = new File(BaseDirectory + "/ForTextFiles");
                        if (ForTextDir.exists() == false) {//If the directory TextFiles doesn't exists//
                                System.out.println("Finding the Fourgrams...");
                                ngrams.forgram(BaseDirectory);
                        }//Convert to Text

			File IndexDir = new File(BaseDirectory + "/Index");
			if(IndexDir.exists() == false){ // Indexing is not done
                System.out.println("Indexing the Text...");
					IndexDir.mkdir();
					texttoindex.TexIndx(BaseDirectory + "/TextFiles",BaseDirectory + "/Index");

			}//Create Index

			File BiIndexDir = new File(BaseDirectory + "/BiIndex");
			if(BiIndexDir.exists() == false){ // Indexing is not done
                System.out.println("Indexing the Bigrams...");
					BiIndexDir.mkdir();
					texttoindex.TexIndx(BaseDirectory + "/BiTextFiles",BaseDirectory + "/BiIndex");
			}//Create Index

			File TriIndexDir = new File(BaseDirectory + "/TriIndex");
			if(TriIndexDir.exists() == false){ // Indexing is not done
                System.out.println("Indexing the Trigrams...");
					TriIndexDir.mkdir();
					texttoindex.TexIndx(BaseDirectory + "/TriTextFiles",BaseDirectory + "/TriIndex");
			}//Create Index

			File ForIndexDir = new File(BaseDirectory + "/ForIndex");
			if(ForIndexDir.exists() == false){ // Indexing is not done
                System.out.println("Indexing the Fourgrams...");
					ForIndexDir.mkdir();
					texttoindex.TexIndx(BaseDirectory + "/ForTextFiles",BaseDirectory + "/ForIndex");
			}//Create Index

									//ngrams.unigrams(BaseDirectory); //Top 20 Unigrams
									//ngrams.bigrams(BaseDirectory); //Top 20 Bigrams
									//ngrams.trigrams(BaseDirectory); //Top 20 Trigrams
			File key = new File(BaseDirectory + "/tfidf.txt");
			if(key.exists() == false){
				System.out.println("Finding the Keywords...");
            	topiapy.keyword(BaseDirectory);
            	tfidf.tfid(BaseDirectory, BaseDirectory + "/Index", 40);
            	tfidf.tfid(BaseDirectory, BaseDirectory + "/BiIndex", 30);
            	tfidf.tfid(BaseDirectory, BaseDirectory + "/TriIndex", 20);
            	tfidf.tfid(BaseDirectory, BaseDirectory + "/ForIndex", 10);
            	dup.duplicate(BaseDirectory);
			}
            
			File ManualFile = new File(BaseDirectory + "/manual");
            if (ManualFile.exists() == true) {//If manual file exists
            	common.comm(BaseDirectory);//to identify false positive and negative.
            }
            
            System.out.println("Finding the Relations...");
            	relation.reln(BaseDirectory);
            
           System.out.println("Generating teh DAG...");
                         				//position.prece("", "", BaseDirectory);
                         
                         				//count.occur("unique", "birth", BaseDirectory);
				dag.draw(BaseDirectory);
            	dag.show(BaseDirectory);
            	dag.protege(BaseDirectory);
										//bigram.test(BaseDirectory);
										//trigram.test(BaseDirectory);
										//tfidf.tfid(BaseDirectory); //Top 200 TfIdf
										//HighFreqTerms.termfreq(BaseDirectory, "fieldname"); //top 200 High Frequency words
										//indexsearch.search(BaseDirectory,null);

            long stopTime = System.currentTimeMillis();
    		System.out.println("Total time: " + (stopTime - startTime) + " ms"); 
		}//If the directory is selected

	}//end of main

}//end of class
