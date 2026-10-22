
import java.util.Calendar;


public class CuentaBancaria {

    //atributos
    protected int numero;
    protected String cliente, moneda;
    protected double saldo;
    protected Calendar apertura;
    protected TipoCuenta tipo;
    
    protected String[] historial = new String[50];
    protected int cantiMovimientos = 0;
    

    public CuentaBancaria(int numero, String cliente, String moneda){
        this.numero = numero;
        this.cliente = cliente;
        this.saldo = 500;
        this.apertura = Calendar.getInstance();
         tipo = TipoCuenta.CHEQUES;
       

    }
    
      public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getCliente() {
        return cliente;
    }

    public String getMoneda() {
        return moneda;
    }

    public TipoCuenta getTipo() {
        return tipo;
    }

    public Calendar getApertura() {
        return apertura;
    }
    
     private void registrarMovimiento(String linea) {
        try {
            if (cantiMovimientos >= historial.length) {
                throw new IndexOutOfBoundsException("El historial de movimientos se ha llenado");
            }
            historial[cantiMovimientos] = linea;
            cantiMovimientos++;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Alerta: " + e.getMessage());
        }
    }
    
    public void imprimirHistorial() {
        System.out.println("--- Historial de Movimientos (" + numero + ") ---");
        for (int i = 0; i < cantiMovimientos; i++) {
            System.out.println(historial[i]);
        }
    }
    

       public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor a cero");

        }

        saldo += monto;
        registrarMovimiento("Depositado: +" + monto + " saldo: " + saldo);
    }

     public boolean retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor a cero");
        }

        if (monto > saldo) {
            return false;
        }

        saldo -= monto;
        registrarMovimiento("RETIRO -" + monto + " -> saldo: " + saldo);
        return true;
    }

    public void registrarIntereses() {
        if (tipo != null) {
            double ganancia = saldo * tipo.tasa();
            saldo += ganancia;
        }
    }

    public void reintegrar(double monto){
        if(monto>0){
            saldo += monto;
            historial[cantiMovimientos] = "Reversa : +"+monto;
            cantiMovimientos++;
        }
    }


    public String toString(){
        return String.format("Cuenta #%d | %S | $%.2f | %S ",numero,cliente,saldo,apertura.getTime());
    }

    public void quienSoy() {
        System.out.println("CuentaBancaria(clase base)");
    }
    
    
    public void transferir(double monto, CuentaBancaria destino){

        if (this.retirar(monto)) {

            destino.retirar(monto);

        }else{
            System.out.println("Transferencia fallida ");
        }

    }

}
