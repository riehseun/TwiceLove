package rieh.com.twicelove.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import rieh.com.twicelove.ApplicationActivity;
import rieh.com.twicelove.R;

/**
 * Created by user on 2016-03-30.
 */
public class Post extends Fragment {
    public static final String NAME = Post.class.getSimpleName();

    private Button send;
    private EditText message;
    private EditText photoUrl;
    private EditText videoUrl;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.post, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        send = (Button) view.findViewById(R.id.send);
        message = (EditText) view.findViewById(R.id.message);
        photoUrl = (EditText) view.findViewById(R.id.photo);
        videoUrl = (EditText) view.findViewById(R.id.video);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                String postToString = message.getText().toString();
                String photoUrlToString = photoUrl.getText().toString();
                String videoUrlToString = videoUrl.getText().toString();
                sharedPreferences
                        .edit()
                        .putString(ApplicationActivity.MESSAGE_KEY, postToString)
                        .putString(ApplicationActivity.PHOTO_URL_KEY, photoUrlToString)
                        .putString(ApplicationActivity.VIDEO_URL_KEY, videoUrlToString)
                        .commit();
                ((ApplicationActivity) getActivity()).addActivities();
                //Toast.makeText(getActivity(), "Sends Clicked", Toast.LENGTH_SHORT).show();
                //((ApplicationActivity) getActivity()).post();
            }
        });
    }
}
