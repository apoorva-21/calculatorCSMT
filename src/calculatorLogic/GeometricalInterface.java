package calculatorLogic;

public interface GeometricalInterface {
   
   public <T extends Number>double sine(int deg );
   public <T extends Number>double cosine(int deg);
   public <T extends Number>double tangent(int deg);
   public <T extends Number>double sec(int deg);
   public <T extends Number>double cosec(int deg);
   public <T extends Number>double cotangent(int deg);
   
}
