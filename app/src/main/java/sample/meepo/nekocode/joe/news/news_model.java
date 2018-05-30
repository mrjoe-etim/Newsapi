package sample.meepo.nekocode.joe.news;



public class news_model {

    String name;
    String Author;
    String title;
    String ImageUrl;
    String description;
    String WebUrl;
    String date;

    public news_model(String name, String author, String title,
                      String imageUrl, String description,
                      String webUrl, String date) {
        this.name = name;
        Author = author;
        this.title = title;
        ImageUrl = imageUrl;
        this.description = description;
        WebUrl = webUrl;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebUrl() {
        return WebUrl;
    }

    public void setWebUrl(String webUrl) {
        WebUrl = webUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
