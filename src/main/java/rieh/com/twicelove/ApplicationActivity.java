package rieh.com.twicelove;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ListView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import rieh.com.twicelove.adapter.FeedListAdapter;
import rieh.com.twicelove.app.AppController;
import rieh.com.twicelove.model.FeedItem;
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

public class ApplicationActivity extends AppCompatActivity {
    public static final String NAME = ApplicationActivity.class.getSimpleName();

    public static final String MESSAGE_KEY = "message_key";
    public static final String PHOTO_URL_KEY = "photo_url_key";
    public static final String VIDEO_URL_KEY = "video_url_key";

    private ListView listView;
    private FeedListAdapter listAdapter;
    private List<FeedItem> feedItems;
    private String URL_FEED = "http://api.androidhive.info/feed/feed.json";

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

        //listView = (ListView) findViewById(R.id.recyclerView);
        listView = (ListView) findViewById(R.id.list);

        feedItems = new ArrayList<FeedItem>();

        listAdapter = new FeedListAdapter(this, feedItems);
        System.out.println("listAdapter: " + listAdapter);
        listView.setAdapter(listAdapter);

        // These two lines not needed,
        // just to get the look of facebook (changing background color & hiding the icon)
        /*
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3b5998")));
        getActionBar()
                .setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        */

        // We first check for cached request
        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED);
        if (entry != null) {
            // fetch the data from cache
            try {
                String data = new String(entry.data, "UTF-8");
                try {
                    parseJsonFeed(new JSONObject(data));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {
            // making fresh volley request and getting json
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                    URL_FEED, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    //VolleyLog.d(TAG, "Response: " + response.toString());
                    if (response != null) {
                        parseJsonFeed(response);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    //VolleyLog.d(TAG, "Error: " + error.getMessage());
                }
            });

            // Adding request to volley request queue
            AppController.getInstance().addToRequestQueue(jsonReq);
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

    private void parseJsonFeed(JSONObject response) {
        try {
            JSONArray feedArray = response.getJSONArray("feed");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                FeedItem item = new FeedItem();
                item.setId(feedObj.getInt("id"));
                item.setName(feedObj.getString("name"));

                // Image might be null sometimes
                String image = feedObj.isNull("image") ? null : feedObj
                        .getString("image");
                item.setImge(image);
                item.setStatus(feedObj.getString("status"));
                item.setProfilePic(feedObj.getString("profilePic"));
                item.setTimeStamp(feedObj.getString("timeStamp"));

                // url might be null sometimes
                String feedUrl = feedObj.isNull("url") ? null : feedObj
                        .getString("url");
                item.setUrl(feedUrl);

                feedItems.add(item);
            }

            // notify data changes to list adapater
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
