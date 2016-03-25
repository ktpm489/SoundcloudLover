package vn.com.lonelyknight.soundcloudlover.apis;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.lonelyknight.soundcloudlover.SoundcloudLoverApplication;
import vn.com.lonelyknight.soundcloudlover.events.EventPlaylistSearchComplete;
import vn.com.lonelyknight.soundcloudlover.events.EventSearchComplete;
import vn.com.lonelyknight.soundcloudlover.events.EventTrackSearchLoadMoreCompleted;
import vn.com.lonelyknight.soundcloudlover.models.Playlist;
import vn.com.lonelyknight.soundcloudlover.models.Track;

/**
 * Created by duclm on 3/25/2016.
 */
public class SoundcloudAPIRequestHelper {
    private static final String DEBUG_TAG = SoundcloudAPIRequestHelper.class.getSimpleName();

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
                Log.e(DEBUG_TAG, "enqueueSearchQuery failed");
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
                Log.e(DEBUG_TAG, "enqueuePlaylistSearchQuery failed");
            }
        });
    }

    public static void requestLoadMoreTrackSearchResult(String query, int page){
        Log.d(DEBUG_TAG, "requestLoadMoreTrackSearchResult: offset = " + page);

        Call<List<Track>> call_search = SoundcloudLoverApplication.getSoundcloudApiService().loadMoreTrack(query, page * SoundcloudAPIEndpoint.DEFAULT_PAGE_SIZE);
        call_search.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                SoundcloudLoverApplication.eventBus.post(new EventTrackSearchLoadMoreCompleted(response.body()));
            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                Log.e(DEBUG_TAG, "requestLoadMoreTrackSearchResult failed");
            }
        });
    }
}
