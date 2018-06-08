import java.io.File;


public class topiapy {

	/**
	 * @param args
	 */
	public static void keyword(String BasDir) {
		try{
			// TODO Auto-generated method stub
			File TexDir = new File(BasDir + "/TextFiles");
			File files[] = TexDir.listFiles();
			
			for(int i = 0; i < files.length; i++){
				//String filename = files[i].getName().toString();
				//System.out.println(filename);
				if(files[i].isFile()){
					String filepath = files[i].getPath();
			
					Runtime r = Runtime.getRuntime();
					r.exec("python term.py " + filepath + " "+ BasDir + "/temp.txt");
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

}
