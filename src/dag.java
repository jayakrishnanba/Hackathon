


public class dag {
	public static void draw(String BasDir){
		try{
			Runtime r = Runtime.getRuntime();
			r.exec("dot -Tps "+ BasDir +"/relation.dot -o" + BasDir+ "/dag.pdf");
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
        public static void show(String BasDir){
		try{
			Runtime r = Runtime.getRuntime();
			r.exec("evince " + BasDir+ "/dag.pdf");
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
        public static void protege(String BasDir){
    		try{
    			Runtime r = Runtime.getRuntime();
    			r.exec("sh /Protege/run.sh");
    		}
    		catch(Exception e){
    			System.out.println("Error: " + e.getMessage());
    			e.printStackTrace();
    		}
    	}

}
