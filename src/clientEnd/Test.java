package clientEnd;
import operands.*;
import calculatorLogic.*;

public class Test {
	public static void main(String[] args) {
		Matrix<Integer> myMat = new Matrix(1, 5);
		for (int i = 0; i < 5; i++)
			myMat.get(0).set(i, 1);
		
		Matrix<Integer> myMat2 = new Matrix(5, 5);
		for (int i = 0; i < 5; i++) {
			myMat2.set(i, i, 7);
		}
		MatrixOperations matOps = new MatrixOperations();
		try {
			matOps.show(matOps.transpose(myMat));
			System.out.println();
			matOps.show(myMat2);
			System.out.println();
			matOps.show(matOps.minor(myMat2, 2, 1));
			System.out.println(matOps.determinant(myMat2));
//			matOps.show(matOps.mul(myMat, myMat2));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
