public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double g = 6.67e-11;
	

	public Body(double xP,double yp,double xV,double yV,double m,String img){
		xxPos=xP;
		yyPos=yp;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}
	public Body(Body b){
		xxPos=b.xxPos;
		yyPos=b.yyPos;
		xxVel=b.xxVel;
		yyVel=b.yyVel;
		mass=b.mass;
		imgFileName=b.imgFileName;
	}
	public double calcDistance(Body c){
		double x = this.xxPos-c.xxPos;
		double y = this.yyPos-c.yyPos;
		double r2 = x*x + y*y;
		double r= Math.sqrt(r2);
		return r;
	}
	public double calcForceExertedBy(Body c){
		double r = this.calcDistance(c);
		double f = g*(this.mass*c.mass)/(r*r);
		return f;
	}
	public double calcForceExertedByX(Body c){
		double cos =(c.xxPos-this.xxPos)/this.calcDistance(c);
		double fx = this.calcForceExertedBy(c)*cos;
		return fx;
	}
	public double calcForceExertedByY(Body c){
		double sin =(c.yyPos-this.yyPos)/this.calcDistance(c);
		double fy = this.calcForceExertedBy(c)*sin;
		return fy;
	}
	public double calcNetForceExertedByX(Body[] all){
		int i = 0;
		double fnx=0;
		while(i<all.length){
			if(!this.equals(all[i])){
				fnx+=this.calcForceExertedByX(all[i]);
			}
			i++;
		}	
		return fnx;
	}
	public double calcNetForceExertedByY(Body[] all){
		int i = 0;
		double fny=0;
		while(i<all.length){
			if(!this.equals(all[i])){
				fny+=this.calcForceExertedByY(all[i]);
			}
			i++;
		}	
		return fny;
	}
	public void update(double dt, double fx, double fy){
		double ax = fx/this.mass;
		double ay = fy/this.mass;
		this.xxVel+= ax*dt;
		this.yyVel+= ay*dt;
		this.xxPos+=this.xxVel*dt;
		this.yyPos+=this.yyVel*dt;
	}
	public void draw(){
		StdDraw.picture(this.xxPos,this.yyPos,"./images/" + imgFileName);
	}

}
