
import java.awt.Font;
import java.io.InputStream;
 
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
 
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.ResourceLoader;
  
public class text {
  
    private UnicodeFont font3;
    public float x=20,y=20;
    public void initGL() {
       
  
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_SMOOTH);        
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LIGHTING);                    
  
       // GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
        //GL11.glClearDepth(1);                                       
  
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
  
       // GL11.glViewport(0,0,800,600);
       // GL11.glMatrixMode(GL11.GL_MODELVIEW);
  
      //  GL11.glMatrixMode(GL11.GL_PROJECTION);
      //  GL11.glLoadIdentity();
         GL11.glOrtho(0, 800, 600, 0, 1, -1);
      //  GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
    @SuppressWarnings("unchecked")
	public text(String type,float size) {
     
       try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("Fonts/"+type+".ttf");
            
            Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            
            awtFont2 = awtFont2.deriveFont(size); // set font size
            
            font3 = new UnicodeFont(awtFont2);
             
        } catch (Exception e) {
            e.printStackTrace();
        }
       
       System.out.println("start fucking:");
       font3.addAsciiGlyphs();
       
       /*font3.addGlyphs("韓國瑜以中英文交雜的「晶晶體」呈現，引起網友討論。其實不說你不知道，"
       		+ "韓國瑜以前曾經在世新大學教過英文，還被曾經上過他課的學生大讚"
       		+ "「這輩子沒遇過比他還好的老師。」");*/
       
       System.out.println("Done fucking!");
       font3.getEffects().add(new ColorEffect());
       
       try {
			font3.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
		}
    }
    
    public void draw(String text)  {
        font3.drawString(x, y, text, Color.white);
    }
}