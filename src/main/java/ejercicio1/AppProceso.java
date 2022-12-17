package ejercicio1;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class AppProceso {

    public static void main(String[] args) throws IOException {
        // Ubicacion del Jar
        String userDir = System.getProperty("user.dir");
        Path pathJar = Paths.get(userDir + File.separator + "dataJar");

        // Comprobamos el sistema
        boolean comprobador = SOController.init();

        ArrayList<String> listaPersonajes = new ArrayList<>();
        boolean aviso = false;
        String linea;

        // Lectura de teclado
        Scanner input = new Scanner(System.in);
        System.out.print("Escriba el numero de mensajes ha ser captados: ");
        int numeroSolicitado = input.nextInt();
        input.close();

        // Windows
        if (!comprobador) {
            Process pB = new ProcessBuilder("cmd.exe", "/c", "cd " + pathJar + " & java -jar mensajes.jar " + numeroSolicitado).start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(pB.getInputStream()));

            while ((linea = reader.readLine()) != null) {
                System.out.print(".");
                if (linea.toLowerCase().startsWith("darth")) {
                    listaPersonajes.add(linea.toLowerCase());
                }
            }
            // Linux
        } else {
            Process pB = new ProcessBuilder("bash", "-c", "cd " + pathJar + " && java -jar mensajes.jar " + numeroSolicitado).start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(pB.getInputStream()));

            while ((linea = reader.readLine()) != null) {
                System.out.print(".");
                if (linea.toLowerCase().startsWith("darth")) {
                    listaPersonajes.add(linea.toLowerCase());
                }
            }
        }
        //System.out.println("\n" + listaPersonajes);

        // Buscamos a darth vader
        for (int i = 0; i < listaPersonajes.size(); i++) {
            aviso = listaPersonajes.contains("darth vader");
        }

        // Resultado del ejercicio
        System.out.println("\nSiths existentes: " + listaPersonajes.size());
        if (aviso) {
            System.out.println("Darth Vader detectado");
        } else System.out.println("Darth Vader NO detectado");
    }

}
