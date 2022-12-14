# imposto-renda
### 1. Considerando que deve-se garantir que o cálculo inclua o valor do IR, que padrão de projeto pode ser aplicado para isto? Lembrando que não necessariamente o padrão precisa ser aplicado, pois podemos resolver de uma forma simples. Tudo depende do problema em questão. Considerando que existem diversas maneiras de calcular o IR para PJ e que precisaríamos trocar uma implementação pela outra de acordo com determinadas condições, isto justificaria a implementação de um padrão.

Eu escolhi por usar o padrão Template Method uma vez que a receita que se tem
para descobrir o total de impostos é sempre a soma do IR (seja ele de PF ou PJ) com os impostos
adicionais que sobrecaem sobre cada uma das implementações concretas de pessoa.

Usei também do Builder para construir mais facilmente as instâncias de `PessoaFisica` e `PessoaJuridica`