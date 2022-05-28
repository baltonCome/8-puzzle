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
public class JogoView extends JFrame {

    // Cores sobre os botões que representam as casas do tabuleiro.
    private static final Color COR_FUNDO_BOTAO = new Color(66, 134, 244);
    private static final Color COR_FUNDO_BOTAO_PRESSIONADO = Color.LIGHT_GRAY;
    private static final Color COR_NUMERO_BOTAO = /*Color.WHITE*/ Color.BLACK;
    private static final Font FONTE_BOTAO = new Font("Tahoma", Font.PLAIN, 60);
    private static final Font FONTE_BOTAODOIS = new Font("Tahoma", Font.PLAIN, 35);
    private EstadoJogo estadoAtual;
    private EstadoJogo estadoInicial;
    private JButton casas[][]; // Representa as casas no tabuleiro.
    private JButton primeiroBotaoPressionado;
    private final JButton butaoAvancar, botaoVoltar, botaoSolucao;
    private int contadorJogadas;
    private int qtdJogadas;
    private boolean flag;
    private ArrayList<EstadoJogo> caminho;
    private int aux = 0;

    /**
     * Permite criar uma janela gráfica que representa o tabuleiro do jogo da
     * velha.
     *
     * @param estadoInicial Matriz de integer 3x3
     */
    public JogoView(EstadoJogo estadoInicial, boolean flag, ArrayList<EstadoJogo> caminho) {
        this.estadoAtual = estadoInicial;
        this.estadoInicial = estadoInicial;
        this.primeiroBotaoPressionado = null;
        this.contadorJogadas = 0;
        this.qtdJogadas = 0;
        this.caminho = caminho;

        this.butaoAvancar = new JButton("Avançar");
        this.botaoVoltar = new JButton("Voltar");
        this.botaoSolucao = new JButton("Solução");

        inicializarJanela();
        reposicionarCasas(estadoInicial);
    }

    // Inicializa as configurações iniciais da janela gráfica do jogo.
    private void inicializarJanela() {
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
     * @param estado Estado do jogo
     */
    public final void reposicionarCasas(EstadoJogo estado) {
        // Remove tudo o que já está no tabuleiro
        if (casas != null) {
            for (JButton[] linha : casas) {
                for (JButton casa : linha) {
                    remove(casa);
                }
            }
        }

        casas = new JButton[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                casas[i][j] = new JButton(estado.getJogo()[i][j] != 0 ? estado.getJogo()[i][j].toString() : "");
                casas[i][j].setBackground(COR_FUNDO_BOTAO);
                casas[i][j].setForeground(COR_NUMERO_BOTAO);
                casas[i][j].setFont(FONTE_BOTAO);
                addEvento(casas[i][j]);
                add(casas[i][j]);
            }

        }

        estadoAtual = estado;

        butaoAvancar.addActionListener((ActionEvent ae) -> { // Avançar mostra o próximo passo da solução
            if (aux != caminho.size() - 1) {
                aux++;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        casas[i][j].setText(caminho.get(aux).getJogo()[i][j] != 0 ? caminho.get(aux).getJogo()[i][j].toString() : "");
                    }
                }
            }
        });
        botaoVoltar.addActionListener((ActionEvent ae) -> { // Voltar mostra o passo anterior da solução
            if (aux != 0) {
                aux--;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        casas[i][j].setText(caminho.get(aux).getJogo()[i][j] != 0 ? caminho.get(aux).getJogo()[i][j].toString() : "");
                    }
                }
            }
        });
        botaoSolucao.addActionListener((ActionEvent ae) -> { // Solução vai para o estado final
            aux = caminho.size() - 1;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    casas[i][j].setText(caminho.get(aux).getJogo()[i][j] != 0 ? caminho.get(aux).getJogo()[i][j].toString() : "");
                }
            }
        });

        butaoAvancar.setFont(FONTE_BOTAODOIS);
        botaoVoltar.setFont(FONTE_BOTAODOIS);
        botaoSolucao.setFont(FONTE_BOTAODOIS);

        add(butaoAvancar);
        add(botaoVoltar);
        add(botaoSolucao);

        // Atualiza a view
        revalidate();
        repaint();
    }

    private void addEvent(JButton botao) {
        botao.addActionListener((ActionEvent ae) -> { // Se o usuário desistiu, vez da IA
            setVisible(false);
            JogoView viewIA = new JogoView(estadoInicial, false, caminho);
        });
    }

    private void addEvento(JButton casa) {

        casa.addActionListener((ActionEvent ae) -> {

            if (primeiroBotaoPressionado == null) {
                primeiroBotaoPressionado = casa;
                primeiroBotaoPressionado.setBackground(COR_FUNDO_BOTAO_PRESSIONADO);

            } else if (!primeiroBotaoPressionado.getText().equals(casa.getText())) {
                // Procurando a posição das casas na matriz
                int i1 = -1, j1 = -1, i2 = -1, j2 = -1;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (casas[i][j].getText().equals(primeiroBotaoPressionado.getText())) {
                            i1 = i;
                            j1 = j;
                        } else if (casas[i][j].getText().equals(casa.getText())) {
                            i2 = i;
                            j2 = j;
                        }
                        if (i1 != -1 && i2 != -1) {
                            i = 3;
                            j = 3;
                        }
                    }
                }

                ArrayList<EstadoJogo> filhos = estadoAtual.gerarFilhos();

                Integer[][] estadoPretendido = new Integer[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (i == i1 && j == j1) {
                            estadoPretendido[i][j] = casas[i2][j2].getText().equals("") ? 0 : Integer.valueOf(casas[i2][j2].getText());
                        } else if (i == i2 && j == j2) {
                            estadoPretendido[i][j] = casas[i1][j1].getText().equals("") ? 0 : Integer.valueOf(casas[i1][j1].getText());
                        } else {
                            estadoPretendido[i][j] = casas[i][j].getText().equals("") ? 0 : Integer.valueOf(casas[i][j].getText());
                        }
                    }
                }

                for (EstadoJogo filho : filhos) {
                    boolean ehFilho = true;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (!Objects.equals(filho.getJogo()[i][j], estadoPretendido[i][j])) {
                                ehFilho = false;
                                i = 3;
                                j = 3;
                            }
                        }
                    }
                    if (ehFilho) {
                        EstadoJogo novoEstado = new EstadoJogo(estadoPretendido, estadoAtual);
                        estadoAtual = novoEstado;
                        reposicionarCasas(estadoAtual);

                        contadorJogadas++;

                        // Verifica se atingiu um estado final
                        if (estadoAtual.ehEstadoFinal()) {
                            JOptionPane.showMessageDialog(this, "Fim de jogo! Total de jogadas: " + contadorJogadas);

                            JogoView viewIA = new JogoView(estadoInicial, false, caminho);
                        }
                        break;
                    }
                }

                primeiroBotaoPressionado.setBackground(COR_FUNDO_BOTAO);
                primeiroBotaoPressionado = null;
                estadoAtual.resetarEstadosJaGerados();
            } else {
                // Clicou no mesmo botão duas vezes
                primeiroBotaoPressionado.setBackground(COR_FUNDO_BOTAO);
                primeiroBotaoPressionado = null;
            }
        });
    }

    public int getQtdJogadas() {
        return qtdJogadas;
    }

    public void setQtdJogadas(int qtdJogadas) {
        this.qtdJogadas = qtdJogadas;
    }
}
