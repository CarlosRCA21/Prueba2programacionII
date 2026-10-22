import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Banco banco = new Banco();
        boolean salir = false;

        System.out.println("=== BIENVENIDO AL BANCO FINANZAS DEL VALLE ===");

        while (!salir) {
            System.out.println("\n---------------- MENU PRINCIPAL ----------------");
            System.out.println("1. Crear cuenta");
            System.out.println("2. Depositar en una cuenta");
            System.out.println("3. Retirar de una cuenta");
            System.out.println("4. Transferir entre cuentas");
            System.out.println("5. Aplicar intereses globales");
            System.out.println("6. Ver reporte del banco (saldos y total)");
            System.out.println("7. Ver historial de movimientos de una cuenta");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = 0;
            try {
                opcion = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero valido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    try {
                        System.out.println("\n CREAR CUENTA ");
                        System.out.println("1. Cuenta de Ahorro");
                        System.out.println("2. Cuenta de Cheques");
                        System.out.println("3. Plazo Fijo");
                        System.out.print("Tipo: ");
                        int tipoSel = Integer.parseInt(input.nextLine());

                        System.out.print("Numero de cuenta: ");
                        int num = Integer.parseInt(input.nextLine());
                        System.out.print("Nombre del cliente: ");
                        String cliente = input.nextLine();
                        System.out.print("Moneda (Lps / USD): ");
                        String moneda = input.nextLine();

                        CuentaBancaria nuevaCuenta = null;

                        if (tipoSel == 1) {
                            nuevaCuenta = new CuentaAhorro(num, cliente, moneda);
                        } else if (tipoSel == 2) {
                            System.out.print("Limite de sobregiro: ");
                            double sobregiro = Double.parseDouble(input.nextLine());
                            nuevaCuenta = new CuentaCheques(num, cliente, moneda, sobregiro);
                        } else if (tipoSel == 3) {
                            CuentaPlazoFijo cpf = new CuentaPlazoFijo(num, cliente, moneda);
                            System.out.print("Anio de vencimiento (ej. 2026): ");
                            int anio = Integer.parseInt(input.nextLine());
                            System.out.print("Mes de vencimiento (1-12): ");
                            int mes = Integer.parseInt(input.nextLine());
                            System.out.print("Dia de vencimiento (1-31): ");
                            int dia = Integer.parseInt(input.nextLine());
                            cpf.setPlazo(anio, mes, dia);
                            nuevaCuenta = cpf;
                        } else {
                            System.out.println("Tipo de cuenta no valido.");
                            break;
                        }

                        banco.agregarCuenta(nuevaCuenta);
                        System.out.println("Cuenta creada exitosamente!");

                    } catch (Exception e) {
                        System.out.println("Error al crear cuenta: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.println("\n--- DEPOSITAR ---");
                        System.out.print("Ingrese numero de cuenta: ");
                        int num = Integer.parseInt(input.nextLine());
                        CuentaBancaria c = banco.buscarPorNumero(num);

                        System.out.print("Monto a depositar: ");
                        double monto = Double.parseDouble(input.nextLine());

                        c.depositar(monto);
                        System.out.println("Deposito realizado con exito.");
                    } catch (Exception e) {
                        System.out.println("Error en deposito: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        System.out.println("\n--- RETIRAR ---");
                        System.out.print("Ingrese numero de cuenta: ");
                        int num = Integer.parseInt(input.nextLine());
                        CuentaBancaria c = banco.buscarPorNumero(num);

                        System.out.print("Monto a retirar: ");
                        double monto = Double.parseDouble(input.nextLine());

                        boolean ok = c.retirar(monto);
                        if (ok) {
                            System.out.println("Retiro exitoso.");
                        } else {
                            System.out.println("No se pudo completar el retiro (Fondos insuficientes).");
                        }
                    } catch (Exception e) {
                        System.out.println("Error en retiro: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.println("\n--- TRANSFERENCIA ---");
                        System.out.print("Numero cuenta origen: ");
                        int origen = Integer.parseInt(input.nextLine());
                        System.out.print("Numero cuenta destino: ");
                        int destino = Integer.parseInt(input.nextLine());
                        System.out.print("Monto a transferir: ");
                        double monto = Double.parseDouble(input.nextLine());

                        banco.transferir(origen, destino, monto);
                        System.out.println("Transferencia realizada exitosamente!");
                    } catch (Exception e) {
                        System.out.println("Error en transferencia: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("\nCalculando e incrementando intereses a todas las cuentas...");
                    banco.aplicarInteresesGlobal();
                    System.out.println("Intereses aplicados.");
                    break;

                case 6:
                    System.out.println("\nGenerando reporte completo...");
                    banco.generarReporte();
                    System.out.println("Saldo total en el banco: " + banco.saldoTotalDelBanco());
                    break;

                case 7:
                    try {
                        System.out.print("\nIngrese numero de cuenta para ver historial: ");
                        int num = Integer.parseInt(input.nextLine());
                        CuentaBancaria c = banco.buscarPorNumero(num);
                        c.imprimirHistorial();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 8:
                    salir = true;
                    System.out.println("Saliendo del sistema.");
                    break;

                default:
                    System.out.println("Opcion invalida, intente de nuevo.");
            }
        }
        
    }
}
