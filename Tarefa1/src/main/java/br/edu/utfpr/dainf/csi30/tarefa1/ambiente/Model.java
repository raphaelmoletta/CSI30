package br.edu.utfpr.dainf.csi30.tarefa1.ambiente;

/**
 *
 * @author tacla Modela o ambiente 'simulado' composto por um labirinto com
 * paredes e por um agente
 */
public class Model {

    // coordenadas para ação ir
    public final static int N = 0;
    public final static int NE = 1;
    public final static int L = 2;
    public final static int SE = 3;
    public final static int S = 4;
    public final static int SO = 5;
    public final static int O = 6;
    public final static int NO = 7;
    public final static String acao[] = {"N","NE","L","SE","S","SO","O","NO"};

    protected int maxCol; // tamanho do labirinto em X (colunas)
    protected int maxLin; // tamanho do labirinto em Y (linhas)
    protected int pos[]={0,0}; // pos do agente {linha, coluna)
    protected int parede[][]; // armazena posicao das paredes
    protected View view;

    public Model(int colunas, int linhas) {
        this.maxCol = colunas;
        this.maxLin = linhas;
        if (maxCol <= 0) {
            maxCol = 5;
        }
        if (maxLin <= 0) {
            maxLin = 5;
        }
        parede = new int[maxLin][maxCol];
        view = new View(this);
    }
    
    public void desenhar() {
        view.desenhar();
    }
    
    public int[] lerPos() {
        return pos;
    }

    /**
     * @param coord int de 0 a 7 iniciando por N no sentido horário
     */
    public void ir(int coord) {
        int lin = this.pos[0];
        int col = this.pos[1];
        switch (coord) {
            case N:
                lin--;
                break;
            case NE:
                col++;
                lin--;
                break;
            case L:
                col++;
                break;
            case SE:
                col++;
                lin++;
                break;
            case S:
                lin++;
                break;
            case SO:
                col--;
                lin++;
                break;
            case O:
                col--;
                break;
            case NO:
                col--;
                lin--;
                break;
        }
        // verifica se está fora do grid
        if (col < 0 || col >= maxCol || lin < 0 || lin >= maxLin) {
            lin = pos[0];
            col = pos[1];  // fica na posicao atual
            
        }
        // verifica se bateu em algum obstaculo
        if (parede[lin][col] == 1) {
            lin = pos[0];
            col = pos[1];  // fica na posicao atual
        }

        // atribui nova posicao
        this.pos[0] = lin;
        this.pos[1] = col;
    }

    /*
    * @param ini: coluna inicial entre 0 e número máximo de colunas - 1
    * @param fim: coluna final (> coluna inicial)
    * @param linha: em qual linha por a parede (entre 0 e máx. de linhas - 1)
    */
    public void porParedeHorizontal(int ini, int fim, int linha) {
        if (fim > ini && ini >= 0 && fim < maxCol && linha >= 0 && linha < maxLin) {
            for (int c = ini; c <= fim; c++) {
                parede[linha][c] = 1;
            }
        }
    }
    /*
    * @param ini: linha inicial entre 0 e  máximo de linhas - 1
    * @param fim: linha final (> linha inicial)
    * @param linha: em qual coluna por a parede (entre 0 e máx. de colunas - 1)
    */
    public void porParedeVertical(int ini, int fim, int coluna) {
        if (fim > ini && ini >= 0 && fim < maxLin && coluna >= 0 && coluna < maxCol) {
            for (int l = ini; l <= fim; l++) {
                parede[l][coluna] = 1;
            }
        }
    }
}
