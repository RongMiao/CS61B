

public class Planet {
    
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    
    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((xxPos-p.xxPos)*(xxPos-p.xxPos) + (yyPos-p.yyPos)*(yyPos-p.yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        return 6.67e-11*mass*p.mass/(calcDistance(p)*calcDistance(p));
    }

    public double calcForceExertedByY(Planet p) {
        return calcForceExertedBy(p)*(p.yyPos-yyPos)/calcDistance(p);
    }

    public double calcForceExertedByX(Planet p) {
        return calcForceExertedBy(p)*(p.xxPos-xxPos)/calcDistance(p);
    }

    public double calcNetForceExertedByY(Planet[] p) {
        double ret = 0;
        for (int i = 0; i < p.length; i++) {
            if (p[i] == this)
                continue;
            ret += calcForceExertedByY(p[i]);
        }
        return ret;
    }

    public double calcNetForceExertedByX(Planet[] p) {
        double ret = 0;
        for (int i = 0; i < p.length; i++) {
            if (p[i] == this)
                continue;
            ret += calcForceExertedByX(p[i]);
        }
        return ret;
    }

    public void update(double dt, double xV, double yV) {
        xxPos += (xxVel*dt + xV/mass*dt*dt);
        yyPos += (yyVel*dt + yV/mass*dt*dt);
        xxVel += dt*xV/mass;
        yyVel += dt*yV/mass;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/"+ imgFileName);
    }
}