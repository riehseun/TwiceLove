package rieh.com.twicelove.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.content.SharedPreferences;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import rieh.com.twicelove.ApplicationActivity;
import rieh.com.twicelove.FeedImageView;
import rieh.com.twicelove.R;
import rieh.com.twicelove.app.AppController;
import rieh.com.twicelove.model.FeedItem;

/**
 * Created by user on 2016-03-28.
 */
public class Activities extends Fragment {
    public static final String NAME = Activities.class.getSimpleName();

    private FloatingActionButton write;
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;

    private ListView listView;
    private FeedListAdapter listAdapter;
    private List<FeedItem> feedItems;
    private String URL_FEED = "http://api.androidhive.info/feed/feed.json";

    //private static List<Kudo> kudos = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activities, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        write = (FloatingActionButton) view.findViewById(R.id.write);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ((ApplicationActivity) getActivity()).post();
            }
        });

        /*
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        if (savedInstanceState != null && savedInstanceState.containsKey(ApplicationActivity.POST_KEY)) {
            note.setText(savedInstanceState.getString(ApplicationActivity.POST_KEY));
        }

        String post = sharedPreferences.getString(ApplicationActivity.MESSAGE_KEY, "");
        String photoUrl = sharedPreferences.getString(ApplicationActivity.PHOTO_URL_KEY, "");
        String videoUrl = sharedPreferences.getString(ApplicationActivity.VIDEO_URL_KEY, "");
        */

        //Toast.makeText(getActivity(), post, Toast.LENGTH_SHORT).show();
        //kudos.add(new Kudo(post, photoUrl, videoUrl));
        //recyclerView.setAdapter(new Adapter(getActivity(), kudos));
        //listView = (ListView) findViewById(R.id.recyclerView);

        listView = (ListView) getView().findViewById(R.id.list);

        feedItems = new ArrayList<FeedItem>();

        listAdapter = new FeedListAdapter(getActivity(), feedItems);
        System.out.println("listAdapter: " + listAdapter);
        listView.setAdapter(listAdapter);

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

    public class FeedListAdapter extends BaseAdapter {
        private Activity activity;
        private LayoutInflater inflater;
        private List<FeedItem> feedItems;
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        public FeedListAdapter(Activity activity, List<FeedItem> feedItems) {
            this.activity = activity;
            this.feedItems = feedItems;
        }

        @Override
        public int getCount() {
            return feedItems.size();
        }

        @Override
        public Object getItem(int location) {
            return feedItems.get(location);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (inflater == null)
                inflater = (LayoutInflater) activity
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null)
                convertView = inflater.inflate(R.layout.feed_item, null);

            if (imageLoader == null)
                imageLoader = AppController.getInstance().getImageLoader();

            TextView name = (TextView) convertView.findViewById(R.id.name);
            TextView timestamp = (TextView) convertView
                    .findViewById(R.id.timestamp);
            TextView statusMsg = (TextView) convertView
                    .findViewById(R.id.txtStatusMsg);
            TextView url = (TextView) convertView.findViewById(R.id.txtUrl);
            NetworkImageView profilePic = (NetworkImageView) convertView
                    .findViewById(R.id.profilePic);
            FeedImageView feedImageView = (FeedImageView) convertView
                    .findViewById(R.id.feedImage1);

            FeedItem item = feedItems.get(position);

            name.setText(item.getName());

            // Converting timestamp into x ago format
            CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                    Long.parseLong(item.getTimeStamp()),
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
            timestamp.setText(timeAgo);

            // Chcek for empty status message
            if (!TextUtils.isEmpty(item.getStatus())) {
                statusMsg.setText(item.getStatus());
                statusMsg.setVisibility(View.VISIBLE);
            } else {
                // status is empty, remove from view
                statusMsg.setVisibility(View.GONE);
            }

            // Checking for null feed url
            if (item.getUrl() != null) {
                url.setText(Html.fromHtml("<a href=\"" + item.getUrl() + "\">"
                        + item.getUrl() + "</a> "));

                // Making url clickable
                url.setMovementMethod(LinkMovementMethod.getInstance());
                url.setVisibility(View.VISIBLE);
            } else {
                // url is null, remove from the view
                url.setVisibility(View.GONE);
            }

            // user profile pic
            profilePic.setImageUrl(item.getProfilePic(), imageLoader);

            // Feed image
            if (item.getImge() != null) {
                feedImageView.setImageUrl(item.getImge(), imageLoader);
                feedImageView.setVisibility(View.VISIBLE);
                feedImageView
                        .setResponseObserver(new FeedImageView.ResponseObserver() {
                            @Override
                            public void onError() {
                            }

                            @Override
                            public void onSuccess() {
                            }
                        });
            } else {
                feedImageView.setVisibility(View.GONE);
            }

            return convertView;
        }
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


}

