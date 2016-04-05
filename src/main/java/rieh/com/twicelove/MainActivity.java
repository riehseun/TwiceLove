package rieh.com.twicelove;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

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

/**
 * Created by user on 2016-04-05.
 */
public class MainActivity extends AppCompatActivity {
    public static final String NAME = ApplicationActivity.class.getSimpleName();

    public static final String MESSAGE_KEY = "message_key";
    public static final String PHOTO_URL_KEY = "photo_url_key";
    public static final String VIDEO_URL_KEY = "video_url_key";

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

        // These two lines not needed,
        // just to get the look of facebook (changing background color & hiding the icon)
        /*
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3b5998")));
        getActionBar()
                .setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        */
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
