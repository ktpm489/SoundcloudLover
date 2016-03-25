package vn.com.lonelyknight.soundcloudlover.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
public class SoundcloudPlaylistSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String DEBUG_TAG = SoundcloudPlaylistSearchAdapter.class.getSimpleName();

    private static final int VIEW_ITEM = 0;
    private static final int VIEW_PROG = 1;
    private static final int VIEW_ERROR = 2;

    public interface OnErrorViewClickListener {
        void onErrorViewClick();
    }

    private OnErrorViewClickListener onErrorViewClickListener;
    private boolean isLoadMoreError = false;

    private List<Playlist> result;
    private Context mContext;

    public SoundcloudPlaylistSearchAdapter() {
        result = null;
    }

    public SoundcloudPlaylistSearchAdapter(Context context, List<Playlist> result, OnErrorViewClickListener listener) {
        this.mContext = context;
        this.result = result;
        this.onErrorViewClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (VIEW_ITEM == viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_search_playlist_item, parent, false);
            vh = new ItemViewHolder(v);
        } else if (VIEW_PROG == viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_progressbar_load_more, parent, false);
            vh = new ProgressViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_load_more_error, parent, false);
            vh = new ErrorViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).tvPlaylistItemTitle.setText(result.get(position).getTitle());
            ((ItemViewHolder) holder).tvPlaylistUsername.setText(result.get(position).getUser().getUsername());
            ((ItemViewHolder) holder).tvPlaylistTrackCount.setText(Utilities.formatPlaylistTrackCountText(result.get(position).getTrackCount()));
            Picasso.with(mContext)
                    .load(result.get(position).getArtworkUrl())
                    .resize(192, 192)
                    .centerCrop()
                    .placeholder(R.drawable.icon_avatar_default)
                    .into(((ItemViewHolder) holder).imgvPlaylistArtwork);
        } else if (holder instanceof ProgressViewHolder) {
            ((ProgressViewHolder) holder).loadMoreProgressBar.setIndeterminate(true);
        } else {
            ((ErrorViewHolder) holder).bindErrorView(this.onErrorViewClickListener);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= result.size()) {
            if (isLoadMoreError){
                return VIEW_ERROR;
            }
            return VIEW_PROG;
        }
        return VIEW_ITEM;
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

    /**
     * Set value of flag to indicate load more finished error or successfully
     *
     * @param error : new value of error flag
     */
    public void setLoadMoreError(boolean error) {
        this.isLoadMoreError = error;
        notifyItemChanged(result.size());
    }

    /**
     * Append loaded more items to the list
     *
     * @param moreResult : the loaded more items
     */
    public void addMoreItems(List<Playlist> moreResult) {
        if (null != moreResult && moreResult.size() > 0) {
            Log.d(DEBUG_TAG, "Add more items to data source");
            int currentSize = getItemCount();
            result.addAll(moreResult);
            notifyItemRangeInserted(currentSize, result.size() - 1);
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_playlist_item_title)
        TextView tvPlaylistItemTitle;
        @Bind(R.id.tv_playlist_item_user)
        TextView tvPlaylistUsername;
        @Bind(R.id.imgv_playlist_artwork)
        ImageView imgvPlaylistArtwork;
        @Bind(R.id.tv_playlist_item_trackcount)
        TextView tvPlaylistTrackCount;

        public ItemViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
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

    public static class ErrorViewHolder extends RecyclerView.ViewHolder {
        View errorView;

        public ErrorViewHolder(View v) {
            super(v);
            this.errorView = v;
        }

        public void bindErrorView(final OnErrorViewClickListener listener){
            errorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onErrorViewClick();
                }
            });
        }
    }
}
