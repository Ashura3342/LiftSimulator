package fr.wildcodeschool.liftsimulator.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class ButtonRowViewHolder extends RecyclerView.ViewHolder
        implements ButtonRowView {

    private Button button;

    public ButtonRowViewHolder(@NonNull View itemView) {
        super(itemView);
        button = (Button) itemView;
    }

    @Override
    public void setText(int text) {
        button.setText(itemView.getContext().getString(text));
    }

    @Override
    public void setOnItemClickListener(final int position,
                                       final OnItemClickListener itemClickListener) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(position);
            }
        });
    }
}
