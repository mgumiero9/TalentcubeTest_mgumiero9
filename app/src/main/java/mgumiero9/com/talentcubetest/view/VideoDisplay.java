package mgumiero9.com.talentcubetest.view;

/**
 * Created by mgumiero9 on 17/03/17.
 */

import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import mgumiero9.com.talentcubetest.R;
import mgumiero9.com.talentcubetest.util.SharedPrefStore;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VideoDisplay.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VideoDisplay#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoDisplay extends Fragment  {

    private static final String TAG = VideoDisplay.class.getSimpleName();

    private int whichTry = 1;

    private OnFragmentInteractionListener mListener;
    private Typeface mTypeFace;
    private Typeface mTypeFaceProximaRegular;
    private Typeface mTypeFaceProximaLight;
    private View view;
    private Button mBtnTutCadNext;
    private String viaCepUrl;
    private String mWhichTry;
    private SharedPreferences.Editor editor;
    private SharedPrefStore mSharedPrefStore;


    public VideoDisplay() {
        // Required empty public constructor
    }

    public static VideoDisplay newInstance() {
        return new VideoDisplay();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        view = inflater.inflate(R.layout.fragment_video_view, container, false);

        final SharedPreferences mSharedPreferences = getActivity().getSharedPreferences("myPrefs",0);
        mWhichTry = mSharedPreferences.getString("SPwhichTry","");
        Log.e(TAG, "SPwhichTry: " + mWhichTry);

        Button btnSecondTry = (Button) view.findViewById(R.id.btn_second_try);

        btnSecondTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if (mWhichTry.equals("first")) {
                    getFragmentManager().popBackStackImmediate();
                }
            }
        });

        Button btnContinue = (Button) view.findViewById(R.id.btn_continue);

        editor = mSharedPreferences.edit();
        mSharedPrefStore = new SharedPrefStore();
        
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSharedPrefStore.StorePair(mSharedPreferences, editor, "questionFinished", "one");

                MainFragment mainFragment = new MainFragment();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.container, mainFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button btnPlayStop = (Button) view.findViewById(R.id.btn_play_stop);
        btnPlayStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoView videoView = (VideoView) view.findViewById(R.id.videoview);
                //MediaController mediaController = new MediaController(this);
                // mediaController.setAnchorView(videoView);
                //videoView.setMediaController(mediaController);

                videoView.setMediaController(new MediaController(getActivity()));
                videoView.setVideoPath("/answerq1.mp4");
                videoView.requestFocus();
                videoView.start();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

/*    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

