package vn.com.lonelyknight.soundcloudlover.apis;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vn.com.lonelyknight.soundcloudlover.models.Playlist;
import vn.com.lonelyknight.soundcloudlover.models.Track;

public interface SoundcloudAPIEndpoint {

    int DEFAULT_PAGE_SIZE = 20;

    @GET("tracks?client_id=1f5a55bf4e77f8da5156edac58fe2c31&limit=" + DEFAULT_PAGE_SIZE)
    Call<List<Track>> searchTrackByTitle(@Query("q") String title);

    @GET("tracks?client_id=1f5a55bf4e77f8da5156edac58fe2c31&limit=" + DEFAULT_PAGE_SIZE)
    Call<List<Track>> loadMoreTrack(@Query("q") String title, @Query("offset") int offset);

    @GET("playlists?client_id=1f5a55bf4e77f8da5156edac58fe2c31&representation=compact&limit=" + DEFAULT_PAGE_SIZE)
    Call<List<Playlist>> searchPlaylistsByTitle(@Query("q") String title);

    @GET("playlists?client_id=1f5a55bf4e77f8da5156edac58fe2c31&representation=compact&limit=" + DEFAULT_PAGE_SIZE)
    Call<List<Playlist>> loadMorePlaylist(@Query("q") String title, @Query("offset") int offset);

}
