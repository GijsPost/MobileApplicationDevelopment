package nl.gijspost.geoguessswipe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GeoAdapter extends RecyclerView.Adapter<GeoAdapter.ViewHolder> {

    private List<Geo> geoLocations;

    public GeoAdapter(List<Geo> geoListInput) {
        this.geoLocations = geoListInput;
    }

    @NonNull
    @Override
    public GeoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View newView = inflater.inflate(R.layout.image_view, null);

        GeoAdapter.ViewHolder viewHolder = new GeoAdapter.ViewHolder(newView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GeoAdapter.ViewHolder holder, int position) {
        Geo geo = geoLocations.get(position);
        holder.imageView.setImageResource(geo.getImage());
    }

    @Override
    public int getItemCount() {
        return geoLocations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.geoImageView);
        }
    }

}
