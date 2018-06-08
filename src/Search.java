import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.TermFreqVector;
import org.apache.lucene.index.TermPositionVector;
import org.apache.lucene.index.TermVectorOffsetInfo;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


 public class Search {  
	       
	     /*public static void main(String[] args) throws IOException, ParseException{  
	     
	         Search s = new Search();  
	         s.doSearch(args[0], args[1]);  
	     } */ 
	       
	     Search(){}  
	       
	     public static int doSearch(String db, String querystr){ 
	    	 int[] termposx = null;
	           
	         // 1. Specify the analyzer for tokenizing text.  
	         //    The same analyzer should be used as was used for indexing  
	    	 try{
	         /*StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);  
	       
	         Directory index = FSDirectory.open(new File(db+"/Index"));  
	       
	         // 2. query  
	         Query q = new QueryParser(Version.LUCENE_CURRENT, "fieldname", analyzer).parse(querystr);  
	       
	         // 3. search  
	         int hitsPerPage = 10;  
	         IndexSearcher searcher = new IndexSearcher(index, true);  
	         IndexReader reader = IndexReader.open(index, true);  
	         searcher.setDefaultFieldSortScoring(true, false);  
	         TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);  
	         searcher.search(q, collector);  
	         ScoreDoc[] hits = collector.topDocs().scoreDocs; */ 
	         
	    		 Directory index = FSDirectory.open(new File(db+"/Index"));
	         
	         Analyzer analyzer = new StandardAnalyzer();
				IndexSearcher searcher = new IndexSearcher(index);
				QueryParser parser = new QueryParser("fieldname", analyzer);
				IndexReader reader = IndexReader.open(index, true);
							
				//To search
				
				Query query = parser.parse(querystr);
				Hits hits = searcher.search(query);
				
				
	           
	         // 4. display term positions, and term indexes   
	         //System.out.println("Found " + hits.length() + " hits.");  
	         for(int i=0;i<hits.length();++i) {  
	               //System.out.println("here");
	             int docId = hits.id(i);
	             TermFreqVector tfvector = reader.getTermFreqVector(docId, "fieldname");  
	             TermPositionVector tpvector = (TermPositionVector)tfvector;  
	             // this part works only if there is one term in the query string,  
	             // otherwise you will have to iterate this section over the query terms.  
	             int termidx = tfvector.indexOf(querystr);  
	             termposx = tpvector.getTermPositions(termidx);  
	             TermVectorOffsetInfo[] tvoffsetinfo = tpvector.getOffsets(termidx);
	             if(termposx.length != 0)
	            	 return termposx[0];
	            	 
	             
	             /*System.out.println("index: "+ termidx);
	             System.out.println("length: "+ termposx.length);
	             
	             
	             for (int j=0;j<termposx.length;j++) {  
	                 System.out.println("termpos : "+termposx[j]);  
	             }  
	             for (int j=0;j<tvoffsetinfo.length;j++) {  
	                 int offsetStart = tvoffsetinfo[j].getStartOffset();  
	                 int offsetEnd = tvoffsetinfo[j].getEndOffset();  
	                 System.out.println("offsets : "+offsetStart+" "+offsetEnd);  
	             }  
	               
	            // print some info about where the hit was found...  
	             Document d = searcher.doc(docId);  
	             System.out.println((i + 1) + ". " + d.get("filename")); */ 
	         }  
	       
	         // searcher can only be closed when there  
	         // is no need to access the documents any more.   
	         searcher.close();  
	     }  
	     catch(Exception e){
	    	 System.err.println("Error: "+e.getMessage());
				e.printStackTrace();
	     }
	     
			return 0;
	     }
	 } 