package dnr2i.antoine.amaury.livetweethashtag.model;

/**
 * Created by amaury on 04/02/15.
 */
public class Hashtag {

    private int id;

    private String label;

    public Hashtag(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
