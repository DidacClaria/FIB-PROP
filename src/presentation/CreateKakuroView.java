package presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class represents the CreateKakuroView and with all the components added into the createKakuroPanel it is represented. It communicates with the Presentation Controller.
 */
public class CreateKakuroView {
    private final CtrlPresentation ctrlPresentation;

    private JPanel createKakuroPanel;
    private JLabel viewTitle;
    private JButton GOBACKbutton;
    private JPanel titleContainer;
    private JPanel buttonsDisplay;
    private JTextField widthField;
    private JTextField heightField;
    private JComboBox difficultyComboBox;
    private JTextField numFilledCellsField;
    private JButton READFROMFILEButton;
    private JButton INFOButton;
    private JButton AUTOMATICGENERATIONButton;
    private JButton VALIDATEButton;
    private JLabel ERRORdisplay;
    private JPanel kakuroPanel;

    private int rows, cols;
    private String field[][];

    /**
     * Default CreateKakuroView creator.
     * @param ctrlPresentation It contains the reference of the Presentation Controller.
     */
    public CreateKakuroView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        rows=cols=-1;
        field=null;
        initComponents();
    }

    /**
     * This method initialize all the different buttons and their specific behaviour.
     */
    private void initComponents() {
        initINFOButton();
        ERRORdisplay.setText("");
        GOBACKbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ERRORdisplay.setText("");
                widthField.setText("");
                heightField.setText("");
                numFilledCellsField.setText("");
                createKakuroPanel.setVisible(false);
                ctrlPresentation.makeUserMenuViewVisible();
            }
        });
        READFROMFILEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readFile();
            }
        });
        INFOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "La primera línia conté dos nombres n (número de files) i m (número de columnes)\n" +
                        "Les següents n línies contenen m elements separats per ',' on cadascun defineix una cel.la\n" +
                        "Valors de cada cel∙la:\n" +
                        "1. ? per indicar cel.la blanca a omplir\n" +
                        "2. nombre per indicar una cel.la ja emplenada\n" +
                        "3. * per indicar cel.la negra buida\n" +
                        "4. Fnombre per indicar cel.la negra amb suma de fila\n" +
                        "5. Cnombre per indicar cel.la negra amb suma de columna\n" +
                        "6. CnombreFnombre per indicar cel.la negra amb sumes de columna i fila\n" +
                        "Es tracta de representar l’àrea del kakuro com una matriu nombre_files X nombre_columnes.\n" +
                        "D’aquesta manera, el kakuro inicial de l’enunciat es representaria:\n" +
                        "9,9\n" +
                        "*,*,C19,C12,*,*,*,C7,C10\n" +
                        "*,F14,?,?,C4,C11,C17F4,?,?\n" +
                        "*,C7F36,?,?,?,?,?,?,?\n" +
                        "F12,?,?,F10,?,?,?,C25,C14\n" +
                        "F3,?,?,C20,C11F20,?,?,?,?\n" +
                        "F17,?,?,?,?,C8,F6,?,?\n" +
                        "*,C11,C7F13,?,?,?,C4F10,?,?\n" +
                        "F28,?,?,?,?,?,?,?,*\n" +
                        "F6,?,?,*,*,F8,?,?,*");
            }
        });
        VALIDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idKakuro = ctrlPresentation.proposeKakuro(rows,cols,field);
                if (idKakuro == -1) ERRORdisplay.setText("The kakuro has no solution");
                else {
                    JOptionPane.showMessageDialog(null,"The kakuro with id "+idKakuro+" was created succesfully!");
                    createKakuroPanel.setVisible(false);
                    ctrlPresentation.makeUserMenuViewVisible();
                    ERRORdisplay.setText("");
                }
            }
        });
        AUTOMATICGENERATIONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Integer.parseInt(heightField.getText())<3 || Integer.parseInt(widthField.getText())<3){
                        ERRORdisplay.setText("The size of the field is too small");
                    }
                    else {
                        ERRORdisplay.setText("");
                        widthField.setText("");
                        heightField.setText("");
                        numFilledCellsField.setText("");
                    }
                    int width = Integer.parseInt(widthField.getText());
                    int height = Integer.parseInt(heightField.getText());
                    int difficulty = difficultyComboBox.getSelectedIndex();
                    int numFilledCells = Integer.parseInt(numFilledCellsField.getText());
                    int kakuroID = ctrlPresentation.generateKakuro(width,height,difficulty,numFilledCells);

                    kakuroPanel.removeAll();
                    kakuroPanel.repaint();
                    kakuroPanel.revalidate();

                    kakuroPanel.add(new KakuroGrid(rows,cols,field,false));
                    kakuroPanel.repaint();
                    kakuroPanel.revalidate();

                    JOptionPane.showMessageDialog(null,"The kakuro with id #"+kakuroID+"was created succesfully!");
                    setVisible(false);
                    ctrlPresentation.makeUserMenuViewVisible();
                }
                catch (NumberFormatException ignored){
                    ERRORdisplay.setText("Please add the size of the field.");
                }

                //TESTING GETTING FIELD
//                KakuroGrid aux = (KakuroGrid) kakuroPanel.getComponent(0);
//                String[][] fieldAux = aux.getFieldStatus();
//                for (int i=0; i<9; ++i ){
//                    for (int j=0; j<9; ++j){
//                        System.out.println(fieldAux[i][j]);
//                    }
//                }
            }
        });
    }

    /**
     * This method configures the format information button.
     */
    private void initINFOButton() {
        ImageIcon infoIcon = new ImageIcon(new ImageIcon("./DOCS/info.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        INFOButton.setIcon(infoIcon);
        INFOButton.setText("");
        INFOButton.setBorderPainted(false);
        INFOButton.setFocusPainted(false);
        INFOButton.setContentAreaFilled(false);
    }

    /**
     * This method sets the panel of the view as visible or not depending on the bool of the parameter.
     * @param b Indicates whether the view must show or not.
     */
    public void setVisible(boolean b) {
        ERRORdisplay.setText("");
        createKakuroPanel.setVisible(b);
    }

    /**
     * Getter method of the createKakuroPanel.
     * @return It returns the instance of the createKakuroPanel.
     */
    public JPanel getCreateKakuroPanel() {
        return createKakuroPanel;
    }

    /**
     * This method initialize the custom components of the view.
     */
    private void createUIComponents() {
        kakuroPanel = new JPanel(new GridLayout());
        kakuroPanel.add(new KakuroGrid(10,10,null,false));
        createBackImage();
    }

    /**
     * This method configure the GOBACKbutton so that it has the desired appearance.
     */
    private void createBackImage () {
        try {
            BufferedImage image;
            image = ImageIO.read(new File("DOCS/gobackLogo.png"));
            Image newImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon (newImage);
            GOBACKbutton = new JButton(icon);
            GOBACKbutton.setText("");
            GOBACKbutton.setBorderPainted(false);
            GOBACKbutton.setFocusPainted(false);
            GOBACKbutton.setContentAreaFilled(false);
        } catch (IOException ex) {
//            System.out.println("The file does not exists");
        }
    }

    /**
     * This method opens up a FileChooser and once it is selected, it reads it and tries to print it into the kakuroPanel if it's valid.
     */
    private void readFile(){
        try {
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.showOpenDialog(null);
            File f = fileChooser.getSelectedFile();
            Scanner sca = new Scanner(f);
            String s = sca.nextLine();

            String[] input = s.split (",");
            rows = Integer.parseInt(input[0]);
            cols = Integer.parseInt(input[1]);
            field = new String[rows][cols];

            for (int i = 0; i<rows; ++i) {
                s = sca.nextLine();
                String[] text = s.split (",");
                if (cols >= 0) System.arraycopy(text, 0, field[i], 0, cols);
            }
            String errorMessage = ctrlPresentation.validateKakuro(rows,cols,field);
            if (!errorMessage.equals("OK")) ERRORdisplay.setText(errorMessage);
            else {
                kakuroPanel.removeAll();
                kakuroPanel.repaint();
                kakuroPanel.revalidate();

                kakuroPanel.add(new KakuroGrid(rows,cols,field,false));
                kakuroPanel.repaint();
                kakuroPanel.revalidate();
            }
        }
        catch(Exception ignored){}
    }
}
