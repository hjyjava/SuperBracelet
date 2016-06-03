package com.huang.superbracelet.activity.exam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.huang.bean.Jindu;
import com.huang.superbracelet.R;
import com.huang.superbracelet.activity.exam.ExamGridActivity;
import com.huang.superbracelet.activity.exam.ScoreActivity;
import com.huang.superbracelet.base.BaseFragment;
import com.huang.superbracelet.http.MyVolleyListener;
import com.huang.superbracelet.http.everyhttp.ExamHttp;
import com.huang.superbracelet.utils.RefreshUtil;

/**
 * Created by 黄家远 on 16/5/26.
 */
public class ExamHomeFragment extends BaseFragment implements View.OnClickListener {

    private TextView child_subject_name_tv, jindu_tv, score_tv, rank_tv;
    private RelativeLayout jindu_layout, score_layout, rank_layout;
    private Button answer_bt, error_bt;

    private Jindu mJindu;
    private static final String CHILD_SUBJECT_NAME = "childSubjectName";
    private static final String MAX_GATE_NUM = "maxGateNum";
    private String childSubjectName;
    private String maxGateNum;
    private ExamHttp examHttp;

    public static ExamHomeFragment newInstance(String childSubjectName, String maxGateNum) {
        ExamHomeFragment fragment = new ExamHomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CHILD_SUBJECT_NAME, childSubjectName);
        bundle.putString(MAX_GATE_NUM, maxGateNum);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        childSubjectName = getArguments().getString(CHILD_SUBJECT_NAME);
        maxGateNum = getArguments().getString(MAX_GATE_NUM);
        examHttp = new ExamHttp(mContext);

//        localBroadcastManager = LocalBroadcastManager.getInstance(mContext);
//        localBroadcastManager.registerReceiver(updateReceiver, new IntentFilter("exam_home_update"));
    }

    @Override
    protected void initWidget() {
        child_subject_name_tv = (TextView) mainLayout.findViewById(R.id.child_subject_name_tv);
        child_subject_name_tv.setText(childSubjectName);
        jindu_tv = (TextView) mainLayout.findViewById(R.id.jindu_tv);
        score_tv = (TextView) mainLayout.findViewById(R.id.score_tv);
        rank_tv = (TextView) mainLayout.findViewById(R.id.rank_tv);

        jindu_layout = (RelativeLayout) mainLayout.findViewById(R.id.jindu_layout);
        score_layout = (RelativeLayout) mainLayout.findViewById(R.id.score_layout);
        rank_layout = (RelativeLayout) mainLayout.findViewById(R.id.rank_layout);
        jindu_layout.setOnClickListener(this);
        score_layout.setOnClickListener(this);
        rank_layout.setOnClickListener(this);

        answer_bt = (Button) mainLayout.findViewById(R.id.answer_bt);
        answer_bt.setOnClickListener(this);
//        error_bt = (Button) mainLayout.findViewById(R.id.error_bt);
//        error_bt.setOnClickListener(this);
        initData();
        RefreshUtil.getIntance(mContext).register(getClass(), new RefreshUtil.UpdateReceiverListener() {
            @Override
            public void updateData() {
                initData();
            }
        });
    }

    @Override
    protected View buildMainView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_exam_home, null);
    }

    private void initData() {
        examHttp.getJindu(studentId, childSubjectId, new MyVolleyListener<Jindu>() {
            @Override
            public void onSuccess(Jindu jindu) {
                mJindu = jindu;
                jindu_tv.setText(jindu.getNum() + "/" + maxGateNum);
                score_tv.setText(jindu.getGrade());
                rank_tv.setText(jindu.getRank());
            }

            @Override
            public void onError(VolleyError volleyError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jindu_layout:
                break;
            case R.id.score_layout:
                startActivity(new Intent(mContext, ScoreActivity.class));
                break;
            case R.id.rank_layout:
                break;
            case R.id.answer_bt:
                if (mJindu == null)
                    return;
                Intent intent = new Intent(mContext, ExamGridActivity.class);
                intent.putExtra("maxGateNum", maxGateNum);
                int askNum = Integer.valueOf(mJindu.getNum());
                intent.putExtra("jinduNum", askNum);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        RefreshUtil.getIntance(mContext).unregister(getClass());
    }


    //    class UpdateReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            initData();
//        }
//    }
}
