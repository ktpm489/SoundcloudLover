package vn.com.lonelyknight.soundcloudlover.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.com.lonelyknight.soundcloudlover.R;
import vn.com.lonelyknight.soundcloudlover.Utilities;
import vn.com.lonelyknight.soundcloudlover.fragments.FragmentSearchResultAll;
import vn.com.lonelyknight.soundcloudlover.models.Track;

/**
 * Created by King on 3/8/2016.
 */
public class SoundcloudTrackSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String DEBUG_TAG = SoundcloudTrackSearchAdapter.class.getSimpleName();

    private static final int VIEW_ITEM = 0;
    private static final int VIEW_PROG = 1;

    private List<Track> result;
    private Context mContext;

    public SoundcloudTrackSearchAdapter() {
        result = null;
    }

    public SoundcloudTrackSearchAdapter(Context context, List<Track> result) {
        this.mContext = context;
        this.result = result;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_search_item_new, parent, false);
            vh = new ItemViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_progressbar_load_more, parent, false);

            vh = new ProgressViewHolder(v);
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder){
            ((ItemViewHolder) holder).tvSearchItemTitle.setText(result.get(position).getTitle());
            ((ItemViewHolder) holder).tvSearchItemSubtile.setText(result.get(position).getUser().getUsername());
            ((ItemViewHolder) holder).tvListenCount.setText(Utilities.formatShortRepresent(result.get(position).getPlaybackCount()));
            ((ItemViewHolder) holder).tvLikeCount.setText(Utilities.formatShortRepresent(result.get(position).getLikesCount()));
            ((ItemViewHolder) holder).tvDuration.setText(Utilities.getDurationBreakdown((long) result.get(position).getDuration()));
            Picasso.with(mContext)
                    .load(result.get(position).getArtworkUrl())
                    .resize(192, 192)
                    .centerCrop()
                    .placeholder(R.drawable.icon_avatar_default)
                    .into(((ItemViewHolder) holder).imgvTrackItemAvatar);
        }else if (holder instanceof ProgressViewHolder){
            ((ProgressViewHolder) holder).loadMoreProgressBar.setIndeterminate(true);
        }
    }

    /**
     * the size of the data plus one, the one is the last row, which displays a Progressbar
     */
    @Override
    public int getItemCount() {
        if (null == result) {
            return 0;
        }
        return result.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position >= result.size() ? VIEW_PROG : VIEW_ITEM;
    }

    public void addMoreItems(List<Track> moreResult) {
        if (null != moreResult && moreResult.size() > 0) {
            Log.d(DEBUG_TAG, "Add more items to data source");
            int currentSize = getItemCount();
            result.addAll(moreResult);
            notifyItemRangeInserted(currentSize, result.size() - 1);
        }
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {
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

        public ItemViewHolder(View v) {
            super(v);

            ButterKnife.bind(this, v);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.progressbar_loadmore)
        ProgressBar loadMoreProgressBar;

        public ProgressViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
