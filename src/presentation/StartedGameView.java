package presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartedGameView {

    private int idKakuro;

    private final CtrlPresentation ctrlPresentation;

    private JPanel startedGamePanel;
    private JPanel headerContainer;
    private JLabel titleLabel;
    private JButton GOBACKButton;
    private JScrollPane mygames;


    public StartedGameView(CtrlPresentation ctrlPresentation, int id) {
        this.idKakuro = id;
        this.ctrlPresentation = ctrlPresentation;
        initComponents();
    }

    private void initComponents() {

        titleLabel.setText("MY GAMES OF KAKURO " + idKakuro);

        GOBACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ctrlPresentation.iniGame(idKakuro);
                ctrlPresentation.makeSelectGameViewVisible();
            }
        });
    }

    public void setVisible(boolean b) {
        startedGamePanel.setVisible(b);
    }
    
    public void setGameVisible(int idGame) {ctrlPresentation.makePlayGameViewVisible(idGame);}
    
    public void setSelectGameVisible() {ctrlPresentation.makeSelectGameViewVisible();}
    
    public void deleteGame (int id_game) {
        String username = ctrlPresentation.getActiveUser();
        ctrlPresentation.deleteGame(username, idKakuro, id_game);
    }

    public JPanel getStartedGamePanel() {
        return startedGamePanel;
    }

    private void createUIComponents() {
        createBackImage();
        createListGames();
    }

    private void createBackImage () {
        try {
            BufferedImage image;
            image = ImageIO.read(new File("DOCS/gobackLogo.png"));
            Image newImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon (newImage);
            GOBACKButton = new JButton(icon);
        } catch (IOException ex) {
            System.out.println("The file does not exists");
        }
    }

    private void createListGames () {

        String username = ctrlPresentation.getActiveUser();
        String [] files = ctrlPresentation.getGames(username, idKakuro);
        if (files == null) mygames = new JScrollPane();
        else {
            JPanel aux = new JPanel();

            for (int i = 0; i < files.length; ++i) {
                if (i % 2 != 0) {
                    int id = files[i].charAt(5) - '0';
                    JPanel aux2 = new RowSelectGame(this, id);
                    aux2.setBorder(BorderFactory.createLineBorder(Color.black));
                    aux.add(aux2);
                }
            }
            aux.setLayout(new BoxLayout(aux, BoxLayout.Y_AXIS));
            mygames = new JScrollPane(aux);
        }
    }


}
