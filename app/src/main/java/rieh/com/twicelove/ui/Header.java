package rieh.com.twicelove.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import rieh.com.twicelove.ApplicationActivity;
import rieh.com.twicelove.R;

/**
 * Created by user on 2016-03-28.
 */
public class Header extends Fragment implements View.OnClickListener {
    public static final String NAME = Header.class.getSimpleName();

    private TextView activity;
    private TextView video;
    private TextView image;
    private TextView voice;
    private TextView vote;
    private TextView leaderboard;
    private TextView chat;

    @Override
    public void onClick(View view) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.header, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        activity = (TextView) view.findViewById(R.id.activity);
        video = (TextView) view.findViewById(R.id.video);
        image = (TextView) view.findViewById(R.id.image);
        voice = (TextView) view.findViewById(R.id.voice);
        vote = (TextView) view.findViewById(R.id.vote);
        leaderboard = (TextView) view.findViewById(R.id.leaderboard);
        chat = (TextView) view.findViewById(R.id.chat);

        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ((ApplicationActivity) getActivity()).addActivities();
                Toast.makeText(getActivity(), "activity", Toast.LENGTH_SHORT).show();
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(getActivity(), "video", Toast.LENGTH_SHORT).show();
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(getActivity(), "image", Toast.LENGTH_SHORT).show();
            }
        });
        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(getActivity(), "voice", Toast.LENGTH_SHORT).show();
            }
        });
        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ((ApplicationActivity) getActivity()).addVote();
                Toast.makeText(getActivity(), "vote", Toast.LENGTH_SHORT).show();
            }
        });
        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(getActivity(), "leaderboard", Toast.LENGTH_SHORT).show();
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(getActivity(), "chat", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
