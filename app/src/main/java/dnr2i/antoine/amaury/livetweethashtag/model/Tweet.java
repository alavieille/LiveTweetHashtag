package dnr2i.antoine.amaury.livetweethashtag.model;

/**
 * Classe qui représente un tweet
 * Created by amaury and antoine on 04/02/15.
 */
public class Tweet {

    /**
     * Pseudo du l'utlisateur qui a posté le tweet
     */
    private String pseudo;

    /**
     * Contenue du tweet
     */
    private String content;

    /**
     * Id du hashtag correpondant a ce tweet
     * Relation many to one
     */
    private String hashtagId;

    /**
     * id du tweet
     */
    private String id;


    /**
     * Créé une nouvelle instance de tweet
     * @param pseudo Pseudo du l'utlisateur
     * @param content Contenue du tweet
     * @param hashtagId Id du hashtag correpondant
     */
    public Tweet(String pseudo, String content, String hashtagId) {
        this.pseudo = pseudo;
        this.content = content;
        this.hashtagId = hashtagId;
    }

    /**
     * Créé une nouvelle instance de tweet
     * @param pseudo Pseudo du l'utlisateur
     * @param content Contenue du tweet
     * @param hashtagId Id du hashtag correpondant
     * @param id id du tweet
     */
    public Tweet(String pseudo, String content, String hashtagId, String id) {
        this.pseudo = pseudo;
        this.content = content;
        this.hashtagId = hashtagId;
        this.id = id;
    }

    /**
     * Retourne l'id du hashtag
     * @return string
     */
    public String getHashtag() {
        return hashtagId;
    }

    /**
     * Change l'id du hashtag
     * @param hashtag le nouveau id
     */
    public void setHashtag(String hashtag) {
        this.hashtagId = hashtag;
    }

    /**
     * Retourne l'id du tweet
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Change l'id du tweet
     * @param id le nouveau id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retourne le pseudo de l'utilisateur
     * @return String
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Change le pseudo de l'utilisateur
     * @param pseudo pseudo de l'utilisateur
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }


    /**
     * Retourne le contenue du tweet
     * @return String
     */
    public String getContent() {
        return content;
    }

    /**
     * Change le contenue du tweet
     * @param content nouveau contenu
     */
    public void setContent(String content) {
        this.content = content;
    }
}
