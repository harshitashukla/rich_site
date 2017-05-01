package trainedge.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashMap;

public class ChoiceActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private RecyclerView rvCategory;
    HashMap<String, Integer> choicesTech = new HashMap<>();
    HashMap<String, Integer> choicesFashion = new HashMap<>();
    HashMap<String, Integer> choicesEducation = new HashMap<>();
    HashMap<String, Integer> choicesBusiness = new HashMap<>();
    HashMap<String, Integer> choicesTravel = new HashMap<>();
    HashMap<String, Integer> choicesEntertainment = new HashMap<>();
    HashMap<String, Integer> choicesFood = new HashMap<>();
    HashMap<String, Integer> choicesSport = new HashMap<>();
    HashMap<String, Integer> choicesGadgets = new HashMap<>();
    HashMap<String, Integer> choicesNews = new HashMap<>();
    HashMap<String, Integer> choicesHealth_and_fitness = new HashMap<>();
    HashMap<String, Integer> choicesPolitics = new HashMap<>();
    private int categoryType;
    private Object[] feedNames;
    private HashMap<String, Integer> megaList;
    private ListView lvChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        choicesTech.put("Techcrunch", R.string.techcrunch);
        choicesTech.put("Komodo Media", R.string.komodo);
        choicesTech.put("BaluArt", R.string.balu);
        choicesTech.put("LifeHack", R.string.life);
        choicesTech.put("El Webmaster", R.string.elweb);
        choicesTech.put("Cnet", R.string.cnet);
        choicesTech.put("Techradar", R.string.techradar);

        choicesGadgets.put("Gottabemobile", R.string.gotta);
        choicesGadgets.put("Tools and Toys" , R.string.tools);
        choicesGadgets.put("TechPin", R.string.techpin);
        choicesGadgets.put("SlashGear", R.string.slash);

        choicesEntertainment.put("CartoonBrew", R.string.cartoon);
        choicesEntertainment.put("Splitsider", R.string.split);
        choicesEntertainment.put("Dilbert Daily Strip", R.string.dilbert);
        choicesEntertainment.put("Daily Telegraph", R.string.daily);
        choicesEntertainment.put("Flowing Data", R.string.flowing);
        choicesEntertainment.put("Panel Syndicate", R.string.panel);


        choicesPolitics.put("The Daily Dish", R.string.thedaily);
        choicesPolitics.put("The Atlantic", R.string.theatlanti);
        choicesPolitics.put("Liberal Conspiracy", R.string.liberal);
        choicesPolitics.put("Weekly Sift", R.string.weekly);
        choicesPolitics.put("New Statesman", R.string.newstates);
        choicesPolitics.put("Snowblog", R.string.snow);
        choicesPolitics.put("Political Wire", R.string.political);

        choicesSport.put("ABC News: ESPN Sports", R.string.abcnewsespn);
        choicesSport.put("ANTARA News: Sports", R.string.antaranews);
        choicesSport.put("The Washington Post", R.string.washington);
        choicesSport.put("ESPN.com", R.string.espn);
        choicesSport.put("Fark.com", R.string.fark);
        choicesSport.put("For The Win", R.string.forthewin);
        choicesSport.put("NBCSports", R.string.nbcsports);


        choicesFashion.put("Fashion Jobs", R.string.fashionjobs);
        choicesFashion.put("Fashion Blogs", R.string.fashionblogs);
        choicesFashion.put("Fashion Industry", R.string.fashionindustry);
        choicesFashion.put("Fashion Groups", R.string.fashiongroups);
        choicesFashion.put("Fashion Events", R.string.fashionevents);
        choicesFashion.put("Apparel Search", R.string.apparelsearch);
        choicesFashion.put("Spoonful of Style", R.string.spoonfulofstyle);
        choicesFashion.put("Fashion365",R.string.fash365);

        choicesEducation.put("BBC News- Education and Family", R.string.bbcnewseducationandfamily);
        choicesEducation.put("Big Ideas", R.string.bigideas);
        choicesEducation.put("Book Basset", R.string.bookbasset);
        choicesEducation.put("Brain Pickings", R.string.brainpickings);
        choicesEducation.put("Creativity and Innovation", R.string.creativityandinnovation);
        choicesEducation.put("Do Lectures", R.string.dolectures);
        choicesEducation.put("Learn Anything Network", R.string.learnanythingnetwork);
        choicesEducation.put("SchoolTech for Students", R.string.schooltech);

        choicesBusiness.put("Her Two Cents", R.string.hertwocents);
        choicesBusiness.put("Calculated Risk", R.string.calculatedrisk);
        choicesBusiness.put("Naked Capitalism", R.string.nakedcapitalism);
        choicesBusiness.put("The Atlantic - Business", R.string.theatlantic);
        choicesBusiness.put("Entrepreneur", R.string.entrepreneur);
        choicesBusiness.put("HBR.org", R.string.hbr);
        choicesBusiness.put("BusinessWeek.com", R.string.bw);
        choicesBusiness.put("Capital Business", R.string.cb);

        choicesTravel.put("Beautiful Place To Visit", R.string.beautifulplace);
        choicesTravel.put("Cheapest Destinations", R.string.cheapest);
        choicesTravel.put("IBTimes.com", R.string.ibtimes);
        choicesTravel.put("The Flight Deal", R.string.theflight);
        choicesTravel.put("Gandling", R.string.gadling);

        choicesFood.put("101 CookBooks", R.string.cookbook);
        choicesFood.put("American Drink", R.string.american);
        choicesFood.put("Gardening", R.string.gardening);
        choicesFood.put("tastebook", R.string.tastebook);
        choicesFood.put("Recipes and Menus", R.string.recipes);
        choicesFood.put("Make it Tonight", R.string.makeit);
        choicesFood.put("CakeWrecks", R.string.cake);

        choicesNews.put("3 News", R.string.news3);
        choicesNews.put("Five ThirtyEight", R.string.five);
        choicesNews.put("Rivva", R.string.rivva);
        choicesNews.put("ABC News", R.string.abc);
        choicesNews.put("BoingBoing", R.string.boing);
        choicesNews.put("CBS News", R.string.cbs);
        choicesNews.put("BBC News", R.string.bbcnews);
        choicesNews.put("CBC", R.string.cbc);

        choicesHealth_and_fitness.put("MedIndia Health", R.string.medindia);
        choicesHealth_and_fitness.put("Latest Diet", R.string.latestdiet);
        choicesHealth_and_fitness.put("Latest Drug", R.string.latestdrug);
        choicesHealth_and_fitness.put("Latest Mental", R.string.latestmental);
        choicesHealth_and_fitness.put("Latest Diabetes", R.string.latestdiabetes);
        choicesHealth_and_fitness.put("Latest Women Health", R.string.latestwomen);
        choicesHealth_and_fitness.put("Latest Men Health", R.string.latestmen);
        choicesHealth_and_fitness.put("Latest Child Health", R.string.latestchild);



        lvChoice = (ListView) findViewById(R.id.lvChoice);
        if (getIntent() != null && getIntent().hasExtra("trainedge.scoop.EXTRA_CATEGORY")) {
            String category = getIntent().getStringExtra("trainedge.scoop.EXTRA_CATEGORY");
            getSupportActionBar().setSubtitle(category);
            categoryType = getIntent().getIntExtra("trainedge.scoop.EXTRA_POS", 0);
            ArrayAdapter<Object> adapter = null;
            switch (categoryType) {
                case 0:
                    feedNames = choicesTech.keySet().toArray();
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feedNames);
                    break;
                case 1:
                    feedNames = choicesGadgets.keySet().toArray();
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feedNames);
                    break;
                case 2:
                    feedNames = choicesEntertainment.keySet().toArray();
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feedNames);
                    break;
                case 3:
                    feedNames = choicesPolitics.keySet().toArray();
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feedNames);
                    break;
                case 4:
                    feedNames = choicesSport.keySet().toArray();
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feedNames);
                    break;
                case 5:
                    feedNames = choicesBusiness.keySet().toArray();
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feedNames);
                    break;
                case 6:
                    feedNames = choicesNews.keySet().toArray();
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feedNames);
                    break;
                case 7:
                    feedNames = choicesEducation.keySet().toArray();
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feedNames);
                    break;
                case 8:
                    feedNames = choicesTravel.keySet().toArray();
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feedNames);
                    break;
                case 9:
                    feedNames = choicesHealth_and_fitness.keySet().toArray();
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feedNames);
                    break;
                case 10:
                    feedNames = choicesFood.keySet().toArray();
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feedNames);
                    break;
                case 11:
                    feedNames = choicesFashion.keySet().toArray();
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feedNames);
                    break;
                case -1:
                    megaList = new HashMap<String, Integer>();
                    megaList.putAll(choicesTech);
                    megaList.putAll(choicesFashion);
                    megaList.putAll(choicesEducation);
                    megaList.putAll(choicesBusiness);
                    megaList.putAll(choicesTravel);
                    megaList.putAll(choicesEntertainment);
                    megaList.putAll(choicesFood);
                    megaList.putAll(choicesSport);
                    megaList.putAll(choicesGadgets);
                    megaList.putAll(choicesNews);
                    megaList.putAll(choicesHealth_and_fitness);
                    megaList.putAll(choicesPolitics);

                    feedNames = megaList.keySet().toArray();
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feedNames);
                    break;
            }
            lvChoice.setAdapter(adapter);
            lvChoice.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedUrl = null;
        String feedKey = feedNames[i].toString();
        switch (categoryType) {
            case 0:
                selectedUrl = getResources().getString(choicesTech.get(feedKey));
                break;
            case 1:
                Integer id = choicesGadgets.get(feedKey);
                selectedUrl = getResources().getString(id);
                break;
            case 2:
                selectedUrl = getResources().getString(choicesEntertainment.get(feedKey));
                break;
            case 3:
                selectedUrl = getResources().getString(choicesPolitics.get(feedKey));
                break;
            case 4:
                selectedUrl = getResources().getString(choicesSport.get(feedKey));
                break;
            case 5:
                selectedUrl = getResources().getString(choicesBusiness.get(feedKey));
                break;
            case 6:
                selectedUrl = getResources().getString(choicesNews.get(feedKey));
                break;
            case 7:
                selectedUrl = getResources().getString(choicesEducation.get(feedKey));
                break;
            case 8:
                selectedUrl = getResources().getString(choicesTravel.get(feedKey));
                break;
            case 9:
                selectedUrl = getResources().getString(choicesHealth_and_fitness.get(feedKey));
                break;
            case 10:
                selectedUrl = getResources().getString(choicesFood.get(feedKey));
                break;
            case 11:
                selectedUrl = getResources().getString(choicesFashion.get(feedKey));
                break;
            case -1:
                selectedUrl = getResources().getString(megaList.get(feedKey));
                break;
        }
        Intent feedIntent = new Intent(this, FeedActivity.class);
        feedIntent.putExtra("trainedge.scoop.EXTRA_URL", selectedUrl);
        feedIntent.putExtra("trainedge.scoop.EXTRA_NAME", feedKey);
        startActivity(feedIntent);
    }
}

