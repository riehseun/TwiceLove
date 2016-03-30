package rieh.com.twicelove.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import rieh.com.twicelove.ApplicationActivity;
import rieh.com.twicelove.R;

/**
 * Created by user on 2016-03-30.
 */
public class Post extends Fragment {
    public static final String NAME = Post.class.getSimpleName();
    public static final String POST_KEY = "post_key";

    private Button send;
    private EditText post;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.post, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        send = (Button) view.findViewById(R.id.send);
        post = (EditText) view.findViewById(R.id.post);
        final String postToString = post.getText().toString();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
//                sharedPreferences.getString(post);
                sharedPreferences.edit().putString(POST_KEY, postToString).commit();
                ((ApplicationActivity) getActivity()).addActivities();
                //Toast.makeText(getActivity(), "Sends Clicked", Toast.LENGTH_SHORT).show();
                //((ApplicationActivity) getActivity()).post();
            }
        });
    }
}
