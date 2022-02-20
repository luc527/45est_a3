import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;

public class JanelaPrincipal
{
    private Estado estado;

    public JanelaPrincipal()
    {
        JButton btnCarregar = new JButton("Carregar");
        JButton btnProfundidade = new JButton("Profundidade");
        JButton btnLargura = new JButton("Largura");
        JButton btnSobre = new JButton("Sobre");

        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotoes.add(btnCarregar);
        panelBotoes.add(btnProfundidade);
        panelBotoes.add(btnLargura);
        panelBotoes.add(btnSobre);

        JPanel panel = new JPanel(new BorderLayout());
        EstadoImagem estadoImagem = new EstadoImagem();
        panel.add(estadoImagem, BorderLayout.CENTER);
        panel.add(panelBotoes, BorderLayout.PAGE_END);

        JFrame frame = new JFrame();
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
            BuscaLargura busca = new BuscaLargura();
            List<Estado> solucao = busca.fazer(estado);
            int tempoMs = busca.tempoMs();
            new JanelaSolucao(solucao, "Largura", tempoMs);
        });

        btnProfundidade.addActionListener(e -> {
            BuscaProfundidade busca = new BuscaProfundidade();
            List<Estado> solucao = busca.fazer(estado);
            int tempoMs = busca.tempoMs();
            new JanelaSolucao(solucao, "Profundidade", tempoMs);
        });

        btnSobre.addActionListener(e -> {
            JanelaSobre.getInstance().display();
        });

        frame.repaint();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
