import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class JTextFieldHintListener implements FocusListener {
    private String hintText;
    private JTextField textField;
    private JPanel myJPanel;

    public JTextFieldHintListener(JTextField jTextField, String hintText, JPanel myJPanel) {
        this.textField = jTextField;
        this.hintText = hintText;
        this.myJPanel = myJPanel;
        jTextField.setText(hintText);  //默认直接显示
        jTextField.setFont(new Font("宋体", Font.PLAIN, 10 * 2));
        jTextField.setForeground(Color.GRAY);
    }

    @Override
    public void focusGained(FocusEvent e) {
        //获取焦点时，清空提示内容
        String temp = textField.getText();
        if (temp.equals(hintText)) {
            textField.setText("");
            textField.setForeground(Color.BLACK);
        }

    }

    @Override
    public void focusLost(FocusEvent e) {
        //失去焦点时，没有输入内容，显示提示内容
        String temp = textField.getText();
        if (temp.equals("")) {
            textField.setForeground(Color.GRAY);
            textField.setText(hintText);
        } else {
            System.out.println(temp);
//            JOptionPane.showMessageDialog(myJPanel, temp, "温馨提示", JOptionPane.WARNING_MESSAGE);
        }

    }
}