package problemaPuzzle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author
 *Search
 */
public class Search {

    public ArrayList<GameState> search(GameState initialState) {

        HashSet<String> visitedStates = new HashSet<>();
        int qtdVisitedStates = 0;
        initialState.setTotalValue(0);

        PriorityQueue<GameState> stateQueue = new PriorityQueue<>(); // Fila de states a serem expandidos, por ordem de prioridade

        GameState currentState = initialState;

        while (currentState != null && !currentState.isFinalState()) {

            visitedStates.add(currentState.gameInString());
            currentState.generateChild(visitedStates);

            for (GameState child : currentState.getChild()) {
                child.setTotalValue(currentState.getLevel() + child.calculateHeuristicManhattan()); // f(n) = g(n) + h(n);
                stateQueue.add(child);
            }

            currentState = stateQueue.poll();
            qtdVisitedStates += 1;
        }

        if (currentState == null) {
            JOptionPane.showMessageDialog(null, "Impossível encontrar uma solução.");
            System.out.println("Impossível encontrar uma solução.");
            return null;

        } else {
            JOptionPane.showMessageDialog(null, "Solução encontrada: " + currentState.gameInString()
                + "\nQuantidade de jogadas necessárias da raiz até a solução -> " + currentState.getLevel()
                + "\nQuantidade de nós visitados -> "+ qtdVisitedStates 
                + "\nCusto total da solução -> " + currentState.getTotalValue()
            );
            System.out.println("\n"+"Quantidade de jogadas necessárias da raiz até a solução -> " + currentState.getLevel());
            System.out.println("Quantidade de nós visitados -> " + qtdVisitedStates);
            System.out.println("Custo total da solução -> " + currentState.getTotalValue());
            System.out.println("Solução encontrada: " + currentState.gameInString());
            System.out.println();

            return getPath(currentState);
        }
    }

    private ArrayList<GameState> getPath(GameState state) {

        Stack<GameState> gameStack = new Stack<>();
        while (state != null) {
            gameStack.push(state);
            state = state.getParent();
        }
        ArrayList<GameState> traveledPath = new ArrayList<>();
        while (!gameStack.empty()) {
            traveledPath.add(gameStack.pop());
        }
        return traveledPath;
    }
}
