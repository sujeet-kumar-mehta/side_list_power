package in.sample.saltlistpower.model;

import com.google.gson.annotations.SerializedName;

public class ItemModel {

    /**
     * sample response
     * <p>
     * {"image": "http://dummyimage.com/715x350/105B19/907ECC",
     * "description": "sterilizer span ticks continuity hubs procurement vision eggs backups cries gap iron conferences torpedo government catchers restaurant destroyers attribute counsel echo overcurrent classes trip environments forecastle giants conspiracies suppression things rope plans bow blots rescuers incline",
     * "title": "terminations map autos sons utilizations"}
     */

    @SerializedName("image")
    private String image;

    @SerializedName("description")
    private String description;

    @SerializedName("title")
    private String title;

    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * THis is a model class to represent in the list
     *
     * @param image
     * @param description
     * @param title
     */
    public ItemModel(String image, String description, String title) {
        this.image = image;
        this.description = description;
        this.title = title;
    }

    public ItemModel() {
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
