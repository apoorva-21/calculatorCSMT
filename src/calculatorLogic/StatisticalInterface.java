package calculatorLogic;
import operands.Operand;

public interface StatisticalInterface {
	
	public Operand min( Operand [] a);
	public Operand  max(Operand [] a);
	public Operand  mean(Operand [] a);
	public Operand median(Operand[] a);
	public Operand mode(Operand [] a);
	public Operand variance(Operand[] a);
	public Operand stddev(Operand[] a);
}

