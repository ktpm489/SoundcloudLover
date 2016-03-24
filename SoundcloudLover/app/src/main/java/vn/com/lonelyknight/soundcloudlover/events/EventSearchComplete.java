package vn.com.lonelyknight.soundcloudlover.events;

import java.util.List;

import vn.com.lonelyknight.soundcloudlover.models.Track;

/**
 * Created by King on 3/8/2016.
 */
public class EventSearchComplete {
    private List<Track> searchResult;

    public EventSearchComplete(List<Track> result){
        this.searchResult = result;
    }

    public List<Track> getSearchResultData(){
        return this.searchResult;
    }
}
