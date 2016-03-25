package vn.com.lonelyknight.soundcloudlover.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import vn.com.lonelyknight.soundcloudlover.DividerItemDecoration;
import vn.com.lonelyknight.soundcloudlover.EndlessRecyclerViewScrollListener;
import vn.com.lonelyknight.soundcloudlover.R;
import vn.com.lonelyknight.soundcloudlover.SoundcloudLoverApplication;
import vn.com.lonelyknight.soundcloudlover.activities.SearchActivity;
import vn.com.lonelyknight.soundcloudlover.adapters.SoundcloudPlaylistSearchAdapter;
import vn.com.lonelyknight.soundcloudlover.apis.SoundcloudAPIRequestHelper;
import vn.com.lonelyknight.soundcloudlover.events.EventPlaylistSearchComplete;
import vn.com.lonelyknight.soundcloudlover.events.EventPlaylistSearchLoadMoreCompleted;
import vn.com.lonelyknight.soundcloudlover.events.EventPlaylistSearchLoadMoreError;
import vn.com.lonelyknight.soundcloudlover.events.EventTrackSearchLoadMoreCompleted;

/**
 * Created by duclm on 3/23/2016.
 */
public class FragmentSearchResultPlaylist extends Fragment {

    private static final String DEBUG_TAG = FragmentSearchResultPlaylist.class.getSimpleName();

    @Bind(R.id.recyclerview_search_result)
    RecyclerView mRecyclerView;
    @Bind(R.id.layout_explore_soundcloud)
    View layoutExploreSoundcloud;

    private Context mContext;
    private SoundcloudPlaylistSearchAdapter mRecyclerAdapter;
    private LinearLayoutManager mLayoutManager;

    public FragmentSearchResultPlaylist() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View searchResultView = inflater.inflate(R.layout.fragment_soundcloud_lover_main, container, false);

        ButterKnife.bind(this, searchResultView);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider)));
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                loadMorePlaylist(page);
            }
        });

        return searchResultView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        SoundcloudLoverApplication.eventBus.register(this);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        SoundcloudLoverApplication.eventBus.unregister(this);
        mContext = null;
    }

    @Subscribe
    public void onPlaylistSearchCompleted(EventPlaylistSearchComplete event) {
        mRecyclerAdapter = new SoundcloudPlaylistSearchAdapter(mContext, event.getSearchResultData(), new SoundcloudPlaylistSearchAdapter.OnErrorViewClickListener() {
            @Override
            public void onErrorViewClick() {
                mRecyclerAdapter.setLoadMoreError(false);
                // Retry loading more search result
                SoundcloudAPIRequestHelper.retryRequestLoadingMorePlaylistResult();
            }
        });
        mRecyclerView.setAdapter(mRecyclerAdapter);
        switchSearchResultViewVisibility();
    }

    @Subscribe
    public void onLoadMorePlaylistCompleted(EventPlaylistSearchLoadMoreCompleted event) {
        Log.d(DEBUG_TAG, "Event: onLoadMoreResult");
        mRecyclerAdapter.addMoreItems(event.getLoadMoreResult());
    }

    @Subscribe
    public void onLoadMorePlaylistError(EventPlaylistSearchLoadMoreError event){
        mRecyclerAdapter.setLoadMoreError(true);
    }

    private void switchSearchResultViewVisibility() {
        if (mRecyclerView.getVisibility() == View.GONE) {
            mRecyclerView.setVisibility(View.VISIBLE);
            layoutExploreSoundcloud.setVisibility(View.GONE);
        }
    }

    /**
     * Loading more search result from Soundcloud
     *
     * @param page : pagination number
     */
    private void loadMorePlaylist(int page) {
        Log.d(DEBUG_TAG, "Current page = " + page + ", loading more items...");
        SoundcloudAPIRequestHelper.requestLoadMorePlaylistSearchResult(((SearchActivity) mContext).getCurrentSearchTerm(), page);
    }
}
