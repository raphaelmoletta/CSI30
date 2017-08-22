/* 
 * @author Tacla
 * Classe para representar um problema no labirinto; sao representacoes que
 * ficam na 'mente' do agente
 */
package br.edu.utfpr.dainf.csi30.tarefa2.sistema;

import br.edu.utfpr.dainf.csi30.tarefa2.comuns.CoordenadasGeo;
import br.edu.utfpr.dainf.csi30.tarefa2.comuns.Labirinto;


public class Problema implements CoordenadasGeo {

    protected int estIni[] = new int[2];  // estado inicial [linha, coluna] = pos do agente
    protected int estObj[] = new int[2];  // estado objetivo [linha, coluna]
    protected int estAtu[] = new int[2];  // estado atual [linha, coluna]
    protected Labirinto creLab;           // crença do agente sobre como eh o labirinto
    protected int maxLin, maxCol;

    /*
     * O que o agente crê sobre o labirinto
     */
    public void criarLabirinto() {
        maxLin = 8;
        maxCol = 8;
        creLab = new Labirinto(maxLin, maxCol);
        
        creLab.porParedeVertical(0, 1, 1);
        creLab.porParedeVertical(3, 4, 1);
        creLab.porParedeVertical(2, 4, 2);
        creLab.porParedeVertical(1, 2, 3);
        creLab.porParedeVertical(4, 6, 4);
        creLab.porParedeVertical(4, 5, 5);
        creLab.porParedeVertical(3, 6, 7);
        creLab.porParedeHorizontal(2, 4, 2);
        creLab.porParedeHorizontal(0, 2, 4);
        
        
    }

    /*
     * Define estado inicial
     */
    protected void defEstIni(int linha, int coluna) {
        estIni[0] = linha;
        estIni[1] = coluna;
    }

    /*
     * Define estado objetivo
     */
    protected void defEstObj(int linha, int coluna) {
        estObj[0] = linha;
        estObj[1] = coluna;
    }
    /*
     * Define estado atual
     */

    protected void defEstAtu(int linha, int coluna) {
        estAtu[0] = linha;
        estAtu[1] = coluna;
    }
    /*
     * Funcao sucessora: recebe um estado '(lin, col)' e calcula o estado atual
     * que resulta da execucao da acao = {N, NE, L, SE, S, SO, O, NO}
     */

    protected int[] suc(int[] estado, int acao) {
        
        int ap[] = acaoPossivel(acao, estado);
        defEstAtu(ap[0], ap[1]);
        // atribui nova posicao ao estado atual
        // defEstAtu(lin, col);
        return estAtu;
    }

    /*
     * Retorna as acoesPossiveis possiveis de serem executadas em um estado 
     * O valor retornado é um vetor de inteiros. Se o valor da posicao é -1
     * então a ação correspondente não pode ser executada, caso contrario valera 1.
     * Por exemplo, 
     * [-1, -1, -1, 1, 1, -1, -1, -1] indica apenas que S e SO podem ser executadas.
     */
    protected int[] acoesPossiveis(int[] estado) {
        int acoes[] = new int[8];
        
        for(int i = 0 ; i < 7; i++){
            
            int n[] = acaoPossivel(i, estado);
            acoes[i] = (n[0] >= 0 && n[0] < maxCol &&
                n[1] >= 0 && n[1] < maxLin &&
                creLab.parede[n[0]][n[1]] == 0)
                ? 1 : -1;
        }
   
        return acoes;
    }

    protected int[] acaoPossivel(int direcao, int[] estado) {
        int l = estado[0], c = estado[1];
        switch(direcao) {
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
        int novos[] = new int[2];
        novos[0] = l;
        novos[1] = c;
        return novos;
        
    }
    /*
     * Retorna true quando estado atual = estado objetivo, caso contrario retorna falso
     */
    protected boolean testeObjetivo() {
        return estAtu[0] == estObj[0] && estAtu[1] == estObj[1];
    }
}
