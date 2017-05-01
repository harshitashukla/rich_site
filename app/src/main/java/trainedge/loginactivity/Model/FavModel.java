package trainedge.loginactivity.Model;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by hp on 19-Apr-17.
 */

public class FavModel {

    private final String title;
    private final String link;
    private final String image;

    public FavModel(DataSnapshot snapshot) {
        title = snapshot.child("title").getValue(String.class);
        link = snapshot.child("link").getValue(String.class);
        image = snapshot.child("image").getValue(String.class);
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getImage() {
        return image;
    }
}
