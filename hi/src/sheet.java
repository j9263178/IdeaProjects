
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.joml.Matrix4f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.SpriteSheet;

public class sheet {
	private Texture texture;
	private Matrix4f scale;
	private Matrix4f translation;
	private int n=1;
	private float fix=1; 
	public sheet(String texture,int n) {
		
		this.n = n;
		
		try {
			this.texture=TextureLoader.getTexture("PNG", new FileInputStream(new File("sheets/"+texture)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		fix =(float)this.texture.getTextureHeight() /(float) this.texture.getImageHeight(); 
		
		scale = new Matrix4f().scale(1.0f /((float)n*fix));
		
		translation = new Matrix4f();
		
		//System.out.println(this.texture.getTextureID()+" "+scale);
	}
	
	public void bind(int x, int y,Shader shader) {
		texture.bind();
		scale.translate(x,y,0,translation);
		shader.setUniform("sampler", 0);
		shader.setUniform("texmodifier", translation);
	}
	
	/*public void bind(int pos,Shader shader) {
		int x =  pos / n;
		int y =  pos % n;
		bind(x,y,shader);
	}*/
}	
