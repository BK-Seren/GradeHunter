package GradeHunter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * 제한 시간 내에 게이지바를 채우지 못했을 시 나타나는 GameOver 패널을 담당하는 클래스입니다.
 * @author 정서윤
 */


public class GameOver extends JPanel{

    //테스트중
    public ImageIcon stage1Image = new ImageIcon(GameOver.class.getResource("images/stage/s_1.png")); // 이미지 아이콘 로드
    public ImageIcon stage2Image = new ImageIcon(GameOver.class.getResource("images/stage/s_2.png"));
    public ImageIcon stage3Image = new ImageIcon(GameOver.class.getResource("images/stage/s_3.png"));
    public ImageIcon stage4Image = new ImageIcon(GameOver.class.getResource("images/stage/s_4.png"));
    public ImageIcon stage5Image = new ImageIcon(GameOver.class.getResource("images/stage/s_5.png"));
    public ImageIcon stage6Image = new ImageIcon(GameOver.class.getResource("images/stage/s_6.png"));
    public ImageIcon stage7Image = new ImageIcon(GameOver.class.getResource("images/stage/s_7.png"));
    public ImageIcon stage8Image = new ImageIcon(GameOver.class.getResource("images/stage/s_8.png"));
    public ImageIcon gradeAImage = new ImageIcon(GameOver.class.getResource("images/grade/g_a.png"));
    public ImageIcon gradeBImage = new ImageIcon(GameOver.class.getResource("images/grade/g_b.png"));
    public ImageIcon gradeCImage = new ImageIcon(GameOver.class.getResource("images/grade/g_c.png"));
    public ImageIcon gradeDImage = new ImageIcon(GameOver.class.getResource("images/grade/g_d.png"));
    public ImageIcon gradeFImage = new ImageIcon(GameOver.class.getResource("images/grade/g_f.png"));
    public ImageIcon gameoverImage = new ImageIcon(GameOver.class.getResource("images/gameover.png"));
    public ImageIcon mainButtonImage = new ImageIcon(GameOver.class.getResource("images/bt_main.png"));
    public ImageIcon restartButtonImage = new ImageIcon(GameOver.class.getResource("images/bt_restart.png"));
    public ImageIcon stageImage;
    public ImageIcon gradeImage;

    public GameOver(MainPanel mainPanel){

        /**
         * GameOver 클래스에 대한 기본 생성자입니다.
         */
        setLayout(null);    //레이아웃 관리자를 사용하지 않음
        setBackground(Color.BLACK);

        checkStage();
        checkGrade();

        JLabel gameoverimage = new JLabel(gameoverImage);
        gameoverimage.setBounds(50, 460, gameoverImage.getIconWidth(), gameoverImage.getIconHeight()); // x, y 위치와 너비, 높이 설정
        add(gameoverimage);

        JButton mainbutton = new JButton(mainButtonImage);
        mainbutton.setContentAreaFilled(false); // 배경을 투명하게 설정
        mainbutton.setBorderPainted(false); // 테두리를 그리지 않도록 설정
        mainbutton.setFocusPainted(false); // 포커스 테두리를 그리지 않도록 설정
        mainbutton.setBounds(640, 460, mainButtonImage.getIconWidth(), mainButtonImage.getIconHeight()); // x, y 위치와 너비, 높이 설정
        mainbutton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                mainbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));   // 커서를 손 모양으로 변경
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // MainPanel의 값 초기화 후 전환
                mainPanel.resetValues();
                // MainPanel의 메인 콘텐츠 패널로 돌아가기
                mainPanel.switchPanel(mainPanel.getMainContentPanel());
            }
        });
        add(mainbutton);


        JButton restartbutton = new JButton(restartButtonImage);
        restartbutton.setContentAreaFilled(false); // 배경을 투명하게 설정
        restartbutton.setBorderPainted(false); // 테두리를 그리지 않도록 설정
        restartbutton.setFocusPainted(false); // 포커스 테두리를 그리지 않도록 설정
        restartbutton.setBounds(640, 550, restartButtonImage.getIconWidth(), restartButtonImage.getIconHeight()); // x, y 위치와 너비, 높이 설정
        restartbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                restartbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));   // 커서를 손 모양으로 변경
            }

            @Override
            public void mouseClicked(MouseEvent e) {

                // "처음으로" 버튼 클릭시 GamePlay 패널로 전환
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(GameOver.this);
                topFrame.setContentPane(new GamePlayPanel(mainPanel));
                topFrame.revalidate();
                topFrame.repaint();

            }
        });
        add(restartbutton);

    }

    public void checkStage() {
        if (GameLogic.currentStage == 1)
            stageImage = stage1Image;
        else if (GameLogic.currentStage == 2)
            stageImage = stage2Image;
        else if (GameLogic.currentStage == 3)
            stageImage = stage3Image;
        else if (GameLogic.currentStage == 4)
            stageImage = stage4Image;
        else if (GameLogic.currentStage == 5)
            stageImage = stage5Image;
        else if (GameLogic.currentStage == 6)
            stageImage = stage6Image;
        else if (GameLogic.currentStage == 7)
            stageImage = stage7Image;
        else if (GameLogic.currentStage == 8)
            stageImage = stage8Image;
        else
            return;

        JLabel stageimage = new JLabel(stageImage);
        stageimage.setBounds(325, 125, stageImage.getIconWidth(), stageImage.getIconHeight()); // x, y 위치와 너비, 높이 설정
        add(stageimage);
    }

    public void checkGrade() {
        int gradeScore = (GameLogic.gaugeValue * 100) / GameLogic.maxGaugeValue;
        System.out.println("GradeScore = " + gradeScore);
        if (gradeScore >= 80)
            gradeImage = gradeAImage;
        else if (gradeScore >= 60 && gradeScore < 80)
            gradeImage = gradeBImage;
        else if (gradeScore >= 40 && gradeScore < 60)
            gradeImage = gradeCImage;
        else if (gradeScore >= 20 && gradeScore < 40)
            gradeImage = gradeDImage;
        else
            gradeImage = gradeFImage;

        JLabel gradeimage = new JLabel(gradeImage);
        gradeimage.setBounds(170, 260, gradeAImage.getIconWidth(), gradeAImage.getIconHeight()); // x, y 위치와 너비, 높이 설정
        add(gradeimage);
    }

}