package lwjgl2;

public class ball extends Entity {
    public Entity center;

    public ball(Shader shader, float x, float y, float size) {
        super(shader, x, y, size);
        center=new Entity(null,x-0.05f,y,0);
    }

    public void update() {

        this.box=new AABB(pos,size/2);

        center.update();
       // this.setAcc(center.pos.x-this.pos.x,center.pos.y-this.pos.y);
            this.d+=0.002f;
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
