package rieh.com.twicelove.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import rieh.com.twicelove.ApplicationActivity;
import rieh.com.twicelove.R;

/**
 * Created by user on 2016-03-28.
 */
public class Vote extends Fragment implements View.OnClickListener {
    public static final String NAME = Vote.class.getSimpleName();

    private Button tzuyuVote;
    private Button minaVote;
    private Button sanaVote;
    private Button momoVote;
    private Button chaeyoungVote;
    private Button nayeonVote;
    private Button dahyunVote;
    private Button jungyeonVote;
    private Button jihyoVote;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.vote, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        tzuyuVote = (Button) view.findViewById(R.id.tzuyuVote);
        minaVote = (Button) view.findViewById(R.id.minaVote);
        sanaVote = (Button) view.findViewById(R.id.sanaVote);
        momoVote = (Button) view.findViewById(R.id.momoVote);
        chaeyoungVote = (Button) view.findViewById(R.id.chaeyoungVote);
        nayeonVote = (Button) view.findViewById(R.id.nayeonVote);
        dahyunVote = (Button) view.findViewById(R.id.dahyunVote);
        jungyeonVote = (Button) view.findViewById(R.id.jungyeonVote);
        jihyoVote = (Button) view.findViewById(R.id.jihyoVote);

        tzuyuVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(getActivity(), "Voted for Tzuyu", Toast.LENGTH_SHORT).show();
            }
        });
        minaVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(getActivity(), "Voted for Mina", Toast.LENGTH_SHORT).show();
            }
        });
        sanaVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(getActivity(), "Voted for Sana", Toast.LENGTH_SHORT).show();
            }
        });
        momoVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(getActivity(), "Voted for Momo", Toast.LENGTH_SHORT).show();
            }
        });
        chaeyoungVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(getActivity(), "Voted for Chae Young", Toast.LENGTH_SHORT).show();
            }
        });
        nayeonVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(getActivity(), "Voted for Na Yeon", Toast.LENGTH_SHORT).show();
            }
        });
        dahyunVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(getActivity(), "Voted for Da Hyun", Toast.LENGTH_SHORT).show();
            }
        });
        jungyeonVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(getActivity(), "Voted for Jung Yeon", Toast.LENGTH_SHORT).show();
            }
        });
        jihyoVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(getActivity(), "Voted for Ji Hyo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
