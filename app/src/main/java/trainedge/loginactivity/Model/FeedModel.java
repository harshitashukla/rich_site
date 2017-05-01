package trainedge.loginactivity.Model;

import com.einmalfel.earl.Item;

import java.util.Date;

/**
 * Created by hp on 14-Apr-17.
 */

public class FeedModel {
    private final Item item;
    String title;
    String imageLink;
    Date publishedDate;

    public FeedModel(Item item) {
        this.item = item;
        title = item.getTitle();
    }

    public Item getItem() {
        return item;
    }

    public String getTitle() {
        return title;
    }

    public Date getPublishedDate() {
        return item.getPublicationDate();
    }

    public String getDescription() {
        return item.getDescription();
    }

    public String getImageLink() {
        return item.getImageLink();
    }
}


