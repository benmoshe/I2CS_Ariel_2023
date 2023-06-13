package exe.ex3.sol;

import java.io.Serializable;

public class Index2D implements Pixel2D, Serializable{
    private int _x, _y;
    public Index2D() {this(0,0);}
    public Index2D(int x, int y) {_x=x;_y=y;}
    public Index2D(Pixel2D t) {this(t.getX(), t.getY());}
    @Override
    public int getX() {
        return _x;
    }

    @Override
    public int getY() {
        return _y;
    }
    public double distance2D(Pixel2D t) {
        if(t==null) {throw new RuntimeException("ERR: got null for the Pixel2D:distance2D method");}
        double dx = t.getX()-this.getX();
        double dy = t.getY()-this.getY();
        double ans = Math.sqrt(dx*dx + dy*dy);
        return ans;
    }
    @Override
    public String toString() {
        return getX()+","+getY();
    }
    @Override
    public boolean equals(Object t) {
        boolean ans = false;
        if(t instanceof Pixel2D) {
            if(((Pixel2D) t).getX() == this.getX() && ((Pixel2D) t).getY()==this.getY()) {
                ans = true;
            }
        }
        return ans;
    }
}
