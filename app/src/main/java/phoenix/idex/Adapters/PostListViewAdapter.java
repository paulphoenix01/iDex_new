package phoenix.idex.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import phoenix.idex.R;
import phoenix.idex.AdapterClassModel.ItemPost;

/**
 * Created by Ravinder on 2/21/16.
 */
public class PostListViewAdapter extends BaseAdapter {

    private Context context;
    private List<ItemPost> listItem;

    public PostListViewAdapter (Context context, List<ItemPost> itemPosts) {
        this.context = context;
        this.listItem = itemPosts;
    }
    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.item_post, null);

        final LinearLayout buttonLayout = (LinearLayout) v.findViewById(R.id.buttonLinearLayout);
        final LinearLayout textLayout = (LinearLayout) v.findViewById(R.id.textLinearLayout);

        textLayout.post(new Runnable(){
            public void run(){
                int height = textLayout.getHeight();
                buttonLayout.getLayoutParams().height = height;
            }
        });

        TextView txtPost = (TextView) v.findViewById(R.id.txtPost);
        TextView txtTime = (TextView) v.findViewById(R.id.txtTime);

        ItemPost item = listItem.get(position);

        txtPost.setText(item.getPost());
        txtTime.setText(item.getTime());

        return v;
    }
}
