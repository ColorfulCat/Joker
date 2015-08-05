package lee.devis.joker.Entity;

/**
 * Description:投票结果
 * Created by Devis on 14-7-16.
 */
public class Vote {

    private int up;
    private int down;

    public int getUp() {
        return up;
    }

    public int getDown() {
        return down;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public void setDown(int down) {
        this.down = down;
    }
}
