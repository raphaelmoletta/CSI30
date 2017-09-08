package br.edu.utfpr.dainf.csi30.tarefa4.comuns;

/**
 *
 * @author rapha
 */
public class No extends Ponto {
    protected double custo;

    public No() {
        this.custo = 0;
    }
    
    public No(Ponto ponto) {
        super(ponto.getLinha(), ponto.getColuna());
        this.custo = 0;
    }
    
    public No(Ponto ponto, double custo) {
        super(ponto.getLinha(), ponto.getColuna());
        this.custo = custo;
    }
    
    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }
}
