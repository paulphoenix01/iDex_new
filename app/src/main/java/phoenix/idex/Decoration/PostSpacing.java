package phoenix.idex.Decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Ravinder on 2/21/16.
 */
public class PostSpacing extends RecyclerView.ItemDecoration {

    private final int verticalSpacing;

    public PostSpacing(int mVerticalSpaceHeight) {
        this.verticalSpacing = mVerticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = verticalSpacing;
        }
    }
}
