
import java.util.Calendar;


public class CuentaPlazoFijo extends CuentaBancaria {
    private Calendar findePlazo;
    private double interesesAcumulados = 0.0;

    public CuentaPlazoFijo(int numero, String cliente, String moneda) {
        
        super(numero, cliente, moneda);
        this.tipo = TipoCuenta.PLAZO;
        this.findePlazo = Calendar.getInstance();
        
    }

   

    public void setPlazo(int anio, int mes, int dia) {
        findePlazo = Calendar.getInstance();
        findePlazo.set(anio, mes - 1, dia); 
    }

    @Override
    public boolean retirar(double monto) {
        Calendar hoy = Calendar.getInstance();
        
       
        if (hoy.before(findePlazo)) {
            if (monto > interesesAcumulados) {
                throw new IllegalArgumentException("No vence la cuenta. Solo puede retirar hasta " + interesesAcumulados + " de intereses.");
            }
            interesesAcumulados -= monto;
            try {
                if (cantiMovimientos < historial.length) {
                    historial[cantiMovimientos++] = "RETIRO INTERES -" + monto + " -> acumulado sobrante: " + interesesAcumulados;
                }
            } catch (Exception ignored) {}
            return true;
        }

        return super.retirar(monto);
    }

    @Override
    public void registrarIntereses() {
        Calendar hoy = Calendar.getInstance();
        if (hoy.before(findePlazo)) {
            interesesAcumulados += (saldo * tipo.tasa());
        } else {
            super.registrarIntereses();
        }
    }

    @Override
    public void quienSoy() {
        System.out.println("Soy una CuentaPlazoFijo");
    }
}
