package rieh.com.twicelove.ui;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rieh.com.twicelove.ApplicationActivity;
import rieh.com.twicelove.R;

/**
 * Created by user on 2016-03-28.
 */
public class Activities extends Fragment {
    public static final String NAME = Activities.class.getSimpleName();

    private FloatingActionButton write;
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;

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

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        /*
        if (savedInstanceState != null && savedInstanceState.containsKey(ApplicationActivity.POST_KEY)) {
            note.setText(savedInstanceState.getString(ApplicationActivity.POST_KEY));
        }
        */
        String post = sharedPreferences.getString(ApplicationActivity.MESSAGE_KEY, "");
        String photoUrl = sharedPreferences.getString(ApplicationActivity.PHOTO_URL_KEY, "");
        String videoUrl = sharedPreferences.getString(ApplicationActivity.VIDEO_URL_KEY, "");
        //Toast.makeText(getActivity(), post, Toast.LENGTH_SHORT).show();
        //kudos.add(new Kudo(post, photoUrl, videoUrl));
        //recyclerView.setAdapter(new Adapter(getActivity(), kudos));
    }

    /*
    private static final class Adapter extends RecyclerView.Adapter {
        @NonNull
        private final LayoutInflater layoutInflater;

        @NonNull
        private List<Kudo> items;

        private Adapter(@NonNull Context context, @Nullable List<Kudo> items) {
            setHasStableIds(true);
            layoutInflater = LayoutInflater.from(context);
            this.items = items;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            return new RecyclerView.ViewHolder(layoutInflater.inflate(viewType, parent, false)) {};
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            //Kudo item = items.get(position);
            //((TextView) holder.itemView).setText(item.getMessage());
            for (Kudo k : items) {
                ((TextView) holder.itemView).setText(k.getMessage());
                ((TextView) holder.itemView).setText(k.getPhotoUrl());
                ((TextView) holder.itemView).setText(k.getVideoUrl());
            }
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public int getItemViewType(final int position) {
            // if we have at least one note, then we use regular cell layout; for an empty list of notes - we show
            // empty cell (e.g. "No notes")
            return getItemCount() > 0 ? R.layout.notes_cell : R.layout.empty_cell;
        }
    }
    */
}

