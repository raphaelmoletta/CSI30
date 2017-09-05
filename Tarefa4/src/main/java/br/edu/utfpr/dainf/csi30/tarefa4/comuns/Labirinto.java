package br.edu.utfpr.dainf.csi30.tarefa4.comuns;

public class Labirinto {
    public int parede[][]; // armazena posicao das paredes marcando-as com 1
    private final Ponto maximos;     // maximo de colunas
    
    public Labirinto(Ponto maximos) {
        if(maximos.getLinha() < 5) {
            maximos.setLinha(5);
        }
        if(maximos.getColuna() < 5) {
            maximos.setColuna(5);
        }
        this.maximos = maximos;
        parede = new int[maximos.getLinha()][maximos.getColuna()];
    }
    
    public Labirinto (Labirinto labirinto) {
        this.maximos = new Ponto(labirinto.getLinhas(), labirinto.getColunas());
        parede = new int[maximos.getLinha()][maximos.getColuna()];
        for(int l = 0; l < maximos.getLinha(); l++) {
            System.arraycopy(labirinto.parede[l], 0, parede[l], 0, maximos.getColuna());
        }
    }
    
    public int getLinhas() {
        return maximos.getLinha();
    }
    
    public int getColunas() {
        return maximos.getColuna();
    }
    
    public Ponto getMaximos() {
        return maximos;
    }
    
   /*
    * Constroi parede horizontal
    * @param ini: coluna inicial entre 0 e número máximo de colunas - 1
    * @param fim: coluna final (> coluna inicial)
    * @param linha: em qual linha por a parede (entre 0 e máx. de linhas - 1)
    */
    public void porParedeHorizontal(int ini, int fim, int linha) {
        if (fim >= ini && ini >= 0 && fim < maximos.getColuna() && linha >= 0 && linha < maximos.getLinha()) {
            for (int c = ini; c <= fim; c++) {
                parede[linha][c] = 1;
            }
        }
    }
    /*
    * Constroi parede vertical
    * @param ini: linha inicial entre 0 e  máximo de linhas - 1
    * @param fim: linha final (> linha inicial)
    * @param linha: em qual coluna por a parede (entre 0 e máx. de colunas - 1)
    */
    public void porParedeVertical(int ini, int fim, int coluna) {
        if (fim >= ini && ini >= 0 && fim < maximos.getLinha() && coluna >= 0 && coluna < maximos.getColuna()) {
            for (int l = ini; l <= fim; l++) {
                parede[l][coluna] = 1;
            }
        }
    }   
}