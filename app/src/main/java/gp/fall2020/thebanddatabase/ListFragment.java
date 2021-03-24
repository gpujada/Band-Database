package gp.fall2020.thebanddatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ListFragment extends Fragment {

    //For activity to implement
    public interface OnBandSelectedListener {
        void onBandSelected(int bandId);
    }
    //reference to activity
    private OnBandSelectedListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        /*LinearLayout layout = (LinearLayout) view;

        //Create the buttons using the band names and ids from BandDatabase
        List<Band> bandList = BandDatabase.getInstance(getContext()).getBands();
        for(int i=0; i<bandList.size(); i++) {
            Button button = new Button(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,0,0,10);
            button.setLayoutParams(layoutParams); //set button layout

            //set text to band's name and tag to band id
            Band band = BandDatabase.getInstance(getContext()).getBand(i+1);
            button.setText(band.getmName());
            button.setTag(Integer.toString(band.getmId()));

            //all buttons have same click listener
            button.setOnClickListener(buttonClickListener);

            //add button to linearlayout
            layout.addView(button);
        }*/
        RecyclerView recyclerView = view.findViewById(R.id.band_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //send bands to recyclerview
        BandAdapter adapter = new BandAdapter(BandDatabase.getInstance(getContext()).getBands());
        recyclerView.setAdapter(adapter);
        //separator added
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        return view;
    }

    /*private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //send band id of clicked button to detailsactivity
            Intent intent = new Intent(getActivity(), DetailsActivity.class);
            String bandID = (String) view.getTag();
            intent.putExtra(DetailsActivity.EXTRA_BAND_ID, Integer.parseInt(bandID));
            startActivity(intent);
        }
    };*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnBandSelectedListener) {
            mListener = (OnBandSelectedListener) context;
        }
        else {
            throw new RuntimeException(context.toString() + " must implement OnBandSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener =  null;
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //notify activity of band selection
            String bandId = (String) view.getTag();
            mListener.onBandSelected(Integer.parseInt(bandId));
        }
    };

    private class BandHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private Band mBand;
        private TextView mNameTextView;

        public BandHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_band, parent, false));
            itemView.setOnClickListener(this);
            mNameTextView = itemView.findViewById(R.id.bandName);
        }
        public void bind(Band band) {
            mBand = band;
            mNameTextView.setText(mBand.getmName());
        }
        @Override
        public void onClick(View view) {
            // Tell ListActivity what band was clicked
            mListener.onBandSelected(mBand.getmId());
        }
    }

    private class BandAdapter extends RecyclerView.Adapter<BandHolder> {
        private List<Band> mBands;

        public BandAdapter(List<Band> bands) {
            mBands = bands;
        }
        @Override
        public BandHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new BandHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(BandHolder holder, int position) {
            Band band = mBands.get(position);
            holder.bind(band);
        }
        @Override
        public int getItemCount() {
            return mBands.size();
        }
    }
}