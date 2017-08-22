
package br.edu.utfpr.dainf.csi30.tarefa2.sistema;

import br.edu.utfpr.dainf.csi30.tarefa2.ambiente.Model;
import br.edu.utfpr.dainf.csi30.tarefa2.comuns.CoordenadasGeo;

/**
 *
 * @author tacla
 */
public class Agente implements CoordenadasGeo {
    Model model;
    Problema prob;   // Problema estah na 'mente' do agente
    
    //colocar aqui plano armazenado de acoes que será executado 
    // passo a passo em deliberar
    int plan[]={4,3,1,1,3,3,5,5};
    int custo = 0;

    public Agente(Model m) {
        this.model = m;  
        
        prob = new Problema();
        prob.criarLabirinto();
       
        prob.defEstIni(0, 0);
        prob.defEstObj(4, 3);
        prob.defEstAtu(0, 0);
    }
    /* 
     * Escolhe qual ação será executada em um ciclo de raciocínio
     */
    public int deliberar() {
        int ap[];
        ap = prob.acoesPossiveis(prob.estAtu);
        
        // nao atingiu objetivo e ha acoesPossiveis a serem executadas no plano
        if (!prob.testeObjetivo()) {
           System.out.println("estado atual: " + prob.estAtu[0] + "," + prob.estAtu[1]);
           System.out.print("açoes possiveis: ");
           for (int i=0;i<ap.length;i++) {
               if (ap[i]!=-1)
                   System.out.print(acao[i]+" ");
           }
           System.out.println("\nct = "+ custo + " de " + (plan.length-1) + " ação=" + acao[plan[custo]] + "\n");
           executarIr(plan[custo]);
           
           custo++;
        }
        else
            return (-1);
        
        return 1;
    }
    
    /*
    * Atuador: solicita ao agente 'fisico' executar a acao. Atualiza estado.
    */
    public int executarIr(int direcao) {
        model.ir(direcao);
        prob.suc(prob.estAtu, direcao); // atualiza estado do agente
        return 1; 
    }   
    
    //Sensor: lê posição atual do agente 
    // @todo
    public int[] lerSensor() {
        return prob.estAtu;
    }
    
}
    

