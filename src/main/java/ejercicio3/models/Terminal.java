package ejercicio3.models;

import ejercicio3.monitor.Servidor;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Terminal implements Runnable {
    private final String nombre;
    private final Servidor servidor;
    private final int maxMuestras;

    private final int ms = (int) (1000 + Math.random() * 1500);

    boolean cerrarTerminal = false;
    int muestrasTomadas = 0;

    public Terminal(String nombre, Servidor servidor, int maxMuestras) {
        this.nombre = nombre;
        this.servidor = servidor;
        this.maxMuestras = maxMuestras;
    }

    @Override
    public void run() {
        String userDir = System.getProperty("user.dir");
        Path pathFile = Paths.get(userDir + File.separator + "data");
        File file = new File(pathFile + File.separator + "manifiesto.txt");

        while (!cerrarTerminal) {
            // Importante que el sleep este al comienzo
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Muestra muestra;
            try {
                muestra = servidor.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("La terminal: " + nombre + " ha recogido la muestra: " + muestra
                    + "\n------------------------------------------------");
            muestrasTomadas++;

            if (muestra.getPorcentajePureza() > 60) {
                System.out.println("\t-La terminal: " + nombre + " escribiendo en fichero...");
                try {
                    FileWriter myWriter = new FileWriter(file, true);

                    myWriter.write("La terminal: " + nombre + " ha recogido la muestra: " + muestra + "\n");
                    myWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

            if (muestrasTomadas == maxMuestras) {
                System.out.println("\t \t -La terminal: " + nombre + " ha llegado a su limite.");
                cerrarTerminal = true;
            }
        }
    }
}
