import java.util.Calendar;


public class CuentaAhorro extends CuentaBancaria {

    //Atributos

    private Calendar ultimaModif;
    private int retirosRealizados = 0;
    private static final int MAX_RETIROS_MES=3;


    public CuentaAhorro(int cuenta, String cliente){
        super(cuenta, cliente, 0.03);
        this.ultimaModif = Calendar.getInstance();
    }

    public boolean status(){

        Calendar fechaActual=Calendar.getInstance();
        fechaActual.add(Calendar.MONTH, -6);
        return ultimaModif.after(fechaActual);

    }

    public void aplicarIntereses(){
        if (status()) {
            double intereses = saldo*tasa;
            depositar(intereses);
         
        }
    }
    //hacer retirar, evaluar estatus y cant max de retiro, tomar en consideracion ult fecha modifica y cuantos retiros lleva

}
