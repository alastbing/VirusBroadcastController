import javax.swing.*;

/**
 * 模拟程序主入口
 *
 * @author
 * @comment GinRyan
 */
public class Main {

    public static void main(String[] args) {
        initControllerPanel();
    }

    public static void initControllerPanel() {
        ControllerJPanel p = new ControllerJPanel();
        Thread panelThread = new Thread(p);
        JFrame frame = new JFrame();
        frame.add(p);
        frame.setSize(ControllerJPanel.w, ControllerJPanel.h);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("新型冠状病毒传播模拟控制器");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        panelThread.start();//开启画布线程，即世界线程，接着看代码的下一站可以转MyPanel.java
    }

}
