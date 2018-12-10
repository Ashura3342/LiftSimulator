package fr.wildcodeschool.liftsimulator;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {
    V view;

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onRestart() {
        onStart();
    }

    @Override
    public void attach(V view) {
        this.view = view;
        onStart();
    }

    @Override
    public void detach() {
        this.view = null;
        onStop();
    }

    public V getView() {
        return view;
    }
}
