

import java.io.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.Splitter;


public class pdftotext {
	public static void PdfTxt(String BasDir){
		try{
				File PdfDir = new File(BasDir + "/pdf");
				File files[] = PdfDir.listFiles();
				//System.out.println("Number of Files: " + files.length);
				if(files.length == 1){
					File filenames = files[0];
					String filename = files[0].toString();
					PDDocument doc = PDDocument.load(filenames);
					int numOfPages = doc.getNumberOfPages();
					int splitAtPage = numOfPages/20; 
					//System.out.println(doc.getNumberOfPages());
					Splitter split = new Splitter();
					split.setSplitAtPage(splitAtPage);
					java.util.List list = split.split(doc);
					//System.out.println(list.size());
					PDFTextStripper stripper = new PDFTextStripper();
					for(int ii=0;ii<list.size();ii++)
					{
						PDDocument pd = (PDDocument)list.get(ii);
						String temp = stripper.getText(pd);
						//System.out.print(pd.getNumberOfPages()+"\n");
						File TextFile = new File(BasDir+ "/TextFiles" + filename.substring(filename.lastIndexOf('/'),filename.lastIndexOf('.')) +"_"+ ii+".txt"); //Create Text Files
						FileWriter out = new FileWriter(TextFile);  //Write into the Text Files
						out.write(temp);
						out.close();
						pd.close();
					}
					doc.close();
				}
				else for(int i = 0; i < files.length; i++){
					File filenames = files[i];
					String filename = files[i].toString();
					PDDocument doc = PDDocument.load(filenames);  //read the files					
					PDFTextStripper stripper = new PDFTextStripper();					
					String temp = stripper.getText(doc);  //Convert PDF files to Text
					//System.out.println(temp);//Text data
					File TextFile = new File(BasDir+ "/TextFiles" + filename.substring(filename.lastIndexOf('/'),filename.lastIndexOf('.')) + ".txt"); //Create Text Files
					FileWriter out = new FileWriter(TextFile);  //Write into the Text Files
					out.write(temp);
					out.close();					
					doc.close();
				}//End of For loop
				
		
		}//End of try
		
		catch(Exception e){ //Exception
			System.out.println("Error" + e.getMessage());
			e.printStackTrace();
		}//End of Exception
		
	}//End of PdfTxt 	

}//End of Class
