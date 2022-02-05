import javax.swing.*;

public class JanelaSobre
{
    private static JanelaSobre instance;

    public static JanelaSobre getInstance() {
        if (instance == null) instance = new JanelaSobre();
        return instance;
    }

    private JFrame frame;

    private JanelaSobre()
    {
        JLabel lb1 = new JLabel("Desenvolvido por: ");
        JLabel lb2 = new JLabel("Lucas Moraes Schwambach – lucas.schwambach52@gmail.com");
        JLabel lb3 = new JLabel(" TODO ");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(lb1);
        panel.add(lb2);
        panel.add(lb3);

        frame = new JFrame();
        frame.setTitle("Sobre");
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void display()
    {
        frame.setVisible(true);
    }
}
