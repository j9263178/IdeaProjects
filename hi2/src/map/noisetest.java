package map;



public class noisetest {

	public static void main(String[] args) {
		
		double x=0,y=0;
		opensimplex o=new opensimplex();
		for(int i=0;i<30;i++) {
			for(int j=0;j<50;j++) {
				x+=0.4; 
				if(o.eval(x,y)>=0.2) {System.out.print("2");}
				if(o.eval(x,y)<0.2 && o.eval(x,y)>-0.2) {System.out.print("1");}
			//	if(n.generateValueNoise2D(x, y, 1)>=0.2) {System.out.print("1");}
				if(o.eval(x,y)<=-0.2) {System.out.print("0");}
			}
			
			y+=0.1;
			
			System.out.println();
		}
    	

	}

}
