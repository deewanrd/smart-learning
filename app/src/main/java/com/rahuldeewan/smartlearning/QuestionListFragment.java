package com.rahuldeewan.smartlearning;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.logging.Logger;

public class QuestionListFragment extends Fragment {
    private static final String QUESTION = "question";
    private static final String OPTION1 = "option1";
    private static final String OPTION2 = "option2";
    private static final String OPTION3 = "option3";
    private static final String OPTION4 = "option4";
    private static final String HINT = "hint";
    private static final String SOLUTION = "solution";
    private static final String ANSWER = "answer";

    private Logger logger = Logger.getLogger("QuestionListFragment");

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    public QuestionListFragment() {
    }

    public static QuestionListFragment newInstance(Question question) {
        QuestionListFragment fragment = new QuestionListFragment();
        Bundle args = new Bundle();
        args.putString(QUESTION, question.getQuestion());
        args.putString(OPTION1, question.getOptionA());
        args.putString(OPTION2, question.getOptionB());
        args.putString(OPTION3, question.getOptionC());
        args.putString(OPTION4, question.getOptionD());
        args.putString(HINT, question.getHint());
        args.putString(SOLUTION, question.getSolution());
        args.putString(ANSWER, question.getAnswer());
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_question_list, container, false);
        TextView ques = rootView.findViewById(R.id.question);
        TextView op1 = rootView.findViewById(R.id.op1);
        TextView op2 = rootView.findViewById(R.id.op2);
        TextView op3 = rootView.findViewById(R.id.op3);
        TextView op4 = rootView.findViewById(R.id.op4);
        TextView ans = rootView.findViewById(R.id.answer);
        TextView hint = rootView.findViewById(R.id.hint);
        TextView sol = rootView.findViewById(R.id.solution);


        ques.setText(getArguments().getString(QUESTION));
        op1.setText(getArguments().getString(OPTION1));
        op2.setText(getArguments().getString(OPTION2));
        op3.setText(getArguments().getString(OPTION3));
        op4.setText(getArguments().getString(OPTION4));
        hint.setText(getArguments().getString(HINT));
        sol.setText(getArguments().getString(SOLUTION));
        ans.setText(getArguments().getString(ANSWER));
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }


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
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}