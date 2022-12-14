package com.manoelcampos.impostorenda;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PessoaJuridica extends Pessoa {
    static class Builder {
        private final long id;
        private final String nome;
        private double lucro;
        private double valorTotalEmProdutosAdquiridos;

        public Builder(long id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public Builder lucro(double lucro) {
            this.lucro = DoubleObjects.requiresNonNegative(lucro);
            return this;
        }

        public Builder valorTotalEmProdutosAdquiridos(double valorTotalEmProdutosAdquiridos) {
            this.valorTotalEmProdutosAdquiridos = DoubleObjects.requiresNonNegative(valorTotalEmProdutosAdquiridos);
            return this;
        }

        public PessoaJuridica build() {
            return new PessoaJuridica(this);
        }
    }

    private static final Logger LOGGER = Logger.getLogger(PessoaJuridica.class.getName());

    private static final double ALIQUOTA_CSLL = .09;
    private static final double ALIQUOTA_EXCEDENTE_IR = .20;
    private static final double ALIQUOTA_ICMS = .05;
    private static final double ALIQUOTA_LUCRO_REAL = .15;
    private static final double TETO_ISENCAO_EXCEDENTE_IR = 20_000.;

    private final double lucro;
    private final double valorTotalEmProdutosAdquiridos;

    private PessoaJuridica(Builder builder) {
        super(builder.id, builder.nome);
        this.lucro = builder.lucro;
        this.valorTotalEmProdutosAdquiridos = builder.valorTotalEmProdutosAdquiridos;
    }

    @Override
    public double calculaImpostoRenda() {
        LOGGER.log(Level.INFO, "calculando IRPJ");
        double impostoDevido = lucro * ALIQUOTA_LUCRO_REAL;

        if (impostoDevido > TETO_ISENCAO_EXCEDENTE_IR) {
            LOGGER.log(Level.ALL, "calculando com excedente do lucro real");
            impostoDevido += impostoDevido * ALIQUOTA_EXCEDENTE_IR;
        }

        return impostoDevido;
    }

    @Override
    public double calculaImpostosAdicionais() {
        LOGGER.log(Level.INFO, "calculando Impostos Adicionais");
        return calculaCSLL() + calculaICMS();
    }

    private double calculaCSLL() {
        LOGGER.log(Level.INFO, "calculando CSLL");
        return lucro * ALIQUOTA_CSLL;
    }

    private double calculaICMS() {
        LOGGER.log(Level.INFO, "calculando ICMS");
        return valorTotalEmProdutosAdquiridos * ALIQUOTA_ICMS;
    }

    @Override
    public String toString() {
        return "PessoaJuridica{" +
                "lucro=" + lucro +
                ", valorTotalEmProdutosAdquiridos=" + valorTotalEmProdutosAdquiridos +
                "} " + super.toString();
    }
}
