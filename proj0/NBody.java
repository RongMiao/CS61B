


class NBody {
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
        double d0 = Double.parseDouble(args[0]);
        double d1 = Double.parseDouble(args[1]);
        String path = args[2];
        Planet[] planets = NBody.readPlanets(path);
        StdDraw.setScale(-1 * readRadius(path), readRadius(path));
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        int x = 10;
        for (int i = 0; i < planets.length; i++) {
            planets[i].draw();
        }
        StdDraw.show();
        StdDraw.enableDoubleBuffering();
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];
        for (int i = 0; i < d1; i++) {
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int j = 0; j < planets.length; j++) {
                xForces[j] = planets[j].calcNetForceExertedByX(planets);
                yForces[j] = planets[j].calcNetForceExertedByY(planets);
            }
            for (int j = 0; j < planets.length; j++) {
                planets[j].update(5000, xForces[j], yForces[j]);
                System.out.println(j + " " + planets[j].xxPos + " " + planets[j].yyPos);
                planets[j].draw();
            }
            StdDraw.show();
            StdDraw.pause(1);
        }
    }
}