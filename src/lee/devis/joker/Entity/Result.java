package lee.devis.joker.Entity;

/**
 * Description: 接口返回的数据集合实体类
 * Created by Devis on 14-7-17.
 */
public class Result {

    private int count;
    private Object items;
    private int total;
    private int page;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getItems() {
        return items;
    }

    public void setItems(Object items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
