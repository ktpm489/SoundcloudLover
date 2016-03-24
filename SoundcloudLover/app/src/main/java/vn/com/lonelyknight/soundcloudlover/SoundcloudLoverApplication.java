package vn.com.lonelyknight.soundcloudlover;

import android.app.Application;

import com.squareup.otto.Bus;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.com.lonelyknight.soundcloudlover.apis.SoundcloudAPIEndpoint;


/**
 * Created by King on 3/8/2016.
 */
public class SoundcloudLoverApplication extends Application{

    public static final String BASE_URL = "https://api.soundcloud.com/";

    private Retrofit retrofitClient;
    public static SoundcloudAPIEndpoint soundcloudApiService;
    public static Bus eventBus;

    @Override
    public void onCreate() {
        super.onCreate();

        eventBus = new Bus();

        retrofitClient = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        soundcloudApiService = retrofitClient.create(SoundcloudAPIEndpoint.class);
    }

    public static SoundcloudAPIEndpoint getSoundcloudApiService(){
        return soundcloudApiService;
    }
}
