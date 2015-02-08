package dnr2i.antoine.amaury.livetweethashtag.model;

/**
 * Classe qui représente un hashtag avec un id et un label
 * Created by amaury and antoine on 04/02/15.
 */
public class Hashtag {

    /**
     * id du hashtag
     */
    private int id;

    /**
     * label du hashtag
     */
    private String label;

    /**
     * Créé une nouvelle instance
     * @param id id du hashtag
     * @param label label du hashtag
     */
    public Hashtag(int id, String label) {
        this.id = id;
        this.label = label;
    }

    /**
     * Retourne l'id du hashtag
     * @return id du hashtag
     */
    public int getId() {
        return id;
    }

    /**
     * Change l'id du hashtag
     * @param id nouvelle id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retourne le label du hashtag
     * @return String label du hashtag
     */
    public String getLabel() {
        return label;
    }

    /**
     * Change le label du hashtag
     * @param label le nouveau label
     */
    public void setLabel(String label) {
        this.label = label;
    }
}
