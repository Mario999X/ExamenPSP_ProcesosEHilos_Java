package ejercicio2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AppAsincronia {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> lecturaLineas = new ArrayList<>();
        ExecutorService pool = Executors.newSingleThreadExecutor();

        Callable<Void> task = () -> {
            URL url = new URL("https://es.wikipedia.org/wiki/Star_Wars");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String linea;

            while ((linea = reader.readLine()) != null) {
                if (linea.toLowerCase().contains("darth vader")) {
                    lecturaLineas.add(linea);
                }
            }
            System.out.println("\nNumero de veces que aparece Darth Vader: " + lecturaLineas.size());
            return null;
        };

        Future<Void> future = pool.submit(task);
        while (!future.isDone()) {
            System.out.print("Esperando...");
            Thread.sleep(250);
        }
        pool.shutdown();
    }
}
