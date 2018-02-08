
package calculatorLogic;

public class GeometricalOperations<T extends Number> implements GeometricalInterface
{

	public <T extends Number> double sine(int deg) {
		// TODO Auto-generated method stub
		double radianVal = Math.toRadians(deg);
		return Math.sin(radianVal);
	}

	public <T extends Number> double cosine(int deg) {
		double radianVal = Math.toRadians(deg);
		return Math.cos(radianVal);
	}

	public <T extends Number> double tangent(int deg) {
		double radianVal = Math.toRadians(deg);
		return Math.tan(radianVal);
	}

	public <T extends Number> double sec(int deg) {
		double radianVal = Math.toRadians(deg);
		return 1.0 / Math.cos(radianVal);
	}

	public <T extends Number> double cosec(int deg) {
		double radianVal = Math.toRadians(deg);
		return 1.0 / Math.sin(radianVal);
	}

	public <T extends Number> double cotangent(int deg) {
		double radianVal = Math.toRadians(deg);
		return 1.0 / Math.tan(radianVal);
	}

}
