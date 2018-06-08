import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
public class common {
	public static void comm(String BasDir){
		try{
			//BufferedWriter out = new BufferedWriter(new FileWriter(BasDir + "/tfidf.txt"));
			File file1 = new File(BasDir + "/manual"); 
			File file2 = new File(BasDir + "/tfidf.txt");
			BufferedReader bf1 = new BufferedReader(new FileReader(file1));
			String str1;
			String str2;
			int count = 0,f1=0,f2=0;
			BufferedReader bf3 = new BufferedReader(new FileReader(file2));
			while((str2 = bf3.readLine()) != null)
				f2++;
			bf3.close();
			while((str1 = bf1.readLine()) != null){				
				f1++;
				BufferedReader bf2 = new BufferedReader(new FileReader(file2));
				//String str1;
				
				while((str2 = bf2.readLine()) != null){
					
					if(str1.toLowerCase().indexOf(str2.toLowerCase()) != -1){
						count++;
						bf2.close();
						break;
					}
				}
				
			}
			bf1.close();
			float fp = (float)(f2-count)/f1;
			float fn = (float)(f1-count)/f1;
			float gd = (float)(count)/f1;
			System.out.println("f1 = "+ f1);
			System.out.println("f2 = "+ f2);
			System.out.println("common = "+ count);
			System.out.println("Goodness = "+ gd);
			System.out.println("False Positive " + fp);
			System.out.println("False Neagative " + fn);
		}
		catch(Exception e){
			System.err.println("Error: "+e.getMessage());
			e.printStackTrace();
		}
	}
}
