package ejercicio3.models;

import java.time.LocalDate;

public class Muestra {
    private int Id;
    private int porcentajePureza;
    private String fecha;

    public Muestra(int id) {
        Id = id;
        this.porcentajePureza = (int) (10 + Math.random() * 80);
        this.fecha = LocalDate.now().toString();
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPorcentajePureza() {
        return porcentajePureza;
    }

    public void setPorcentajePureza(int porcentajePureza) {
        this.porcentajePureza = porcentajePureza;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Muestra{" +
                "Id=" + Id +
                ", porcentajePureza=" + porcentajePureza +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
