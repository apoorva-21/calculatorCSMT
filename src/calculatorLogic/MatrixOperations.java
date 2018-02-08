package calculatorLogic;
import operands.*;
import calculatorLogic.InvalidShapeException;
import java.util.ArrayList;

public class MatrixOperations implements MatrixInterface {
//	Method for addition of matrices
	public <T extends Number> Operand add(Operand a, Operand b) throws InvalidShapeException {
		ArrayList<Integer> shapeA = ((Matrix<T>)a).shape();
		ArrayList<Integer> shapeB = ((Matrix<T>)b).shape();
		if (shapeA.get(0) != shapeB.get(0) || shapeA.get(1) != shapeB.get(1))
			throw new InvalidShapeException("Please make sure the shapes of both matrices match!");
		
		Matrix<T> result = new Matrix(shapeA.get(0), shapeA.get(1));
		for (int i = 0; i < shapeA.get(0); i++) {
			for (int j = 0; j < shapeB.get(1); j++) {
				result.get(i).set(j, (T) new Double(((Matrix<T>)a).get(i).get(j).doubleValue() + ((Matrix<T>)b).get(i).get(j).doubleValue()));
			}
		}
		return result;
	}
	
//	Method for subtraction of matrices
	public <T extends Number> Operand sub(Operand a, Operand b) throws InvalidShapeException {
		ArrayList<Integer> shapeA = ((Matrix<T>)a).shape();
		ArrayList<Integer> shapeB = ((Matrix<T>)b).shape();
		if (shapeA.get(0) != shapeB.get(0) || shapeA.get(1) != shapeB.get(1))
			throw new InvalidShapeException("Please make sure the shapes of both matrices match!");
		
		Matrix<T> result = new Matrix(shapeA.get(0), shapeA.get(1));
		for (int i = 0; i < shapeA.get(0); i++) {
			for (int j = 0; j < shapeB.get(1); j++) {
				result.get(i).set(j, (T) new Double(((Matrix<T>)a).get(i).get(j).doubleValue() - ((Matrix<T>)b).get(i).get(j).doubleValue()));
			}
		}
		return result;
	}
	
//	Method for multiplication of matrices
	public <T extends Number> Operand mul(Operand a, Operand b) throws InvalidShapeException{
		ArrayList<Integer> shapeA = ((Matrix<T>)a).shape();
		ArrayList<Integer> shapeB = ((Matrix<T>)b).shape();
		if (shapeA.get(1) != shapeB.get(0))
			throw new InvalidShapeException("Matrix multiplcation is not valid for given matrices!");
		
		Matrix<T> result = new Matrix(shapeA.get(0), shapeB.get(1));
		ArrayList<Integer> shapeResult = result.shape();
		for (int i = 0; i < shapeResult.get(0); i++) {
			for (int j = 0; j < shapeResult.get(1); j++) {
				double sum = 0;
				for (int k = 0; k < shapeA.get(1); k++) {
					sum += ((Matrix<T>)a).get(i).get(k).doubleValue() * ((Matrix<T>)b).get(k).get(j).doubleValue();
				} result.get(i).set(j,(T) new Double(sum));
			}
		}
		
		return result;
	}
	
//	Method for division of two matrices
	public <T extends Number> Operand div(Operand a, Operand b) throws InvalidShapeException{
		return mul(a, inverse((Matrix<T>)b));
	}
	
//	Method for transposing the matrix
	public <T extends Number> Matrix<T> transpose(Matrix<T> a) {
		ArrayList<Integer> shapeA = a.shape();
		Matrix<T> result = new Matrix(shapeA.get(1), shapeA.get(0));
		ArrayList<Integer> shapeResult = result.shape();
		for (int i = 0; i < shapeResult.get(0); i++) {
			for (int j = 0; j < shapeResult.get(1); j++) {
				result.get(i).set(j, a.get(j).get(i));
			}
		}
		
		return result;
	}
	
//	Method to find the minor of a matrix given the row and column corresponding to it
	public <T extends Number> Matrix<T> minor(Matrix<T> a, int row, int col) throws InvalidShapeException{
		ArrayList<Integer> shapeA = a.shape();
		if (shapeA.get(0) != shapeA.get(1) || shapeA.get(0) < 2 || shapeA.get(1) < 2)
			throw new InvalidShapeException("Cannot get minor for given matrix");
			
		Matrix<T> result = new Matrix(shapeA.get(0) - 1, shapeA.get(1) - 1);
		
		int rowDisplacement = 0, colDisplacement = 0;
		for (int i = 0; i < shapeA.get(0); i++) {
			for (int j = 0; j < shapeA.get(1); j++) {
				if (i == row) {
					rowDisplacement = -1;
					continue;
				}
				if (j == col) {
					colDisplacement = -1;
					continue;
				}
				result.get(i + rowDisplacement).set(j + colDisplacement, a.get(i).get(j));
			}	colDisplacement = 0;
		}
		
		return result;
	}
	
//	Method to get the determinant of the matrix
	public <T extends Number> Double determinant(Matrix<T> a) throws InvalidShapeException{
		ArrayList<Integer> shapeA = a.shape();
		if (shapeA.get(0) != shapeA.get(1))
			throw new InvalidShapeException("For determinant to be calculated, no. of rows must be equal to no. of columns");
		if (shapeA.get(0) == 1)
			return a.get(0).get(0).doubleValue();

		double result = 0;
		for (int i = 0; i < shapeA.get(0); i++) {
			try {
				Matrix<T> minor = minor(a, i, 0);
				ArrayList<Integer> shapeMinor = minor.shape();
				result += Math.pow(-1, i + 1) * determinant(minor) * a.get(i).get(0).doubleValue();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		return result;
	}
	
//	Method to display Matrix
	public <T extends Number> void show(Matrix<T> mat) {
		ArrayList<Integer> shapeMat = mat.shape();
		for (int i = 0; i < shapeMat.get(0); i++) {
			for (int j = 0; j < shapeMat.get(1); j++) {
				System.out.print(mat.get(i).get(j) + "\t");
			}	System.out.println();
		}
	}
	
//	Adjoint of the matrix
	public <T extends Number> Matrix<T> adjoint(Matrix<T> a) throws InvalidShapeException{
		ArrayList<Integer> shapeA = a.shape();
		Matrix<T> result = new Matrix(shapeA.get(0), shapeA.get(1));
		for (int i = 0; i < shapeA.get(0); i++) {
			for (int j = 0; j < shapeA.get(1); j++) {
				result.set(i, j, (T) new Double(Math.pow(-1, i + j) * determinant(minor(a, i, j))));
			}
		}
		
		return transpose(result);
	}
	
//	Inverse of the matrix
	public <T extends Number> Matrix<T> inverse(Matrix<T> a) throws InvalidShapeException{
		Matrix<T> result = adjoint(a);
		ArrayList<Integer> shapeA = a.shape();
		double detA = determinant(a);
		for (int i = 0; i < shapeA.get(0); i++) {
			for (int j = 0; j < shapeA.get(1); j++) {
				result.set(i,  j, (T) new Double(result.get(i).get(j).doubleValue() / detA));
			}
		}
		
		return result;
	}
}
