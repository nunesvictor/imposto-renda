package com.manoelcampos.impostorenda;

/**
 * @author Manoel Campos da Silva Filho
 */
public abstract class Pessoa {
    private final long id;
    private final String nome;

    public Pessoa(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    protected abstract double calculaImpostoRenda();

    protected abstract double calculaImpostosAdicionais();

    public final double calculaTotalImpostos() {
        return calculaImpostoRenda() + calculaImpostosAdicionais();
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
