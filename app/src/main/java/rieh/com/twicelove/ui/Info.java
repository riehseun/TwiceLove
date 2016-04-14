package rieh.com.twicelove.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rieh.com.twicelove.ApplicationActivity;
import rieh.com.twicelove.R;

/**
 * Created by user on 2016-04-05.
 */
public class Info extends Fragment {
    public static final String NAME = Info.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.info, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
}
