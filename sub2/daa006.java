import java.util.Scanner;
class daa006 {
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    for(int i = 0; i < n; i++){
      int qx = stdin.nextInt();
      int qy = stdin.nextInt();
      int l = stdin.nextInt();
      int cx = stdin.nextInt();
      int cy = stdin.nextInt();
      int r = stdin.nextInt();
      System.out.println(intersection(qx,qy,l,cx,cy,r));
    }
	}

	public static double intersection(double x, double y, double l,double cx,double cy ,double r) {
    //   Se (uma figura está garantidamente fora da outra) então retornar 0
    if(squareOutsideCircle(x,y,l,cx,cy,r)){
      return 0;
    }
    //   Se (quadrado dentro do círculo) então retornar área do quadrado
    else if(squareInsideCircle(x,y,l,cx,cy,r)){
      return l*l;
    }
    
    //Se (círculo dentro do quadrado) então retornar área do círculo
    else if(circleInsideSquare(x,y,l,cx,cy,r)){
      return Math.PI * r * r;
    }

    else if(l > 0.001){
      double area = 0;
      l /= 2;
      area += intersection(x,y,l,cx,cy,r);
      area += intersection(x,y+l,l,cx,cy,r);
      area += intersection(x+l,y,l,cx,cy,r);
      area += intersection(x+l,y+l,l,cx,cy,r);
      return area;
    }
    else return 0;
	}

  public static boolean squareOutsideCircle(double x1, double y1,double l, double cx, double cy, double r) {

    double dx = Math.min(cx - x1, (x1+l) - cx);
    double dy = Math.min(cy - (y1+l), y1 - cy);

    return  r*r > dx*dx + dy*dy;

  }

	public static boolean squareInsideCircle(double x1,double y1,double l ,double cx, double cy, double r) {
 		/*double dx = Math.max(cx - x1, (x1+l) - cx);
 		double dy = Math.max(cy - (y1+l), y1 - cy);

 		return r*r >= dx*dx + dy*dy;
    */
    if(((cx + r >= x1 + l) && (cy - r <= y1) && (cx - r <= x1) && (cy + r >= y1 + l) && (r*r >= (l/2)*(l/2) + (l/2)*(l/2))))
      return true;

    return false;
   }

	public static boolean circleInsideSquare(double x1,double y1,double l ,double cx, double cy, double r) {
 		/*double maxX = cx + r;
 		double maxY = cy + r;
 		double minX = cx - r;
 		double minY = cy - r;

 		if(maxX <= (x1+l) && maxY <= (y1+l) && minX >= x1 && minY >= y1) {
     		return true;
 		}
 		else {
     		return false;
 		}
    */
    if((cx + r <= x1 + l) && (cy - r >= y1) && (cx - r >= x1) && (cy + r <= y1 + l)){
      return true;
    }
    else return false;
	}
}
