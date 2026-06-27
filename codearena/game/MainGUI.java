package codearena.game;

import codearena.ui.GameGUI;
import javax.swing.SwingUtilities;

/**
 * Ponto de entrada da versao grafica (Swing) do jogo.
 * Mantemos separado do Main.java (versao console) para que
 * ambas as versoes continuem funcionando independentemente.
 */
public class MainGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameGUI());
    }
}
