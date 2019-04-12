/* 
 * @author Tacla
 * Classe para representar um problema no labirinto; sao representacoes que
 * ficam na 'mente' do agente
 */
package br.edu.utfpr.dainf.csi30.tarefa4.sistema;

import br.edu.utfpr.dainf.csi30.tarefa4.comuns.CoordenadasGeo;
import br.edu.utfpr.dainf.csi30.tarefa4.comuns.Labirinto;
import br.edu.utfpr.dainf.csi30.tarefa4.comuns.No;
import br.edu.utfpr.dainf.csi30.tarefa4.sistema.Agente.Metodo;
import java.util.ArrayList;
import java.util.List;


public class Problema implements CoordenadasGeo {

    protected No estadoInicial = new No();  // estado inicial [linha, coluna] = pos do agente
    protected No estadoObjetivo = new No();  // estado objetivo [linha, coluna]
    protected No estadoAtual = new No();  // estado atual [linha, coluna]
    protected Labirinto creLab;           // crença do agente sobre como eh o labirinto

    public Problema(Labirinto labrinto) {
        creLab = labrinto;
    }

    /*
     * Define estado inicial
     */
    protected void setEstadoInicial(No ponto) {
        estadoInicial = ponto;
    }

    /*
     * Define estado objetivo
     */
    protected void setEstadoObjetivo(No ponto) {
        estadoObjetivo = ponto;
    }
    /*
     * Define estado atual
     */

    protected void setEstadoAtual(No ponto) {
        estadoAtual = ponto;
    }
    /*
     * Funcao sucessora: recebe um estado '(lin, col)' e calcula o estado atual
     * que resulta da execucao da acao = {N, NE, L, SE, S, SO, O, NO}
     */

    protected No calcularSucessores(No no, Direcao acao) {
        estadoAtual = calcularAcaoPossivel(no, acao);
        // atribui nova posicao ao estado atual
        // defEstAtu(lin, col);
        return estadoAtual;
    }

    /*
     * Retorna as acoesPossiveis possiveis de serem executadas em um estado 
     * O valor retornado é um vetor de inteiros. Se o valor da posicao é -1
     * então a ação correspondente não pode ser executada, caso contrario valera 1.
     * Por exemplo, 
     * [-1, -1, -1, 1, 1, -1, -1, -1] indica apenas que S e SO podem ser executadas.
     */
    protected List<No> calcularAcoesPossiveis(No no, Metodo metodo) {
        
        List<No> acoes = new ArrayList<>();
        double heuristica = 0;
        for(int i = 0 ; i < 7; i++){
            
            No ponto = calcularAcaoPossivel(no, Direcao.values()[i]);
            if(!(ponto.getColuna() == no.getColuna() &&
                    ponto.getLinha() == no.getLinha()) && 
              ponto.getColuna() >= 0 && ponto.getColuna() < creLab.getColunas() &&
                ponto.getLinha() >= 0 && ponto.getLinha() < creLab.getLinhas() &&
                creLab.parede[ponto.getLinha()][ponto.getColuna()] == 0) {
                double custo = (i % 2 == 0) ? 1 : 1.5;
                switch (metodo) {
                    case AEstrelaColuna :
                        heuristica = Math.abs(estadoObjetivo.getColuna() - ponto.getColuna());
                        break;
                    case AEstrelaEuclidiana :
                        heuristica = Math.sqrt(Math.pow(estadoObjetivo.getColuna() - ponto.getColuna(),2) + 
                                Math.pow(estadoObjetivo.getLinha() - ponto.getLinha(), 2));
                        break;
                }
                ponto.setCusto(custo + no.getCusto() + heuristica);
                acoes.add(ponto);
            }
        }
   
        return acoes;
    }

    protected No calcularAcaoPossivel(No no, Direcao direcao) {
        int l = no.getLinha(), c = no.getColuna();
        switch(direcao.ordinal()) {
            case 0 :
                l--;
                break;
            case 1 :
                l--;
                c++;
                break;
            case 2 :
                c++;
                break;
            case 3 :
                c++;
                l++;
                break;
            case 4 :
                l++;
                break;
            case 5 :
                l++;
                c--;
                break;
            case 6 :
                c--;
                break;
            case 7 :
                c--;
                l--;
                break;
        }
        
        return new No(l,c);
        
    }
    /*
     * Retorna true quando estado atual = estado objetivo, caso contrario retorna falso
     */
    protected boolean testarObjetivo(No no) {
        return estadoObjetivo.igual(no);
    }
}