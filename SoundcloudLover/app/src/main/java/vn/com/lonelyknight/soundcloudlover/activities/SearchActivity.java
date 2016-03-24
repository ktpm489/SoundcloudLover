package vn.com.lonelyknight.soundcloudlover.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.lonelyknight.soundcloudlover.R;
import vn.com.lonelyknight.soundcloudlover.SoundcloudLoverApplication;
import vn.com.lonelyknight.soundcloudlover.adapters.SearchResultViewpagerAdapter;
import vn.com.lonelyknight.soundcloudlover.events.EventPlaylistSearchComplete;
import vn.com.lonelyknight.soundcloudlover.events.EventSearchComplete;
import vn.com.lonelyknight.soundcloudlover.fragments.FragmentSearchResultAll;
import vn.com.lonelyknight.soundcloudlover.fragments.FragmentSearchResultPlaylist;
import vn.com.lonelyknight.soundcloudlover.models.Playlist;
import vn.com.lonelyknight.soundcloudlover.models.Track;

/**
 * Created by duclm on 3/23/2016.
 */
public class SearchActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = SearchActivity.class.getSimpleName();

    private static final int FRAGMENT_ALL = 0;
    private static final int FRAGMENT_PLAYLIST = 3;

    private String mCurrentSearchTerm;

    @Bind(R.id.search_result_tabs)
    TabLayout mSearchTabLayout;
    @Bind(R.id.search_viewpager_result)
    ViewPager mSearchViewPager;
    @Bind(R.id.searchbox)
    SearchBox mSearchBox;

    private SearchBox.SearchListener mSearchBoxSearchListener = new SearchBox.SearchListener() {

        @Override
        public void onSearchOpened() {
            //Use this to tint the screen
        }

        @Override
        public void onSearchClosed() {
            //Use this to un-tint the screen
        }

        @Override
        public void onSearchTermChanged(String s) {
            //On searchTermChanged
        }

        @Override
        public void onSearch(String searchTerm) {
            Toast.makeText(SearchActivity.this, "Searching " + searchTerm + "...", Toast.LENGTH_LONG).show();
            mCurrentSearchTerm = searchTerm;

            enqueuePlaylistSearchQuery(mCurrentSearchTerm);
            enqueueSearchQuery(mCurrentSearchTerm);
        }

        @Override
        public void onResultClick(SearchResult result) {
            //React to a result being clicked
        }


        @Override
        public void onSearchCleared() {

        }
    };

    private ViewPager.OnPageChangeListener mOnViewpagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            // Trigger playlist search when Playlist tab is selected
            if (position == FRAGMENT_PLAYLIST) {
                enqueuePlaylistSearchQuery(mCurrentSearchTerm);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);


        if (mSearchViewPager != null) {
            setUpViewpager(mSearchViewPager);
        }

        mSearchTabLayout.setupWithViewPager(mSearchViewPager);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            mCurrentSearchTerm = intent.getStringExtra(SearchManager.QUERY);
        }

        setupSearchBox(mCurrentSearchTerm);
        handleSearchQuery(mCurrentSearchTerm);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    /**
     * Do search Soundcloud
     *
     * @param query : search query by user
     */
    private void handleSearchQuery(String query) {
        enqueueSearchQuery(query);
    }

    /**
     * Handle internal search query
     *
     * @param intent
     */
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            enqueueSearchQuery(query);
        }
    }

    private void setUpViewpager(ViewPager viewpager) {
        viewpager.setOffscreenPageLimit(4);
        SearchResultViewpagerAdapter viewpagerAdapter = new SearchResultViewpagerAdapter(getSupportFragmentManager());
        viewpager.addOnPageChangeListener(mOnViewpagerPageChangeListener);
        viewpagerAdapter.addFragment(new FragmentSearchResultAll(), "All");
        viewpagerAdapter.addFragment(new FragmentSearchResultAll(), "Tracks");
        viewpagerAdapter.addFragment(new FragmentSearchResultAll(), "Users");
        viewpagerAdapter.addFragment(new FragmentSearchResultPlaylist(), "Playlists");
        viewpager.setAdapter(viewpagerAdapter);
    }

    private void setupSearchBox(String queryTerm) {
        // Configure persistence searchbox
        mSearchBox.enableVoiceRecognition(this);
        mSearchBox.setSearchString(queryTerm);
        mSearchBox.setSearchListener(mSearchBoxSearchListener);
    }

    private void enqueueSearchQuery(String query) {
        Log.d(DEBUG_TAG, "enqueueSearchQuery: query = " + query);

        Call<List<Track>> call_search = SoundcloudLoverApplication.getSoundcloudApiService().searchTrackByTitle(query);
        call_search.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                SoundcloudLoverApplication.eventBus.post(new EventSearchComplete(response.body()));
            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Failed to search", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void enqueuePlaylistSearchQuery(String query) {
        Log.d(DEBUG_TAG, "enqueuePlaylistSearchQuery: query = " + query);

        Call<List<Playlist>> call_search = SoundcloudLoverApplication.getSoundcloudApiService().searchPlaylistsByTitle(query);
        call_search.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                SoundcloudLoverApplication.eventBus.post(new EventPlaylistSearchComplete(
                        response.body()));
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Failed to search", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }
}
