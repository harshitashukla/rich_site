package trainedge.loginactivity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by hp on 05-Apr-17.
 */

public class CategoryHolder extends RecyclerView.ViewHolder {
    TextView tvLabel;
    View rlContainer;
    public CategoryHolder(View itemView) {

        super(itemView);
        tvLabel = (TextView) itemView.findViewById(R.id.tvLabel);
        rlContainer=itemView.findViewById(R.id.rlcontainer);
    }
}
