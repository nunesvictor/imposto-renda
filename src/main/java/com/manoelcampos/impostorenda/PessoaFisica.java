package com.manoelcampos.impostorenda;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.manoelcampos.impostorenda.DoubleObjects.requiresNonNegative;

/**
 * @author Manoel Campos da Silva Filho
 */
public class PessoaFisica extends Pessoa {
    static class Builder {
        private final long id;
        private final String nome;
        private double salarioBruto;
        private double deducaoPorDependente;
        private double deducaoINSS;
        private double gastosSaude;
        private double gastosEducacao;

        public Builder(long id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public Builder salarioBruto(double salarioBruto) {
            this.salarioBruto = requiresNonNegative(salarioBruto);
            return this;
        }

        public Builder deducaoPorDependente(double deducaoPorDependente) {
            this.deducaoPorDependente = requiresNonNegative(deducaoPorDependente);
            return this;
        }

        public Builder deducaoINSS(double deducaoINSS) {
            this.deducaoINSS = requiresNonNegative(deducaoINSS);
            return this;
        }

        public Builder gastosSaude(double gastosSaude) {
            this.gastosSaude = requiresNonNegative(gastosSaude);
            return this;
        }

        public Builder gastosEducacao(double gastosEducacao) {
            this.gastosEducacao = requiresNonNegative(gastosEducacao);
            return this;
        }

        public PessoaFisica build() {
            return new PessoaFisica(this);
        }
    }

    private static final Logger LOGGER = Logger.getLogger(PessoaFisica.class.getName());

    private static final double ALIQUOTA_INSS = .11;
    private static final double ALIQUOTA_IRPF = .11;

    private final double salarioBruto;
    private final double deducaoPorDependente;
    private final double deducaoINSS;
    private final double gastosSaude;
    private final double gastosEducacao;

    private PessoaFisica(Builder builder) {
        super(builder.id, builder.nome);
        this.salarioBruto = builder.salarioBruto;
        this.deducaoPorDependente = builder.deducaoPorDependente;
        this.deducaoINSS = builder.deducaoINSS;
        this.gastosSaude = builder.gastosSaude;
        this.gastosEducacao = builder.gastosEducacao;
    }

    @Override
    public double calculaImpostoRenda() {
        LOGGER.log(Level.INFO, "calculando IRPF");
        return ((salarioBruto - deducaoPorDependente - deducaoINSS) * ALIQUOTA_IRPF) - (gastosSaude + gastosEducacao);
    }

    @Override
    public double calculaImpostosAdicionais() {
        LOGGER.log(Level.INFO, "calculando Impostos Adicionais");
        return calculaINSS();
    }

    private double calculaINSS() {
        LOGGER.log(Level.INFO, "calculando INSS");
        return salarioBruto * ALIQUOTA_INSS;
    }

    @Override
    public String toString() {
        return "PessoaFisica{" +
                "salarioBruto=" + salarioBruto +
                ", deducaoPorDependente=" + deducaoPorDependente +
                ", deducaoINSS=" + deducaoINSS +
                ", gastosSaude=" + gastosSaude +
                ", gastosEducacao=" + gastosEducacao +
                "} " + super.toString();
    }
}
