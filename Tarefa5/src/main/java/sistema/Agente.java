package sistema;

import ambiente.*;
import problema.*;
import comuns.*;
import static comuns.PontosCardeais.*;

/**
 *
 * @author tacla
 */
public class Agente implements PontosCardeais {

    /* referência ao ambiente para poder atuar no mesmo*/
    Model model;
    Problema prob;
    Estado estAtu; // guarda o estado atual (posição atual do agente)
    double custo;

    public Agente(Model m) {
        this.model = m;
        prob = new Problema();
        prob.criarLabirinto(9, 9);
        prob.crencaLabir.porParedeVertical(0, 1, 0);
        prob.crencaLabir.porParedeVertical(0, 0, 1);
        prob.crencaLabir.porParedeVertical(5, 8, 1);
        prob.crencaLabir.porParedeVertical(5, 5, 2);
        prob.crencaLabir.porParedeVertical(8, 8, 2);
        prob.crencaLabir.porParedeHorizontal(4, 7, 0);
        prob.crencaLabir.porParedeHorizontal(7, 7, 1);
        prob.crencaLabir.porParedeHorizontal(3, 5, 2);
        prob.crencaLabir.porParedeHorizontal(3, 5, 3);
        prob.crencaLabir.porParedeHorizontal(7, 7, 3);
        prob.crencaLabir.porParedeVertical(6, 7, 4);
        prob.crencaLabir.porParedeVertical(5, 6, 5);
        prob.crencaLabir.porParedeVertical(5, 7, 7);

        // Estado inicial, objetivo e atual
        prob.defEstIni(8, 0);
        prob.defEstObj(2, 8);
        this.estAtu = prob.estIni;
        this.custo = 0;
    }

    /**
     * Escolhe qual ação (UMA E SOMENTE UMA) será executada em um ciclo de
     * raciocínio
     *
     * @return 1 enquanto o plano não acabar; -1 quando acabar
     */
    public int deliberar() {
        double ap[];
        ap = prob.acoesPossiveis(estAtu);
        if (!prob.testeObjetivo(estAtu)) {
            System.out.println("estado atual: " + estAtu.getLin() + "," + estAtu.getCol());
            System.out.print("açoes possiveis: {");
            for (int i = 0; i < ap.length; i++) {
                if (ap[i] != -1) {
                    System.out.print(acao[i] + " ");
                }
            }
            int dir = -1;
            double min = Double.MAX_VALUE;
            for (int i = 0; i < 8; i++) {
                if (ap[i] >= 0) {
                    double a = 0, b = 0;
                    switch (i) {
                        case 0:
                            a = (estAtu.getLin() - 1);
                            b = (estAtu.getCol());
                            break;
                        case 1:
                            a = (estAtu.getLin() - 1);
                            b = (estAtu.getCol() + 1);
                            break;
                        case 2:
                            a = (estAtu.getLin());
                            b = (estAtu.getCol() + 1);
                            break;
                        case 3:
                            a = (estAtu.getLin() + 1);
                            b = (estAtu.getCol() + 1);
                            break;
                        case 4:
                            a = (estAtu.getLin() + 1);
                            b = (estAtu.getCol());
                            break;
                        case 5:
                            a = (estAtu.getLin() + 1);
                            b = (estAtu.getCol() - 1);
                            break;
                        case 6:
                            a = (estAtu.getLin());
                            b = (estAtu.getCol() - 1);
                            break;
                        case 7:
                            a = (estAtu.getLin() - 1);
                            b = (estAtu.getCol() - 1);
                            break;
                    }
                    a -= prob.estObj.getLin();
                    b -= prob.estObj.getCol();
                    double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
                    if (c + ap[i] < min) {
                        dir = i;
                        min = c;
                    }
                }
            }

            custo += ap[dir];
            executarIr(dir);
            System.out.println("}\nação escolhida=" + dir);
            System.out.println("custo ate o momento: " + custo);
            System.out.println("**************************\n\n");

            // atualiza estado atual - sabendo que o ambiente eh deterministico
            estAtu = prob.suc(estAtu, dir);
        }
        return 1;
    }

    /**
     * Funciona como um driver ou um atuador: envia o comando para agente físico
     * ou simulado (no nosso caso, simulado)
     *
     * @param direcao N NE S SE ...
     * @return 1 se ok ou -1 se falha
     */
    public int executarIr(int direcao) {
        model.ir(direcao);
        return 1;
    }

    // Sensor
    public Estado sensorPosicao() {
        int pos[];
        pos = model.lerPos();
        return new Estado(pos[0], pos[1]);
    }
}
