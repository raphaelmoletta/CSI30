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

    public enum Metodo {
        CustoUniforme, AEstrelaEuclidiana, AEstrelaColuna
    }
    protected Ambiente ambiente;
    protected Problema problema;   // Problema estah na 'mente' do agente

    //colocar aqui plano armazenado de acoes que será executado 
    // passo a passo em deliberar
    protected int[] plano;
    protected int passo = 0;

    public Agente(Ambiente ambiente, Problema problema) {
        this.ambiente = ambiente;
        this.problema = problema;
    }

    private boolean contem(Collection<No> colecao, No no) {
        return colecao.stream().anyMatch((n) -> (no.getColuna() == n.getColuna() && no.getLinha() == n.getLinha()));
    }
        
    public boolean solucionar(Metodo metodo) {
        No no = problema.estadoInicial;
        Queue<No> fronteira = new PriorityQueue<>();
        fronteira.add(no);
        Queue<No> explorado = new PriorityQueue<>();
        while (!fronteira.isEmpty()) {
            no = fronteira.poll();
            if (problema.testarObjetivo(no)) {
                gerarResultado(no);
                return true;
            }

            explorado.add(no);
            List<No> acao = problema.calcularAcoesPossiveis(no, metodo);
            for (No n : acao) {
                if (!(contem(explorado, n) || contem(fronteira, n))) {
                    n.setDe(no);
                    fronteira.add(n);
                } else if (fronteira.contains(n)) {
                    fronteira.forEach((No e) -> {
                        if (e.igual(n)) {
                            fronteira.remove(e);
                            fronteira.add(n);
                        }
                    });
                }
            }
        }

        return false;
    }

    public void gerarResultado(No no) {
        Queue<No> resultado = new PriorityQueue<>();
        while (no.getDe() != null) {
            resultado.add(no);
            no = no.getDe();
        }
        resultado.add(no);
        plano = new int[resultado.size()];
        no = resultado.poll();
        No next;
        int l, c, size = resultado.size();
        for (int i = 0; i < size; i++) {
            next = resultado.poll();
            l = next.getLinha() - no.getLinha();
            c = next.getColuna() - no.getColuna();

            if (c > 0) {
                if (l > 0) {
                    plano[i] = 3;
                } else if (l < 0) {
                    plano[i] = 1;
                } else {
                    plano[i] = 2;
                }
            } else if (c < 0) {
                if (l > 0) {
                    plano[i] = 7;
                } else if (l < 0) {
                    plano[i] = 5;
                } else {
                    plano[i] = 2;
                }
            } else {
                if (l < 0) {
                    plano[i] = 0;
                } else if (l > 0) {
                    plano[i] = 4;
                }
            }
            no = next;
        }
    }
    /* 
     * Escolhe qual ação será executada em um ciclo de raciocínio
     */
    public int deliberar() {
        // nao atingiu objetivo e ha acoesPossiveis a serem executadas no plano
        if (!problema.testarObjetivo(problema.estadoAtual)) {
            System.out.println("estado atual: " + problema.estadoAtual.toString());
            System.out.print("açoes possiveis: ");
            problema.calcularAcoesPossiveis(problema.estadoAtual, Metodo.CustoUniforme).forEach((a) -> {
                System.out.println(a.toString());
            });
            
            System.out.println("Ação=" + Direcao.values()[plano[passo]] + "\n");
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
