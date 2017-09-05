package br.edu.utfpr.dainf.csi30.tarefa4.comuns;

/**
 *
 * @author Raphael Zagonel Moletta
 */
public class Ponto {
    protected int linha;
    protected int coluna;

    public Ponto(int linha, int coluna) {
        setLinha(linha);
        setColuna(coluna);
    }

    public Ponto() {
        this(0,0);
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(linha);
        sb.append(", ");
        sb.append(coluna);
        sb.append("]");
                
        return sb.toString();
    }
}
