package phoenix.idex.MainRecyclerViewFeed.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import phoenix.idex.R;
import phoenix.idex.MainRecyclerViewFeed.app.AppController;
import phoenix.idex.MainRecyclerViewFeed.data.FeedItem;

/**
 * Created by Ravinder on 2/25/16.
 */
public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<FeedItem> feedItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public FeedListAdapter(Context context, List<FeedItem> feedItems) {
        inflater = LayoutInflater.from(context);
        this.feedItems = feedItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Custom root of recycle view
        View view = inflater.inflate(R.layout.item_mainfeed, parent, false);
        // Hold a structure of a view. See class viewholder, which holds the structure
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        FeedItem currentPos = feedItems.get(position);
        holder.name.setText(currentPos.getName());

        // Converting timestamp into x ago format
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(currentPos.getTimeStamp()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        holder.timestamp.setText(timeAgo);

        // Chcek for empty status message
        if (!TextUtils.isEmpty(currentPos.getStatus())) {
            holder.txtStatusMsg.setText(currentPos.getStatus());
            holder.txtStatusMsg.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            holder.txtStatusMsg.setVisibility(View.GONE);
        }

        // user profile pic
        holder.profilePic.setImageUrl(currentPos.getProfilePic(), imageLoader);


    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }
    // Holder knows and references where the fields are
    class ViewHolder extends RecyclerView.ViewHolder {

        NetworkImageView profilePic;
        TextView name;
        TextView timestamp;
        TextView txtStatusMsg;

        public ViewHolder(View itemView) {
            super(itemView);
            profilePic = (NetworkImageView) itemView.findViewById(R.id.profilePic);
            name = (TextView) itemView.findViewById(R.id.name);
            timestamp = (TextView) itemView.findViewById(R.id.timestamp);
            txtStatusMsg = (TextView) itemView.findViewById(R.id.txtStatusMsg);
        }
    }
}
