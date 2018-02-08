package calculatorLogic;
import operands.*;

public class BasicOperations implements CalculatorInterface{
	public <T extends Number> Operand add(Operand a, Operand b) {
		return new RealNumber(((RealNumber<T>)a).value() + ((RealNumber<T>)b).value());
	}
	
	//	method to subtract two numbers
	public <T extends Number> Operand sub(Operand a, Operand b) {
    	return new RealNumber(((RealNumber<T>)a).value() - ((RealNumber<T>)b).value());
    }
    
    //    method to multiply two numbers
	public <T extends Number> Operand mul(Operand a, Operand b) {
    	return new RealNumber(((RealNumber<T>)a).value() * ((RealNumber<T>)b).value());
    }
    
    //    method to divide two numbers
	public <T extends Number> Operand div(Operand a, Operand b) {
    	return new RealNumber(((RealNumber<T>)a).value() / ((RealNumber<T>)b).value());
    }
}
