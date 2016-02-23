package phoenix.idex.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import phoenix.idex.R;
import phoenix.idex.AdapterClassModel.ItemPost;

/**
 * Created by Ravinder on 2/21/16.
 */
public class PostRecycleViewAdapter extends RecyclerView.Adapter<PostRecycleViewAdapter.ViewHolder> {

    private LayoutInflater layoutInflater; // Layout inflater hold the context on where it was called
    private List<ItemPost> listPost = Collections.emptyList(); // A list of posts will be provided

    public PostRecycleViewAdapter(Context context, List<ItemPost> listPost) {
        layoutInflater = LayoutInflater.from(context);
        this.listPost = listPost;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Custom root of recycle view
        View view = layoutInflater.inflate(R.layout.item_post, parent, false);
        // Hold a structure of a view. See class viewholder, which holds the structure
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    // When there is a view holder, do the operations of setting the attributes
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemPost currentPost = listPost.get(position);
        holder.textPost.setText(currentPost.getPost());
        holder.textTime.setText(currentPost.getTime());
    }

    @Override
    public int getItemCount() {
        return listPost.size();
    }
    // Holder knows and references where the fields are
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textPost;
        TextView textTime;

        public ViewHolder(View itemView) {
            super(itemView);

            textPost = (TextView) itemView.findViewById(R.id.txtPost);
            textTime = (TextView) itemView.findViewById(R.id.txtTime);
        }
    }
}
