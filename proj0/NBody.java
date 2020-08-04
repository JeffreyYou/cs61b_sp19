public class NBody{
	
	public static void main(String[] args){
		double t = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Double radius = readRadius(filename);
		Body[] b = readBodies(filename);

		double[] xForces = new double[5];
		double[] yForces = new double[5];

		double k=0;
		while (k<=t) {
		
		for (int j=0;j<5 ; j++) {
			xForces[j] = b[j].calcNetForceExertedByX(b);
			yForces[j] = b[j].calcNetForceExertedByY(b);
		}	
		
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		
		StdDraw.clear();
		StdDraw.picture(0,0,"./images/starfield.jpg");
		
		
		//StdDraw.pause(20);
		for (int j=0;j<5 ; j++) {
			b[j].draw();
			b[j].update(dt,xForces[j],yForces[j]);
		}
		StdDraw.show();
		StdDraw.pause(10);
		k = k + dt;
		}
	}
		
		
		
	
	public static double readRadius(String x){
		In i = new In(x);
		int number = i.readInt();
		double radiusOfUniverse = i.readDouble();
		
		return radiusOfUniverse;
	}
	public static Body[] readBodies(String s){
		In i = new In(s);
		Body planet[] = new Body[5];
		i.readString();
		i.readString();
		int k = 0;
		for(int j=0;j<5;j++){ 
		double xp = i.readDouble();
		double yp = i.readDouble();
		double xv = i.readDouble();
		double yv = i.readDouble();
		double ma = i.readDouble();
		String na = i.readString();
		planet[k] = new Body(xp,yp,xv,yv,ma,na);
		k++;}
		return planet;
		
	}
}