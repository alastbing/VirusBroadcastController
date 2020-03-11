import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 主面板。
 *
 * @ClassName: MyPanel
 * @Description: 主面板
 * @author: Bruce Young
 * @date: 2020年02月02日 17:03
 */
public class MyPanel extends JPanel implements Runnable {
    private Constants constants;
    private Hospital hospital;
    private PersonPool personPool;

    private int times;

    private int hx;

    public MyPanel(Constants constants, Hospital hospital, PersonPool personPool, int hx, int times) {
        super();
        this.setBackground(new Color(0x444444));
//        this.setBackground(Color.GRAY);

        this.constants = constants;
        this.hospital = hospital;
        this.personPool = personPool;
        this.hx = hx;
        this.times = times;
    }


//    public MyPanel() {
//        super();
//        this.setBackground(new Color(0x444444));
////        this.setLayout(null);
//    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(0x00ff00));//设置医院边界颜色
        //绘制医院边界
        g.drawRect(hospital.getX(), hospital.getY(),
                hospital.getWidth(), hospital.getHeight());
        g.setFont(new Font("微软雅黑", Font.BOLD, 16 * times));
        g.setColor(new Color(0x00ff00));

        if (times > 1) {
            g.drawString("医院", hospital.getX(), hospital.getY() - 16);
        } else {
            g.drawString("医院", hospital.getX() + hospital.getWidth() / 4, hospital.getY() - 16);
        }

        //绘制代表人类的圆点
        List<Person> people = personPool.getPersonList();
        if (people == null) {
            return;
        }
        for (Person person : people) {
            switch (person.getState()) {
                case Person.State.NORMAL: {
                    //健康人
                    g.setColor(new Color(0xdddddd));
                    break;
                }
                case Person.State.SHADOW: {
                    //潜伏期感染者
                    g.setColor(new Color(0xffee00));
                    break;
                }

                case Person.State.CONFIRMED: {
                    //确诊患者
                    g.setColor(new Color(0xff0000));
                    break;
                }
                case Person.State.FREEZE: {
                    //已隔离者
                    g.setColor(new Color(0x48FFFC));
                    break;
                }
                case Person.State.DEATH: {
                    //死亡患者

                    g.setColor(new Color(0x000000));
                    break;
                }
            }
            person.update();//对各种状态的市民进行不同的处理
            g.fillOval(person.getX(), person.getY(), 3, 3);

        }

        int captionStartOffsetX = hospital.getX() + hospital.getWidth() + 40;
        if (times > 1)
            captionStartOffsetX = hx + hospital.getWidth() + 40 * times;
        int captionStartOffsetY = 100;
        int captionSize = 24 * times;

        //显示数据信息
        g.setColor(Color.WHITE);
        g.drawString("城市总人数：" + constants.getCityPersonSize(), captionStartOffsetX, captionStartOffsetY);
        g.setColor(new Color(0xdddddd));
        g.drawString("健康者人数：" + personPool.getPeopleSize(Person.State.NORMAL), captionStartOffsetX, captionStartOffsetY + captionSize);
        g.setColor(new Color(0xffee00));
        g.drawString("潜伏期人数：" + personPool.getPeopleSize(Person.State.SHADOW), captionStartOffsetX, captionStartOffsetY + 2 * captionSize);
        g.setColor(new Color(0xff0000));
        g.drawString("发病者人数：" + personPool.getPeopleSize(Person.State.CONFIRMED), captionStartOffsetX, captionStartOffsetY + 3 * captionSize);
        g.setColor(new Color(0x48FFFC));
        g.drawString("已隔离人数：" + personPool.getPeopleSize(Person.State.FREEZE), captionStartOffsetX, captionStartOffsetY + 4 * captionSize);
        g.setColor(new Color(0x00ff00));
        g.drawString("空余病床：" + Math.max(constants.getBedCount() - personPool.getPeopleSize(Person.State.FREEZE), 0), captionStartOffsetX, captionStartOffsetY + 5 * captionSize);
        g.setColor(new Color(0xE39476));
        //暂定急需病床数量为 NEED = 确诊发病者数量 - 已隔离住院数量
        //
        int needBeds = personPool.getPeopleSize(Person.State.CONFIRMED)
                - personPool.getPeopleSize(Person.State.FREEZE);

        g.drawString("急需病床：" + (needBeds > 0 ? needBeds : 0), captionStartOffsetX, captionStartOffsetY + 6 * captionSize);
        g.setColor(new Color(0xccbbcc));
        g.drawString("病死人数：" + personPool.getPeopleSize(Person.State.DEATH), captionStartOffsetX, captionStartOffsetY + 7 * captionSize);
        g.setColor(new Color(0xffffff));
        g.drawString("世界时间（天）：" + (int) (worldTime / 10.0), captionStartOffsetX, captionStartOffsetY + 8 * captionSize);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 0, y = 0;
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/cn.jpeg"));
        //("003.jpg");// 003.jpg是测试图片在项目的根目录下
        g.drawImage(icon.getImage(), x, y, hx,
                getSize().height, this);// 图片会自动缩放
//    g.drawImage(icon.getImage(), x, y,this);//图片不会自动缩放
    }

    public Timer timer = new Timer();

    public int worldTime = 0;//世界时间

    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            MyPanel.this.repaint();
            worldTime++;
        }
    }

    public int getWorldTime() {
        return worldTime;
    }

    public void setWorldTime(int worldTime) {
        this.worldTime = worldTime;
    }

    @Override
    public void run() {
        timer.schedule(new MyTimerTask(), 0, 100);//启动世界计时器，时间开始流动（突然脑补DIO台词：時は停た）
    }

}
