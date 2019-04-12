package br.edu.utfpr.dainf.csi30.tarefa4.sistema;

import br.edu.utfpr.dainf.csi30.tarefa4.ambiente.Ambiente;
import br.edu.utfpr.dainf.csi30.tarefa4.ambiente.Exibicao;
import br.edu.utfpr.dainf.csi30.tarefa4.comuns.Labirinto;
import br.edu.utfpr.dainf.csi30.tarefa4.comuns.No;
import br.edu.utfpr.dainf.csi30.tarefa4.sistema.Agente.Metodo;

/**
 *
 * @author tacla
 */
public class Main {
    public static void main(String args[]) {
        // Cria o labirinto
        System.out.println("Criando Labirinto...");
        Labirinto labirinto = new Labirinto(new No(9,9));
        labirinto.porParedeVertical(0, 1, 0);
        labirinto.porParedeHorizontal(0, 1, 0);
        labirinto.porParedeHorizontal(4, 7, 0);
        labirinto.porParedeHorizontal(0, 0, 1);
        labirinto.porParedeHorizontal(7, 7, 1);
        labirinto.porParedeHorizontal(3, 5, 2);
        labirinto.porParedeHorizontal(3, 5, 3);
        labirinto.porParedeHorizontal(7, 7, 3);
        labirinto.porParedeVertical(5, 8, 1);
        labirinto.porParedeVertical(5, 5, 2);
        labirinto.porParedeVertical(8, 8, 2);
        labirinto.porParedeVertical(6, 7, 4);
        labirinto.porParedeVertical(5, 6, 5);
        labirinto.porParedeVertical(5, 7, 7);
        
        //cria o ambiente e passa o labirinto real
        System.out.println("Criando Ambiente...");
        Exibicao exibicao = new Exibicao();
        Ambiente ambiente = new Ambiente(new Labirinto(labirinto), exibicao, new No(8,0));
        ambiente.desenhar();

        //cria o problema e passa o labirinto que é a crença do agente
        System.out.println("Criando Problema...");
        Problema problema = gerarProblema(labirinto);
        
        // Cria um agente passando o ambiente e o problema
        System.out.println("Criando Agente...");
        Agente agente = new Agente(ambiente, problema);
        
        System.out.println("Solucionando por Busca de Custo Uniforme");
        if (agente.solucionar(Metodo.CustoUniforme)) {
            agente.executar();
        } else {
            System.out.println(" # Falha na solução.");
        }
        
        //Reiniciando parametros
        System.out.println("Reiniciando parametros...");
        ambiente = new Ambiente(new Labirinto(labirinto), exibicao, new No(8,0));
        problema = gerarProblema(labirinto);
        agente = new Agente(ambiente, problema);
        //Solucionando com A* heuristica trigonométrica
        System.out.println("Solucionando por A* com Heuristica Euclidiana");
        if (agente.solucionar(Metodo.AEstrelaEuclidiana)) {
            agente.executar();
        } else {
            System.out.println(" # Falha na solução.");
        }
        
        //Reiniciando parametros
        System.out.println("Reiniciando parametros...");
        ambiente = new Ambiente(new Labirinto(labirinto), exibicao, new No(8,0));
        problema = gerarProblema(labirinto);
        agente = new Agente(ambiente, problema);
        
        //Solucionando com A* heuristica linear...
        System.out.println("Solucionando por A* com Heuristica Coluna");
        if(agente.solucionar(Metodo.AEstrelaColuna)) {
            agente.executar();
        } else {
            System.out.println(" # Falha na solução.");
        }
    }
    
    private static Problema gerarProblema(Labirinto labirinto) {
        Problema problema = new Problema(new Labirinto(labirinto));
        
        //Define ponto inicial
        No estadoInicial = new No(8, 0);
        System.out.println("Estado inicial: " + estadoInicial.toString());
        problema.setEstadoInicial(estadoInicial);
        
        //Define ponto objetivo
        No estadoObjetivo = new No(2, 8);
        System.out.println("Estado Objetivo: " + estadoObjetivo.toString());
        problema.setEstadoObjetivo(estadoObjetivo);
        
        //Define ponto atual
        No estadoAtual = new No(8,0);
        System.out.println("Estado Atual: " + estadoAtual.toString());
        problema.setEstadoAtual(estadoAtual);
        
        return problema;
    }
}