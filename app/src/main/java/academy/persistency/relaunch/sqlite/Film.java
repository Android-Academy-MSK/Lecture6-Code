package academy.persistency.relaunch.sqlite;

public class Film {
    private String mTitle;
    private String mDescription;
    private String mUrl;

    public Film(String title, String description, String url) {
        this.mTitle = title;
        this.mDescription = description;
        this.mUrl = url;
    }

    public Film() {
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }
}
