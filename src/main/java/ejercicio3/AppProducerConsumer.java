package ejercicio3;

import ejercicio3.models.Androide;
import ejercicio3.models.Terminal;
import ejercicio3.monitor.Servidor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppProducerConsumer {

    private static final int PRODUCCION = 7; // Para que el programa termine, si no, simplemente hariamos un bucle infinito en Androide
    private static final int MAX_MUESTRAS = 5; // Maximo de muestras que tomaran las terminales
    private static final int NUM_HILOS = 4; // El numero de hilos, 1 para cada objeto del problema.

    public static void main(String[] args) throws IOException {
        limpiezaTxt();

        Servidor servidor = new Servidor();

        Androide androide = new Androide("r2d2", PRODUCCION, servidor);
        Androide androide2 = new Androide("bb8", PRODUCCION, servidor);

        Terminal terminal = new Terminal("Luke", servidor, MAX_MUESTRAS);
        Terminal terminal2 = new Terminal("Leia", servidor, MAX_MUESTRAS);

        ExecutorService pool = Executors.newFixedThreadPool(NUM_HILOS);

        pool.execute(androide);
        pool.execute(androide2);

        pool.execute(terminal);
        pool.execute(terminal2);

        pool.shutdown();
    }

    private static void limpiezaTxt() throws IOException {
        String userDir = System.getProperty("user.dir");
        Path pathFile = Paths.get(userDir + File.separator + "data");
        File file = new File(pathFile + File.separator + "manifiesto.txt");

        FileWriter myWriter = new FileWriter(file);
        myWriter.flush();
        myWriter.close();
    }
}

// "Life is too short to program in Java man" - Fireship