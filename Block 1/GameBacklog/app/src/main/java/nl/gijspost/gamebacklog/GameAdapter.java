package nl.gijspost.gamebacklog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private List<Game> games;

    public GameAdapter(List<Game> games, GameClickListener clickListener) {
        this.games = games;
        this.clickListener = clickListener;
    }

    private GameClickListener clickListener;

    public interface GameClickListener {
        void gameOnClick(int i);
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_card, parent, false);
        GameAdapter.GameViewHolder viewHolder = new GameAdapter.GameViewHolder(view);
        return viewHolder;
    }

    public class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView gameNameLabel;
        TextView gamePlatformLabel;
        TextView gameDateLabel;
        TextView gameStatusLabel;

        public GameViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            gameStatusLabel = itemView.findViewById(R.id.cardStatus);
            gameNameLabel = itemView.findViewById(R.id.cardName);
            gamePlatformLabel = itemView.findViewById(R.id.cardPlatform);
            gameDateLabel = itemView.findViewById(R.id.cardDate);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            clickListener.gameOnClick(clickedPosition);
        }
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        Game game = games.get(position);
        holder.gameNameLabel.setText(game.getGameName());
        holder.gamePlatformLabel.setText(game.getGamePlatform());
        holder.gameDateLabel.setText(game.getGameDate());
        holder.gameStatusLabel.setText(game.getGameStatus());
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public void setGames(List games) {
        this.games = games;
        this.notifyDataSetChanged();
    }

}