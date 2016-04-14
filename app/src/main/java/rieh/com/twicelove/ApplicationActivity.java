package rieh.com.twicelove;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import rieh.com.twicelove.ui.Chat;
import rieh.com.twicelove.ui.Header;
import rieh.com.twicelove.ui.Activities;
import rieh.com.twicelove.ui.Images;
import rieh.com.twicelove.ui.Info;
import rieh.com.twicelove.ui.Leaderboard;
import rieh.com.twicelove.ui.Post;
import rieh.com.twicelove.ui.Videos;
import rieh.com.twicelove.ui.Voices;
import rieh.com.twicelove.ui.Vote;
import rieh.com.twicelove.util.SQLiteHandler;
import rieh.com.twicelove.util.SessionManager;

public class ApplicationActivity extends AppCompatActivity {
    public static final String NAME = ApplicationActivity.class.getSimpleName();

    public static final String MESSAGE_KEY = "message_key";
    public static final String PHOTO_URL_KEY = "photo_url_key";
    public static final String VIDEO_URL_KEY = "video_url_key";

    private SQLiteHandler db;
    private SessionManager session;
    private Button btnLogout;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applicationactivity);

        // add Form
        if (savedInstanceState == null) {
            // adding a fragment dynamically requires to ensure activity is not recreated from saved instance state
            // if 'savedInstanceState' is null - this menas activity is created for the first time, ie. app just started
            getFragmentManager().beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .add(R.id.info, new Info(), Info.NAME)
                    .add(R.id.header, new Header(), Header.NAME)
                    .add(R.id.content, new Activities(), Activities.NAME)
                    .commit();
        }

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        if (!session.isLoggedIn()) {
            logoutUser();
        }
    }

    public void addActivities() {
        getFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.content, new Activities(), Activities.NAME)
                .addToBackStack(null)
                .commit();
    }

    public void addVideos() {
        getFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.content, new Videos(), Videos.NAME)
                .addToBackStack(null)
                .commit();
    }

    public void addImages() {
        getFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.content, new Images(), Images.NAME)
                .addToBackStack(null)
                .commit();
    }

    public void addVoices() {
        getFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.content, new Voices(), Voices.NAME)
                .addToBackStack(null)
                .commit();
    }

    public void addVote() {
        getFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.content, new Vote(), Vote.NAME)
                .addToBackStack(null)
                .commit();
    }

    public void addLeaderboard() {
        getFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.content, new Leaderboard(), Leaderboard.NAME)
                .addToBackStack(null)
                .commit();
    }

    public void addChat() {
        getFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.content, new Chat(), Chat.NAME)
                .addToBackStack(null)
                .commit();
    }

    public void post() {
        getFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.content, new Post(), Post.NAME)
                .addToBackStack(null)
                .commit();
    }

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(ApplicationActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
