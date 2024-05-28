package klerer.rijks;

import com.andrewoid.ApiKey;
import hu.akarnokd.rxjava3.swing.SwingSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import klerer.rijks.json.ArtObject;
import klerer.rijks.json.ArtObjects;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

public class RijksSearchFrame extends JFrame {
    private RijksService rijksService;
    private JTextField searchBar;
    private JButton prevButton;
    private JButton nextButton;
    private JPanel imagePanel;
    private int currentPage = 1;
    private ApiKey apiKey = new ApiKey();
    private String keyString = apiKey.get();
    private Disposable disposable;

    public RijksSearchFrame() {
        setTitle("Rijks Museum");
        setSize(1050, 850);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        searchBar = new JTextField(60);
        prevButton = new JButton("Previous Page");
        nextButton = new JButton("Next Page");
        JPanel top = new JPanel();
        top.add(prevButton);
        top.add(searchBar);
        top.add(nextButton);
        add(top, BorderLayout.NORTH);

        imagePanel = new JPanel(new GridLayout(2, 5, 5, 5));
        add(new JScrollPane(imagePanel), BorderLayout.CENTER);

        rijksService = new RijksServiceFactory().getService();

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 1) {
                    currentPage--;
                    performSearch();
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPage++;
                performSearch();
            }
        });

        searchBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPage = 1;
                performSearch();
            }
        });

        performSearch();
    }

    private void performSearch() {

        if (searchBar.getText().isEmpty()) {
            disposable = rijksService.pageNum(keyString, currentPage)
                    // tells Rx to request the data on a background Thread
                    .subscribeOn(Schedulers.io())
                    // tells Rx to handle the response on Swing's main Thread
                    .observeOn(SwingSchedulers.edt())
                    //.observeOn(AndroidSchedulers.mainThread()) // Instead use this on Android only
                    .subscribe(
                            (response) -> handleResponse(response),
                            Throwable::printStackTrace);
        } else {
            disposable = rijksService.queryAndPageNum(keyString, searchBar.getText(), currentPage)
                    // tells Rx to request the data on a background Thread
                    .subscribeOn(Schedulers.io())
                    // tells Rx to handle the response on Swing's main Thread
                    .observeOn(SwingSchedulers.edt())
                    //.observeOn(AndroidSchedulers.mainThread()) // Instead use this on Android only
                    .subscribe(
                            (response) -> handleResponse(response),
                            Throwable::printStackTrace);
        }
    }

    private void handleResponse(ArtObjects artObjects) {
        imagePanel.removeAll();

        if (artObjects.getArtObjects() == null || artObjects.getArtObjects().length == 0) {
            JLabel noResultsLabel = new JLabel("No results found for your search.");
            imagePanel.add(noResultsLabel);
            return;
        }

        for (ArtObject art : artObjects.getArtObjects()) {
            try {
                Image image = downloadAndScaleImage(art.webImage.url);
                ImageIcon icon = new ImageIcon(image);

                JLabel label = createArtLabel(art, icon);

                imagePanel.add(label);
            } catch (IOException ex) {
                System.err.println("Error downloading image for " + art.title + ": " + ex.getMessage());
            }
        }

        imagePanel.revalidate();
        imagePanel.repaint();
    }

    private Image downloadAndScaleImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        Image image = ImageIO.read(url);
        return image.getScaledInstance(200, -1, Image.SCALE_DEFAULT);
    }

    private JLabel createArtLabel(ArtObject art, ImageIcon icon) {
        JLabel label = new JLabel(icon);
        label.setToolTipText(art.title + " by " + art.principalOrFirstMaker);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    new ImageFrame(art.title, art.principalOrFirstMaker, art.webImage.url).setVisible(true);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        return label;
    }

    public static void main(String[] args) {
        RijksSearchFrame frame = new RijksSearchFrame();
        frame.setVisible(true);
    }
}
