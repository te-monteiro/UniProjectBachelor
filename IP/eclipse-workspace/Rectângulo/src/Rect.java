
public class Rect {
	private double left, top, right, bottom;
	public static final double CASA = 0.5;
	public Rect() {
		left=CASA;
		top=CASA;
		right=CASA;
		bottom=CASA;
	}
	public Rect(double left,double top, double right, double bottom ) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	public Rect(double left, double top, double length ) {
		this.left = left;
		this.top = top;
		this.right= left - length;
		this.bottom= top - length;
	}
	public double getXCenter() { return (left+right)/2; }
	public double getYCenter() { return (top+bottom)/2; }
	public double getWidth() { return right-left; }
	public double getHeight() { return top-bottom; }
	public double getPerimeter() { return (this.getWidth()*2)+(this.getHeight()*2); }
	public double getArea() { return this.getWidth()*this.getHeight(); }
	public boolean ptInRect(double x, double y) {
		return left <=x && right >=x && bottom <=y & top >=y;
	}
	public double getLeft() {return (left); }
	public double getRight() {return (right); }
	public double getBottom() {return (bottom); }
	public double getTop() {return (top); }
	public boolean rectInRect(Rect r) {
		return (r.getLeft() >= left) && (r.getRight() <= right) && (r.getBottom() >= bottom) && (r.getTop() <= top);
	}
	public void translate (double dx, double dy) {
		left += dx;
		right += dx;
		top += dy;
		bottom += dy;
	}
	public void turn() {
		 double cx = this.getXCenter();
		 double cy = this.getYCenter();
		 double halfWidth = this.getWidth()/2;
		 double halfHeight = this.getHeight()/2;
		 left = cx + halfHeight;
		 right = cx - halfHeight;
		 bottom = cy - halfWidth;
		 top = cy + halfWidth;
		}


	

}
