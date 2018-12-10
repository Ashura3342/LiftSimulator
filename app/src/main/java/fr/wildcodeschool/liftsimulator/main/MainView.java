package fr.wildcodeschool.liftsimulator.main;

import fr.wildcodeschool.liftsimulator.MvpView;

public interface MainView extends MvpView {
    void setFloor(int text);
    void setMoving(boolean moving);
    void movingFloor();
}
