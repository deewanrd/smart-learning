package com.rahuldeewan.smartlearning;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.logging.Logger;

public class QuestionListFragment extends Fragment implements View.OnClickListener {
    private static final String QUESTION_NO = "id";
    private static final String QUESTION = "question";
    private static final String OPTIONA = "optionA";
    private static final String OPTIONB = "optionB";
    private static final String OPTIONC = "optionC";
    private static final String OPTIOND = "optionD";
    private static final String HINT = "hint";
    private static final String SOLUTION = "solution";
    private static final String ANSWER = "answer";

    private TextView tvQuestionNo;
    private TextView tvQuestion;
    private TextView tvOptionA;
    private TextView tvOptionB;
    private TextView tvOptionC;
    private TextView tvOptionD;
    private TextView tvAnswer;
    private CustomDialog customDialog;
    private ImageView tvhint;
    private ImageView tvSolution;
    private ImageView imageViewSubmit;
    private Logger logger = Logger.getLogger("QuestionListFragment");

    public QuestionListFragment() {
    }

    public static QuestionListFragment newInstance(int position, Question question) {
        QuestionListFragment fragment = new QuestionListFragment();
        Bundle args = new Bundle();
        args.putString(QUESTION_NO, "Question " + String.valueOf(position));
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
        tvQuestionNo = rootView.findViewById(R.id.tv_question_no);
        tvQuestion = rootView.findViewById(R.id.tv_question);
        tvOptionA = rootView.findViewById(R.id.tv_option_a);
        tvOptionB = rootView.findViewById(R.id.tv_option_b);
        tvOptionC = rootView.findViewById(R.id.tv_option_c);
        tvOptionD = rootView.findViewById(R.id.tv_option_d);
        tvAnswer = rootView.findViewById(R.id.tv_answer);
        tvhint = rootView.findViewById(R.id.tv_hint);
        tvSolution = rootView.findViewById(R.id.tv_solution);
        imageViewSubmit = rootView.findViewById(R.id.iv_submit_icon);


        tvOptionA.setOnClickListener(this);
        tvOptionB.setOnClickListener(this);
        tvOptionC.setOnClickListener(this);
        tvOptionD.setOnClickListener(this);
        tvhint.setOnClickListener(this);
        tvSolution.setOnClickListener(this);
        tvAnswer.setOnClickListener(this);
        imageViewSubmit.setOnClickListener(this);

        tvQuestionNo.setText(getArguments().getString(QUESTION_NO));
        tvQuestion.setText(getArguments().getString(QUESTION));
        tvOptionA.setText(getArguments().getString(OPTIONA));
        tvOptionB.setText(getArguments().getString(OPTIONB));
        tvOptionC.setText(getArguments().getString(OPTIONC));
        tvOptionD.setText(getArguments().getString(OPTIOND));
        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_option_a) {
            logger.info(getArguments().getString(ANSWER) + "ANSWER");
            if (getArguments().getString(ANSWER).equalsIgnoreCase("A")) {
                tvOptionA.setBackgroundColor(Color.GREEN);
            }
        }
        if (view.getId() == R.id.tv_option_b) {
            if (getArguments().getString(ANSWER).equalsIgnoreCase("B")) {
                tvOptionB.setBackgroundColor(Color.GREEN);
            }
        }
        if (view.getId() == R.id.tv_option_c) {
            if (getArguments().getString(ANSWER).equalsIgnoreCase("C")) {
                tvOptionC.setBackgroundColor(Color.GREEN);
            }
        }
        if (view.getId() == R.id.tv_option_d) {
            if (getArguments().getString(ANSWER).equalsIgnoreCase("D")) {
                tvOptionD.setBackgroundColor(Color.GREEN);
            }
        }
        if (view.getId() == R.id.tv_hint) {
            customDialog = new CustomDialog(getActivity(), "Hint", getArguments().getString(HINT), "GOT IT!");
            customDialog.show();
        }
        if (view.getId() == R.id.tv_solution) {
            customDialog = new CustomDialog(getActivity(), "Solution", getArguments().getString(SOLUTION), "GOT IT!");
            customDialog.show();
        }
        if (view.getId() == R.id.tv_answer) {
            customDialog = new CustomDialog(getActivity(), "Answer", getArguments().getString(ANSWER), "GOT IT!");
            customDialog.show();
        }
        if (view.getId() == R.id.iv_submit_icon) {
            customDialog = new CustomDialog(getActivity(), "Submit", "Are you sure you want to submit?", "YES");
            customDialog.show();
        }

    }
}