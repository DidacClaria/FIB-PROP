package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateKakuroView {
    private final CtrlPresentation ctrlPresentation;

    private JPanel createKakuroPanel;
    private JLabel viewTitle;
    private JButton GOBACKbutton;
    private JPanel titleContainer;
    private JPanel kakuroDisplay;
    private JPanel buttonsDisplay;
    private JTextField widthField;
    private JTextField heightField;
    private JComboBox difficultyComboBox;
    private JTextField numFilledCellsField;
    private JButton READFROMFILEButton;
    private JButton GENERATEFIELDButton;
    private JButton INFOButton;
    private JButton AUTOMATICGENERATIONButton;
    private JButton VALIDATEButton;
    private JLabel ERRORdisplay;

    public CreateKakuroView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initComponents();
    }

    private void initComponents() {
        GOBACKbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ctrlPresentation.makeUserMenuViewVisible();
            }
        });
        GENERATEFIELDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        AUTOMATICGENERATIONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                int width = Integer.parseInt(widthField.getText());
//                int height = Integer.parseInt(heightField.getText());
//                int difficulty = difficultyComboBox.getSelectedIndex();
//                int numFilledCells = Integer.parseInt(numFilledCellsField.getText());
//                ctrlPresentation.generateKakuro(width,height,difficulty,numFilledCells);
            }
        });
        READFROMFILEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
//                ctrlPresentation.validateKakuro(widht,height,kakuro);
            }
        });
        heightField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (heightField.getText().equals("") || widthField.getText().equals("")){
                    ERRORdisplay.setText("");
                }
                else {
                    if (Integer.parseInt(heightField.getText())<3 || Integer.parseInt(widthField.getText())<3){
                        ERRORdisplay.setText("This size is too small");
                    }
                    else {
                        ERRORdisplay.setText("");
                    }
                }
            }
        });
    }

    public void setVisible(boolean b) {
        createKakuroPanel.setVisible(b);
    }

    public JPanel getCreateKakuroPanel() {
        return createKakuroPanel;
    }
}
