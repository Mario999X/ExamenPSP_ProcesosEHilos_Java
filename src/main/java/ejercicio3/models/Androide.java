package ejercicio3.models;

import ejercicio3.monitor.Servidor;

public class Androide implements Runnable {
    private String nombre;
    private int cantidadMinerales;

    private final Servidor servidor;

    private final long ms = 1500;

    public Androide(String nombre, int cantidadMinerales, Servidor servidor) {
        this.nombre = nombre;
        this.cantidadMinerales = cantidadMinerales;
        this.servidor = servidor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadMinerales() {
        return cantidadMinerales;
    }

    public void setCantidadMinerales(int cantidadMinerales) {
        this.cantidadMinerales = cantidadMinerales;
    }

    @Override
    public void run() {
        for (int i = 1; i < cantidadMinerales + 1; i++) {

            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Muestra muestra = new Muestra(i);

            try {
                servidor.put(muestra);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(" ||Androide " + nombre + " -> Muestra: " + muestra);
        }
    }
}
