package klerer.rijks;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageFrame extends JFrame {
    public ImageFrame(String title, String artist, String imageUrl) throws Exception {
        setTitle(title + " by " + artist);
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        URL url = new URL(imageUrl);
        Image image = ImageIO.read(url);
        Image scaledImage = image.getScaledInstance(700, -1, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(scaledImage);

        JLabel label = new JLabel(imageIcon);
        JScrollPane scrollPane = new JScrollPane(label);

        add(scrollPane, BorderLayout.CENTER);
    }
}
