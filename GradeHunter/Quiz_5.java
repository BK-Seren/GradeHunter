package GradeHunter;




import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 퀴즈 분야 - 역사 를 담당하는 클래스입니다.
 * @author 서보경
 */

public class Quiz_5 extends JPanel implements Quiz{

    private List<QuizItem> quizzes;
    private int currentQuizIndex;
    private JLabel imageLabel;
    private JTextField answerField;
    private Timer timer;
    private JLabel timerLabel;
    private int remainingSeconds = 10;

    public ImageIcon oImage = new ImageIcon("GradeHunter/images/o.png");
    public ImageIcon xImage = new ImageIcon("GradeHunter/images/x.png");
    public JLabel oPopup = new JLabel(oImage);
    public JLabel xPopup = new JLabel(xImage);

    private MainPanel mainPanel;


    public Quiz_5(MainPanel mainPanel) {

        this.mainPanel = mainPanel;

        quizzes = initializeQuizzes();
        currentQuizIndex = 0;

        oPopup.setBounds(320, 100, 400, 400);
        oPopup.setVisible(false);
        add(oPopup);
        xPopup.setBounds(320, 100, 400, 400);
        xPopup.setVisible(false);
        add(xPopup);

        setupUI();
        showNextQuiz();
    }

    private List<QuizItem> initializeQuizzes() {
        List<QuizItem> quizzes = new ArrayList<>();
        quizzes.add(new QuizItem("images/quiz_5/1.png", "1", 1080,720));
        quizzes.add(new QuizItem("images/quiz_5/2.png", "2",1080,720));
        quizzes.add(new QuizItem("images/quiz_5/3.png", "2",1080,720));
        quizzes.add(new QuizItem("images/quiz_5/4.png", "1",1080,720));
        quizzes.add(new QuizItem("images/quiz_5/5.png", "2",1080,720));
        quizzes.add(new QuizItem("images/quiz_5/6.png", "3",1080,720));
        quizzes.add(new QuizItem("images/quiz_5/7.png", "1",1080,720));
        quizzes.add(new QuizItem("images/quiz_5/8.png", "3",1080,720));
        quizzes.add(new QuizItem("images/quiz_5/9.png", "1",1080,720));
        quizzes.add(new QuizItem("images/quiz_5/10.png", "3",1080,720));

        // 랜덤으로 5개 선택
        Collections.shuffle(quizzes);
        return quizzes.subList(0,5);
    }

    @Override
    public void setupUI() {
        setLayout(new BorderLayout());
        setSize(1080,720);
        setLocation(0,0);

        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imageLabel = new JLabel();
        QuizItem firstQuiz = quizzes.get(0);
        imageLabel.setIcon(new ImageIcon(Main.class.getResource(firstQuiz.getImagePath())));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        add(imagePanel, BorderLayout.NORTH);
        imagePanel.add(imageLabel, BorderLayout.NORTH);

        answerField = new JTextField();
        answerField.setBounds(450, 560, 200, 50);
        answerField.setOpaque(false);
        answerField.setBorder(null);
        answerField.setForeground(Color.WHITE);
        Font font = new Font("SansSerif", Font.PLAIN, 50);
        answerField.setFont(font);
        imageLabel.add(answerField);

        // KeyListener 추가
        answerField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c != '1' && c != '2' && c != '3') {
                    e.consume(); // 1, 2, 3 이외의 입력은 무시
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!answerField.getText().isEmpty()) {
                        checkAnswerAndShowNextQuiz();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // 필요에 따라 구현
            }
        });

        timerLabel = new JLabel("10"); // 초기값은 10초
        timerLabel.setFont(new Font("Arial", Font.BOLD, 48));
        timerLabel.setForeground(Color.WHITE); // 텍스트 색상을 흰색으로 설정
        timerLabel.setBounds(980, 30, 100, 100);
        imageLabel.add(timerLabel);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingSeconds--;
                updateTimerLabel();

                if (remainingSeconds == 0) {
                    timer.stop();
                    checkAnswer(answerField.getText());
                    answerField.setText(""); // 텍스트 필드 비우기
                    showNextQuiz();
                    answerField.requestFocusInWindow();
                }
            }
        });
        timer.setRepeats(true);

        startTimer(); // 시작 시 타이머 동작
    }

    // 타이머 레이블을 업데이트하는 메서드
    @Override
    public void updateTimerLabel() {
        SwingUtilities.invokeLater(() -> {
            timerLabel.setText(Integer.toString(remainingSeconds)); // 초 단위로 표시
        });
    }
    @Override
    public void displayImage(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(Main.class.getResource(imagePath));
        Image image = imageIcon.getImage();
        imageLabel.setIcon(new ImageIcon(image));
    }
    @Override
    public void showNextQuiz() {
        if (currentQuizIndex < quizzes.size()) {
            QuizItem quiz = quizzes.get(currentQuizIndex);
            timer.stop();
            displayImage(quiz.getImagePath());
            startTimer(); // 다음 퀴즈를 보여주기 전에 타이머 재시작
            currentQuizIndex++;
        } else {
            JOptionPane.showMessageDialog(this, "게임 종료!");
            SwingUtilities.invokeLater(() -> {
                EndingPanel endingPanel = new EndingPanel(mainPanel);
                mainPanel.switchPanel(endingPanel);
                endingPanel.setVisible(true);
            });
        }
    }
    @Override
    public void checkAnswerAndShowNextQuiz() {
        timer.stop();
        checkAnswer(answerField.getText());
        answerField.setText(""); // 텍스트 필드 비우기

        // 다음 문제로 넘어가기 전 팝업창에 맞춰 2초간 딜레이
        Timer delayTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextQuiz();
                answerField.requestFocusInWindow();
            }
        });
        delayTimer.setRepeats(false); // 타이머가 한 번만 실행되도록 설정
        delayTimer.start();
    }

    @Override
    public void startTimer() {
        timer.stop();
        timer.setInitialDelay(0);
        timer.restart();
        timer.setDelay(1000);
        remainingSeconds = 10;
    }

    @Override
    public void checkAnswer(String userAnswer) {
        QuizItem quizItem = quizzes.get(currentQuizIndex - 1);
        String correctAnswer = quizItem.getAnswer();

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            oPopup.setVisible(true);
            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    oPopup.setVisible(false);
                }
            });
            timer.start();

        } else {
            System.out.println("오답 처리 시작"); // 로그 출력
            xPopup.setVisible(true);
            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("xPopup 숨김 처리"); // 숨김 처리 로그
                    xPopup.setVisible(false);
                }
            });
            timer.start();
        }
    }
}



