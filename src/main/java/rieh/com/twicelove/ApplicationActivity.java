package rieh.com.twicelove;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import rieh.com.twicelove.ui.Chat;
import rieh.com.twicelove.ui.Header;
import rieh.com.twicelove.ui.Activities;
import rieh.com.twicelove.ui.Images;
import rieh.com.twicelove.ui.Info;
import rieh.com.twicelove.ui.Leaderboard;
import rieh.com.twicelove.ui.Videos;
import rieh.com.twicelove.ui.Voices;
import rieh.com.twicelove.ui.Vote;

public class ApplicationActivity extends Activity {

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
                    .commit();
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
}
