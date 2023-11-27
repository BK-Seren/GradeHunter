package GradeHunter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;
/**
 * 메인 화면을 담당하는 클래스입니다.
 * @author 서보경
 */
public class MainPanel extends JFrame{

    public ImageIcon startButtonImage = new ImageIcon(Main.class.getResource("images/bt_guide.png"));
    public ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("images/bt_guide_entered.png"));
    public ImageIcon rankButtonImage = new ImageIcon(Main.class.getResource("images/bt_rank.png"));
    public ImageIcon rankButtonEnteredImage = new ImageIcon(Main.class.getResource("images/bt_rank_entered.png"));

    public ImageIcon mCheckImage = new ImageIcon("GradeHunter/images/check_m.png");
    public ImageIcon fCheckImage = new ImageIcon("GradeHunter/images/check_f.png");
    public ImageIcon mCheckEnteredImage = new ImageIcon("GradeHunter/images/check_entered_m.png");
    public ImageIcon fCheckEnteredImage = new ImageIcon("GradeHunter/images/check_entered_f.png");

    public JButton startButton = new JButton(startButtonImage);
    public JButton rankButton = new JButton(rankButtonImage);

    public JButton mCheckButton = new JButton(mCheckImage);

    public JButton fCheckButton = new JButton(fCheckImage);

    /*public JCheckBox mCheckBox = new JCheckBox("");
    public JCheckBox fCheckBox = new JCheckBox("");*/

    public static final int SCREEN_WIDTH = 1080;
    public static final int SCREEN_HEIGHT = 720;

    public static Image background = new ImageIcon(Main.class.getResource("images/bg_main.png")).getImage();

    public Container cPane;
    public JTextField studentID;
    public JButton saveButton;
    public String savedText;

    public static JFrame guide;
    public static JFrame rank;

    public static JFrame qspanel;

    public MainPanel(){
        cPane = getContentPane();
        setTitle("Grade Hunter");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        Toolkit kit = Toolkit.getDefaultToolkit();

        Image img = kit.getImage(Main.class.getResource("images/Gradcap.png"));
        setIconImage(img);

        drawback mainpanel = new drawback();
        startButton.setBounds(360,500,360,80);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(true);
        startButton.addMouseListener(new MouseAdapter() {

            /**
             * 마우스가 입학 버튼에 올라갈 경우 이미지와 커서모양을 바꿔주는 메소드입니다.
             */
            @Override
            public void mouseEntered(MouseEvent e){
                startButton.setIcon(startButtonEnteredImage);
                startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            /**
             * 마우스가 입학 버튼 범위에서 벗어났을 경우 원래 이미지와 커서로 바꿔주는 메소드입니다.
             */

            public void mouseExited(MouseEvent e){
                startButton.setIcon(startButtonImage);
                startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            public void mousePressed(MouseEvent e){
                remove(mainpanel);
                repaint();
                /*JFrame guidepanel = new GuideFrame();
                guidepanel.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
                cPane.add(guidepanel);
                guidepanel.setVisible(true);
                guidepanel.repaint();*/
                /*JPanel quizselectpanel = new QuizSelectPanel(qspanel);
                quizselectpanel.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
                cPane.add(quizselectpanel);
                quizselectpanel.setVisible(true);
                quizselectpanel.repaint();*/
            }

        });

        rankButton.setBounds(880,500,140,140);
        rankButton.setBorderPainted(false);
        rankButton.setContentAreaFilled(false);
        rankButton.setFocusPainted(false);
        rankButton.addMouseListener(new MouseAdapter() {
            /**
             * 마우스가 역대졸업생 버튼에 올라갈 경우 이미지와 커서모양을 바꿔주는 메소드입니다.
             */
            @Override
            public void mouseEntered(MouseEvent e){
                rankButton.setIcon(rankButtonEnteredImage);
                rankButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            /**
             * 마우스가 역대졸업생 버튼 범위에서 벗어났을 경우 원래 이미지와 커서로 바꿔주는 메소드입니다.
             */

            public void mouseExited(MouseEvent e){
                rankButton.setIcon(rankButtonImage);
                rankButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            public void mousePressed(MouseEvent e){
                remove(mainpanel);
                repaint();
                JPanel rankpanel = new EndCreditsPanel(rank);
                rankpanel.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
                cPane.add(rankpanel);
                rankpanel.setVisible(true);
                rankpanel.repaint();
            }
        });

        ButtonGroup checkGroup = new ButtonGroup();
        checkGroup.add(mCheckButton);
        checkGroup.add(fCheckButton);

        mCheckButton.setBounds(370,405,50,60);
        mCheckButton.setBorderPainted(false);
        mCheckButton.setContentAreaFilled(false);
        mCheckButton.setFocusPainted(false);
        mCheckButton.addMouseListener(new MouseAdapter() {
            /**
             * 마우스가 남자 체크 버튼에 올라갈 경우 커서모양을 바꿔주는 메소드입니다.
             */
            @Override
            public void mouseEntered(MouseEvent e){
                mCheckButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            /**
             * 마우스가 역대졸업생 버튼 범위에서 벗어났을 경우 원래 커서로 바꿔주는 메소드입니다.
             */

            public void mouseExited(MouseEvent e){
                mCheckButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            /**
             * 마우스로 클릭했을 때, 체크 되었다는 표시와 성별 정보를 저장합니다.
             */
            public void mousePressed(MouseEvent e){
                mCheckButton.setIcon(mCheckEnteredImage);
                fCheckButton.setIcon(fCheckImage);
            }
        });
        fCheckButton.setBounds(570,405,50,60);
        fCheckButton.setBorderPainted(false);
        fCheckButton.setContentAreaFilled(false);
        fCheckButton.setFocusPainted(false);
        fCheckButton.addMouseListener(new MouseAdapter() {
            /**
             * 마우스가 남자 체크 버튼에 올라갈 경우 커서모양을 바꿔주는 메소드입니다.
             */
            @Override
            public void mouseEntered(MouseEvent e){
                fCheckButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            /**
             * 마우스가 역대졸업생 버튼 범위에서 벗어났을 경우 원래 커서로 바꿔주는 메소드입니다.
             */

            public void mouseExited(MouseEvent e){
                fCheckButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            /**
             * 마우스로 클릭했을 때, 체크 되었다는 표시와 성별 정보를 저장합니다.
             */
            public void mousePressed(MouseEvent e){
                fCheckButton.setIcon(fCheckEnteredImage);
                mCheckButton.setIcon(mCheckImage);

            }
        });

       /* ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(mCheckBox);
        genderGroup.add(fCheckBox);

        mCheckBox.setBounds(388,438,15,15);
        fCheckBox.setBounds(588,438,15,15);
        mCheckBox.setOpaque(false);
        fCheckBox.setOpaque(false);*/

        /*ActionListener checkBoxListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton)e.getSource();
                boolean selected = abstractButton.getModel().isSelected();

            }
        };*/

        /*mCheckBox.addActionListener(checkBoxListener);
        fCheckBox.addActionListener(checkBoxListener);*/

        studentID = new JTextField();
        studentID.setBounds(450,350,200,50);
        studentID.setOpaque(false);
        studentID.setBorder(null);
        studentID.setForeground(Color.WHITE);
        Font font = new Font("SansSerif", Font.PLAIN, 30);
        studentID.setFont(font);
        // 저장 버튼 초기화
        saveButton = new JButton("Save");
        saveButton.setBounds(720,360,50,30);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 텍스트 필드의 내용을 변수에 저장
                savedText = studentID.getText();
                JOptionPane.showMessageDialog(MainPanel.this, "Text saved: " + savedText);
            }
        });


        cPane.add(mainpanel);
        mainpanel.setLayout(null);
        mainpanel.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        mainpanel.add(startButton);
        mainpanel.add(rankButton);
        mainpanel.add(mCheckButton);
        mainpanel.add(fCheckButton);
        /*mainpanel.add(mCheckBox);
        mainpanel.add(fCheckBox);*/
        mainpanel.add(studentID);
        mainpanel.add(saveButton);
        setVisible(true);

    }
    /**
     * cPane에  이미지를 그려주는 메소드 입니다.
     */


    class drawback extends JPanel{
        @Override
        public void paintComponent(Graphics graphics){
            /**
             * cPane에 시작화면을 그려주는 메소드 입니다.
             * @param graphics
             */
            super.paintComponent(graphics);

            graphics.drawImage(background,0,0, getWidth(), getHeight(),this );
        }

    }

}
