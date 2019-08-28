package lwjgl2;

import org.joml.AABBf;

public class Chara extends Entity{

    public boolean moving;
    public AABB box;
    public enum direction{
        UP,DOWN,LEFT,RIGHT;
    }

    public Chara(Shader shader, float x, float y, float size) {

        super(shader, x, y, size);
        super.vertices= new float[] {
                pos.x-d,pos.y+d +0.04f,0,  //top left
                pos.x+d,pos.y+d +0.04f,0,	//top right
                pos.x+d,pos.y-d -0.04f,0,	//bottom right
                pos.x-d,pos.y-d -0.04f,0   //bottom left
        };

        this.box=new AABB(pos,size/4);
    }


    public void update(){

          /*  if(vel.x>0)
                this.setFrame(0,1);
            else if(vel.x<0)
                this.setFrame(1,1);
            else if(vel.y>0)
                this.setFrame(0,0);
            else if(vel.y<0)
                this.setFrame(1,0);*/

        this.box=new AABB(pos,size/4);

        if(!vel.equals(0, 0) || !acc.equals(0,0)) {
            vel.x+=0.1*acc.x;
            vel.y+=0.1*acc.y;
            pos.x+=0.1*vel.x;
            pos.y+=0.1*vel.y;

            vertices = new float[] {
                    pos.x-d,pos.y+d +0.04f,0,  //top left
                    pos.x+d,pos.y+d +0.04f,0,	//top right
                    pos.x+d,pos.y-d -0.04f,0,	//bottom right
                    pos.x-d,pos.y-d -0.04f,0   //bottom left
            };
            vbo.newVs(vertices);
        }
    }

}
