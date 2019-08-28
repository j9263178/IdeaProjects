package map;


import lwjgl2.*;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import static  org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;




public class tileTest {
	
	vbo vbo;
	Shader shader;
	Matrix4f scale;
	Camara camara;
	Window win;
	float Mx;
	float My;
	Entity b;

	public static void main(String[] argv) {
			System.out.println((int)2.35);
			System.out.println((int)2.88);
	        tileTest displayExample = new tileTest();
	        displayExample.start();
	}
	
    public void start() {
        setDisplay();
        loop();
        Display.destroy();
        System.exit(0);
    }
    
    public void setDisplay() {
    	win = new Window();
    	win.createWindow("FUCK THE MAP");
    }	

    public void loop() {
    	
    	
    	camara = new Camara(800,600);
    	scale = new Matrix4f().scale(512);
    	shader = new Shader("cs");
 

    	double frame_time=0;
    	double time = Timer.getTime();
    	double time_2=0;
    	
    	map m=new map(shader);
    	ArrayList<Entity> tiles=new ArrayList<>();
    	tiles=m.renderMap(m.loadMap("test.map"));

    	b=new Entity(shader,0f,0f,0.1f);
    	b.setSheet("3.png", 1,1);

    	camara.pos.x=0;

		text a =new text("TaipeiSansTCBeta-Bold",24f);
		a.initGL();

    	while (!win.isClosed()) {
    		
    		glClear(GL_COLOR_BUFFER_BIT |GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
    		
    		time = Timer.getTime();
			getInput();

    		Mx=Mouse.getX()-300;
    		My=Mouse.getY()-300;

    		camara.vel.x=-Mx/50;
    		camara.vel.y=-My/50;
    		camara.update();
			shader.bind();
			shader.setUniform("ambientStrength",1.0f);
    		shader.setUniform("projection", camara.getProjection().mul(scale));


			Vector2f dis=new Vector2f();

			for(Entity tile:tiles) {
				if(b.box.test(tile.box)) {
					b.pos.sub(tile.pos,dis);
					if(Math.abs(dis.x)>Math.abs(dis.y)){
						if(b.pos.x>tile.pos.x)
							b.pos.x=tile.pos.x+tile.size;
						else if(b.pos.x<tile.pos.x){
							b.pos.x=tile.pos.x-tile.size;}}
					else{
						if(b.pos.y>tile.pos.y)
							b.pos.y=tile.pos.y+tile.size;
						else if(b.pos.y<tile.pos.y){
							b.pos.y=tile.pos.y-tile.size;}}
				}
    			tile.draw();
    		}

			b.update();
			b.draw();



    		shader.unbind();

			a.draw(b.box.test(tiles.get(0).box)+
					"\nMx:"+Math.floor(Mx/Display.getWidth() *10)+"\n"+
					"My:"+Math.floor(My/Display.getHeight() *10)
			);

        	win.update();
        
        	time_2 = Timer.getTime();
    		double passed = time_2 -time;
    		frame_time += passed;
    		
    	}
        
    }

	public void getInput() {
		while (Keyboard.next()) {
			int key = Keyboard.getEventKey();
			if (Keyboard.getEventKeyState() ) {

				if (key == Keyboard.KEY_ESCAPE) {
					win.setSize(1024,768);
				}

				if (key == Keyboard.KEY_RIGHT) {
					//	b.d= Chara.direction.RIGHT;
					b.addVel(0.05f, 0);
					//b.setSheet("Undyne-3.png", 1);
				}
				if (key == Keyboard.KEY_LEFT) {
					//	b.d= Chara.direction.LEFT;
					b.addVel(-0.05f, 0);
					//b.setSheet("Undyne-4.png", 1);
				}
				if (key == Keyboard.KEY_DOWN) {
					//b.d= Chara.direction.DOWN;
					b.addVel(0, -0.05f);
					//b.setSheet("Undyne-1.png", 1);
				}
				if (key == Keyboard.KEY_UP) {
					//	b.d= Chara.direction.UP;
					b.addVel(0f, 0.05f);
					//b.setSheet("Undyne-1.png", 1);
				}

				if (key == Keyboard.KEY_SPACE) {
					//b.setSheet("Undyne-2.png", 1);


				}
			}else {
				if (key == Keyboard.KEY_RIGHT) {
					b.addVel(-0.05f, 0);
				}
				if (key == Keyboard.KEY_LEFT) {
					b.addVel(+0.05f, 0);
				}

				if (key == Keyboard.KEY_DOWN) {
					b.addVel(0, 0.05f);

				}
				if (key == Keyboard.KEY_UP) {
					b.addVel(0f, -0.05f);
				}
			}
		}
	}

   
}

