package nl.gijspost.individualassignment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nl.gijspost.individualassignment.models.App;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder>{

    //variables
    private List<App> apps;
    final private AppClickListener clickListener;

    public AppAdapter(List<App> apps, AppClickListener clickListener) {
        this.apps = apps;
        this.clickListener = clickListener;
    }

    @Override
    public AppAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.app_item, null);

        AppAdapter.ViewHolder viewHolder = new AppAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AppAdapter.ViewHolder viewHolder, int i) {
        App app = this.apps.get(i);
        viewHolder.appNameView.setText(app.getName());
        viewHolder.appIdView.setText("App ID: " + app.getAppId());
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView appNameView;
        public TextView appIdView;

        public ViewHolder(View itemView){
            super(itemView);
            appNameView = itemView.findViewById(R.id.appTitleView);
            appIdView = itemView.findViewById(R.id.appIdView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            clickListener.appOnClick(clickedPosition);
        }

    }

    public void swap(List<App> newList) {
        apps = newList;
        if (newList != null) {
            this.notifyDataSetChanged();
        }
    }

    public interface AppClickListener{
        void appOnClick(int i);
    }
}