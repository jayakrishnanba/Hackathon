

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;


public class conf {
	public static int occur(String str1, String str2, String BasDir){
		int c = 0;
		try{
			IndexSearcher is = new IndexSearcher(BasDir+"/Index");
			Analyzer analyzer = new StandardAnalyzer();
			QueryParser parser = new QueryParser("fieldname", analyzer);
			
			String lower_str1 = str1.toLowerCase();
			String lower_str2 = str2.toLowerCase();
			
			Query query = parser.parse(lower_str1);
	        Hits hits = is.search(query);
	        
	        int size = hits.length();
	        String arr[] = new String[size];
	        
	        for (int i=0; i<size; i++) {
	            Document doc = hits.doc(i);
	            arr[i] = doc.get("fileaddress").toString();
	            //System.out.println(arr[i]);
	        }
	        
	      //To remove Duplicates
			java.util.Arrays.sort(arr);
			
			List list = Arrays.asList(arr);
			Set set = new HashSet(list);
			
			//System.out.println("Number of Files " + set.size());
			int df = set.size();
			
			
			File TexDir = new File(BasDir + "/TextFiles");
			File files[] = TexDir.listFiles();
			int numDoc = files.length;
			
			
			//To display results
			
			for(Iterator iterator = set.iterator(); iterator.hasNext(); ){
				Object res = iterator.next();
				String FileAdd = res.toString();
				//System.out.println("In File " + FileAdd + " :**********");
				String FileAdd1;				
				int coun = 0;
				int counu = 0;
				
				
				for(int i = 0; i < hits.length(); i++){
					Document hitdoc = hits.doc(i);
					FileAdd1 = hitdoc.get("fileaddress").toString();
					
					if(FileAdd.equals(FileAdd1)){
						String text = hitdoc.get("fieldname").toString();
						coun = (text.length() - text.toLowerCase().replaceAll("\\b"+lower_str2+"\\b","").length())/str2.length();
						counu  = (text.length() - text.toUpperCase().replaceAll("\\b"+str2+"\\b","").length())/str2.length();
						if(coun ==0 && counu == 0){
							c++;
						}			
					}
				}
				
			}
			
	        is.close();

			//System.out.println("count "+c);
			
		}
		catch(Exception e){
			System.err.println("Error: "+e.getMessage());
			e.printStackTrace();
		}
		return c;
	}	
}
