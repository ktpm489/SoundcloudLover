package vn.com.lonelyknight.soundcloudlover.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.com.lonelyknight.soundcloudlover.R;
import vn.com.lonelyknight.soundcloudlover.SoundcloudLoverApplication;
import vn.com.lonelyknight.soundcloudlover.apis.SoundcloudAPIEndpoint;
import vn.com.lonelyknight.soundcloudlover.events.EventSearchComplete;
import vn.com.lonelyknight.soundcloudlover.models.Track;

public class SoundcloudLoverMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String BASE_URL = "https://api.soundcloud.com/";
    @Bind(R.id.drawerlayout_main_content)
    DrawerLayout navigationDrawer;
    @Bind(R.id.searchbox)
    SearchBox searchBox;
    private Retrofit retrofitClient;
    private SoundcloudAPIEndpoint soundcloudApiService;

    private SearchBox.MenuListener mSearchBoxMenuListener = new SearchBox.MenuListener() {
        @Override
        public void onMenuClick() {
            navigationDrawer.openDrawer(GravityCompat.START);
        }
    };

    private SearchBox.SearchListener mSearchBoxSearchListener = new SearchBox.SearchListener() {

        @Override
        public void onSearchOpened() {
            //Use this to tint the screen
            Toast.makeText(SoundcloudLoverMainActivity.this, "onSearchOpened", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSearchClosed() {
            //Use this to un-tint the screen
        }

        @Override
        public void onSearchTermChanged(String s) {

        }

        @Override
        public void onSearch(String searchTerm) {
            Toast.makeText(SoundcloudLoverMainActivity.this, searchTerm + " Searched", Toast.LENGTH_LONG).show();
//            enqueueSearchQuery(searchTerm);
            Intent searchIntent = new Intent(SoundcloudLoverMainActivity.this, SearchActivity.class);
            searchIntent.setAction(Intent.ACTION_SEARCH);
            searchIntent.putExtra(SearchManager.QUERY, searchTerm);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(SoundcloudLoverMainActivity.this, searchBox, "searchbox");
            startActivity(searchIntent, options.toBundle());
        }

        @Override
        public void onResultClick(SearchResult result) {
            //React to a result being clicked
        }


        @Override
        public void onSearchCleared() {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Configure persistence searchbox
        searchBox.enableVoiceRecognition(this);
        searchBox.setLogoText(getResources().getString(R.string.app_name));
        searchBox.setLogoTextColor(getResources().getColor(R.color.colorListItemSubtitle));
        searchBox.setMenuListener(mSearchBoxMenuListener);
        searchBox.revealFromMenuItem(R.id.action_search, this);
        searchBox.setSearchListener(mSearchBoxSearchListener);

        navigationDrawer = (DrawerLayout) findViewById(R.id.drawerlayout_main_content);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        retrofitClient = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        soundcloudApiService = retrofitClient.create(SoundcloudAPIEndpoint.class);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SoundcloudLoverApplication.eventBus.unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SoundcloudLoverApplication.eventBus.register(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

//        MenuItem searchItem = menu.findItem(R.id.action_search);
//
//        SearchManager searchManager = (SearchManager) SoundcloudLoverMainActivity.this.getSystemService(Context.SEARCH_SERVICE);
//
//        SearchView searchView = null;
//        if (searchItem != null) {
//            searchView = (SearchView) searchItem.getActionView();
//        }
//        if (searchView != null) {
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(SoundcloudLoverMainActivity.this.getComponentName()));
//        }
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d("soundcloud", "OnNewIntent");
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void enqueueSearchQuery(String query) {
        Call<List<Track>> call_search = soundcloudApiService.searchTrackByTitle(query);
        call_search.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                SoundcloudLoverApplication.eventBus.post(new EventSearchComplete(response.body()));
            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                Toast.makeText(SoundcloudLoverMainActivity.this, "Failed to search", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d("soundcloud", "OnNewIntent: ACTION_SEARCH, query = " + query);

            enqueueSearchQuery(query);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            searchBox.revealFromMenuItem(R.id.action_search, this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Toast.makeText(this, "On item selected: " + item.getItemId(), Toast.LENGTH_SHORT).show();
        navigationDrawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {
        if (navigationDrawer.isDrawerOpen(GravityCompat.START)) {
            navigationDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
