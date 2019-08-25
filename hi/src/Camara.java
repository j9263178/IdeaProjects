
import org.joml.*;



public class Camara  {
	public  Vector3f position;
	public  Vector2f pos,vel,acc;
	private Matrix4f projection;
	private int scale=1;
	
	public Camara(int width,int height) {
		
		position = new Vector3f(0,0,0);
		pos = new Vector2f(0,0);
		vel = new Vector2f(0,0);
		acc = new Vector2f(0,0);
		projection = new Matrix4f().setOrtho2D(-width/2,width/2,-height/2,height/2).scale(scale);
		
	}
	
	
	
	public void update() {
		
	
			vel.x+=0.1*acc.x;
			vel.y+=0.1*acc.y;
			pos.x+=0.1*vel.x;
			pos.y+=0.1*vel.y;
			
			position.x=pos.x;
			position.y=pos.y;
			
	}
	
	public Matrix4f getProjection() {
		Matrix4f target = new Matrix4f();
		Matrix4f pos = new Matrix4f().setTranslation(position);
		projection.mul(pos,target);
		return target;
	}
	
	public void zoomIn(float i) {
		projection.scale(i);
	}
	public void zoomOut(float i) {
		scale-=i;
		projection.scale(scale);
	}

}
