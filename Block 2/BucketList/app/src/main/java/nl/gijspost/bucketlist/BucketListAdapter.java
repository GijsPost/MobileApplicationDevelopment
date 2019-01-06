package nl.gijspost.bucketlist;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import javax.security.auth.callback.Callback;

public class BucketListAdapter extends RecyclerView.Adapter<BucketListAdapter.ViewHolder> {

    private List<BucketList> bucketLists;
    final private checkChangedCallback checkChangedCallback;

    public interface checkChangedCallback extends Callback{
        void checkChanged(int i, boolean checked);
    }

    public BucketListAdapter(List<BucketList> bucketLists, checkChangedCallback callback) {
        this.bucketLists = bucketLists;
        this.checkChangedCallback = callback;
    }

    @NonNull
    @Override
    public BucketListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.bucket_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BucketListAdapter.ViewHolder holder, final int position) {

        final BucketList bucketList = bucketLists.get(position);
        holder.title.setText(bucketList.getTitle());
        holder.description.setText(bucketList.getDescription());
        holder.finished.setChecked(bucketList.isDone());

        // Mark with line if done, or not
        holder.title.setPaintFlags(bucketList.isDone() ? holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG : 0);
        holder.description.setPaintFlags(bucketList.isDone() ? holder.description.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG : 0);


        // Add the check change listener on the checkbox
        holder.finished.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkChangedCallback.checkChanged(position, isChecked);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bucketLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        TextView description;
        CheckBox finished;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textviewTitle);
            description = itemView.findViewById(R.id.textViewDescription);
            finished = itemView.findViewById(R.id.done);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Do nothing
        }
    }

    public void swapList(List<BucketList> newList) {
        bucketLists = newList;
        if (newList != null) {
            this.notifyDataSetChanged();
        }
    }
}