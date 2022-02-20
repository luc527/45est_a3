import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;

public class JanelaPrincipal
{
    private Estado estado;
    private JFrame frame;

    public JanelaPrincipal()
    {
        JButton btnCarregar = new JButton("Carregar");
        JButton btnProfundidade = new JButton("Profundidade");
        JButton btnLargura = new JButton("Largura");
        JButton btnSobre = new JButton("Sobre");

        SpinnerNumberModel spinnerPmaxModel = new SpinnerNumberModel(-1, -1, Integer.MAX_VALUE, 1);
        JSpinner spinnerPmax = new JSpinner(spinnerPmaxModel);
        JLabel lbPmax = new JLabel("Profundidade máxima (-1 = ∞)");

        JPanel panelPmax = new JPanel(new BorderLayout());
        panelPmax.add(lbPmax, BorderLayout.NORTH);
        panelPmax.add(spinnerPmax, BorderLayout.SOUTH);

        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotoes.add(btnCarregar);
        panelBotoes.add(panelPmax);
        panelBotoes.add(btnProfundidade);
        panelBotoes.add(btnLargura);
        panelBotoes.add(btnSobre);

        JPanel panel = new JPanel(new BorderLayout());
        EstadoImagem estadoImagem = new EstadoImagem();
        panel.add(estadoImagem, BorderLayout.CENTER);
        panel.add(panelBotoes, BorderLayout.PAGE_END);

        frame = new JFrame();
        frame.setContentPane(panel);
        frame.setTitle("Ball Sort Puzzle Solver – Trabalho de 45EST");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        btnProfundidade.setEnabled(false);
        btnLargura.setEnabled(false);

        btnCarregar.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("Arquivos de texto", "txt"));
            int returnVal = chooser.showOpenDialog(panel);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    String jogo = Files.readString(
                            Path.of(chooser.getSelectedFile().getAbsolutePath()));
                    estado = Estado.from(jogo);

                    if (!estado.valido()) {
                        JOptionPane.showMessageDialog(
                            frame,
                            "Jogo inválido. "+estado.corQueNaoAparece4vezes()+" não aparece 4 vezes.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    estadoImagem.setEstado(estado);
                    btnProfundidade.setEnabled(true);
                    btnLargura.setEnabled(true);
                    frame.repaint();
                    frame.pack();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnLargura.addActionListener(e -> {
            fazerBusca(new BuscaLargura(), (Integer) spinnerPmax.getValue(), "Largura");
        });

        btnProfundidade.addActionListener(e -> {
            fazerBusca(new BuscaProfundidade(), (Integer) spinnerPmax.getValue(), "Profundidade");
        });

        btnSobre.addActionListener(e -> {
            JanelaSobre.getInstance().display();
        });

        frame.repaint();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void fazerBusca(Busca busca, int pmax, String titulo) {
        List<Estado> solucao = busca.fazer(estado, pmax);
        if (!busca.sucesso()) {
            JOptionPane.showMessageDialog(frame,
                "Não conseguimos encontrar uma solução",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
        } else {
            int tempoMs = busca.tempoMs();
            new JanelaSolucao(solucao, titulo, tempoMs);
        }
    }
}
