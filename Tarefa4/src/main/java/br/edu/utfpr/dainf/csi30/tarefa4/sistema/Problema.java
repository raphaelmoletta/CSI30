/* 
 * @author Tacla
 * Classe para representar um problema no labirinto; sao representacoes que
 * ficam na 'mente' do agente
 */
package br.edu.utfpr.dainf.csi30.tarefa4.sistema;

import br.edu.utfpr.dainf.csi30.tarefa4.comuns.CoordenadasGeo;
import br.edu.utfpr.dainf.csi30.tarefa4.comuns.Labirinto;
import br.edu.utfpr.dainf.csi30.tarefa4.comuns.Ponto;
import java.util.ArrayList;
import java.util.List;


public class Problema implements CoordenadasGeo {

    protected Ponto estadoInicial = new Ponto();  // estado inicial [linha, coluna] = pos do agente
    protected Ponto estadoObjetivo = new Ponto();  // estado objetivo [linha, coluna]
    protected Ponto estadoAtual = new Ponto();  // estado atual [linha, coluna]
    protected Labirinto creLab;           // crença do agente sobre como eh o labirinto

    public Problema(Labirinto labrinto) {
        creLab = labrinto;
    }

    /*
     * Define estado inicial
     */
    protected void setEstadoInicial(Ponto ponto) {
        estadoInicial = ponto;
    }

    /*
     * Define estado objetivo
     */
    protected void setEstadoObjetivo(Ponto ponto) {
        estadoObjetivo = ponto;
    }
    /*
     * Define estado atual
     */

    protected void setEstadoAtual(Ponto ponto) {
        estadoAtual = ponto;
    }
    /*
     * Funcao sucessora: recebe um estado '(lin, col)' e calcula o estado atual
     * que resulta da execucao da acao = {N, NE, L, SE, S, SO, O, NO}
     */

    protected Ponto calcularSucessores(Direcao acao) {
        estadoAtual = calcularAcaoPossivel(acao);
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
    protected List<Ponto> calcularAcoesPossiveis() {
        List<Ponto> acoes = new ArrayList<>();
        
        for(int i = 0 ; i < 7; i++){
            
            Ponto ponto = calcularAcaoPossivel(Direcao.values()[i]);
            if(!(ponto.getColuna() == estadoAtual.getColuna() &&
                    ponto.getLinha() == estadoAtual.getLinha()) && 
              ponto.getColuna() >= 0 && ponto.getColuna() < creLab.getColunas() &&
                ponto.getLinha() >= 0 && ponto.getLinha() < creLab.getLinhas() &&
                creLab.parede[ponto.getLinha()][ponto.getColuna()] == 0) {
                acoes.add(ponto);
            }
        }
   
        return acoes;
    }

    protected Ponto calcularAcaoPossivel(Direcao direcao) {
        int l = estadoAtual.getLinha(), c = estadoAtual.getColuna();
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
        
        return new Ponto(l,c);
        
    }
    /*
     * Retorna true quando estado atual = estado objetivo, caso contrario retorna falso
     */
    protected boolean testarObjetivo() {
        return estadoAtual.getColuna() == estadoObjetivo.getColuna() &&
                estadoAtual.getLinha() == estadoObjetivo.getLinha();
    }
}