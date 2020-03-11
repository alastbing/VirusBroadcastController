import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class VirusBroadcastView {
//    public static int hospitalWidth;

    private Constants constants;

    private Hospital hospital;

    private PersonPool personPool;

    private MyPanel p;

    private int sw;
    private int sh;
    private int inHx;

    public VirusBroadcastView(Constants constants, Hospital hospital, PersonPool personPool, MyPanel p, int sw, int sh, int inHx) {
        this.constants = constants;
        this.hospital = hospital;
        this.personPool = personPool;
        this.p = p;
        this.sw = sw;
        this.sh = sh;
        this.inHx = inHx;
    }

//        initHospital();
//        initPanel();
//        initInfected();


    public void initPanel() {
        Thread panelThread = new Thread(p);
        JFrame frame = new JFrame();
        frame.add(p);
        frame.setSize(sw, sh);
//        frame.setSize(Constants.CITY_WIDTH + hospital.getWidth() + 300, Constants.CITY_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("新型冠状病毒传播模拟");
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setFocusable(true);
        frame.getContentPane().setBackground(new Color(0x444444));
//        frame.pack();
//        frame.setVisible(true);  //设置jframe可见
        panelThread.start();
    }

    /**
     * 初始化初始感染者
     */
    public void initInfected() {
        Long cityCenterX = Math.round(inHx * 0.5);
        Long cityCenterY = Math.round(sh * 0.5);
        personPool.initPersonPool(cityCenterX.intValue(), cityCenterY.intValue());
        List<Person> people = personPool.getPersonList();//获取所有的市民
        for (int i = 0; i < constants.getOriginalCount(); i++) {
            Person person;
            do {
                person = people.get(new Random().nextInt(people.size() - 1));//随机挑选一个市民
            } while (person.isInfected());//如果该市民已经被感染，重新挑选
            person.beInfected();//让这个幸运的市民成为感染者
        }
    }
}
