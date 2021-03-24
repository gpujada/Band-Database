package gp.fall2020.thebanddatabase;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class BandDatabase {

    private static BandDatabase sBandDatabase;
    private List<Band> mBands;

    private BandDatabase(Context context) {
        mBands = new ArrayList<>();
        Resources res = context.getResources();
        String[] bands = res.getStringArray(R.array.bands);
        String[] descriptions = res.getStringArray(R.array.descriptions);
        for(int i=0; i<bands.length; i++) {
            mBands.add(new Band(i+1, bands[i], descriptions[i]));
        }
    }

    public static BandDatabase getInstance(Context context) {
        if(sBandDatabase == null) {
            sBandDatabase = new BandDatabase(context);
        }
        return sBandDatabase;
    }

    public List<Band> getBands() {
        return mBands;
    }

    public Band getBand(int bandId) {
        for(Band band : mBands) {
            if(band.getmId() == bandId) {
                return band;
            }
        }
        return null;
    }
}
