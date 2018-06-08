

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;


public class ngrams {
	public static void bigram(String BasDir){
		try{
			File TexDir = new File(BasDir + "/TextFiles");
			File files[] = TexDir.listFiles();
			//System.out.println("Total Files: " + files.length);
			
			File TextDir = new File(BasDir + "/BiTextFiles");
			if(TextDir.exists() == false){//If the directory TextFiles doesn't exists 
				TextDir.mkdir();								
			}//
			
			for(int i = 0; i < files.length; i++){
				String filename = files[i].getName().toString();
				//System.out.println(filename);
				if(files[i].isFile()){
					String filepath = files[i].getPath();
					BufferedReader in = new BufferedReader(new FileReader(filepath));
					String tmp = "";
					String line = "";
					
					while((line = in.readLine()) != null){
						StringTokenizer st = new StringTokenizer(line," ");
						String prev = "";
						while(st.hasMoreTokens()){
							String curr = st.nextToken();
							
							curr = curr.replaceAll("[^0-9a-zA-Z.]", " ");
							int tem = 0, ind = -1;
		                    if(curr.length() < 2 || prev.length() < 2){		        			   
			        			  tem++;	        			  
			        		  }	
		                    if((curr.indexOf(" ") !=-1) || (prev.indexOf(" ") !=-1)){
								tem++;
							}
		                    if(curr.compareTo(" ") == 0 || prev.compareTo(" ") == 0){
		                    	tem++;
		                    }
		        	        for (int k = 0; k < prev.length(); k++) {
				        		  	
			        	            //If we find a non-digit character we return true.
			        	            if (prev.indexOf(".") != -1 ){
			        	            	tem++;
			        	            	break;
			        	            }
		        	        }
		        	        for (int k = 0; k < prev.length(); k++) {
			        		  	
		        	            //If we find a non-digit character we return true.
		        	            if (prev.indexOf(",") != -1 ){
		        	            	tem++;
		        	            	break;
		        	            }
	        	        }
		                    while(ind < (HighFreqTerms.number.length - 1)){
				        		  ind ++;		        		  
				        		  if(curr.toLowerCase().indexOf(HighFreqTerms.number[ind].toLowerCase()) != -1 || prev.toLowerCase().indexOf(HighFreqTerms.number[ind].toLowerCase()) != -1){
				        			  tem++;
				        			  break;
				        		  }
				        	  }
				        	  ind = -1;
				        	  while(ind < (HighFreqTerms.stopwords.length-1)){
				        		  ind++;		        		  	
				        		  if(curr.toLowerCase().compareTo(HighFreqTerms.stopwords[ind].toLowerCase())==0 || prev.toLowerCase().compareTo(HighFreqTerms.stopwords[ind].toLowerCase())==0){
				        			  tem++;
				        			  break;		    
				        		  }
				        		}
				        	  if(tem != 0){
			                    	prev = curr;
			                    	continue;
			                    }
							String bigram = prev +"zzzz"+curr;
							prev = curr;
							tmp = tmp + " " + bigram;
						}
						//System.out.println(tmp);
						File TextFile = new File(BasDir + "/BiTextFiles/" + filename); //Create Text Files
						FileWriter out = new FileWriter(TextFile);  //Write into the Text Files
						out.write(tmp);
						out.close();
					}
				}
				
			}
		}
		catch(Exception e){
			System.out.println("Error: "+e.getMessage());
			e.printStackTrace();
		}
	
	}
	public static void trigram(String BasDir){
		try{
			File TexDir = new File(BasDir + "/TextFiles");
			File files[] = TexDir.listFiles();
			//System.out.println("Total Files: " + files.length);
			
			File TextDir = new File(BasDir + "/TriTextFiles");
			if(TextDir.exists() == false){//If the directory TextFiles doesn't exists 
				TextDir.mkdir();								
			}//
			
			for(int i = 0; i < files.length; i++){
				String filename = files[i].getName().toString();
				//System.out.println(filename);
				if(files[i].isFile()){
					String filepath = files[i].getPath();
					BufferedReader in = new BufferedReader(new FileReader(filepath));
					String tmp = "";
					String line = "";
					
					while((line = in.readLine()) != null){
						StringTokenizer st = new StringTokenizer(line," ");
						String prev = "";
						String next = "";
						while(st.hasMoreTokens()){
							String curr = st.nextToken();
							
							curr = curr.replaceAll("[^0-9a-zA-Z.]", " ");
							int tem = 0, ind = -1;
		                    if(curr.length() < 2 || prev.length() < 2 || next.length() < 2){		        			   
			        			  tem++;	        			  
			        		  }	
		                    if((curr.indexOf(" ") !=-1) || (prev.indexOf(" ") !=-1) ||(next.indexOf(" ") !=-1)){
								tem++;
							}
		                    if(curr.compareTo(" ") == 0 || prev.compareTo(" ") == 0 || next.compareTo(" ") == 0){
		                    	tem++;
		                    }
		                    for (int j = 0; j < next.length(); j++) {
			        		  	
		        	            //If we find a non-digit character we return true.
		        	            if (next.indexOf(".") != -1 ){
		        	            	tem++;
		        	            	break;
		        	            } 
		                    }
		        	        for (int k = 0; k < prev.length(); k++) {
				        		  	
			        	            //If we find a non-digit character we return true.
			        	            if (prev.indexOf(".") != -1 ){
			        	            	tem++;
			        	            	break;
			        	            }
		        	        }
		        	        for (int j = 0; j < next.length(); j++) {
			        		  	
		        	            //If we find a non-digit character we return true.
		        	            if (next.indexOf(",") != -1 ){
		        	            	tem++;
		        	            	break;
		        	            } 
		                    }
		        	        for (int k = 0; k < prev.length(); k++) {
				        		  	
			        	            //If we find a non-digit character we return true.
			        	            if (prev.indexOf(",") != -1 ){
			        	            	tem++;
			        	            	break;
			        	            }
		        	        }
		                    while(ind < (HighFreqTerms.number.length - 1)){
				        		  ind ++;		        		  
				        		  if(curr.toLowerCase().indexOf(HighFreqTerms.number[ind].toLowerCase()) != -1 || prev.toLowerCase().indexOf(HighFreqTerms.number[ind].toLowerCase()) != -1 || next.toLowerCase().indexOf(HighFreqTerms.number[ind].toLowerCase()) != -1){
				        			  tem++;
				        			  break;
				        		  }
				        	  }
				        	  ind = -1;
				        	  while(ind < (HighFreqTerms.stopwords.length-1)){
				        		  ind++;		        		  	
				        		  if(curr.toLowerCase().compareTo(HighFreqTerms.stopwords[ind].toLowerCase())==0 || prev.toLowerCase().compareTo(HighFreqTerms.stopwords[ind].toLowerCase())==0){
				        			  tem++;
				        			  break;		    
				        		  }
				        		}
				        	  if(tem != 0){
				        		  prev = next;
									next = curr;
			                    	continue;
			                    }
							String trigram = prev +"zzzz"+next+"zzzz"+curr;
							prev = next;
							next = curr;
							tmp = tmp + " " + trigram;
						}
						//System.out.println(tmp);
						File TextFile = new File(BasDir + "/TriTextFiles/" + filename); //Create Text Files
						FileWriter out = new FileWriter(TextFile);  //Write into the Text Files
						out.write(tmp);
						out.close();
					}
				}
				
			}
		}
		catch(Exception e){
			System.out.println("Error: "+e.getMessage());
			e.printStackTrace();
		}
	
	}
	
	public static void forgram(String BasDir){
		try{
			File TexDir = new File(BasDir + "/TextFiles");
			File files[] = TexDir.listFiles();
			//System.out.println("Total Files: " + files.length);
			
			File TextDir = new File(BasDir + "/ForTextFiles");
			if(TextDir.exists() == false){//If the directory TextFiles doesn't exists 
				TextDir.mkdir();								
			}//
			
			for(int i = 0; i < files.length; i++){
				String filename = files[i].getName().toString();
				//System.out.println(filename);
				if(files[i].isFile()){
					String filepath = files[i].getPath();
					BufferedReader in = new BufferedReader(new FileReader(filepath));
					String tmp = "";
					String line = "";
					
					while((line = in.readLine()) != null){
						StringTokenizer st = new StringTokenizer(line," ");
						String prev = "";
						String next = "";
						String befo = "";
						while(st.hasMoreTokens()){
							String curr = st.nextToken();
							
							curr = curr.replaceAll("[^0-9a-zA-Z.]", " ");
							int tem = 0, ind = -1;
							if((curr.indexOf(" ") !=-1) || (prev.indexOf(" ") !=-1) ||(next.indexOf(" ") !=-1) || (befo.indexOf(" ") !=-1)){
								tem++;
							}
		                    if(curr.length() < 2 || prev.length() < 2 || next.length() < 2 || befo.length() < 2){		        			   
			        			  tem++;	        			  
			        		  }	
		                    if(curr.compareTo(" ") == 0 || prev.compareTo(" ") == 0 || next.compareTo(" ") == 0 || befo.compareTo(" ")==0){
		                    	tem++;
		                    }
		                    for (int j = 0; j < next.length(); j++) {
			        		  	
		        	            //If we find a non-digit character we return true.
		        	            if (next.indexOf(".") != -1 ){
		        	            	tem++;
		        	            	break;
		        	            } 
		                    }
		        	        for (int k = 0; k < prev.length(); k++) {
				        		  	
			        	            //If we find a non-digit character we return true.
			        	            if (prev.indexOf(".") != -1 ){
			        	            	tem++;
			        	            	break;
			        	            }
		        	        }
		        	        for (int k = 0; k < befo.length(); k++) {
			        		  	
		        	            //If we find a non-digit character we return true.
		        	            if (befo.indexOf(".") != -1 ){
		        	            	tem++;
		        	            	break;
		        	            }
	        	        }
		        	        for (int j = 0; j < next.length(); j++) {
			        		  	
		        	            //If we find a non-digit character we return true.
		        	            if (next.indexOf(",") != -1 ){
		        	            	tem++;
		        	            	break;
		        	            } 
		                    }
		        	        for (int k = 0; k < prev.length(); k++) {
				        		  	
			        	            //If we find a non-digit character we return true.
			        	            if (prev.indexOf(",") != -1 ){
			        	            	tem++;
			        	            	break;
			        	            }
		        	        }
		        	        for (int k = 0; k < befo.length(); k++) {
			        		  	
		        	            //If we find a non-digit character we return true.
		        	            if (befo.indexOf(",") != -1 ){
		        	            	tem++;
		        	            	break;
		        	            }
	        	        }
		                    while(ind < (HighFreqTerms.number.length - 1)){
				        		  ind ++;		        		  
				        		  if(curr.toLowerCase().indexOf(HighFreqTerms.number[ind].toLowerCase()) != -1 || prev.toLowerCase().indexOf(HighFreqTerms.number[ind].toLowerCase()) != -1 || next.toLowerCase().indexOf(HighFreqTerms.number[ind].toLowerCase()) != -1 || befo.toLowerCase().indexOf(HighFreqTerms.number[ind].toLowerCase()) != -1){
				        			  tem++;
				        			  break;
				        		  }
				        	  }
				        	  ind = -1;
				        	  while(ind < (HighFreqTerms.stopwords.length-1)){
				        		  ind++;		        		  	
				        		  if(curr.toLowerCase().compareTo(HighFreqTerms.stopwords[ind].toLowerCase())==0 || prev.toLowerCase().compareTo(HighFreqTerms.stopwords[ind].toLowerCase())==0){
				        			  tem++;
				        			  break;		    
				        		  }
				        		}
				        	  if(tem != 0){
				        		  prev = next;
									next = befo;
									befo = curr;
			                    	continue;
			                    }
							String frigram = prev + "zzzz" + next + "zzzz" + befo + "zzzz" + curr;
							prev = next;
							next = befo;
							befo = curr;
							tmp = tmp + " " + frigram;
						}
						//System.out.println(tmp);
						File TextFile = new File(BasDir + "/ForTextFiles/" + filename); //Create Text Files
						FileWriter out = new FileWriter(TextFile);  //Write into the Text Files
						out.write(tmp);
						out.close();
					}
				}
				
			}
		}
		catch(Exception e){
			System.out.println("Error: "+e.getMessage());
			e.printStackTrace();
		}
	
	}
	
}