public class Main {
    public static void main(String[] args) {
        Complex complex1 = new Complex(2, 2);
        Complex complex2 = new Complex(2, 1);

        System.out.println("Комплексное число 1: " + complex1.getRe() + " + " + complex1.getIm() + "i");
        System.out.println("Комплексное число 2: " + complex2.getRe() + " + " + complex2.getIm() + "i\n");

        // Сложение
        Complex sum = complex1.sum(complex2);
        System.out.println("Сумма комплексных чисел: " + sum.getRe() + " + " + sum.getIm() + "i\n");

        // Вычитание
        Complex difference = complex1.diff(complex2);
        System.out.println("Разница комплексных чисел: " + difference.getRe() + " + " + difference.getIm() + "i\n");

        // Умножение
        Complex product = complex1.multiply(complex2);
        System.out.println("Умножение комплексных чисел: " + product.getRe() + " + " + product.getIm() + "i\n");

        // Деление
        Complex division = complex1.divide(complex2);
        System.out.println("Деление комплексных чисел: " + division.getRe() + " + " + division.getIm() + "i\n");


        Matrix matrix1 = new Matrix(2, 2);
        Matrix matrix2 = new Matrix(2, 2);

        matrix1.setter(0, 0, new Complex(1, 1));
        matrix1.setter(0, 1, new Complex(2, -1));
        matrix1.setter(1, 0, new Complex(3, 0));
        matrix1.setter(1, 1, new Complex(4, 2));

        matrix2.setter(0, 0, new Complex(4, 2));
        matrix2.setter(0, 1, new Complex(1, 4));
        matrix2.setter(1, 0, new Complex(2, 4));
        matrix2.setter(1, 1, new Complex(4, 0));

        System.out.println("Первая матрица:");
        printMatrix(matrix1);
        System.out.println("Вторая матрица:");
        printMatrix(matrix2);

        Matrix sumMatrix = matrix1.sum(matrix2);
        System.out.println("Сумма матриц:");
        printMatrix(sumMatrix);

        Matrix diffMatrix = matrix1.diff(matrix2);
        System.out.println("Разница матриц:");
        printMatrix(diffMatrix);

        Matrix multiMatrix = matrix1.multiply(matrix2);
        System.out.println("Умножение матриц:");
        printMatrix(multiMatrix);

        Matrix multiScalarMatrix = matrix1.multiply_by_scalar(complex1);
        System.out.println("Матрица 1 умнрженная на комплексное число 1:");
        printMatrix(multiScalarMatrix);

        Matrix transposeMatrix = matrix1.transpose();
        System.out.println("Транспонированная матрица 1:");
        printMatrix(transposeMatrix);

        Complex deter = matrix1.determinant();
        System.out.println("Детерминант матрицы 1:");
        System.out.println(deter.getRe() + " + " + deter.getIm() + "i\n");

        Matrix inverseMatrix = matrix1.inverse();
        System.out.println("Обратная матрица 1");
        printMatrix(inverseMatrix);

        Matrix divideMatrix = matrix1.divide(matrix2);
        System.out.println("Деление матриц:");
        printMatrix(divideMatrix);


    }

    private static String formatComplex(Complex c) {
        return String.format("%.2f + %.2fi", c.getRe(), c.getIm());
    }
    private static void printMatrix(Matrix matrix){
        for (int i = 0; i < matrix.getRows(); i++){
            for (int j = 0; j < matrix.getCols(); j++){
                Complex num = matrix.getter(i, j);
                System.out.print(formatComplex(matrix.getter(i, j)) + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}