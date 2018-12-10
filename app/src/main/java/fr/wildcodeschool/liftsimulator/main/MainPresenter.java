package fr.wildcodeschool.liftsimulator.main;

import fr.wildcodeschool.liftsimulator.MvpPresenter;

public interface MainPresenter<V extends MainView> extends MvpPresenter<V> {
    void onEndMovingFloor();
    void onButtonClick(int position);
    void onCancelMoving();
}
