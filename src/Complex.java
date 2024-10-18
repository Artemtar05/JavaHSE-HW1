public class Complex {
    private double re;
    private double im;

    public Complex(double value_re, double value_im){
        this.re = value_re;
        this.im = value_im;
    }

    public Complex(){
        re = 0.0;
        im = 0.0;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    public void setRe(double re) {
        this.re = re;
    }

    public void setIm(double im) {
        this.im = im;
    }

    public Complex sum(Complex other){
        return new Complex(this.re + other.re, this.im + other.im);
    }

    public Complex diff(Complex other){
        return new Complex(this.re - other.re, this.im - other.im);
    }

    public Complex multiply(Complex other){
        double re_part = (this.re * other.re) - (this.im * other.im);
        double im_part = (this.re * other.im) + (this.im * other.re);
        return new Complex(re_part, im_part);
    }

    public Complex divide(Complex other){
        if (other.re == 0 && other.im == 0){
            throw new IllegalArgumentException("ОШИБКА! Деление на ноль");
        }
        double re_part = ((this.re * other.re) + (this.im * other.im)) / ((other.re * other.re) + (other.im * other.im));
        double im_part = ((other.re * this.im) - (this.re * other.im)) / ((other.re * other.re) + (other.im * other.im));
        return new Complex(re_part, im_part);
    }


}
