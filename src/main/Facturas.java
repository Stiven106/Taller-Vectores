package main;

public class Facturas {

    private String concepto;
    private int valor;
    private String nPersonaAutorizada;

    public Facturas(String concepto, int valor, String nPersonaAutorizada) {
        this.concepto = concepto;
        this.valor = valor;
        this.nPersonaAutorizada = nPersonaAutorizada;
    }
}
