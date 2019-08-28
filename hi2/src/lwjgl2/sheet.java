package lwjgl2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.joml.Matrix4f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


public class sheet {
	private Texture texture;
	private Matrix4f scale,scalex,scaley;
	private Matrix4f translation,translationx,translationy;

	sheet(String texture, int xn, int yn) {
		
	//	this.n = n;
		
		try {
			this.texture=TextureLoader.getTexture("PNG", new FileInputStream(new File("sheets/"+texture)));
		} catch (IOException e) {
			e.printStackTrace();
		}


		assert this.texture != null;

		float yfix = (float) this.texture.getTextureHeight() / (float) this.texture.getImageHeight();
		//private int n=1;
		float xfix = (float) this.texture.getTextureWidth() / (float) this.texture.getImageWidth();


		scale = new Matrix4f().scale(1.0f /((float)xn* xfix),1.0f /((float)yn* yfix),0);



		translation = new Matrix4f();
		//System.out.println(this.texture.getTextureID()+" "+scale);
	}
	
	public void bind(int x, int y, Shader shader) {
		texture.bind();
		//scalex.translate(x,0,0).add(scaley.translate(0,y,0),translation);
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
