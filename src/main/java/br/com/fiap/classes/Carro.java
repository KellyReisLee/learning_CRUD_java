package br.com.fiap.classes;

public class Carro {
    public int id;
    public int ano;
    public String modelo;

    public Carro(int id, int ano, String modelo) {
        this.id = id;
        this.ano = ano;
        this.modelo = modelo;
    }

    public Carro(int ano, String modelo) {
        this.ano = ano;
        this.modelo = modelo;
    }
}
