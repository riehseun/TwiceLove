package rieh.com.twicelove.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import rieh.com.twicelove.ApplicationActivity;
import rieh.com.twicelove.R;

/**
 * Created by user on 2016-03-28.
 */
public class Activities extends Fragment {
    public static final String NAME = Activities.class.getSimpleName();

    private FloatingActionButton write;

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
                //Toast.makeText(getActivity(), "Write Clicked", Toast.LENGTH_SHORT).show();
                ((ApplicationActivity) getActivity()).post();
            }
        });
    }
}

