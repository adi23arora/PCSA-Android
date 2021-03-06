package com.peacecorps.pcsa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.peacecorps.pcsa.circle_of_trust.CircleIntro;
import com.peacecorps.pcsa.circle_of_trust.CircleOfTrustFragment;
import com.peacecorps.pcsa.get_help_now.ContactPostStaff;
import com.peacecorps.pcsa.reporting.HomeScreen;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public final static String TAG = "MainActivityFragment";
    private boolean introFinished = false;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Button circleButton = (Button) rootView.findViewById(R.id.circleButton);
        Button getHelpNowButton = (Button) rootView.findViewById(R.id.getButton);
        Button reportingProcessButton = (Button) rootView.findViewById(R.id.reportButton);
        Button safetyResourceButton = (Button) rootView.findViewById(R.id.safetyResourceButton);
        Button getHelpButton = (Button) rootView.findViewById(R.id.getHelpButton);

        reportingProcessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HomeScreen.class));
            }
        });
        getHelpNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Swapping ContactPostStaff into the fragment container dynamically
                Fragment contactPostStaffFragment = new ContactPostStaff();
                MainActivity.swapFragmentIn(getActivity(),contactPostStaffFragment,ContactPostStaff.TAG);
            }
        });

        safetyResourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Temporarily commented out until the functionality is implemented.
                //startActivity(new Intent(getActivity(), SafetyResources.class));
                Toast.makeText(getActivity(), getString(R.string.unavailable_function), Toast.LENGTH_SHORT).show();
            }
        });

        getHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getHelpButton does not have any functioanlity yet.
                Toast.makeText(getActivity(), getString(R.string.unavailable_function), Toast.LENGTH_SHORT).show();
            }
        });

        circleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!introFinished)
                    startActivityForResult(new Intent(getActivity(), CircleIntro.class),2);
                else
                {
                    //Swapping CircleOfTrustFragment into the container
                    CircleOfTrustFragment circleOfTrustFragment = new CircleOfTrustFragment();
                    MainActivity.swapFragmentIn(getActivity(),circleOfTrustFragment,CircleOfTrustFragment.TAG);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 2:
                introFinished = true;
                //Swapping CircleOfTrustFragment into the container
                CircleOfTrustFragment circleOfTrustFragment = new CircleOfTrustFragment();
                MainActivity.swapFragmentIn(getActivity(),circleOfTrustFragment,CircleOfTrustFragment.TAG);
        }
    }
}
