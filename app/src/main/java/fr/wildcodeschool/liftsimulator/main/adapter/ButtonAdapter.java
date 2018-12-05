package fr.wildcodeschool.liftsimulator.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Button;

import fr.wildcodeschool.liftsimulator.main.MainPresenter;
import fr.wildcodeschool.liftsimulator.main.MainView;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonRowViewHolder>
    implements OnItemClickListener {

    private MainPresenter<MainView> mainPresenter;
    private int[] buttonsTextId;
    private int[] buttonOrdered;

    public ButtonAdapter(MainPresenter<MainView> mainPresenter, int[] buttonsTextId, int [] buttonOrdered) {
        this.mainPresenter = mainPresenter;
        this.buttonsTextId = buttonsTextId;
        this.buttonOrdered = buttonOrdered;
    }

    @NonNull
    @Override
    public ButtonRowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ButtonRowViewHolder(new Button(viewGroup.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonRowViewHolder buttonRowViewHolder, int i) {
        buttonRowViewHolder.setText(buttonsTextId[buttonOrdered[i]]);
        buttonRowViewHolder.setOnItemClickListener(buttonOrdered[i], this);
    }

    @Override
    public int getItemCount() {
        return this.buttonsTextId.length;
    }

    @Override
    public void onItemClick(int i) {
        this.mainPresenter.onButtonClick(i);
    }
}
