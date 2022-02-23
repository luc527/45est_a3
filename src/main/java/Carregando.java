import javax.swing.*;
import java.awt.*;

public class Carregando {

    private final JDialog dialog;
    private final Thread thread;

    Carregando(JFrame framePai) {
        dialog = new JDialog(framePai, "Carregando...", true);
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setIndeterminate(true);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
        panel.add(progressBar);
        dialog.add(BorderLayout.CENTER, panel);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setSize(256, 128);
        dialog.setLocationRelativeTo(framePai);

        thread = new Thread(() -> dialog.setVisible(true));
    }

    public void iniciar() {
        thread.start();
    }

    public void parar() {
        dialog.setVisible(false);
        System.gc();
    }
}
