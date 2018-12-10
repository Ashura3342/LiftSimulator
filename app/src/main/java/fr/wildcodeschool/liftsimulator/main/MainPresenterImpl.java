package fr.wildcodeschool.liftsimulator.main;

import fr.wildcodeschool.liftsimulator.BasePresenter;

public class MainPresenterImpl<V extends MainView>
    extends BasePresenter<V>
    implements MainPresenter<V> {

    private boolean isMoving;
    private int currentFloor;
    private int endFloor;

    MainPresenterImpl() {
        this.isMoving = false;
        this.currentFloor = 0;
        this.endFloor = this.currentFloor;
    }

    @Override
    public void onStart() {
        getView().setFloor(this.currentFloor);
        getView().setMoving(this.isMoving);
    }

    @Override
    public void onButtonClick(int position) {

        if (!this.isMoving && position != this.currentFloor) {
            this.isMoving = true;
            this.endFloor = position;
            getView().setMoving(this.isMoving);
            nextMovingFloor();
        }
    }

    private void nextMovingFloor() {
        if (this.endFloor != this.currentFloor) {
            getView().movingFloor();
        } else {
            this.isMoving = false;
            getView().setMoving(this.isMoving);
        }
    }

    @Override
    public void onEndMovingFloor() {
        this.currentFloor = this.endFloor > this.currentFloor ? this.currentFloor + 1 : this.currentFloor - 1;
        getView().setFloor(this.currentFloor);
        nextMovingFloor();
    }

    @Override
    public void onCancelMoving() {
        this.isMoving = false;
    }
}
