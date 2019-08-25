package lwjgl2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.joml.Matrix2f;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import lwjgl2.*;

public class Background{
	
	vbo vbo;
	Shader shader;
//	Camara camara;
	String bg;
	Texture tex;
	ArrayList<sheet> sheets;
	float[] vertices,tex_coords,colors;
	sheet sheet;
	int i=0,j=0;
	float x,y,w,h,d;
	float size;
	public Vector2f pos,vel,acc;
	
	public Background(Shader shader,String bg,float x ,float y,float w,float h) {
		
		//this.camara=camara;
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.shader=shader;
		this.bg=bg;
		setUp();
		
	}
	
	public void setUp() {
		d = size/2;
		
		vertices = new float[] {
				 x-w/2, y+h/2,0,  //top left
				 x+w/2, y+h/2,0,	//top right
				 x+w/2, y-h/2,0,	//bottom right
				 x-w/2, y-h/2,0   //bottom left		
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
		try {
			this.tex=TextureLoader.getTexture("PNG", new FileInputStream(new File("sheets/"+bg)));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
	}
	
	public void setPose(int i,int j) {
		this.i=i;
		this.j=j;
	}
	
	public void draw() {
	//	target = scale;
	//	projection = new Matrix4f().ortho2D(-350,350,-350,350).scale(512);
		tex.bind();
		shader.setUniform("texmodifier", new Matrix4f().setTranslation(0, 0, 0));
		vbo.render();
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
	
	public void next() {
		if(i>=5) {
			i=0;
			
			if(j>=5) {
				j=0;
			}else {j++;}
			
		}else{i++;}
		
		
	}

	
	
	

}
