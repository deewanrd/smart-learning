package com.rahuldeewan.smartlearning;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.logging.Logger;

public class QuestionListFragment extends Fragment {
    private static final String QUESTION_NO = "id";
    private static final String QUESTION = "question";
    private static final String OPTIONA = "optionA";
    private static final String OPTIONB = "optionB";
    private static final String OPTIONC = "optionC";
    private static final String OPTIOND = "optionD";
    private static final String HINT = "hint";
    private static final String SOLUTION = "solution";
    private static final String ANSWER = "answer";

    private Logger logger = Logger.getLogger("QuestionListFragment");

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    private OnFragmentInteractionListener mListener;
    Button b1;
    public QuestionListFragment() {
    }

    public static QuestionListFragment newInstance(int position,Question question) {
        QuestionListFragment fragment = new QuestionListFragment();
        Bundle args = new Bundle();
        args.putString(QUESTION_NO, "Question "+String.valueOf(position));
        args.putString(QUESTION, question.getQuestion());
        args.putString(OPTIONA, question.getOptionA());
        args.putString(OPTIONB, question.getOptionB());
        args.putString(OPTIONC, question.getOptionC());
        args.putString(OPTIOND, question.getOptionD());
        args.putString(HINT, question.getHint());
        args.putString(SOLUTION, question.getSolution());
        args.putString(ANSWER, question.getAnswer());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_question_list, container, false);
        TextView tvQuestionNo = rootView.findViewById(R.id.tv_question_no);
        TextView tvQuestion = rootView.findViewById(R.id.tv_question);
        TextView tvOptionA = rootView.findViewById(R.id.tv_option_a);
        TextView tvOptionB = rootView.findViewById(R.id.tv_option_b);
        TextView tvOptionC = rootView.findViewById(R.id.tv_option_c);
        TextView tvOptionD = rootView.findViewById(R.id.tv_option_d);
        TextView tvAnswer = rootView.findViewById(R.id.tv_answer);
        TextView tvHint = rootView.findViewById(R.id.tv_hint);
        TextView tvSolution = rootView.findViewById(R.id.tv_solution);

     /*   b1 = rootView.findViewById(R.id.test);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = 4;
                logger.info(QuestionListActivity.questionList.get(4).getQuestion());
                QuestionListFragment q1 = new QuestionListFragment();
                q1.newInstance((position+1),QuestionListActivity.questionList.get(position));
                logger.info("after");
            }
        });*/

        tvQuestionNo.setText(getArguments().getString(QUESTION_NO));
        tvQuestion.setText(getArguments().getString(QUESTION));
        tvOptionA.setText(getArguments().getString(OPTIONA));
        tvOptionB.setText(getArguments().getString(OPTIONB));
        tvOptionC.setText(getArguments().getString(OPTIONC));
        tvOptionD.setText(getArguments().getString(OPTIOND));
//        tvHint.setText(getArguments().getString(HINT));
//        tvSolution.setText(getArguments().getString(SOLUTION));
//        tvAnswer.setText(getArguments().getString(ANSWER));
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