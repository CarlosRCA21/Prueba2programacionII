
import java.util.Calendar;


public class CuentaBancaria {

    //atributos
    protected int numero;
    protected String cliente, moneda;
    protected double saldo;
    protected double tasa;
    protected Calendar apertura;
    
    protected String[] historial = new String[50];
    protected int cantiMovimientos = 0;
    

    public CuentaBancaria(int numero, String cliente, double tasa){
        this.numero = numero;
        this.cliente = cliente;
        this.tasa = tasa;
        this.saldo = 500;
        this.apertura = Calendar.getInstance();
       

    }

    public void depositar(double monto){
        try{
           if(monto<=0){
               System.out.println("Insuficiente saldo");
           }else{
               saldo += monto;
           }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public boolean retirar(double monto){
        if (monto>0 && saldo>=monto) {
            saldo-=monto;
            return true;
        }else{
           return false;
        }
    }


    public void reintegrar(double monto){
        if(monto>0){
            saldo += monto;
            historial[cantiMovimientos] = "Reversa : +"+monto;
            cantiMovimientos++;
        }
    }

    public double getSaldo(){
        return saldo;
    }

    public String toString(){
        return String.format("Cuenta #%d | %S | $%.2f",numero,cliente,saldo);
    }

    public void transferir(double monto, CuentaBancaria destino){

        if (this.retirar(monto)) {

            destino.retirar(monto);

        }else{
            System.out.println("Transferencia fallida ");
        }

    }

}
