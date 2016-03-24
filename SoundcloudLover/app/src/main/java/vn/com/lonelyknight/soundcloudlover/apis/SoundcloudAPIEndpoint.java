package vn.com.lonelyknight.soundcloudlover.apis;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vn.com.lonelyknight.soundcloudlover.models.Playlist;
import vn.com.lonelyknight.soundcloudlover.models.Track;

/**
 * Created by King on 3/8/2016.
 */
public interface SoundcloudAPIEndpoint {

    @GET("tracks?client_id=1f5a55bf4e77f8da5156edac58fe2c31&limit=10")
    Call<List<Track>> searchTrackByTitle(@Query("q") String title);

    @GET("playlists?client_id=1f5a55bf4e77f8da5156edac58fe2c31&limit=10&representation=compact")
    Call<List<Playlist>> searchPlaylistsByTitle(@Query("q") String title);
}
