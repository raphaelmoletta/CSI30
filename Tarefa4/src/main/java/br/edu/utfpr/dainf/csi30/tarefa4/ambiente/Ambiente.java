/**
 *
 * @author tacla Modela o ambiente 'simulado' composto por um labirinto com
 * paredes e por um agente
 */

package br.edu.utfpr.dainf.csi30.tarefa4.ambiente;

import br.edu.utfpr.dainf.csi30.tarefa4.comuns.CoordenadasGeo;
import br.edu.utfpr.dainf.csi30.tarefa4.comuns.InterfaceExibicao;
import br.edu.utfpr.dainf.csi30.tarefa4.comuns.Labirinto;
import br.edu.utfpr.dainf.csi30.tarefa4.comuns.No;

public class Ambiente implements CoordenadasGeo {
    protected No posicaoAgente;   // pos do agente {linha, coluna)
    protected Labirinto labirinto;   // representacao do labirinto
    protected InterfaceExibicao exibicao;     

    public Ambiente(Labirinto labirinto, InterfaceExibicao exibicao, No posicaoAgente) {
        this.labirinto = labirinto;
        this.exibicao = exibicao;
        this.posicaoAgente = posicaoAgente;
        // instancia a visualizacao do ambiente associando-a ao model
    }
    
    public void desenhar() {
        exibicao.desenhar(this);
    }
    
    public No lerPosicaoAgente() {
        return posicaoAgente;
    }
    
    public No lerMaximos() {
        return this.labirinto.getMaximos();
    }

    /**
     * @param direcao int de 0 a 7 iniciando por N no sentido horário
     */
    public void ir(Direcao direcao) {
        int lin = posicaoAgente.getLinha();
        int col = posicaoAgente.getColuna();
        switch (direcao.ordinal()) {
            case 0:
                lin--;
                break;
            case 1:
                col++;
                lin--;
                break;
            case 2:
                col++;
                break;
            case 3:
                col++;
                lin++;
                break;
            case 4:
                lin++;
                break;
            case 5:
                col--;
                lin++;
                break;
            case 6:
                col--;
                break;
            case 7:
                col--;
                lin--;
                break;
        }
        // verifica se está fora do grid
        if (col < 0 || col >= labirinto.getColunas() || lin < 0 || lin >= labirinto.getLinhas() 
            // verifica se bateu em algum obstaculo
            || labirinto.parede[lin][col] == 1) {
            lin = posicaoAgente.getLinha();
            col = posicaoAgente.getColuna();  // fica na posicao atual
            
        }

        // atribui nova posicao
        posicaoAgente.setLinha(lin);
        posicaoAgente.setColuna(col);
    }
}