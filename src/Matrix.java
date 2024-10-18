public class Matrix {
    private int rows;
    private int cols;
    private Complex[][] matrix;

    public Matrix(int rows, int cols){
        if (rows <= 0 || cols <= 0){
            throw new IllegalArgumentException("Значения должны быть числами > 0");
        }
        this.rows = rows;
        this.cols = cols;
        matrix = new Complex[rows][cols];
    }

    public Complex getter(int row, int col){
        return matrix[row][col];
    }

    public int getRows(){
        return rows;
    }

    public int getCols(){
        return cols;
    }

    public void setter(int row, int col, Complex value){
        matrix[row][col] = value;
    }

    public Matrix sum(Matrix other){
        if (this.rows != other.rows || this.cols != other.cols){
            throw new IllegalArgumentException("Размеры матриц не подходят для сложения");
        }
        Matrix res = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                res.setter(i, j, this.getter(i, j).sum(other.getter(i, j)));
            }
        }
        return res;
    }

    public Matrix diff(Matrix other){
        if (this.rows != other.rows || this.cols != other.cols){
            throw new IllegalArgumentException("Размеры матриц не подходят для вычитания");
        }
        Matrix res = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                res.setter(i, j, this.getter(i, j).diff(other.getter(i, j)));
            }
        }
        return res;
    }

    public Matrix transpose(){
        Matrix res = new Matrix(cols, rows);
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                res.setter(j, i, this.getter(i, j));
            }
        }
        return res;
    }

    public Matrix multiply(Matrix other){
        if (this.cols != other.rows){
            throw new IllegalArgumentException("Невозможно произвести умножение из за размерностей матриц");
        }
        Matrix res = new Matrix(this.rows, other.cols);
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < other.cols; j++){
                Complex summa = new Complex();
                for (int k = 0; k < this.cols; k++){
                    summa = summa.sum(this.getter(i, k).multiply(this.getter(k, j)));
                }
                res.setter(i, j, summa);
            }
        }
        return res;
    }

    public Matrix multiply_by_scalar(Complex scalar){
        Matrix res = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                res.setter(i, j, this.getter(i, j).multiply(scalar));
            }
        }
        return res;
    }

    public Complex determinant(){
        if (rows != cols){
            throw new IllegalArgumentException("Невозможно вычислить детерминант, так как матрица не квадратная");
        }
        return deterRecursive(matrix);
    }

    public Complex deterRecursive(Complex[][] matrix){
        int n = matrix.length;
        if (n == 1){
            return matrix[0][0];
        }
        Complex det = new Complex();
        for (int i = 0; i < n; i++){
            det = det.sum(matrix[0][i].multiply(cofactor(matrix, 0, i)));
        }
        return det;
    }

    public Complex cofactor(Complex[][] matrix, int row, int col){
        int n = matrix.length;
        Complex[][] submatrix = new Complex[n - 1][n - 1];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (i != row && j != col){
                    int subrow;
                    int subcol;
                    if (i < row){
                        subrow = i;
                    } else {
                        subrow = i - 1;
                    }
                    if (j < col){
                        subcol = j;
                    } else{
                        subcol = j - 1;
                    }
                    submatrix[subrow][subcol] = matrix[i][j];
                }
            }
        }

        Complex sign;
        if ((row + col) % 2 == 0) {
            sign = new Complex(1, 0);
        } else {
            sign = new Complex(-1, 0);
        }

        return sign.multiply(deterRecursive(submatrix));
    }

    public Matrix inverse(){
        if (rows != cols){
            throw new IllegalArgumentException("Чтобы найти обратную, матрицы должны быть квадратные");
        }
        Complex det = this.determinant();
        if (det.getRe() == 0 && det.getIm() == 0){
            throw new IllegalArgumentException("Матрица вырожденная и обратная не сущетсвует");
        }
        Matrix adjugate = this.adjugate();
        Matrix inverse = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                inverse.setter(i, j, adjugate.getter(i , j).divide(det));
            }
        }
        return inverse;
    }

    public Matrix adjugate(){
        Matrix adjugate = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                adjugate.setter(i, j, cofactor(matrix, i, j));
            }
        }
        return adjugate.transpose();
    }

    public Matrix divide (Matrix other){
        Matrix inverseOther = other.inverse();
        return this.multiply(inverseOther);
    }

}
