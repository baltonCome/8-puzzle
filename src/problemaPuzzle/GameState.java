package problemaPuzzle;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author
 *Definicao de estados
 */
public class GameState implements Comparable<GameState> {

    public GameState parent;
    private Integer[][] game;
    private Integer heuristicValue;
    private Integer totalValue;
    private Integer level;
    private HashSet<String> generatedStates;
    private final ArrayList<GameState> child;

    // Construtor sem parâmetros
    public GameState() {
        this.parent = null;
        this.game = new Integer[3][3];
        this.heuristicValue = null;
        this.totalValue = null;
        this.level = 0;
        this.generatedStates = new HashSet<>();
        this.child = new ArrayList<>();
    }

    // Construtor passando o game inicial (utilizado na entrada de dados manual)
    public GameState(Integer[][] game) {
        this.parent = null;
        this.game = game;
        this.heuristicValue = null;
        this.totalValue = null;
        this.level = 0;
        this.generatedStates = new HashSet<>();
        this.child = new ArrayList<>();
    }

    // Construtor passando o game inicial e o parent (utilizado na jogada manual)
    public GameState(Integer[][] game, GameState parent) {
        this.parent = parent;
        this.game = game;
        this.heuristicValue = null;
        this.totalValue = null;
        this.level = parent.getLevel() + 1;
        this.generatedStates = new HashSet<>();
        this.child = new ArrayList<>();
    }

    public void addChild(GameState cloned) {
        cloned.parent = this;
        this.child.add(cloned);
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public GameState clone() {
        GameState clone = new GameState();
        for (int i = 0; i < 3; i++)
            System.arraycopy(this.game[i], 0, clone.game[i], 0, 3);
        return clone;
    }

    // Conta o número de inversões para verificar se o estado inicial tem solução
    static int getAmountOfInversion(Integer[][] arr) {
        int inv_count = 0, cont = 0;
        Integer[] puzzle = new Integer[9];

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                puzzle[cont++] = arr[i][j];

        for (int i = 0; i < puzzle.length; i++) {
            for (int j = i + 1; j < puzzle.length; j++)
                if ((puzzle[i] > puzzle[j]) && (puzzle[i] > 0) && (puzzle[j] > 0))
                    inv_count++;
        }
        return inv_count;
    }

    // Retorna true se o quebra cabeca tiver solucao
    public boolean hasSolution(Integer[][] puzzle) {
        // conta quantas inversoes tem no quebra cabeca
        int invCount = getAmountOfInversion(puzzle);
        // retorna true se a quantidade de inversao for par, ou seja, é possível resolver
        return (invCount % 2 == 0);
    }


    public int calculateHeuristicManhattan() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (this.game[i][j]) {
                    case 0 : count += Math.abs(i) + Math.abs(j); break;
                    case 1 : count += Math.abs(i) + Math.abs(j - 1); break;
                    case 2 : count += Math.abs(i) + Math.abs(j - 2); break;
                    case 3 : count += Math.abs(i - 1) + Math.abs(j); break;
                    case 4 : count += Math.abs(i - 1) + Math.abs(j - 1); break;
                    case 5 : count += Math.abs(i - 1) + Math.abs(j - 2); break;
                    case 6 : count += Math.abs(i - 2) + Math.abs(j); break;
                    case 7 : count += Math.abs(i - 2) + Math.abs(j - 1); break;
                    case 8 : count += Math.abs(i - 2) + Math.abs(j - 2); break;
                    default : {}
                }
            }
        }
        this.heuristicValue = count;
        return count;
    }

    // Converte a matriz do estado do game para uma string de linha única
    public String gameInString() {
        String gameString = "";

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String aux = this.game[i][j].toString();
                gameString = gameString.concat(aux);
            }
        }
        return gameString;
    }

    // Gera todos os child possíveis, sem repetição
    public ArrayList<GameState> generateChild() {
        int i0 = -1, j0 = -1;
        GameState clone = this.clone();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (clone.game[i][j] == 0) {
                    i0 = i;
                    j0 = j;
                    break;
                }
            }
            if (i0 != -1) {
                break;
            }
        }
        //troca 0 com o valor de cima
        if (i0 - 1 >= 0) {
            int aux = clone.game[i0 - 1][j0];
            clone.game[i0 - 1][j0] = clone.game[i0][j0];
            clone.game[i0][j0] = aux;
            String gameString = clone.gameInString();
            if (this.generatedStates.add(gameString)) {
                clone.parent = this;
                clone.calculateHeuristicManhattan();
                clone.level = this.level + 1;
                addChild(clone);
            }
        }
        //troca 0 com o valor de baixo
        if (i0 + 1 < 3) {
            clone = this.clone();
            int aux = clone.game[i0 + 1][j0];
            clone.game[i0 + 1][j0] = clone.game[i0][j0];
            clone.game[i0][j0] = aux;
            String gameString = clone.gameInString();
            if (this.generatedStates.add(gameString)) {
                clone.parent = this;
                clone.calculateHeuristicManhattan();
                clone.level = this.level + 1;
                addChild(clone);
            }
        }
        //troca 0 com o valor a direita
        if (j0 + 1 < 3) {
            clone = this.clone();
            int aux = clone.game[i0][j0 + 1];
            clone.game[i0][j0 + 1] = clone.game[i0][j0];
            clone.game[i0][j0] = aux;
            String gameString = clone.gameInString();
            if (this.generatedStates.add(gameString)) {
                clone.parent = this;
                clone.calculateHeuristicManhattan();
                clone.level = this.level + 1;
                addChild(clone);
            }
        }
        //troca 0 com o valor a esquerda
        if (j0 - 1 >= 0) {
            clone = this.clone();
            int aux = clone.game[i0][j0 - 1];
            clone.game[i0][j0 - 1] = clone.game[i0][j0];
            clone.game[i0][j0] = aux;
            String gameString = clone.gameInString();
            if (this.generatedStates.add(gameString)) {
                clone.parent = this;
                clone.calculateHeuristicManhattan();
                clone.level = this.level + 1;
                addChild(clone);
            }
        }
        return this.child;
    }

    public ArrayList<GameState> generateChild(HashSet<String> generatedStates) {
        this.generatedStates = generatedStates;
        return generateChild();
    }

    // Utilizado na jogada manual
    public void resetGeneratedStates() {
        generatedStates = new HashSet<>();
    }

    // Verifica se o estado final é um estado final
    @SuppressWarnings("SimplifyStreamApiCallChains")
    public boolean isFinalState() {
        final List<Integer> finalState = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 0, 8);

        List<Integer> game =  Stream.of(this.game)
                .flatMap(Stream::of)
                .collect(Collectors.toUnmodifiableList());

        return finalState.equals(game);
    }

    public Integer[][] getGame() {
        return game;
    }

    public void setGame(Integer[][] game) {
        this.game = game;
    }

    public Integer getHeuristicValue() {
        return this.heuristicValue;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Integer totalValue) {
        this.totalValue = totalValue;
    }

    public ArrayList<GameState> getChild() {
        return child;
    }

    public GameState getParent() {
        return parent;
    }

    @Override
    public int compareTo(GameState t) {
        if (totalValue == null) {
            if (t.getHeuristicValue() < getHeuristicValue())
                return 1;
            if (t.getHeuristicValue() > getHeuristicValue())
                return -1;
            if (Objects.equals(t.getHeuristicValue(), getHeuristicValue())) {
                if (t.getLevel() < getLevel())
                    return 1;
                if (t.getLevel() > getLevel())
                    return -1;
            }
        } else {
            if (t.getTotalValue() < totalValue)
                return 1;
            if (t.getTotalValue() > totalValue)
                return -1;
            if (Objects.equals(t.getTotalValue(), totalValue)) {
                if (t.getLevel() < level)
                    return 1;
                if (t.getLevel() > level)
                    return -1;
            }
        }
        return 0;
    }
}
