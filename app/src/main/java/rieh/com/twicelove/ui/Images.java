package rieh.com.twicelove.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rieh.com.twicelove.R;

/**
 * Created by user on 2016-03-28.
 */
public class Images extends Fragment{
    public static final String NAME = Images.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.images, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
}
