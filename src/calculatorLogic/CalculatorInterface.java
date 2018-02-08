package calculatorLogic;
import operands.Matrix;
import operands.Operand;

public interface CalculatorInterface {
	public <T extends Number> Operand add(Operand a, 	Operand b) throws Exception;
	
//	finds difference
	public <T extends Number> Operand sub(Operand a,	Operand b) throws Exception;
    
//	finds product
	public <T extends Number> Operand mul(Operand a,	Operand b) throws Exception;
	
//	finds quotient
	public <T extends Number> Operand div(Operand a,	Operand b) throws Exception;
}