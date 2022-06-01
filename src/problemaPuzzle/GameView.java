package problemaPuzzle;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author
 *Criacao da matriz
 */
public class GameView extends JFrame {

    // Cores sobre os botões que representam as puzzle do tabuleiro.
    private static final Color BACKGROUND_BTN_COLOR = new Color(100, 150, 44);
    private static final Color BACKGROUND_BTN_COLOR_PRESSED = Color.BLUE;
    private static final Color FOREROUND_BTN_COLOR = /*Color.WHITE*/ Color.BLACK;
    private static final Font BTN_FONT = new Font("Consolas", Font.PLAIN, 60);
    private static final Font BTN_FONT_TWO = new Font("Consolas", Font.PLAIN, 35);
    private GameState currentState;
    private final GameState initialState;
    private JButton[][] puzzle; // Representa as puzzle no tabuleiro.
    private JButton firstPressedBtn;
    private final JButton btnAdvance, btnRedo, btnGoal;
    private int playCounter;
    private int qtdPlay;
    private boolean flag;
    private final ArrayList<GameState> path;
    private int aux = 0;

    /**
     * Permite criar uma janela gráfica que representa o tabuleiro do jogo da
     * velha.
     *
     * @param initialState Matriz de integer 3x3
     */
    public GameView(GameState initialState, boolean flag, ArrayList<GameState> path) {
        this.currentState = initialState;
        this.initialState = initialState;
        this.firstPressedBtn = null;
        this.playCounter = 0;
        this.qtdPlay = 0;
        this.path = path;

        this.btnAdvance = new JButton("Avançar");
        this.btnRedo = new JButton("Voltar");
        this.btnGoal = new JButton("Solução");

        initializeWindow();
        resetPuzzle(initialState);
    }

    // Inicializa as configurações iniciais da janela gráfica do jogo.
    private void initializeWindow() {
        setLayout(new FlowLayout());
        setSize(500, 500);
        setLayout(new GridLayout(4, 3));
        setTitle("8-puzzle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     *
     * @param state state do jogo
     */
    public final void resetPuzzle(GameState state) {
        // Remove tudo o que já está no tabuleiro
        if (puzzle != null) {
            for (JButton[] linha : puzzle) {
                for (JButton box : linha) {
                    remove(box);
                }
            }
        }

        puzzle = new JButton[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                puzzle[i][j] = new JButton(state.getGame()[i][j] != 0 ? state.getGame()[i][j].toString() : "");
                puzzle[i][j].setBackground(BACKGROUND_BTN_COLOR);
                puzzle[i][j].setForeground(FOREROUND_BTN_COLOR);
                puzzle[i][j].setFont(BTN_FONT);
                addEvento(puzzle[i][j]);
                add(puzzle[i][j]);
            }

        }

        currentState = state;

        btnAdvance.addActionListener((ActionEvent ae) -> { // Avançar mostra o próximo passo da solução
            if (aux != path.size() - 1) {
                aux++;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        puzzle[i][j].setText(path.get(aux).getGame()[i][j] != 0 ? path.get(aux).getGame()[i][j].toString() : "");
                    }
                }
            }
        });
        btnRedo.addActionListener((ActionEvent ae) -> { // Voltar mostra o passo anterior da solução
            if (aux != 0) {
                aux--;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        puzzle[i][j].setText(path.get(aux).getGame()[i][j] != 0 ? path.get(aux).getGame()[i][j].toString() : "");
                    }
                }
            }
        });
        btnGoal.addActionListener((ActionEvent ae) -> { // Solução vai para o state final
            aux = path.size() - 1;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    puzzle[i][j].setText(path.get(aux).getGame()[i][j] != 0 ? path.get(aux).getGame()[i][j].toString() : "");
                }
            }
        });

        btnAdvance.setFont(BTN_FONT_TWO);
        btnRedo.setFont(BTN_FONT_TWO);
        btnGoal.setFont(BTN_FONT_TWO);

        add(btnAdvance);
        add(btnRedo);
        add(btnGoal);

        // Atualiza a view
        revalidate();
        repaint();
    }

    private void addEvent(JButton btn) {
        btn.addActionListener((ActionEvent ae) -> { // Se o usuário desistiu, vez da IA
            setVisible(false);
            GameView viewIA = new GameView(initialState, false, path);
        });
    }

    private void addEvento(JButton box) {

        box.addActionListener((ActionEvent ae) -> {

            if (firstPressedBtn == null) {
                firstPressedBtn = box;
                firstPressedBtn.setBackground(BACKGROUND_BTN_COLOR_PRESSED);

            } else if (!firstPressedBtn.getText().equals(box.getText())) {
                // Procurando a posição das puzzle na matriz
                int i1 = -1, j1 = -1, i2 = -1, j2 = -1;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (puzzle[i][j].getText().equals(firstPressedBtn.getText())) {
                            i1 = i;
                            j1 = j;
                        } else if (puzzle[i][j].getText().equals(box.getText())) {
                            i2 = i;
                            j2 = j;
                        }
                        if (i1 != -1 && i2 != -1) {
                            i = 3;
                            j = 3;
                        }
                    }
                }

                ArrayList<GameState> children = currentState.generateChild();

                Integer[][] intendedState = new Integer[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (i == i1 && j == j1) {
                            intendedState[i][j] = puzzle[i2][j2].getText().equals("") ? 0 : Integer.parseInt(puzzle[i2][j2].getText());
                        } else if (i == i2 && j == j2) {
                            intendedState[i][j] = puzzle[i1][j1].getText().equals("") ? 0 : Integer.parseInt(puzzle[i1][j1].getText());
                        } else {
                            intendedState[i][j] = puzzle[i][j].getText().equals("") ? 0 : Integer.parseInt(puzzle[i][j].getText());
                        }
                    }
                }

                for (GameState child : children) {
                    boolean isChild = true;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (!Objects.equals(child.getGame()[i][j], intendedState[i][j])) {
                                isChild = false;
                                i = 3;
                                j = 3;
                            }
                        }
                    }
                    if (isChild) {
                        currentState = new GameState(intendedState, currentState);
                        resetPuzzle(currentState);

                        playCounter++;

                        // Verifica se atingiu um state final
                        if (currentState.isFinalState()) {
                            JOptionPane.showMessageDialog(this, "Fim de jogo! Total de jogadas: " + playCounter);

                            GameView viewIA = new GameView(initialState, false, path);
                        }
                        break;
                    }
                }

                firstPressedBtn.setBackground(BACKGROUND_BTN_COLOR);
                firstPressedBtn = null;
                currentState.resetGeneratedStates();
            } else {
                // Clicou no mesmo botão duas vezes
                firstPressedBtn.setBackground(BACKGROUND_BTN_COLOR);
                firstPressedBtn = null;
            }
        });
    }

    public int getQtdPlay() {
        return qtdPlay;
    }

    public void setQtdPlay(int qtdPlay) {
        this.qtdPlay = qtdPlay;
    }
}
