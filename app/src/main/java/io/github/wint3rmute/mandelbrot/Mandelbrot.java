package io.github.wint3rmute.mandelbrot;

/**
 * Created by mateu on 08/08/2017.
 */

public class Mandelbrot
    {
    public static double isPartOf(Complex n, int accuracy)
        {
        double real = n.real;
        double imaginary = n.imaginary;
        int iterations = 0;

        double x = 0;
        double y = 0;
        double new_x = 0;						// or <=
        while((iterations < accuracy) && (x*x+y*y < 4))
            {
            new_x = x*x - y*y + real;
            y = 2*x*y + imaginary;
            x = new_x;
            iterations++;
            }

        return ((double)accuracy)/((double)iterations);
        }

}

class Complex
{
public double real;
public double imaginary;

public Complex(double real, double imaginary)
{
this.real = real;
this.imaginary = imaginary;
}
}


