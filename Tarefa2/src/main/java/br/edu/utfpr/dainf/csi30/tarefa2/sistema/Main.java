package br.edu.utfpr.dainf.csi30.tarefa2.sistema;

import br.edu.utfpr.dainf.csi30.tarefa2.ambiente.Model;


/**
 *
 * @author tacla
 */
public class Main {
    public static void main(String args[]) {
        // Cria o labirinto = ambiente
        Model mdl = new Model(8, 8);

        // Cria um agente
        Agente ag = new Agente(mdl);
        
        // Ciclo de execucao do sistema
        mdl.desenhar();
        while (ag.deliberar() != -1) {
            mdl.desenhar();
        }
    }
}
