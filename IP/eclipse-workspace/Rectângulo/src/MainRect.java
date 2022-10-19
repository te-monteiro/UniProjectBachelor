
public class MainRect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rect r = new Rect(10,50,120,20);
		Rect r1 = new Rect();
		r.getArea();
		System.out.println(r.getArea());
		r.getPerimeter();
		System.out.println(r.getPerimeter());
		System.out.println(r.ptInRect(30,40));
		System.out.println(r.ptInRect(130,40));
		r.translate(10,-30);
		System.out.println(r.ptInRect(130, 40));
		r.rectInRect(r1);
		System.out.println(r.rectInRect(r1));
		System.out.println(r.ptInRect(30,40));
		r.turn();
		System.out.println(r.getLeft());
		System.out.println(r.getTop());
		System.out.println(r.getRight());
		System.out.println(r.getBottom());
		
	}

}
