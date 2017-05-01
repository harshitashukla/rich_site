package trainedge.loginactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private SharedPreferences pref;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    private ArrayList<CategoryModel> CategoryList;
    private RecyclerView rvCategory;

    private CategoryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Button lgt =
        mAuth = FirebaseAuth.getInstance();
//objt
        //lgt.setOnClickListener(this);

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // if (mAuth.getCurrentUser()==null){
                    Intent logint = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(logint);
                    finish();

                    // }
                }
            }
        };


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        CategoryList = new ArrayList<>();


        rvCategory = (RecyclerView) findViewById(R.id.rvCategory);
        GridLayoutManager manager = new GridLayoutManager(this, 1);

        rvCategory.setLayoutManager(manager);
        generateCategories();

    }


    public void generateCategories() {
        CategoryList.add(new CategoryModel("Tech", R.drawable.tech));
        CategoryList.add(new CategoryModel("Gadgets", R.drawable.gadgets));
        CategoryList.add(new CategoryModel("Entertainment", R.drawable.entertainment));
        CategoryList.add(new CategoryModel("Politics", R.drawable.politics));
        CategoryList.add(new CategoryModel("Sports", R.drawable.sports));
        CategoryList.add(new CategoryModel("Business", R.drawable.business));
        CategoryList.add(new CategoryModel("News", R.drawable.news));
        CategoryList.add(new CategoryModel("Education", R.drawable.education));
        CategoryList.add(new CategoryModel("Travel", R.drawable.travel));
        CategoryList.add(new CategoryModel("Health and Fitness", R.drawable.health));
        CategoryList.add(new CategoryModel("Food", R.drawable.food));
        CategoryList.add(new CategoryModel("Fashion", R.drawable.fashion));
        adapter = new CategoryAdapter(CategoryList);
        rvCategory.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);

        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.activity_settings) {
            Intent setIntent = new Intent(HomeActivity.this, Settings.class);
            startActivity(setIntent);
            return true;
        } else if (id == R.id.activity_logout) {
            mAuth.signOut();
            try {
                LoginManager.getInstance().logOut();
                AccessToken.setCurrentAccessToken(null);
            } catch (Exception ignored) {

            }
            Intent lgtIntent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(lgtIntent);
            finish();

        } else if (id == R.id.activity_exit) {
            finish();
        } /*else if (id == R.id.activity_search) {


            // TODO: 09-Apr-17
        }*/ else if (id == R.id.activity_about) {
            Intent aboutIntent = new Intent(HomeActivity.this, Aboutus.class);
            startActivity(aboutIntent);
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nav_fav:
                Intent fav = new Intent(HomeActivity.this, FavoritesActivity.class);
                startActivity(fav);
                break;

            case R.id.nav_share:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT, "Scoop");

                share.putExtra(Intent.EXTRA_TEXT, "Your friend has invited you to join the app./n To join click the link");
                startActivity(Intent.createChooser(share, "Share via..."));
                break;
            case R.id.nav_feedback:
                Intent feedIntent = new Intent(HomeActivity.this, Feedback.class);
                startActivity(feedIntent);
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }


}