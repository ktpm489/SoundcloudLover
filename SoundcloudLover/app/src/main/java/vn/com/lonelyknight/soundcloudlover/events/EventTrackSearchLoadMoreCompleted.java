package vn.com.lonelyknight.soundcloudlover.events;

import java.util.List;

import vn.com.lonelyknight.soundcloudlover.models.Track;

public class EventTrackSearchLoadMoreCompleted {
    private List<Track> mTrackMore;

    public EventTrackSearchLoadMoreCompleted(List<Track> result){
        this.mTrackMore = result;
    }

    public List<Track> getLoadMoreResult(){
        return this.mTrackMore;
    }
}
