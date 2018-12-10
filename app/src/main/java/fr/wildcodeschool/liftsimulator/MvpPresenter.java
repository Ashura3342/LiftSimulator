package fr.wildcodeschool.liftsimulator;

public interface MvpPresenter<V extends MvpView> {
    void attach(V view);
    void onStart();
    void onRestart();
    void onStop();
    void detach();
}
