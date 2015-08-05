package lee.devis.joker.Entity;

/**
 * Description: 糗事实体类
 * Created by Devis on 14-7-16.
 */
public class Joke {

    private String image;
    private int published_at;
    private String tag;
    private User user;
    private ImageSize image_size;
    private String id;
    private Vote votes;
    private int created_at;
    private String content;
    private String state;
    private int comments_count;
    private boolean allow_comment;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPublished_at() {
        return published_at;
    }

    public void setPublished_at(int published_at) {
        this.published_at = published_at;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ImageSize getImage_size() {
        return image_size;
    }

    public void setImage_size(ImageSize image_size) {
        this.image_size = image_size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vote getVotes() {
        return votes;
    }

    public void setVotes(Vote votes) {
        this.votes = votes;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public boolean isAllow_comment() {
        return allow_comment;
    }

    public void setAllow_comment(boolean allow_comment) {
        this.allow_comment = allow_comment;
    }
}
