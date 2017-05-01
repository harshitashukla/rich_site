package trainedge.loginactivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.einmalfel.earl.EarlParser;
import com.einmalfel.earl.Feed;
import com.einmalfel.earl.Item;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

import trainedge.loginactivity.Model.FeedModel;

public class FeedActivity extends AppCompatActivity {

    private static final String TAG = "FEED_ACTIVITY";
    private ViewGroup flProgressContainer;
    private RecyclerView rvFeedList;
    private CustomAdapter adapter;

    int techurls[]=new int[]{R.string.techcrunch,R.string.techradar,R.string.cnet,R.string.life,R.string.elweb,R.string.balu,R.string.komodo};
    int entertainmenturls[]=new int []{R.string.cartoon,R.string.split,R.string.dilbert,R.string.daily,R.string.flowing,R.string.panel};
    int educationurls[]=new int []{R.string.bbcnewseducationandfamily,R.string.bigideas,R.string.bookbasset,R.string.brainpickings,R.string.creativityandinnovation,R.string.dolectures,R.string.learnanythingnetwork,R.string.schooltech};
    int businessurls[]=new  int []{R.string.hertwocents,R.string.calculatedrisk,R.string.nakedcapitalism,R.string.theatlantic,R.string.entrepreneur,R.string.hbr,R.string.bw,R.string.cb};
    int travelurls[]=new int[]{R.string.beautifulplace,R.string.cheapest,R.string.ibtimes,R.string.theflight,R.string.gadling};
    int foodurls[]=new int[]{R.string.cookbook,R.string.american,R.string.gardening,R.string.tastebook,R.string.recipes,R.string.makeit,R.string.cake};
    int sportsurls[]= new int[]{R.string.abcnewsespn,R.string.antaranews,R.string.washington,R.string.espn,R.string.fark,R.string.forthewin,R.string.nbcsports};
    int gadgetsurla[]=new int[]{R.string.gotta,R.string.tools,R.string.techpin,R.string.slash};
    int newsurls[]=new int[]{R.string.news3,R.string.five,R.string.rivva,R.string.abc,R.string.bbcnews,R.string.boing,R.string.cbs,R.string.cbc};
    int healthandfitnessurls[]=new int[]{R.string.medindia,R.string.latestdiet,R.string.latestdrug,R.string.latestmental,R.string.latestdiabetes,R.string.latestwomen,R.string.latestmen,R.string.latestchild};
    int politicsurls[]= new int[]{R.string.thedaily, R.string.theatlanti,R.string.liberal,R.string.weekly,R.string.newstates,R.string.snow,R.string.political};
    int fashionurls[]=new int []{R.string.fashionjobs,R.string.fashionblogs,R.string.fashionindustry,R.string.fashiongroups,R.string.fashionevents,R.string.apparelsearch,R.string.spoonfulofstyle,R.string.fash365};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        flProgressContainer = (ViewGroup) findViewById(R.id.flProgressContainer);
        flProgressContainer.setVisibility(View.GONE);

        Intent feedIntent = getIntent();
        if (feedIntent != null && feedIntent.hasExtra("trainedge.scoop.EXTRA_URL")) {
            String url = feedIntent.getStringExtra("trainedge.scoop.EXTRA_URL");
            String name = feedIntent.getStringExtra("trainedge.scoop.EXTRA_NAME");
            getSupportActionBar().setTitle(name);
            FeedFetchTask task = new FeedFetchTask();

            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
            flProgressContainer.setVisibility(View.VISIBLE);
        }
        else{
            finish();
        }
        rvFeedList = (RecyclerView) findViewById(R.id.rvFeedList);
        rvFeedList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomAdapter(this);
    }


    class FeedFetchTask extends AsyncTask<String, Void, ArrayList<FeedModel>> {

        @Override
        protected void onPreExecute() {

        }

        @Override

        protected ArrayList<FeedModel> doInBackground(String... strings) {
            String link = strings[0];
            InputStream inputStream = null;
            ArrayList<FeedModel> feeds = new ArrayList<>();
            ArrayList<Feed> retreivedFeed = new ArrayList<>();
            try {
                inputStream = new URL(link).openConnection().getInputStream();
                retreivedFeed.add(EarlParser.parseOrThrow(inputStream, 0));
                for (Feed feed : retreivedFeed) {
                    for (Item item : feed.getItems()) {
                        //add the data to your arraylist
                        feeds.add(new FeedModel(item));
                        //testingw
                        //Log.i(TAG, "Item title: " + (item.getTitle() == null ? "N/A" : item.getTitle()));
                        //Log.i(TAG, "Item author: " + (item.getAuthor() == null ? "N/A" : item.getAuthor()));
                        Log.i(TAG, "Item description: " + (item.getDescription() == null ? "N/A" : item.getDescription()));
                        Log.i(TAG, "Image link: " + (item.getImageLink() == null ? "N/A" : item.getImageLink()));
                        //Log.i(TAG, "link: " + (item.getImageLink() == null ? "N/A" : item.getLink()));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DataFormatException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } finally {
                try {

                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return feeds;
        }


        @Override
        protected void onPostExecute(ArrayList<FeedModel> objects) {
            updateRecyclerFeeds(objects);
            flProgressContainer.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        public void onActivityStopped() {

        }
    }

    private void updateRecyclerFeeds(ArrayList<FeedModel> modelArrayList) {
        adapter.setListContent(modelArrayList);
        rvFeedList.setAdapter(adapter);
    }


}
