import javax.swing.*;
import java.awt.*;

public class ControllerJPanel extends JPanel implements Runnable {
    public final static int h = 380 * 2;
    public final static int w = 440 * 2;

    private final static int x = 200 * 2;
    private final static int y = 290 * 2;
    private final static int s = 30 * 2;


    JButton btn1 = new JButton("恢复");
    //    JButton btn2 = new JButton("配置");
    JButton btn3 = new JButton("开始");

    JLabel label1 = new JLabel("病毒传播模拟配置项", JLabel.CENTER);

    JLabel label0 = new JLabel("病毒传播率");
    JLabel label2 = new JLabel("医疗响应时间");
    JLabel label3 = new JLabel("初始化感染者");
    JLabel label4 = new JLabel("潜伏期");
    JLabel label5 = new JLabel("床位数");
    JLabel label6 = new JLabel("人员流动速率");

//    JLabel label7 = new JLabel("城市总人口数量");
//    JLabel label8 = new JLabel("病死率");
//    JLabel label9 = new JLabel("死亡时间均值");
//    JLabel label10 = new JLabel("死亡时间方差");

    JTextField text1 = new JTextField(10);
    JTextField text2 = new JTextField(10);
    JTextField text3 = new JTextField(10);
    JTextField text4 = new JTextField(10);
    JTextField text5 = new JTextField(10);
    JTextField text6 = new JTextField(10);
//    JTextField text7 = new JTextField(10);
//    JTextField text8 = new JTextField(10);
//    JTextField text9 = new JTextField(10);
//    JTextField text10 = new JTextField(10);

    public ControllerJPanel() {
        super();
        this.setBackground(new Color(0x444444));
        this.setLayout(null);

        btn1.setBounds(x, y, 60 * 2, 20 * 2);
        btn1.addActionListener(new JButtonActionListener(btn1, this));
        btn1.setFont(new Font("宋体", Font.PLAIN, 12 * 2));

        btn3.setBounds(x + (50 + 30) * 2, y, 60 * 2, 20 * 2);
        btn3.addActionListener(new JButtonActionListener(btn3, this));
        btn3.setFont(new Font("宋体", Font.PLAIN, 12 * 2));
        this.add(btn1);
        this.add(btn3);

        label1.setBounds(x - 65 * 2, y - 8 * s, 180 * 2, 20 * 2);
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("宋体", Font.BOLD, 12 * 2));
        this.add(label1);

        label0.setBounds(x - 130 * 2, y - 7 * s, 180 * 2, 20 * 2);
        label0.setForeground(Color.WHITE);
        label0.setFont(new Font("宋体", Font.PLAIN, 12 * 2));

        label2.setBounds(x - 130 * 2, y - 6 * s, 180 * 2, 20 * 2);
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("宋体", Font.PLAIN, 12 * 2));

        label3.setBounds(x - 130 * 2, y - 5 * s, 180 * 2, 20 * 2);
        label3.setForeground(Color.WHITE);
        label3.setFont(new Font("宋体", Font.PLAIN, 12 * 2));

        label4.setBounds(x - 130 * 2, y - 4 * s, 180 * 2, 20 * 2);
        label4.setForeground(Color.WHITE);
        label4.setFont(new Font("宋体", Font.PLAIN, 12 * 2));

        label5.setBounds(x - 130 * 2, y - 3 * s, 180 * 2, 20 * 2);
        label5.setForeground(Color.WHITE);
        label5.setFont(new Font("宋体", Font.PLAIN, 12 * 2));

        label6.setBounds(x - 130 * 2, y - 2 * s, 180 * 2, 20 * 2);
        label6.setForeground(Color.WHITE);
        label6.setFont(new Font("宋体", Font.PLAIN, 12 * 2));

        this.add(label0);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(label5);
        this.add(label6);


        text1.setBounds(x - 20, y - 7 * s, 180 * 2, 20 * 2);
        text1.addFocusListener(new JTextFieldHintListener(text1, "0.8", this));
        text2.setBounds(x - 20, y - 6 * s, 180 * 2, 20 * 2);
        text2.addFocusListener(new JTextFieldHintListener(text2, "10", this));
        text3.setBounds(x - 20, y - 5 * s, 180 * 2, 20 * 2);
        text3.addFocusListener(new JTextFieldHintListener(text3, "50", this));
        text4.setBounds(x - 20, y - 4 * s, 180 * 2, 20 * 2);
        text4.addFocusListener(new JTextFieldHintListener(text4, "0", this));
        text5.setBounds(x - 20, y - 3 * s, 180 * 2, 20 * 2);
        text5.addFocusListener(new JTextFieldHintListener(text5, "0", this));
        text6.setBounds(x - 20, y - 2 * s, 180 * 2, 20 * 2);
        text6.addFocusListener(new JTextFieldHintListener(text6, "0.99", this));

//        text7.setBounds(x - 20, y - 5 * s, 180 * 2, 20 * 2);
//        text7.addFocusListener(new JTextFieldHintListener(text7, "城市总人口数量，默认：5000", this));
//        text8.setBounds(x - 20, y - 4 * s, 180 * 2, 20 * 2);
//        text8.addFocusListener(new JTextFieldHintListener(text8, "病死率，默认：0.50", this));
//        text9.setBounds(x - 20, y - 3 * s, 180 * 2, 20 * 2);
//        text9.addFocusListener(new JTextFieldHintListener(text9, "死亡时间均值，默认：100", this));
//        text10.setBounds(x - 20, y - 2 * s, 180 * 2, 20 * 2);
//        text10.addFocusListener(new JTextFieldHintListener(text10, "死亡时间方差，默认：1", this));

        this.add(text1);
        this.add(text2);
        this.add(text3);
        this.add(text4);
        this.add(text5);
        this.add(text6);
//        this.add(text7);
//        this.add(text8);
//        this.add(text9);
//        this.add(text10);
    }


    @Override
    public void run() {

    }
}
