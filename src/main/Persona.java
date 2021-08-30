package main;

public class Persona {
    private Consumo consumos[] = new Consumo[20];
    private String nombre;
    private String cedula;

    public Persona() {
    }

    public Persona(Consumo[] consumos, String nombre) {
        this.consumos = consumos;
        this.nombre = nombre;
    }

    public Consumo[] getConsumos() {
        return consumos;
    }

    public void setConsumos(Consumo[] consumos) {
        this.consumos = consumos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
