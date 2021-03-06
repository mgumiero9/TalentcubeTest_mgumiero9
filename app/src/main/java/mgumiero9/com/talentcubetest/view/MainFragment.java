package mgumiero9.com.talentcubetest.view;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import mgumiero9.com.talentcubetest.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    private static final String TAG = MainFragment.class.getSimpleName();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Typeface mTypeFace;
    private Typeface mTypeFaceProximaRegular;
    private Typeface mTypeFaceProximaLight;
    private View view;
    private Button mBtnTutCadNext;
    private String viaCepUrl;
    private Button btnStart, btnStart2;
    private TextView tvQuestion2;
    private Button btnDone1, btnDone2;

    public MainFragment() {
        // Required empty public constructor
    }

/*    *//**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PwdFragment.
     *//*
    // TODO: Rename and change types and number of parameters
    public static PwdFragment newInstance() {
        PwdFragment fragment = new PwdFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    public static MainFragment newInstance() {
        return new MainFragment();
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

        view = inflater.inflate(R.layout.fragment, container, false);

        btnStart = (Button) view.findViewById(R.id.btn_start);
        btnDone1 = (Button) view.findViewById(R.id.done1);
        btnDone2 = (Button) view.findViewById(R.id.done2);
        btnStart2 = (Button) view.findViewById(R.id.btn_start2);
        tvQuestion2 = (TextView) view.findViewById(R.id.tv_question2);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionFragment questionFragment= new QuestionFragment();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.container, questionFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        /* Check which question */
        SharedPreferences myPreferences = getActivity().getSharedPreferences("myPrefs",0);

        String mQuestion = myPreferences.getString("questionFinished","");
        Log.e(TAG, "SPwhichTry: " + mQuestion);

        if (mQuestion.equals("one")) {
            FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.fl2);
            frameLayout.setBackgroundColor(Color.parseColor("#308BCB"));
            btnStart.setVisibility(View.GONE);
            btnStart2.setTextColor(Color.parseColor("#ffffff"));
            btnDone1.setVisibility(View.VISIBLE);
            btnStart2.setVisibility(View.VISIBLE);
            tvQuestion2.setVisibility(View.VISIBLE);
            tvQuestion2.setTextColor(Color.parseColor("#ffffff"));
        }

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
