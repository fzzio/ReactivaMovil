package reactiva.reactivamovil.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import reactiva.reactivamovil.R;

/**
 * Created by Erasmo Zurita on 2017-08-06.
 */

public class FragmentRVCitasDelDia extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_rv_citasdeldia,container,false);
        return v;
    }
}
