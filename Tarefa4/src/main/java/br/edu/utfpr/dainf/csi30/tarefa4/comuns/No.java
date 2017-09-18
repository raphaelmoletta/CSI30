package br.edu.utfpr.dainf.csi30.tarefa4.comuns;

/**
 *
 * @author Raphael Zagonel Moletta
 */
public final class No implements Comparable<No> {

    protected int linha;
    protected int coluna;
    protected double custo;
    protected No de;

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

    public No getDe() {
        return de;
    }

    public void setDe(No de) {
        this.de = de;
    }
    
    public boolean igual (No no) {
        return no.getColuna() == this.getColuna() &&
                no.getLinha() == this.getLinha();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(linha);
        sb.append(", ");
        sb.append(coluna);
        sb.append("] (");
        sb.append(custo);
        sb.append(") ");
        sb.append(de);

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
