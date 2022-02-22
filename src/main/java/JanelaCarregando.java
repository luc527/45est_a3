import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class JanelaCarregando {

    private static JanelaCarregando instance;

    public static JanelaCarregando getInstance() {
        if (instance == null) instance = new JanelaCarregando();
        return instance;
    }

    private final JFrame frame;
    private final JProgressBar progressBar;

    private JanelaCarregando() {
        progressBar = new JProgressBar(0, 100);
        progressBar.setIndeterminate(true);
        progressBar.setVisible(true);

        frame = new JFrame();
        frame.setTitle("Carregando");
        frame.add(progressBar, BorderLayout.NORTH);
        frame.setSize(300, 200);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void display()
    {
        frame.setVisible(true);
    }

    public void destroy() { frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); }
}
