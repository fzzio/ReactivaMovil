package reactiva.reactivamovil;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;


/**
 * Created by edgardan on 12/07/2017.
 */

public class VerTerapiaFragment extends Fragment {
    View section1, section2, section3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedinstanceState)
    {
        /*
        section1 = getView().findViewById(R.id.section1);
        section2 = getView().findViewById(R.id.section2);
        section3 = getView().findViewById(R.id.section3);

        final View header1 = getView().findViewById(R.id.header1);
        header1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (section1.getVisibility() == View.GONE)
                {
                    section1.setVisibility(View.VISIBLE);
                    v.setVisibility(View.GONE);
                }
                else
                {
                    section1.setVisibility(View.GONE);
                }
            }
        });

        section1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (header1.getVisibility() == View.GONE)
                {
                    header1.setVisibility(View.VISIBLE);
                    v.setVisibility(View.GONE);
                }
                else
                {
                    header1.setVisibility(View.GONE);
                }
            }
        });

        View header2 = getView().findViewById(R.id.header2);
        header2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (section2.getVisibility() == View.GONE)
                {
                    section2.setVisibility(View.VISIBLE);
                }
                else
                {
                    section2.setVisibility(View.GONE);
                }
            }
        });

        View header3 = getView().findViewById(R.id.header3);
        header3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (section3.getVisibility() == View.GONE)
                {
                    section3.setVisibility(View.VISIBLE);
                }
                else
                {
                    section3.setVisibility(View.GONE);
                }
            }
        });
*/
        return inflater.inflate(R.layout.fragment_ver_terapias,container,false);

    }

}
