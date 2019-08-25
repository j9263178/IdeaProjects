package lwjgl2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.joml.Matrix2f;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import lwjgl2.*;

public class vboBox{
	
	vbo vbo;
	Shader shader;
//	Camara camara;
	ArrayList<sheet> sheets;
	float[] vertices,tex_coords,colors;
	sheet sheet;
	int i=0,j=0;
	float d;
	float size;
	public Vector2f pos,vel,acc;
	public Matrix2f rotate;
	boolean rotating;
	
	public vboBox(Shader shader,float x,float y,float size) {
		sheets = new ArrayList<>();
		//this.camara=camara;
		this.shader=shader;
		pos=new Vector2f(x,y);
		vel=new Vector2f(0,0); 
		acc=new Vector2f(0,0);
		this.size = size;
		setUp();
		
	}
	
	public void setUp() {
		d = size/2;
		
		vertices = new float[] {
				pos.x -d,pos.y +d,0,  //top left
				pos.x +d,pos.y +d,0,	//top right
				pos.x +d,pos.y -d,0,	//bottom right
				pos.x -d,pos.y -d,0   //bottom left		
    	};
		
		tex_coords = new float[] {
    			0,0,  //top left
    			1,0,  //top right
    			1,1,  //bottom right
    			0,1  //bottom left	
    			
    	};
		
		colors = new float[] {
    			0.4f,0.35f,0.3f, //top left
    			0.4f,0.35f,0.3f,	//top right
    			0.4f,0.35f,0.3f,	//bottom right
    			0.2f,0.1f,0.04f//bottom left
    			
    	};
		
		vbo=new vbo(vertices,tex_coords,colors);
	
    	
	}
	
	public void setSheet(String name,int n) {
		sheet = new sheet(name,n);
	}
	
	public void setPose(int i,int j) {
		this.i=i;
		this.j=j;
	}
	
	public void draw() {
	//	target = scale;
	//	projection = new Matrix4f().ortho2D(-350,350,-350,350).scale(512);
		sheet.bind(i,j,shader);
		vbo.render();
	}
	
	public void glow() {
		float[] colors = new float[] {
    			0.3f,0.3f,0.3f, //top left
    			0.3f,0.3f,0.3f,	//top right
    			0.3f,0.3f,0.3f,	//bottom right
    			0.3f,0.3f,0.3f//bottom left
    			
    	};
		vbo.newCs(colors);
	}

	public void setPos(float x,float y) {
		pos.x=x;
		pos.y=y;
	}
	public void setVel(float x,float y) {
		vel.x=x;
		vel.y=y;
	}
	public void setAcc(float x,float y) {
		acc.x=x;
		acc.y=y;
	}
	public void addPos(float x,float y) {
		pos.x+=x;
		pos.y+=y;
	}
	public void addVel(float x,float y) {
		vel.x+=x;
		vel.y+=y;
	}
	public void addAcc(float x,float y) {
		acc.x+=x;
		acc.y+=y;
	}
	public void update() {
		
		if(!vel.equals(0, 0) || !acc.equals(0,0)) {
			vel.x+=0.1*acc.x;
			vel.y+=0.1*acc.y;
			pos.x+=0.1*vel.x;
			pos.y+=0.1*vel.y;
			
			vertices = new float[] {
					pos.x-d,pos.y+d,0,  //top left
					pos.x+d,pos.y+d,0,	//top right
					pos.x+d,pos.y-d,0,	//bottom right
					pos.x-d,pos.y-d,0   //bottom left		
	    	};
			vbo.newVs(vertices);
		}
		
	}
	
	public void rotate() {
		rotating=true;
		rotate=new Matrix2f().rotate(0.2f);
		pos.mul(rotate);
		
	}
	
	
	public void next() {
		if(i>=5) {
			i=0;
			
			if(j>=5) {
				j=0;
			}else {j++;}
			
		}else{i++;}
		
		
	}

	
	
	

}
