package lwjgl2;

import java.util.ArrayList;

public class Depth {
    public Camara camara;
    public  ArrayList<Background> bg;
    public  ArrayList<Entity> entity;

    public Depth(Camara camara){
        bg=new ArrayList<>();
        entity=new ArrayList<>();
        this.camara=camara;
    }

    public void addEntities(Entity e){
        entity.add(e);
    }

    public void addBackground(Background b){
        bg.add(b);
    }

    public void drawBg(){
        for(Background b :bg){
            b.draw();
        }
    }

    public void drawEn(){
        for(Entity e :entity){
            e.update();
            e.draw();
        }
    }
}
