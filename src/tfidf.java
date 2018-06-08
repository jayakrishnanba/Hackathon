import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermFreqVector;


public class tfidf {
		
		
		
		public static void tfid(String BasDir, String IndDir, int iidf) {
			try{
				BufferedWriter out = new BufferedWriter(new FileWriter(BasDir + "/temp.txt",true));
				//StrInfoQueue tfque = new StrInfoQueue(idf);
				Comparator<StrInfo> comparator = new StringLengthComparator();
				PriorityQueue<StrInfo> tfqueue = new PriorityQueue<StrInfo>(10, comparator);
				
				TermFreqVector tfv = null;
				IndexReader reader = IndexReader.open(IndDir);
				//System.out.println(reader.maxDoc());
				int numDoc = reader.maxDoc();
				for(int i = 0;i<reader.maxDoc();i++){
					
					
					tfv = reader.getTermFreqVector(i,"fieldname");
					if(tfv==null)
						continue;
					
					
					for(int termNum=0; termNum<tfv.size();termNum++){
				    	double docFreq=0,tf=0;
		                double idf=0, score=0;
		                
		                docFreq=reader.docFreq(new Term("fieldname", tfv.getTerms()[termNum]));
				      tf=tfv.getTermFrequencies()[termNum];
				      //System.out.println(tf);
				                    idf=Math.log10((numDoc)/docFreq);
				                    score=tf*idf;
				                    //System.out.println(tfv.getTerms()[termNum] +" : "+score);
				                    int temp = 0, ind = -1;
				                    if(tfv.getTerms()[termNum].length() < 3){		        			   
					        			  temp++;	        			  
					        		  }	
				                    for (int j = 0; j < tfv.getTerms()[termNum].length(); j++) {
					        		  	
				        	            //If we find a non-digit character we return true.
				        	            if (Character.isDigit(tfv.getTerms()[termNum].charAt(j))){
				        	            	temp++;
				        	            	break;
				        	            } 	
				        	        }
				                    while(ind < (HighFreqTerms.number.length - 1)){
						        		  ind ++;		        		  
						        		  if(tfv.getTerms()[termNum].toLowerCase().indexOf(HighFreqTerms.number[ind].toLowerCase()) != -1){
						        			  //System.out.println(terms.term().text()+"++++++++++++++++++");
						        			  temp++;
						        			  break;
						        		  }
						        	  }
						        	  ind = -1;
						        	  while(ind < (HighFreqTerms.stopwords.length-1)){
						        		  ind++;		        		  	
						        		  if(tfv.getTerms()[termNum].toLowerCase().compareTo(HighFreqTerms.stopwords[ind].toLowerCase())==0){
						        			  temp++;
						        			  break;		    
						        		  }
						        		}
				                    if(temp == 0){
				                    	/*int in = 0;
				    					while(tfque.size() != 0){
				    						StrInfo strInfo = (StrInfo) tfque.pop();						
				    						if(strInfo.str.compareTo(tfv.getTerms()[termNum])==0)
				    							in++;
				    							continue;		
				    							
				    					}
				    					if(in==0)*/
				    						priqueue.addToQueue(tfqueue, new StrInfo(tfv.getTerms()[termNum] , score));
				                    }
				                    
				   }
				}
				String str = "";
				String str1 = "";
				//System.out.println(tfque.size());
				while (tfqueue.size() > iidf) 
					tfqueue.remove();
				while (tfqueue.size() != 0) {
		  			StrInfo strInfo = (StrInfo) tfqueue.remove();
		  			str1 = strInfo.str.replaceAll("zzzz", " ");
		  			//System.out.println(str1 + "\t\t" + strInfo.Freq);		  			
			        str = str1.toString() +"\n" + str;
		  		}
				out.write(str);
		  		out.newLine();
		  		out.close();
				
			}
			catch(Exception e){
				System.out.println("Error " + e.getMessage());
				e.printStackTrace();
			}
		}
}
