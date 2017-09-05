
package br.edu.utfpr.dainf.csi30.tarefa4.sistema;

import br.edu.utfpr.dainf.csi30.tarefa4.ambiente.Ambiente;
import br.edu.utfpr.dainf.csi30.tarefa4.comuns.CoordenadasGeo;
import br.edu.utfpr.dainf.csi30.tarefa4.comuns.Ponto;
import java.util.List;

/**
 *
 * @author tacla
 */
public class Agente implements CoordenadasGeo {
    public enum Heuristica {euclidiana, coluna}
    protected Ambiente ambiente;
    protected Problema problema;   // Problema estah na 'mente' do agente
    
    //colocar aqui plano armazenado de acoes que será executado 
    // passo a passo em deliberar
    protected int[] plano;
    protected int custo = 0;

    public Agente(Ambiente ambiente, Problema problema) {
        this.ambiente = ambiente;  
        this.problema = problema;
    }
    
    public boolean solucionarCustoUniforme() {
        List<Ponto> acoes = problema.calcularAcoesPossiveis();
        
        return false;
    }
    
    public boolean solucionarAEstrela(Heuristica heuristica) {
        return false;
    }
    
    /* 
     * Escolhe qual ação será executada em um ciclo de raciocínio
     */
    public int deliberar() {
        List<Ponto> ap = problema.calcularAcoesPossiveis();
        
        // nao atingiu objetivo e ha acoesPossiveis a serem executadas no plano
        if (!problema.testarObjetivo()) {
           System.out.println("estado atual: " + problema.estadoAtual.toString());
           System.out.print("açoes possiveis: ");
           for (int i=0;i<ap.size();i++) {
               //todo if (ap.get(i)!=-1)
                   //todo System.out.print(acao[i]+" ");
           }
           //todo System.out.println("\nct = "+ custo + " de " + (plano.length-1) + " ação=" + acao[plano[custo]] + "\n");
           ir(Direcao.values()[plano[custo]]);
           
           custo++;
        }
        else
            return (-1);
        
        return 1;
    }
    
    /*
    * Atuador: solicita ao agente 'fisico' executar a acao. Atualiza estado.
    */
    public int ir(Direcao direcao) {
        ambiente.ir(direcao);
        problema.calcularSucessores(direcao); // atualiza estado do agente
        return 1; 
    }   
    
    //Sensor: lê posição atual do agente 
    public Ponto lerSensor() {
        return problema.estadoAtual;
    }
    
    public void executar() {
    // Ciclo de execucao do sistema
        ambiente.desenhar();
        while (deliberar() != -1) {
            ambiente.desenhar();
        }
    }
}