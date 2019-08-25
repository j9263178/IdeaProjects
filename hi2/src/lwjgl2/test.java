package lwjgl2;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

//import map.map;

import static  org.lwjgl.opengl.GL11.*;

public class test {
	
	vbo vbo;
	Shader shader,shader2;
	Matrix4f scale;
	Camara camara;
	Camara camara2;
	Window win;
	float Mx;
	float My;
	float op=0;
	vboBox b;
	
	public static void main(String[] argv) {
	        test displayExample = new test();
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
    	win.createWindow("FUCK THE WINDOW");
    }

    public void loop() {
    	camara = new Camara(800,600);
    	camara2 = new Camara(800,600);
    	Camara camara3 =new Camara(800,600);
    	Camara camara4 =new Camara(800,600);
    	scale = new Matrix4f().scale(512);
    	shader = new Shader("cs");
    	
    	Background back=new Background(shader,"back.png",0.4f,0,3.5f,1.5f);
    	Background back2=new Background(shader,"back2.png",0.4f,-0.18f,3.5f,1.5f);
    	Background scene=new Background(shader,"mid2.png",0.4f,0,3.5f,1.5f);
    	Background front=new Background(shader,"front.png",0,-0.5f,3.0f,1.8f);
    	
    	
    	b = new vboBox(shader,0f,-0.4f,0.5f);
    	b.setSheet("Undyne-1.png", 1);
    
    	double frame_time=0;
    	double time = Timer.getTime();
    	double time_2=0;
    	float light =2.0f;
    	text a =new text("TaipeiSansTCBeta-Bold",24f);
		a.initGL();
    	while (!win.isClosed()) {
    		glClear(GL_COLOR_BUFFER_BIT |GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
    		
    		time = Timer.getTime();
    		
    		getInput();
    		Mx=Mouse.getX()-300;
    		My=Mouse.getY()-300;
    		//b.update();
    		
    		/*camara3.setVelocity(-Mx/500,0);
    		camara3.update(0.1f);
    		camara2.setVelocity(-Mx/200,0);
    		camara2.update(0.1f);*/
    		//camara.setVelocity(-Mx/50,-My/50);
    		camara.vel.x=(-b.pos.x*512-camara.pos.x);
    		camara2.vel.x=(-b.pos.x*512-camara.pos.x);
    		camara3.vel.x=(-b.pos.x*512-camara.pos.x)/6;
    		camara4.vel.x=(-b.pos.x*512-camara.pos.x)/8;
    		
    		camara.update();
    		camara2.update();
    		camara3.update();
    		camara4.update();
    		
    		shader.bind();
    		shader.setUniform("projection", camara4.getProjection().mul(scale));
    		shader.bind();
    		back.draw();

    		shader.setUniform("projection", camara3.getProjection().mul(scale));
    		shader.bind();
    		back2.draw();
    		
    		shader.setUniform("projection", camara2.getProjection().mul(scale));
    		shader.bind();
    		scene.draw();
    		b.update();
    		b.draw();
    		shader.setUniform("projection", camara.getProjection().mul(scale));
    		shader.bind();
    		front.draw();
    		
    		
    		if(frame_time >=0.1) {
    			if(light>=100000) {light=0;}
    			light += 0.1;
    			frame_time=0;
    			shader.setUniform("ambientStrength",4.2f-(float)Math.abs(Math.sin(light)));
    		}
    		
    		shader.unbind();
    		
    		
    		a.draw("Undyne: \n"+"X:"+Float.toString(b.pos.x)+'\n'+"Y:"+Float.toString(b.pos.y));
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
    		
            if (key == Keyboard.KEY_RIGHT) {
            	b.addVel(0.1f, 0);
            	b.setSheet("Undyne-3.png", 1);
             }
            if (key == Keyboard.KEY_LEFT) {
            	b.addVel(-0.1f, 0);
            	b.setSheet("Undyne-4.png", 1);
             }
            if (key == Keyboard.KEY_DOWN) {
            	b.addVel(0, -0.1f);
            	b.setSheet("Undyne-1.png", 1);
             }
            if (key == Keyboard.KEY_UP) {
            	b.addVel(0f, 0.1f);
            	b.setSheet("Undyne-1.png", 1);
             }
            
            if (key == Keyboard.KEY_SPACE) {
            	b.setSheet("Undyne-2.png", 1);
            	
            	
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
            	 if (key == Keyboard.KEY_SPACE) {
                	 b.setSheet("Undyne-1.png", 1);
            	}
            }
    	}
    }
    
    
}
