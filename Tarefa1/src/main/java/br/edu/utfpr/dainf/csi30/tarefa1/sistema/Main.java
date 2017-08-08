package br.edu.utfpr.dainf.csi30.tarefa1.sistema;

import br.edu.utfpr.dainf.csi30.tarefa1.ambiente.Model;

/**
 *
 * @author tacla
 */
public class Main {
    public static void main(String args[]) {
        // Cria o labirinto = ambiente
        Model model = new Model(8, 8);
        model.porParedeHorizontal(1, 3, 2);
        model.porParedeHorizontal(0, 2, 4);
        model.porParedeHorizontal(4, 7, 4);
        
        // Cria um agente
        Agente ag = new Agente(model);
        
        // Ciclo de execucao do sistema
        model.desenhar();
        while (ag.deliberar() != -1) {
            model.desenhar();
        }
    }
}
