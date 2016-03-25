package vn.com.lonelyknight.soundcloudlover.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.com.lonelyknight.soundcloudlover.DividerItemDecoration;
import vn.com.lonelyknight.soundcloudlover.EndlessRecyclerViewScrollListener;
import vn.com.lonelyknight.soundcloudlover.R;
import vn.com.lonelyknight.soundcloudlover.SoundcloudLoverApplication;
import vn.com.lonelyknight.soundcloudlover.activities.SearchActivity;
import vn.com.lonelyknight.soundcloudlover.adapters.SoundcloudTrackSearchAdapter;
import vn.com.lonelyknight.soundcloudlover.apis.SoundcloudAPIRequestHelper;
import vn.com.lonelyknight.soundcloudlover.events.EventSearchComplete;
import vn.com.lonelyknight.soundcloudlover.events.EventTrackSearchLoadMoreCompleted;

/**
 * Created by duclm on 3/23/2016.
 */
public class FragmentSearchResultAll extends Fragment {

    private static final String DEBUG_TAG = FragmentSearchResultAll.class.getSimpleName();

    @Bind(R.id.recyclerview_search_result)
    RecyclerView recyclerView;
    @Bind(R.id.layout_explore_soundcloud)
    View layoutExploreSoundcloud;

    private Context mContext;
    private SoundcloudTrackSearchAdapter mRecyclerAdapter;
    private LinearLayoutManager mLayoutManager;

    public FragmentSearchResultAll() {
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
        recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider)));
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Request recyclerview to display a progressbar at bottom
                loadMoreSearchResult(page);
            }
        });

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

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
    public void onSearchCompleted(EventSearchComplete event) {
        mRecyclerAdapter = new SoundcloudTrackSearchAdapter(mContext, event.getSearchResultData());
        recyclerView.setAdapter(mRecyclerAdapter);
        switchSearchResultViewVisibility();
    }

    @Subscribe
    public void onLoadMoreResultCompleted(EventTrackSearchLoadMoreCompleted event) {
        Log.d(DEBUG_TAG, "Event: onLoadMoreResult");
        mRecyclerAdapter.addMoreItems(event.getLoadMoreResult());
    }

    private void switchSearchResultViewVisibility() {
        if (recyclerView.getVisibility() == View.GONE) {
            recyclerView.setVisibility(View.VISIBLE);
            layoutExploreSoundcloud.setVisibility(View.GONE);
        }
    }

    /**
     * Loading more search result from Soundcloud
     * @param page : pagination number
     */
    private void loadMoreSearchResult(int page) {
        Log.d(DEBUG_TAG, "Current page = " + page + ", loading more items...");
        SoundcloudAPIRequestHelper.requestLoadMoreTrackSearchResult(((SearchActivity)mContext).getCurrentSearchTerm(), page);
    }
}
