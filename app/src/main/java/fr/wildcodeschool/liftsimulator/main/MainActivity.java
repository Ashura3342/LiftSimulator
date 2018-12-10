package fr.wildcodeschool.liftsimulator.main;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import fr.wildcodeschool.liftsimulator.R;
import fr.wildcodeschool.liftsimulator.main.adapter.ButtonAdapter;

public class MainActivity extends AppCompatActivity
        implements  MainView {

    private static final int[] buttonsText = new int[] {
            R.string.floor_num_0,
            R.string.floor_num_1,
            R.string.floor_num_2,
            R.string.floor_num_3,
            R.string.floor_num_4,
            R.string.floor_num_5,
            R.string.floor_num_6,
            R.string.floor_num_7,
            R.string.floor_num_8,
            R.string.floor_num_9,
    };

    private static final int[] buttonOrdered = new int[] {
            7, 8, 9,
            4, 5, 6,
            1, 2, 3,
            0
    };

    private MainPresenter<MainView> presenter = new MainPresenterImpl<>();
    private TextView floorValueView;
    private TextView movingView;
    private MoveLift moveLift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floorValueView = findViewById(R.id.main_floor_value);
        movingView = findViewById(R.id.main_moving_value);
        RecyclerView buttonView = findViewById(R.id.main_button_list);

        presenter.attach(this);

        ButtonAdapter adapter = new ButtonAdapter(presenter, buttonsText, buttonOrdered);
        buttonView.setLayoutManager(new GridLayoutManager(this, 3));
        buttonView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        if (moveLift != null)
            moveLift.cancel(true);
        presenter.detach();
        super.onDestroy();
    }

    @Override
    public void setFloor(final int position) {
        floorValueView.setText(getString(buttonsText[position]));
    }

    @Override
    public void setMoving(final boolean moving) {
        movingView.setText(getString(moving ? R.string.moving : R.string.not_moving));
    }

    @Override
    public void movingFloor() {
        moveLift = new MoveLift(this.presenter);
        moveLift.execute();
    }

    static class MoveLift extends AsyncTask<Void, Void, Void> {

        private WeakReference<MainPresenter<MainView> > presenter;

        MoveLift(MainPresenter<MainView> presenter) {
            this.presenter = new WeakReference<>(presenter);
        }

        @Override
        protected Void doInBackground(Void... args) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            this.presenter.get().onEndMovingFloor();
        }
    }
}