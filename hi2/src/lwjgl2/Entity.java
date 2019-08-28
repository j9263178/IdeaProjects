package lwjgl2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.joml.AABBf;
import org.joml.Matrix2f;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import lwjgl2.*;

public class Entity {
	
	vbo vbo;
	Shader shader;
//	Camara camara;
	ArrayList<sheet> sheets;
	float[] vertices,tex_coords,colors;
	sheet sheet;
	int i=0,j=0;
	float d;
	public float size;
	public Vector2f pos,vel,acc;
	public Matrix2f rotate;
	boolean rotating;
	public AABB box;
	
	public Entity(Shader shader, float x, float y, float size) {
		sheets = new ArrayList<>();

		this.shader=shader;
		pos=new Vector2f(x,y);
		vel=new Vector2f(0,0); 
		acc=new Vector2f(0,0);

		this.size = size;

		d = this.size/2;
		this.box=new AABB(pos,size/2);

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
				1.0f,1.0f,1.0f, //top left
				1.0f,1.0f,1.0f,	//top right
				1.0f,1.0f,1.0f,	//bottom right
				1.0f,1.0f,1.0f//bottom left

		};

		vbo=new vbo(vertices,tex_coords,colors);
		
	}
	public void setColor(float[] newColor){
		this.colors=newColor;
		vbo=new vbo(vertices,tex_coords,colors);
	}

	public void setSheet(String name,int xn,int yn) {
		sheet = new sheet(name,xn,yn);
	}

	public void draw() {
		sheet.bind(i,j,shader);
		vbo.render();
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

	public void rotate() {
		rotating=true;
		rotate=new Matrix2f().rotate(0.2f);
		pos.mul(rotate);

	}

	public void update() {

		this.box=new AABB(pos,size/2);

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
	

	
	
	public void nextFrame() {
		if(i>=5) {
			i=0;
			
			if(j>=5) {
				j=0;
			}else {j++;}
			
		}else{i++;}
		
		
	}

	public void setFrame(int i,int j) {
		this.i=i;
		this.j=j;
	}
	
	

}
