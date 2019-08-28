package map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import lwjgl2.Entity;
import lwjgl2.Shader;
import org.lwjgl.opengl.Display;

public class map {
	private Shader shader;
	private ArrayList<Entity> tiles;
	
	public map(Shader shader) {
		this.shader = shader;
		tiles=new ArrayList<>();
	}
	
	public String loadMap(String fileName) {
		BufferedReader reader=null;
		
		try {
			reader = new BufferedReader(new FileReader("maps/"+fileName));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		
		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// delete the last new line separator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String content = stringBuilder.toString();
		
		return content;
		}

	public ArrayList<Entity> renderMap(String map) {
		float x=0,y=0,unitx=0.1f;


		for(int i=0;i<map.length();i++) {
			Entity temp=new Entity(shader,-0.5f+unitx*x ,0.5f-unitx*y,unitx);
			if(map.charAt(i)=='1') {
				temp.setSheet("1.png", 1,1);
				tiles.add(temp);
				x+=1;
			}
			
			if(map.charAt(i)=='2') {
				temp.setSheet("2.png", 1,1);
				tiles.add(temp);
				x+=1;
			}
			
			if(map.charAt(i)=='0') {
				temp.setSheet("0.png", 1,1);
				tiles.add(temp);
				x+=1;
			}
			if(map.charAt(i)=='\n') {
				y+=1;
				x=0;
			}
			
		/*	if(map.charAt(i)=='0') {
				Entity temp=new Entity(shader,-0.3f+0.25f*x ,0f-0.25f*y,0.4f);
				temp.setSheet("Undyne-2.png", 1);
				tiles.add(temp);
			}*/
			
			
		}
		return tiles;
	}

}
