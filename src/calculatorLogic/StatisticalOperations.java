package calculatorLogic;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
import operands.Operand;

public class StatisticalOperations<T extends Number> implements StatisticalInterface{
	
	// this class has an array of numbers whose mean,mode,standard deviation, variance 
	//private T[] a;
	 
	public <T extends Number> double add(T a, T b) {
	return a.doubleValue() + b.doubleValue();
    }

//	method to subtract two numbers
    public <T extends Number> double sub(T a,	T b) {
	return a.doubleValue() - b.doubleValue();
    }

//    method to multiply two numbers
    public <T extends Number> double mul(T a,	T b) {
	return a.doubleValue() * b.doubleValue();
    }

//    method to divide two numbers
    public <T extends Number> double div(T a,	T b) {
	return a.doubleValue() / b.doubleValue();
    }


    public String solve(String input) {
	char[] tokens = input.toCharArray();
	
	// Stack for numbers
    Stack<Double> values = new Stack<Double>();


    // Stack for Operators
    Stack<Character> ops = new Stack<Character>();


    for (int i = 0; i < tokens.length; i++) {
    	
    	// Current token is a number, push it to stack for numbers
    	if (tokens[i] >= '0' && tokens[i] <= '9') {
    		StringBuffer sbuf = new StringBuffer();
    		
    		// There may be more than one digits in number
    		while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.'))
    			sbuf.append(tokens[i++]);
    		
    		values.push(Double.parseDouble(sbuf.toString()));
    	} else if (tokens[i] == '(') {
    		ops.push(tokens[i]);
    	} else if (tokens[i] == ')') {
            while (ops.peek() != '(')
            	values.push(applyOp(ops.pop(), values.pop(), values.pop()));
            
            ops.pop();
        } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
            // While top of 'ops' has same or greater precedence to current
            // token, which is an operator. Apply operator on top of 'ops'
            // to top two elements in values stack
            while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
            	values.push(applyOp(ops.pop(), values.pop(), values.pop()));
             
            ops.push(tokens[i]);
        }
    }


    // Entire expression has been parsed at this point, apply remaining operators to remaining values
    while (!ops.empty())
    	values.push(applyOp(ops.pop(), values.pop(), values.pop()));

    // Top of 'values' contains result, return it
    return new BigDecimal(values.pop()).setScale(12, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
//    double rawResult = 
//    return values.pop().toString();
    
}
  
    public static boolean hasPrecedence(char op1, char op2) {
	if (op2 == '(' || op2 == ')')
		return false;
    if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
        return false;
    else
        return true;
  	}
  
  	public Double applyOp(char op,  Double b, Double a) {
    switch (op) {
      	case '+':
      		return add(a,b);  // calling the business class 
      	case '-':
      		return sub(a, b);
      	case '*':
      		return mul(a, b);
      	case '/':
      		if (b == 0)
      			throw new UnsupportedOperationException("Cannot divide by zero");
      		return div(a, b);
    }
    return 0.00;
    }

  	public <T extends Number> double min(T a[]){
		double minValue = a[0].doubleValue();
	    for (int i = 1; i < a.length; i++) {
	        if (a[i].doubleValue() < minValue) {
	            minValue = a[i].doubleValue();
	        }
	    }
	    return minValue;
		
	}
	public <T extends Number> double max(T a[]){
		double maxValue = a[0].doubleValue();
	    for (int i = 1; i < a.length; i++) {
	        if (a[i].doubleValue() > maxValue) {
	            maxValue = a[i].doubleValue();
	        }
	    }
	    return maxValue;
	}
	public <T extends Number> double mean(T a[]){
	  
	    double mean;
	    double tot=0;
		for(int i=0; i<a.length;i++)
	    {
	    tot =tot+a[i].doubleValue();
	    }
	    mean = tot/a.length;
	    return mean;
	}
	public <T extends Number> double median(T a[]){
		if(a.length%2==0)
		return  ( a[a.length/2-1].doubleValue() + a[a.length/2].doubleValue() )/2;
		else 
			return a[a.length/2].doubleValue();
	}
	
	public <T extends Number> double mode(T a[]){
        double mode = 0, maxCount = 0;
        for (int i = 0; i < a.length; ++i) 
        {  int count = 0;
            for (int j = 0; j < a.length; ++j) 
            {
                if (a[j].doubleValue() == a[i].doubleValue())
                    ++count;
            }
            if (count > maxCount) 
            {
                maxCount = count;
                mode = a[i].doubleValue();
            }
        }
        return mode;
	
	}
	
	public <T extends Number> double variance(T a[]){
		double mean=mean(a);
		double temp=0,diff,square;
	    for(int i=0;i<a.length;i++)
	    {
	    	diff=a[i].doubleValue()-mean;
	    	square=diff*diff;
	    	temp+=square;
	    }
	    return temp/a.length;    
	}
	
	//returns standard deviation
	public <T extends Number> double stddev(T a[]){ 
	  return Math.sqrt(variance(a));
	}
	
	public <T extends Number> double skewness(T a[]){
		double temp=0,cube,diff;
		double mean=mean(a);
		for(int i=0;i<a.length;i++)
		{
			diff=a[i].doubleValue()-mean;
			cube=Math.pow(diff,3);              //skewness=
			temp+=cube;
		}
		return temp/(a.length*Math.pow(stddev(a),3));
	}

	public Operand min(Operand[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	public Operand max(Operand[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	public Operand mean(Operand[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	public Operand median(Operand[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	public Operand mode(Operand[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	public Operand variance(Operand[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	public Operand stddev(Operand[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	
}



