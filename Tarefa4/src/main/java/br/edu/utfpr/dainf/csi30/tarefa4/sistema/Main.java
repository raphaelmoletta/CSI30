package br.edu.utfpr.dainf.csi30.tarefa4.sistema;

import br.edu.utfpr.dainf.csi30.tarefa4.ambiente.Ambiente;
import br.edu.utfpr.dainf.csi30.tarefa4.ambiente.Exibicao;
import br.edu.utfpr.dainf.csi30.tarefa4.comuns.Labirinto;
import br.edu.utfpr.dainf.csi30.tarefa4.comuns.Ponto;

/**
 *
 * @author tacla
 */
public class Main {
    public static void main(String args[]) {
        // Cria o labirinto
        System.out.println("Criando Labirinto...");
        Labirinto labirinto = new Labirinto(new Ponto(9,9));
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
        Ambiente ambiente = new Ambiente(labirinto, new Exibicao());

        //cria o problema e passa o labirinto que é a crença do agente
        System.out.println("Criando Problema...");
        Problema problema = new Problema(new Labirinto(labirinto));
        
        //Define ponto inicial
        Ponto estadoInicial = new Ponto(8, 0);
        System.out.println("Estado inicial: " + estadoInicial.toString());
        problema.setEstadoInicial(estadoInicial);
        
        //Define ponto objetivo
        Ponto estadoObjetivo = new Ponto(2, 8);
        System.out.println("Estado Objetivo: " + estadoObjetivo.toString());
        problema.setEstadoObjetivo(estadoObjetivo);
        
        //Define ponto atual
        Ponto estadoAtual = new Ponto(8,0);
        System.out.println("Estado Atual: " + estadoAtual.toString());
        problema.setEstadoAtual(estadoAtual);
        
        // Cria um agente passando o ambiente e o problema
        System.out.println("Criando Agente...");
        Agente agente = new Agente(ambiente, problema);
        
        System.out.println("Solucionando por Busca de Custo Uniforme");
        if (agente.solucionarCustoUniforme()) {
            agente.executar();
        } else {
            System.out.println(" # Falha na solução.");
        }
        
        //Reiniciando parametros
        System.out.println("Reiniciando parametros...");
        estadoAtual.setLinha(estadoInicial.getLinha());
        estadoAtual.setColuna(estadoInicial.getColuna());
        problema.setEstadoAtual(estadoAtual);
        //Solucionando com A* heuristica trigonométrica
        System.out.println("Solucionando por A* com Heuristica Euclidiana");
        if (agente.solucionarAEstrela(Agente.Heuristica.euclidiana)) {
            agente.executar();
        } else {
            System.out.println(" # Falha na solução.");
        }
        
        //Reiniciando parametros
        System.out.println("Reiniciando parametros...");
        estadoAtual.setLinha(estadoInicial.getLinha());
        estadoAtual.setColuna(estadoInicial.getColuna());
        problema.setEstadoAtual(estadoAtual);
        //Solucionando com A* heuristica linear...
        System.out.println("Solucionando por A* com Heuristica Coluna");
        if(agente.solucionarAEstrela(Agente.Heuristica.coluna)) {
            agente.executar();
        } else {
            System.out.println(" # Falha na solução.");
        }
        
    }
}
