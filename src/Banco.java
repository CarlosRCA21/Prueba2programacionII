
public class Banco {
     private CuentaBancaria[] cuentas = new CuentaBancaria[20];
    private int cantidadCuentas = 0;

    public void agregarCuenta(CuentaBancaria c) {
        if (cantidadCuentas >= cuentas.length) {
            throw new IndexOutOfBoundsException("El Almacenamiento del banco se ha llenado.");
        }
        cuentas[cantidadCuentas] = c;
        cantidadCuentas++;
    }

    public CuentaBancaria buscarPorNumero(int numero) {
        return buscarRecursivo(numero, 0);
    }

    private CuentaBancaria buscarRecursivo(int numero, int index) {
        if (index >= cantidadCuentas) {
            throw new IllegalArgumentException("No se encontro ninguna cuenta con el numero: " + numero);
        }
        if (cuentas[index].getNumero() == numero) {
            return cuentas[index];
        }
        return buscarRecursivo(numero, index + 1);
    }

    public void ordenarPorSaldo() {
        ordenarRecursivo(cantidadCuentas);
    }

    private void ordenarRecursivo(int n) {
        if (n <= 1) {
            return;
        }
        ordenarRecursivo(n - 1);
        insertarRecursivo(n - 1);
    }

    private void insertarRecursivo(int i) {
        if (i <= 0) {
            return;
        }
        if (cuentas[i].getSaldo() < cuentas[i - 1].getSaldo()) {
            CuentaBancaria temp = cuentas[i];
            cuentas[i] = cuentas[i - 1];
            cuentas[i - 1] = temp;
            insertarRecursivo(i - 1);
        }
    }

    public void transferir(int numeroOrigen, int numeroDestino, double monto) {
        CuentaBancaria origen = buscarPorNumero(numeroOrigen);
        CuentaBancaria destino = buscarPorNumero(numeroDestino);

        boolean retiroExitoso = origen.retirar(monto);
        if (!retiroExitoso) {
            throw new IllegalStateException("Saldo insuficiente en la cuenta .");
        }

        try {
            destino.depositar(monto);
        } catch (Exception e) {

            origen.depositar(monto);
            throw new IllegalStateException("Error al depositar. Se ha devuelto su dinero. Motivo: " + e.getMessage());
        }
    }

    public void aplicarInteresesGlobal() {
        for (int i = 0; i < cantidadCuentas; i++) {
            try {
                cuentas[i].registrarIntereses();
            } catch (Exception e) {
                System.out.println("Error aplicando los intereses a cuenta #" + cuentas[i].getNumero() + ": " + e.getMessage());
            }
        }
    }

    public double saldoTotalDelBanco() {
        return sumarSaldosRecursivo(0);
    }

    private double sumarSaldosRecursivo(int index) {
        if (index >= cantidadCuentas) {
            return 0.0;
        }
        return cuentas[index].getSaldo() + sumarSaldosRecursivo(index + 1);
    }

    public void generarReporte() {
        ordenarPorSaldo();
        System.out.println("================ REPORTE DEL BANCO ================");
        for (int i = 0; i < cantidadCuentas; i++) {
            System.out.println(cuentas[i].toString());
        }

    }

}
