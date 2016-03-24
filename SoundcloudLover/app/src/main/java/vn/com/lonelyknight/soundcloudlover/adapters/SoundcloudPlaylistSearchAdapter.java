package vn.com.lonelyknight.soundcloudlover.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.com.lonelyknight.soundcloudlover.R;
import vn.com.lonelyknight.soundcloudlover.Utilities;
import vn.com.lonelyknight.soundcloudlover.models.Playlist;

/**
 * Created by King on 3/8/2016.
 */
public class SoundcloudPlaylistSearchAdapter extends RecyclerView.Adapter<SoundcloudPlaylistSearchAdapter.ViewHolder> {

    private List<Playlist> result;
    private Context mContext;

    public SoundcloudPlaylistSearchAdapter() {
        result = null;
    }

    public SoundcloudPlaylistSearchAdapter(Context context, List<Playlist> result) {
        this.mContext = context;
        this.result = result;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_search_playlist_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvPlaylistItemTitle.setText(result.get(position).getTitle());
        holder.tvPlaylistUsername.setText(result.get(position).getUser().getUsername());
        holder.tvPlaylistTrackCount.setText(Utilities.formatPlaylistTrackCountText(result.get(position).getTrackCount()));
        Picasso.with(mContext)
                .load(result.get(position).getArtworkUrl())
                .resize(192, 192)
                .centerCrop()
                .placeholder(R.drawable.icon_avatar_default)
                .into(holder.imgvPlaylistArtwork);
    }

    @Override
    public int getItemCount() {
        if (null == result) {
            return 0;
        }
        return result.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_playlist_item_title)
        TextView tvPlaylistItemTitle;
        @Bind(R.id.tv_playlist_item_user)
        TextView tvPlaylistUsername;
        @Bind(R.id.imgv_playlist_artwork)
        ImageView imgvPlaylistArtwork;
        @Bind(R.id.tv_playlist_item_trackcount)
        TextView tvPlaylistTrackCount;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
