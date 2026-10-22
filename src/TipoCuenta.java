
public class TipoCuenta {
       public static final TipoCuenta AHORRO=new TipoCuenta("Ahorro",0.03);
    public static final TipoCuenta CHEQUES=new TipoCuenta("Cheques",0.00);
    public static final TipoCuenta PLAZO=new TipoCuenta("Plazo",0.05);
    
    private final String nombre;
    private final double tasa;
    
    private TipoCuenta(String nombre, double tasa){
        this.nombre=nombre;
        this.tasa=tasa;
    }
    public double tasa(){
        return tasa;
    }
    public String getNombre(){
        return nombre;
    }
     @Override
    public String toString(){
        return nombre;
    }
}
