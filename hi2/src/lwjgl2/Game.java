package lwjgl2;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

//import map.map;

import java.util.ArrayList;

import static  org.lwjgl.opengl.GL11.*;

public class Game {
	
	vbo vbo;
	Shader shader,shader2;
	Matrix4f scale;
	Camara camara;
	Camara camara2;
	Window win;
	float Mx;
	float My;
	float op=0;
	Chara b;
	ArrayList<Depth> ds;
	
	public static void main(String[] argv) {
	        Game Example = new Game();
	        Example.start();
	}


    public void start() {
		win = new Window();
		win.createWindow("FUCK THE WINDOW");

        loop();

        win.destroy();
        System.exit(0);
    }


    public void loop() {

		float[] colors = new float[] {
				0.9f,0.85f,0.8f, //top left
				0.9f,0.85f,0.8f,	//top right
				0.9f,0.85f,0.8f,	//bottom right
				0.7f,0.6f,0.54f//bottom left

		};

    	camara = new Camara(800,600);
    	camara2 = new Camara(800,600);
    	Camara camara3 =new Camara(800,600);
    	Camara camara4 =new Camara(800,600);

		ds=new ArrayList<>();

		ds.add(new Depth(camara4));
		ds.add(new Depth(camara3));
		ds.add(new Depth(camara2));
		ds.add(new Depth(camara));

    	scale = new Matrix4f().scale(512);
    	shader = new Shader("cs");

    	ds.get(0).addBackground(new Background(shader,"back.png",0.4f,0,3.5f,1.5f));
		ds.get(1).addBackground(new Background(shader,"back2.png",0.4f,-0.18f,3.5f,1.5f));
		ds.get(2).addBackground(new Background(shader,"mid2.png",0.4f,0,3.5f,1.5f));
		ds.get(3).addBackground(new Background(shader,"front.png",0,-0.5f,3.0f,1.8f));

    	b = new Chara(shader,-0.2f,-0.2f,0.3f);
    	b.setSheet("undynetest.png", 2,1);
    	b.setFrame(0,0);
    	b.setColor(colors);

		Chara c = new Chara(shader,0.1f,-0.2f,0.3f);
		c.setSheet("undynetest.png", 2,1);
		c.setFrame(0,0);
		c.setColor(colors);
    	ds.get(2).addEntities(c);
		ds.get(2).addEntities(b);

    	//timer things
    	double frame_time=0;
    	double time = Timer.getTime();
    	double time_2=0;

    	//text here
    	text a =new text("TaipeiSansTCBeta-Bold",24f);
		a.initGL();


    	while (!win.isClosed()) {
    		glClear(GL_COLOR_BUFFER_BIT |GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
    		
    		time = Timer.getTime();
    		
    		getInput();

    		Mx=Mouse.getX()-300;
    		My=Mouse.getY()-300;

    		camara.vel.x=(-b.pos.x*512-camara.pos.x);
    		camara2.vel.x=(-b.pos.x*512-camara.pos.x);
    		camara3.vel.x=(-b.pos.x*512-camara.pos.x)/6;
    		camara4.vel.x=(-b.pos.x*512-camara.pos.x)/8;

    		camara.update();
    		camara2.update();
    		camara3.update();
    		camara4.update();


			shader.bind();

			if(frame_time>=1.){
				frame_time=0;
				if(ds.get(2).entity.size()>2)
					ds.get(2).entity.remove(2);

			}


			Vector2f dis=new Vector2f();

			if(b.box.test(c.box)) {
				b.pos.sub(c.pos,dis);
				if(Math.abs(dis.x)>Math.abs(dis.y)){
					if(b.pos.x>c.pos.x)
						b.pos.x=c.pos.x+c.size/2;
					else if(b.pos.x<c.pos.x){
						b.pos.x=c.pos.x-c.size/2;}}
				else{
					if(b.pos.y>c.pos.y)
						b.pos.y=c.pos.y+c.size/2;
					else if(b.pos.y<c.pos.y){
						b.pos.y=c.pos.y-c.size/2;}}
			}

			for (Depth d : ds) {
				shader.setUniform("projection", d.camara.getProjection().mul(scale));
				shader.bind();
				d.drawBg();
				d.drawEn();
			}


			shader.setUniform("ambientStrength",1.0f);
    		shader.unbind();
    		
    		
    		a.draw("Undyne: \n"+"X:"+Float.toString(b.pos.x)+'\n'+"Y:"+Float.toString(b.pos.y)
			+"\n"+b.box.test(c.box)+"\n"+"x:"+(int)(b.pos.x*10)+"\n"+
					"y:"+(int)(b.pos.y*10)+"\n"+ds.get(2).entity.size());
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
            	b.addVel(0.1f, 0);
            	//b.setSheet("Undyne-3.png", 1);
             }
            if (key == Keyboard.KEY_LEFT) {
			//	b.d= Chara.direction.LEFT;
            	b.addVel(-0.1f, 0);
            	//b.setSheet("Undyne-4.png", 1);
             }
            if (key == Keyboard.KEY_DOWN) {
				//b.d= Chara.direction.DOWN;
            	b.addVel(0, -0.1f);
            	//b.setSheet("Undyne-1.png", 1);
             }
            if (key == Keyboard.KEY_UP) {
			//	b.d= Chara.direction.UP;
            	b.addVel(0f, 0.1f);
            	//b.setSheet("Undyne-1.png", 1);
             }
            
            if (key == Keyboard.KEY_SPACE) {
            	b.setFrame(0,0);
				ball hi=new ball(shader,b.pos.x,b.pos.y,0.1f);
				hi.setVel(0.2f,0.0f);
				hi.setSheet("ball.png",1,1);
				ds.get(2).addEntities(hi);
             }

            }else {
            	 if (key == Keyboard.KEY_RIGHT) {
                 	b.addVel(-0.1f, 0);
                  }
                 if (key == Keyboard.KEY_LEFT) {
                 	b.addVel(+0.1f, 0);
                  }
                 
                 if (key == Keyboard.KEY_DOWN) {
                 	b.addVel(0, 0.1f);
                 
                  }
                 if (key == Keyboard.KEY_UP) {
                 	b.addVel(0f, -0.1f);
                  }
                 if(key==Keyboard.KEY_SPACE){
					 b.setFrame(1,0);
				 }
            }
    	}
    }
    
    
}
