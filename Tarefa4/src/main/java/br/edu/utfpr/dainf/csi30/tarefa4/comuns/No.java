package br.edu.utfpr.dainf.csi30.tarefa4.comuns;

/**
 *
 * @author Raphael Zagonel Moletta
 */
public final class No implements Comparable<No> {

    protected int linha;
    protected int coluna;
    protected double custo;

    public No(int linha, int coluna, double custo) {
        setLinha(linha);
        setColuna(coluna);
        setCusto(custo);
    }

    public No(int linha, int coluna) {
        this(linha, coluna, 0);
    }

    public No(double custo) {
        this(0, 0, custo);
    }

    public No() {
        this(0, 0, 0);
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

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(linha);
        sb.append(", ");
        sb.append(coluna);
        sb.append("] (");
        sb.append(custo);
        sb.append(")");

        return sb.toString();
    }

    @Override
    public int compareTo(No t) {
        if (this.getCusto() > t.getCusto()) {
            return 1;
        } else if (this.getCusto() < t.getCusto()) {
            return -1;
        }
        return 0;
    }
}
