package com.manoelcampos.impostorenda;

public class Main {
    public static void main(String[] args) {
        Pessoa pessoa;

        pessoa = new PessoaFisica.Builder(1, "Fulano")
                .salarioBruto(10_000.)
                .deducaoPorDependente(0.)
                .deducaoINSS(2_750.)
                .gastosSaude(0.)
                .gastosEducacao(0.)
                .build();

        System.out.printf("Pessoa Física: %.2f\n", pessoa.calculaTotalImpostos());

        pessoa = new PessoaJuridica.Builder(1, "Beltrano S/A")
                .lucro(2_000_000.)
                .valorTotalEmProdutosAdquiridos(15_000.)
                .build();

        System.out.printf("Pessoa Jurídica: %.2f\n", pessoa.calculaTotalImpostos());
    }
}
