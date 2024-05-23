package klerer.rijks;

import javax.swing.*;
import java.awt.*;

public class RijksSearchFrame extends JFrame {
    private RijksService rijksService;
    private JTextField searchBar;
    private JButton prevButton;
    private JButton nextButton;
    private JPanel imagePanel;

    public RijksSearchFrame() {
        setTitle("Rijks Museum");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        searchBar = new JTextField(50);
        prevButton = new JButton("Previous Page");
        nextButton = new JButton("Next Page");
        JPanel top = new JPanel();
        top.add(prevButton);
        top.add(searchBar);
        top.add(nextButton);
        add(top, BorderLayout.NORTH);

        imagePanel = new JPanel(new GridLayout(5, 5, 5, 5));
        add(new JScrollPane(imagePanel), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        RijksSearchFrame frame = new RijksSearchFrame();
        frame.setVisible(true);
    }
}
