package br.edu.utfpr.dainf.csi30.tarefa4.ambiente;

import br.edu.utfpr.dainf.csi30.tarefa4.comuns.InterfaceExibicao;

/**
 *
 * @author tacla Desenha o ambiente (o que está representado no Model) em
 * formato texto
 */
public class Exibicao implements InterfaceExibicao {

    @Override
    public void desenhar(Ambiente ambiente) {
        for (int lin = 0; lin < ambiente.lerMaximos().getLinha(); lin++) {
            for (int col = 0; col < ambiente.lerMaximos().getColuna(); col++) {
                System.out.print("+---");
            }
            System.out.print("+\n");
            for (int col = 0; col < ambiente.lerMaximos().getLinha(); col++) {
                if (ambiente.labirinto.parede[lin][col] == 1) {
                    System.out.print("|XXX");  // desenha parede
                } else if (ambiente.lerPosicaoAgente().getColuna() == col && ambiente.lerPosicaoAgente().getLinha() == lin) {
                    System.out.print("| A ");  // desenha agente
                } else {
                    System.out.print("|   ");  // posicao vazia
                }
            }
            System.out.print("|");
            if (lin == (ambiente.lerMaximos().getLinha() - 1)) {
                System.out.print("\n");
                for (int x = 0; x < ambiente.lerMaximos().getColuna(); x++) {
                    System.out.print("+---");
                }
                System.out.println("+\n");
            }
            System.out.print("\n");
        }
    }
}
