package com.example.treningappproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ExcerciceAdapter extends RecyclerView.Adapter<ExcerciceAdapter.ExcerciceHolder> {

    private List<Excercice> excercices = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ExcerciceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.excercice_item,viewGroup,false);
        return new ExcerciceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExcerciceHolder excerciceHolder, int i) {
        Excercice currentExcercice = excercices.get(i);
        excerciceHolder.textViewDay.setText(currentExcercice.getDay());
        excerciceHolder.textViewName.setText(currentExcercice.getName());
        excerciceHolder.textViewSets.setText(String.valueOf(currentExcercice.getSets()));
        excerciceHolder.textViewRpeat.setText(String.valueOf(currentExcercice.getRepeat()));
        excerciceHolder.textViewPriority.setText(String.valueOf(currentExcercice.getPriority()));


    }

    @Override
    public int getItemCount() {
        return excercices.size();
    }

    public void setExcercices(List<Excercice> excercices){
        this.excercices = excercices;
        notifyDataSetChanged();
    }

    public Excercice getExcerciceAt(int position){
        return excercices.get(position);
    }

    class ExcerciceHolder extends RecyclerView.ViewHolder{
        private TextView textViewDay;
        private TextView textViewName;
        private TextView textViewSets;
        private TextView textViewRpeat;
        private TextView textViewPriority;

        public ExcerciceHolder(@NonNull View itemView) {
            super(itemView);
            textViewDay = itemView.findViewById(R.id.text_view_day);
            textViewName = itemView.findViewById(R.id.text_view_excercise);
            textViewSets = itemView.findViewById(R.id.text_view_set);
            textViewRpeat = itemView.findViewById(R.id.text_view_repat);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener !=null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(excercices.get(position));
                    }
                }
            });

        }
    }
    public interface OnItemClickListener{
        void onItemClick(Excercice excercice);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;

    }

}
