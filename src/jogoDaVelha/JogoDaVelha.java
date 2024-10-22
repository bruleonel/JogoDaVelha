package jogoDaVelha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogoDaVelha extends JFrame implements ActionListener {
	
    private JButton[][] botoes = new JButton[3][3];
    private char jogadorAtual = 'X';
    private boolean jogoAtivo = true;

    public JogoDaVelha() {
        setTitle("Jogo da Velha");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        inicializarTabuleiro();
    }

    private void inicializarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j] = new JButton("");
                botoes[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                botoes[i][j].setFocusPainted(false);
                botoes[i][j].addActionListener(this);
                add(botoes[i][j]);
            }
        }
    }
    

    private void alternarJogador() {
        jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
    }
    
    private boolean verificarVitoria() {
        // Verifica linhas e colunas
        for (int i = 0; i < 3; i++) {
            if (botoes[i][0].getText().equals(String.valueOf(jogadorAtual)) &&
                botoes[i][1].getText().equals(String.valueOf(jogadorAtual)) &&
                botoes[i][2].getText().equals(String.valueOf(jogadorAtual))) {
                return true;
            }
            if (botoes[0][i].getText().equals(String.valueOf(jogadorAtual)) &&
                botoes[1][i].getText().equals(String.valueOf(jogadorAtual)) &&
                botoes[2][i].getText().equals(String.valueOf(jogadorAtual))) {
                return true;
            }
        }

        // Verifica diagonais
        if (botoes[0][0].getText().equals(String.valueOf(jogadorAtual)) &&
            botoes[1][1].getText().equals(String.valueOf(jogadorAtual)) &&
            botoes[2][2].getText().equals(String.valueOf(jogadorAtual))) {
            return true;
        }
        if (botoes[0][2].getText().equals(String.valueOf(jogadorAtual)) &&
            botoes[1][1].getText().equals(String.valueOf(jogadorAtual)) &&
            botoes[2][0].getText().equals(String.valueOf(jogadorAtual))) {
            return true;
        }

        return false;
    }
    
    private boolean tabuleiroCheio() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (botoes[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void reiniciarJogo() {
        // Limpa o tabuleiro para uma nova partida
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setText("");
            }
        }
        jogadorAtual = 'X';
        jogoAtivo = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botaoClicado = (JButton) e.getSource();

        if (!botaoClicado.getText().equals("") || !jogoAtivo) {
            return;
        }

        botaoClicado.setText(String.valueOf(jogadorAtual));

        if (verificarVitoria()) {
            JOptionPane.showMessageDialog(this, "Jogador " + jogadorAtual + " venceu!");
            jogoAtivo = false;
            int resposta = JOptionPane.showConfirmDialog(this, "Deseja jogar novamente?", "Fim de Jogo", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                reiniciarJogo();
            } else {
                System.exit(0);
            }
        } else if (tabuleiroCheio()) {
            JOptionPane.showMessageDialog(this, "Empate!");
            jogoAtivo = false;
            int resposta = JOptionPane.showConfirmDialog(this, "Deseja jogar novamente?", "Fim de Jogo", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                reiniciarJogo();
            } else {
                System.exit(0);
            }
        } else {
            alternarJogador();
        }
    }

}

