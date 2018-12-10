package fr.wildcodeschool.liftsimulator.main;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
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
        presenter.detach();
        super.onDestroy();
    }

    @Override
    public void setFloor(final int position) {
        floorValueView.post(new Runnable() {
            @Override
            public void run() {
                floorValueView.setText(getString(buttonsText[position]));
            }
        });
        presenter.onEndMovingFloor();
    }

    @Override
    public void setMoving(final boolean moving) {
        movingView.post(new Runnable() {
            @Override
            public void run() {
                movingView.setText(getString(moving ? R.string.moving : R.string.not_moving));
            }
        });
    }

    @Override
    public void movingFloor(final int text) {
        new MoveLift(this).execute(text);
    }

    static class MoveLift extends AsyncTask<Integer, Void, Integer> {

        private WeakReference<MainActivity> mainActivity;

        MoveLift(MainActivity mainActivity) {
            this.mainActivity = new WeakReference<>(mainActivity);
        }

        @Override
        protected Integer doInBackground(Integer... args) {
            SystemClock.sleep(3000);
            return args[0];
        }

        @Override
        protected void onPostExecute(Integer result) {
            this.mainActivity.get().setFloor(result);
        }
    }
}