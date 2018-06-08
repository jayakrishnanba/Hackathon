import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.TermPositionVector;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

public class position {
	@SuppressWarnings("deprecation")
	public static void prece(String str1, String str2,String BasDir){
		try{
				
				IndexSearcher is = new IndexSearcher(BasDir+"/Index");
				Analyzer analyzer = new StandardAnalyzer();
				QueryParser parser = new QueryParser("fieldname", analyzer);
				
				String lower_str1 = str1.toLowerCase(); 
				Query query = parser.parse("computer");
				Hits hits = is.search(query);
				
				for (int i = 0; i < hits.length(); ++i) {
					TermPositionVector tpv = (TermPositionVector) is.getIndexReader().getTermFreqVector(0, "computer");
					System.out.println(tpv);
				
					int index = tpv.indexOf("the"); // 7
					System.out.println(index);
					int[] positions = tpv.getTermPositions(index); // {0, 6}
					for(int j=0;j<positions.length;j++)
						System.out.println(positions[i]);
				}
				
		}
		catch(Exception e){
			System.err.println("Error: "+e.getMessage());
			e.printStackTrace();
		}
	}
}
