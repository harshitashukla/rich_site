package trainedge.loginactivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;

import trainedge.loginactivity.Model.FeedModel;

/**
 * Created by Dell on 05-Jan-17.
 */

public class CustomAdapter extends RecyclerView.Adapter<Holder> {


    private ArrayList<FeedModel> list_members = new ArrayList<>();
    private final LayoutInflater inflater;
    View view;
    private Context context;
    private Holder hold;

    public CustomAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.custom_row, parent, false);
        hold = new Holder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final FeedModel list_item = list_members.get(position);
        holder.tvTitle.setText(list_item.getTitle());
        String description = parse_html(list_item.getDescription());
        //Log.i("feed1", description);
        if (!description.isEmpty()) {
            holder.tvDesc.setText(description);
        } else {
            holder.tvDesc.setHeight(0);
        }
        holder.tvPubDate.setText(String.valueOf(list_item.getPublishedDate()));
        final String imageLink = list_item.getImageLink();
        if (imageLink == null || imageLink.isEmpty()) {
            Picasso.with(context).load(findImageinDescription(description)).into(holder.feedImg);
        } else {
            Picasso.with(context).load(imageLink).into(holder.feedImg);
        }
        holder.tvReadLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBrowser(list_item.getItem().getLink());
            }
        });
        holder.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareLink(list_item);
            }
        });
        holder.ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String link = list_item.getItem().getLink();
                final DatabaseReference fav = FirebaseDatabase.getInstance().getReference("favorites").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                fav.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("title", list_item.getTitle());
                        map.put("link", link);
                        map.put("image", imageLink);

                        if (dataSnapshot.hasChildren()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                if (snapshot.child("link").getValue(String.class).equals(link)) {
                                    break;
                                } else {
                                    fav.push().setValue(map);
                                    break;
                                }

                            }
                        } else {
                            fav.push().setValue(map, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                    if (databaseError == null) {
                                        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void shareLink(FeedModel item) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "\n\n");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, item.getItem().getLink());
        context.startActivity(Intent.createChooser(sharingIntent, "Share this to"));

    }

    private String findImageinDescription(String content) {
        try {
            if (!content.isEmpty())
                return Jsoup.parse(content).select("img").get(0).attr("src");
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    private void openBrowser(String link) {
        //open your browser
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        context.startActivity(intent);
    }

    private String parse_html(String content) {

        StringBuilder paragraph = getParas(content);

        //if paragraph were not found
        if (paragraph.toString().isEmpty()) {

            return stripNbsp(getOnlyText(content));
        }
        return stripNbsp(paragraph.toString());
    }

    private String stripNbsp(String content) {
        return Parser.unescapeEntities(content, false);
    }


    private String getOnlyText(String content) {
        Document document = Jsoup.parse(content);
        return Jsoup.clean(document.body().html(), Whitelist.none());
    }

    @NonNull
    private StringBuilder getParas(String content) {
        StringBuilder paragraph = new StringBuilder();

        if (content != null) {
            Document document = Jsoup.parse(content);
            Elements paras = document.getElementsByTag("p");
            int count = 0;
            for (Element para : paras) {
                if (count == 3) break;
                paragraph.append(para.text()).append(" ");
                count++;
            }
        }
        return paragraph;
    }

    public void setListContent(ArrayList<FeedModel> list_members) {
        this.list_members = list_members;
        notifyItemRangeChanged(0, list_members.size());

    }

    @Override
    public int getItemCount() {
        return list_members.size();
    }
}
