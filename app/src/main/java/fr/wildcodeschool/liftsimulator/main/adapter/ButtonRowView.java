package fr.wildcodeschool.liftsimulator.main.adapter;

import fr.wildcodeschool.liftsimulator.MvpView;

public interface ButtonRowView extends MvpView {
    void setText(int text);
    void setOnItemClickListener(int position, OnItemClickListener itemClickListener);
}
