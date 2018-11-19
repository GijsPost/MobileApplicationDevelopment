package nl.gijspost.numbertrivia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NumberTriviaAdapter extends RecyclerView.Adapter<NumberTriviaAdapter.ViewHolder>{

    //variables
    private List<NumberTrivia> numbers;

    public NumberTriviaAdapter(List<NumberTrivia> numbers) {
        this.numbers = numbers;
    }

    @Override
    public NumberTriviaAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.number_card, viewGroup, false);
        NumberTriviaAdapter.ViewHolder viewHolder = new NumberTriviaAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NumberTriviaAdapter.ViewHolder viewHolder, int i) {
        NumberTrivia number = numbers.get(i);
        viewHolder.textViewNumber.setText(Integer.toString(number.getNumber()));
        viewHolder.textViewInfo.setText(number.getText());
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    public void addNumber(NumberTrivia number) {
        this.numbers.add(number);
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView textViewNumber;
        public TextView textViewInfo;

        public ViewHolder(View itemView){
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            textViewInfo = itemView.findViewById(R.id.textViewInfo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }

    }
}