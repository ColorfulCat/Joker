package lee.devis.joker.Entity;

/**
 * Description: 用户信息
 * Created by Devis on 14-7-16.
 */
public class User {

    private String created_at;
    private String last_device;
    private String role;
    private String last_visited_at;
    private String state;
    private String login;
    private String id;
    private String icon;


    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLast_device() {
        return last_device;
    }

    public void setLast_device(String last_device) {
        this.last_device = last_device;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLast_visited_at() {
        return last_visited_at;
    }

    public void setLast_visited_at(String last_visited_at) {
        this.last_visited_at = last_visited_at;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
