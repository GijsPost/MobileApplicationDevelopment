package nl.gijspost.studentportal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PortalAdapter extends RecyclerView.Adapter<PortalAdapter.ViewHolder>{

    //variables
    private List<Portal> portals;
    final private PortalClickListener clickListener;

    public PortalAdapter(List<Portal> portals, PortalClickListener clickListener) {
        this.portals = portals;
        this.clickListener = clickListener;
    }

    @Override
    public PortalAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(android.R.layout.simple_list_item_1, null);

        PortalAdapter.ViewHolder viewHolder = new PortalAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PortalAdapter.ViewHolder viewHolder, int i) {
        Portal portal = portals.get(i);
        viewHolder.textView.setText(portal.getPortalLabel());
    }

    @Override
    public int getItemCount() {
        return portals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //ViewHolder displays all the row items that are in the RecyclerView

        public TextView textView;

        public ViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(this);
        }

        //When clicked on portal title in recycler view
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            clickListener.portalOnClick(clickedPosition);
        }

    }

    public interface PortalClickListener{
        void portalOnClick(int i);
    }
}