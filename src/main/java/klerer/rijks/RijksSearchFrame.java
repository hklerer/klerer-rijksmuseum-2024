package klerer.rijks;

import com.andrewoid.ApiKey;
import hu.akarnokd.rxjava3.swing.SwingSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import klerer.rijks.json.RijksCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RijksSearchFrame extends JFrame {
    private RijksService rijksService;
    private JTextField searchBar;
    private JButton prevButton;
    private JButton nextButton;
    private JPanel imagePanel;
    private int currentPage = 1;
    ApiKey apiKey = new ApiKey();
    String keyString = apiKey.get();
    private Disposable disposable;

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

    private void handleResponse(RijksCollection rijksCollection) {
        imagePanel.removeAll();
    }
    public static void main(String[] args) {
        RijksSearchFrame frame = new RijksSearchFrame();
        frame.setVisible(true);
    }
}
