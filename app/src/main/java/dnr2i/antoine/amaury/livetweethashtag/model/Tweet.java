package dnr2i.antoine.amaury.livetweethashtag.model;

/**
 * Created by amaury on 04/02/15.
 */
public class Tweet {


    private String pseudo;

    private String content;

    private String hashtag;

    private String id;



    public Tweet(String pseudo, String content, String hashtag) {
        this.pseudo = pseudo;
        this.content = content;
        this.hashtag = hashtag;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
