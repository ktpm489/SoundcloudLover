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
import vn.com.lonelyknight.soundcloudlover.models.Track;

/**
 * Created by King on 3/8/2016.
 */
public class SoundcloudSearchAdapter extends RecyclerView.Adapter<SoundcloudSearchAdapter.ViewHolder> {

    private List<Track> result;
    private Context mContext;

    public SoundcloudSearchAdapter() {
        result = null;
    }

    public SoundcloudSearchAdapter(Context context, List<Track> result) {
        this.mContext = context;
        this.result = result;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_search_item_new, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvSearchItemTitle.setText(result.get(position).getTitle());
        holder.tvSearchItemSubtile.setText(result.get(position).getUser().getUsername());
        holder.tvListenCount.setText(Utilities.formatShortRepresent(result.get(position).getPlaybackCount()));
        holder.tvLikeCount.setText(Utilities.formatShortRepresent(result.get(position).getLikesCount()));
        holder.tvDuration.setText(Utilities.getDurationBreakdown((long) result.get(position).getDuration()));
        Picasso.with(mContext)
                .load(result.get(position).getArtworkUrl())
                .resize(192, 192)
                .centerCrop()
                .placeholder(R.drawable.icon_avatar_default)
                .into(holder.imgvTrackItemAvatar);
    }

    @Override
    public int getItemCount() {
        if (null == result) {
            return 0;
        }
        return result.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_search_item_title)
        TextView tvSearchItemTitle;
        @Bind(R.id.tv_search_item_subtitle)
        TextView tvSearchItemSubtile;
        @Bind(R.id.imgv_search_item_avatar)
        ImageView imgvTrackItemAvatar;
        @Bind(R.id.tv_search_item_listencount)
        TextView tvListenCount;
        @Bind(R.id.tv_search_item_likecount)
        TextView tvLikeCount;
        @Bind(R.id.tv_search_item_duration)
        TextView tvDuration;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
