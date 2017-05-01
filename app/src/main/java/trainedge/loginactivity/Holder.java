package trainedge.loginactivity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ArrayBlockingQueue;


/**
 * Created by Dell on 05-Jan-17.
 */

public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tvReadLink;
    ImageView feedImg;
    ImageView ivShare;
    TextView tvTitle;
    TextView tvDesc;
    TextView tvPubDate;
    private ArrayBlockingQueue list_members;
    ImageView ivFav;

    public Holder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        tvDesc = (TextView) itemView.findViewById(R.id.tvDesc);
        tvReadLink=(TextView) itemView.findViewById(R.id.tvReadLink);
        tvPubDate = (TextView) itemView.findViewById(R.id.tvPubDate);
        feedImg = (ImageView) itemView.findViewById(R.id.feedImg);
        ivShare = (ImageView) itemView.findViewById(R.id.ivShare);
        ivFav = (ImageView) itemView.findViewById(R.id.ivFav);
    }

    @Override
    public void onClick(View v) {
    }

    public void removeAt(int position) {
        list_members.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(0, list_members.size());
    }

    private void notifyItemRangeChanged(int i, int size) {

    }

    private void notifyItemRemoved(int position) {

    }


}