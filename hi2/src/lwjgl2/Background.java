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
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.shader=shader;
		this.bg=bg;


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
				1.0f,1.0f,1.0f, //top left
				1.0f,1.0f,1.0f,	//top right
				1.0f,1.0f,1.0f,	//bottom right
				1.0f,1.0f,1.0f//bottom left

		};

		vbo=new vbo(vertices,tex_coords,colors);

		try {
			this.tex=TextureLoader.getTexture("PNG", new FileInputStream(new File("sheets/"+bg)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setColor(float[] newColor){
		this.colors=newColor;
		vbo=new vbo(vertices,tex_coords,colors);
	}

	public void draw() {
		tex.bind();
		shader.setUniform("texmodifier", new Matrix4f().setTranslation(0, 0, 0));
		vbo.render();
	}

	public void setFrame(int i,int j) {
		this.i=i;
		this.j=j;
	}

	public void nextFrame() {
		if(i>=5) {
			i=0;
			
			if(j>=5) {
				j=0;
			}else {j++;}
			
		}else{i++;}
		
	}

	
	
	

}
