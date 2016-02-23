package phoenix.idex.AdapterClassModel;

/**
 * Created by Ravinder on 2/21/16.
 */
public class ItemPost {

    private String post;
    private String time;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ItemPost(String post, String time, String username) {
        this.post = post;
        this.time = time;
        this.username = username;

    }

}
