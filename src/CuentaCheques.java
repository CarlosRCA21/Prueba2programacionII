
public class CuentaCheques extends CuentaBancaria {
     private double limiteSobregiro;

    public CuentaCheques(int numero, String cliente, String moneda, double limiteSobregiro) {
        super(numero, cliente, moneda);
        this.tipo = TipoCuenta.CHEQUES;
        this.limiteSobregiro = limiteSobregiro;
    }

    public void girarCheque(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto del cheque debe ser positivo");
        }

        if (monto > (saldo + limiteSobregiro)) {
            throw new IllegalStateException("Fondos insuficientes para girar el cheque");
        }

        saldo -= monto;

        try {
            if (cantiMovimientos < historial.length) {
                historial[cantiMovimientos++] = "CHEQUE -" + monto + " -> saldo: " + saldo;
            }
        } catch (Exception e) {
            System.out.println("No se pudo registrar el cheque en el historial.");
        }
    }

    @Override
    public void quienSoy() {
        System.out.println("Soy una CuentaCheques");
    }

    @Override
    public String toString() {
        double disponible = saldo + limiteSobregiro;
        return super.toString() + " | Sobregiro Limite: " + limiteSobregiro + " | Disponible total: " + disponible;
    }

    @Override
    public boolean retirar(double monto) {
        if (monto < 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo");
        }
        if (monto > (saldo + limiteSobregiro)) {
            return false;
        }
        saldo -= monto;
        try {
            if (cantiMovimientos < historial.length) {
                historial[cantiMovimientos++] = "RETIRO -" + monto + " -> saldo: " + saldo;
            }
        } catch (Exception e) {
            System.out.println("No se pudo registrar en el historial.");
        }
        return true;
    }

}
