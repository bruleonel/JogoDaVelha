package jogoDaVelha;

import javax.swing.SwingUtilities;


public class Main {
    public static void main(String[] args) {
    	
        SwingUtilities.invokeLater(() -> {
            JogoDaVelha jogo = new JogoDaVelha();
            jogo.setVisible(true);
        });
    }
}

