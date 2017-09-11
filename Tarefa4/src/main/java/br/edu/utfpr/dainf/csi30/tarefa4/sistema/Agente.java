package br.edu.utfpr.dainf.csi30.tarefa4.sistema;

import br.edu.utfpr.dainf.csi30.tarefa4.ambiente.Ambiente;
import br.edu.utfpr.dainf.csi30.tarefa4.comuns.CoordenadasGeo;
import br.edu.utfpr.dainf.csi30.tarefa4.comuns.No;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author tacla
 */
public class Agente implements CoordenadasGeo {

    public enum Heuristica {
        euclidiana, coluna
    }
    protected Ambiente ambiente;
    protected Problema problema;   // Problema estah na 'mente' do agente
    private int passo = 0;

    //colocar aqui plano armazenado de acoes que será executado 
    // passo a passo em deliberar
    protected int[] plano;

    public Agente(Ambiente ambiente, Problema problema) {
        this.ambiente = ambiente;
        this.problema = problema;
    }
    
    private boolean contem(Collection<No> colecao, No no) {
        return colecao.stream().anyMatch((n) -> (no.getColuna() == n.getColuna() && no.getLinha() == n.getLinha()));
    }

    public boolean solucionarCustoUniforme() {
        No no = problema.estadoInicial;
        System.out.println("No inicial: " + no.toString());
        Queue<No> fronteira = new PriorityQueue<>();
        fronteira.add(no);
        Queue<No> explorado = new PriorityQueue<>();

        while (!fronteira.isEmpty()) {
            no = fronteira.poll();
            if (problema.testarObjetivo(no)) {
                System.out.println("Custo total: " + no.getCusto());
                
                return true;
            }
            System.out.println("add Explorado nó: " + no.toString());
            explorado.add(no);
            List<No> acao = problema.calcularAcoesPossiveis(no);
            acao.forEach((n) -> {
                System.out.println("Cada nó: " + n.toString());
                if (!(contem(explorado, n) || contem(fronteira, n))) {
                    System.out.println("add Fronteira nó: " + n.toString());
                    fronteira.add(n);
                } else if (fronteira.contains(n)) {
                    fronteira.forEach((No e) -> {
                        if (e.getColuna() == n.getColuna()
                                && e.getLinha() == n.getLinha()
                                && e.getCusto() < n.getCusto()) {
                            e.setCusto(n.getCusto());
                        }
                    });
                }
            });
        }

        return false;
    }

    public boolean solucionarAEstrela(Heuristica heuristica) {
        return false;
    }

    /* 
     * Escolhe qual ação será executada em um ciclo de raciocínio
     */
    public int deliberar() {
        // nao atingiu objetivo e ha acoesPossiveis a serem executadas no plano
        if (!problema.testarObjetivo(problema.estadoAtual)) {
            System.out.println("estado atual: " + problema.estadoAtual.toString());
            System.out.print("açoes possiveis: ");
            for (int i = 0; i < plano.length; i++) {
                //todo if (ap.get(i)!=-1)
                //todo System.out.print(acao[i]+" ");
            }
            //todo System.out.println("\nct = "+ custo + " de " + (plano.length-1) + " ação=" + acao[plano[custo]] + "\n");
            ir(Direcao.values()[plano[passo]]);

            passo++;
        } else {
            return (-1);
        }

        return 1;
    }

    /*
    * Atuador: solicita ao agente 'fisico' executar a acao. Atualiza estado.
     */
    public int ir(Direcao direcao) {
        ambiente.ir(direcao);
        problema.calcularSucessores(problema.estadoAtual, direcao); // atualiza estado do agente
        return 1;
    }

    //Sensor: lê posição atual do agente 
    public No lerSensor() {
        return problema.estadoAtual;
    }

    public void executar() {
        // Ciclo de execucao do sistema
        ambiente.desenhar();
        while (deliberar() != -1) {
            ambiente.desenhar();
        }
    }
}
