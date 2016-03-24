package vn.com.lonelyknight.soundcloudlover.events;

import java.util.List;

import vn.com.lonelyknight.soundcloudlover.models.Playlist;
import vn.com.lonelyknight.soundcloudlover.models.Track;

/**
 * Created by King on 3/8/2016.
 */
public class EventPlaylistSearchComplete {
    private List<Playlist> searchResult;

    public EventPlaylistSearchComplete(List<Playlist> result) {
        this.searchResult = result;
    }

    public List<Playlist> getSearchResultData() {
        return this.searchResult;
    }
}
