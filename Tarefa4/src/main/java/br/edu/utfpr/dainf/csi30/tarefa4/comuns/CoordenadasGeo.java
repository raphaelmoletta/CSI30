
package br.edu.utfpr.dainf.csi30.tarefa4.comuns;

/**
 *
 * @author tacla
 */
public interface CoordenadasGeo {
    // coordenadas para ação ir
    public enum Direcao {
        Norte(0), Nordeste(1), Leste(2), Sudeste(3), Sul(4), Sudoeste(5), Oeste(6), Noroeste(7);
    private final int valor;
    
    private Direcao (int valor) {
        this.valor = valor;
        }
    };
}
