


public class NBody {
    public NBody() {

    }

    public static double readRadius(String path) {
        In in = new In(path);
        in.readDouble();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String path) {
        In in = new In(path);
        int cnt = Integer.parseInt(in.readLine());
        in.readLine();
        Planet[] planets = new Planet[cnt];
        double xxPos = 0;
        double yyPos = 0;
        double xxVel = 0;
        double yyVel = 0;
        double mass = 0;
        String imgFileName = null;
        for (int i = 0; i < cnt; i++) {
            xxPos = in.readDouble();
            yyPos = in.readDouble();
            xxVel = in.readDouble();
            yyVel = in.readDouble();
            mass = in.readDouble();
            imgFileName = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String path = args[2];
        double radius = readRadius(path);
        Planet[] planets = NBody.readPlanets(path);
        StdDraw.setScale(-1 * radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (int i = 0; i < planets.length; i++) {
            planets[i].draw();
        }
        StdDraw.show();
        StdDraw.enableDoubleBuffering();
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];
        for (int i = 0; i < T; i++) {
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int j = 0; j < planets.length; j++) {
                xForces[j] = planets[j].calcNetForceExertedByX(planets);
                yForces[j] = planets[j].calcNetForceExertedByY(planets);
            }
            for (int j = 0; j < planets.length; j++) {
                planets[j].update(dt, xForces[j], yForces[j]);
                //System.out.println(j + " " + planets[j].xxPos + " " + planets[j].yyPos);
                planets[j].draw();
            }
            StdDraw.show();
            StdDraw.pause(1);
            i += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}