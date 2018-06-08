import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


public class texttoindex {
	public static void TexIndx(String TxtDir,String IndDir){
		try{
			File TexDir = new File(TxtDir);
			File files[] = TexDir.listFiles();
			//System.out.println("Total Files: " + files.length);
			
			Analyzer analyzer = new StandardAnalyzer();
			Directory directory = FSDirectory.getDirectory(IndDir);
			IndexWriter iwriter = new IndexWriter(directory, analyzer, true);
			iwriter.setMaxFieldLength(25000);
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
						str = str + temp.toLowerCase();	
						//System.out.println(str);
					}
						
						Document doc = new Document();
						doc.add(new Field("fieldname", str, Field.Store.YES, Field.Index.TOKENIZED, Field.TermVector.WITH_POSITIONS_OFFSETS ));
						doc.add(new Field("filename",filename, Field.Store.YES, Field.Index.TOKENIZED));
						doc.add(new Field("fileaddress", filepath, Field.Store.YES, Field.Index.TOKENIZED));

						
			            iwriter.addDocument(doc);	          
		           		iwriter.optimize();
					
					in.close();
				}
				
			}
			iwriter.close();			
		}
		catch(Exception e){
			System.out.println("Error : " + e.getMessage());
			e.printStackTrace();
		}
	}

}
