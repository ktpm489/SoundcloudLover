package vn.com.lonelyknight.soundcloudlover.events;

import java.util.List;

import vn.com.lonelyknight.soundcloudlover.models.Playlist;
import vn.com.lonelyknight.soundcloudlover.models.Track;

public class EventPlaylistSearchLoadMoreCompleted {
    private List<Playlist> mPlaylistMore;

    public EventPlaylistSearchLoadMoreCompleted(List<Playlist> result) {
        this.mPlaylistMore = result;
    }

    public List<Playlist> getLoadMoreResult() {
        return this.mPlaylistMore;
    }
}
