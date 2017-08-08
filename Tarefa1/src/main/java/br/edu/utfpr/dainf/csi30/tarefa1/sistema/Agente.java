
package br.edu.utfpr.dainf.csi30.tarefa1.sistema;

import br.edu.utfpr.dainf.csi30.tarefa1.ambiente.Model;

/**
 *
 * @author tacla
 */
public class Agente {
    protected Model model;
    protected int rounds = 100;
    protected int round = 0;

    
// @todo crenças do agente ficam aqui
    
    public Agente(Model m) {
        this.model = m;       
    }
    /* 
     * Escolhe qual ação será executada em um ciclo de raciocínio
     */
    public int deliberar() {
        model.ir((int)(Math.random() * 7));
        if(round == rounds)
            return -1;
        round++;
        return 1;
    }
    
    // @todo Atuador

    
    // @todo Sensor
    
}
    

