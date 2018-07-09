package workstation.zjyk.workstation.util.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.ethank.mylibrary.resourcelibrary.toast.ToastUtil;
import workstation.zjyk.workstation.R;
import workstation.zjyk.workstation.modle.bean.WSLabelBean;
import workstation.zjyk.workstation.ui.adapter.WSAdapterLabelTemplet;
import workstation.zjyk.workstation.util.dialog.callback.WSDialogCallBackTwo;

/**
 * 模板
 */

public class WSLabelTempletDialog extends WSCommonDialog implements DialogInterface.OnDismissListener {


    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    String title;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    WSLabelBean labelBean;
    List<WSLabelBean.TempletDataBean> datas;
    WSDialogCallBackTwo wsDialogCallBackTwo;
    @BindView(R.id.edit_count)
    EditText editCount;
    @BindView(R.id.tv_print)
    TextView tvPrint;
    WSLabelBean.TempletDataBean selectLabelBean;
    private WSAdapterLabelTemplet mAdapterLabelTemplet;

    public WSLabelTempletDialog(Context context, String title, WSDialogCallBackTwo wsDialogCallBackTwo) {
        super(context, R.style.CommonDialog);
        this.title = title;
        this.wsDialogCallBackTwo = wsDialogCallBackTwo;
        initDialogView(context);

    }


    public WSLabelTempletDialog(Context context, int themeResId) {
        super(context, themeResId);
        initDialogView(context);
    }

    protected WSLabelTempletDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initDialogView(context);
    }

    private void initDialogView(Context context) {
        setContentView(R.layout.dialog_label_templet);
        ButterKnife.bind(this);
        setOnDismissListener(this);
        setTvBg();
        mAdapterLabelTemplet = new WSAdapterLabelTemplet();
        recycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleview.setAdapter(mAdapterLabelTemplet);
        mAdapterLabelTemplet.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                WSLabelBean.TempletDataBean labelBean = (WSLabelBean.TempletDataBean) adapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.cons_root:

                        if (selectLabelBean != null && selectLabelBean.getTempletType() == labelBean.getTempletType()) {
                            //选中同一个
                            boolean select = selectLabelBean.isSelect();
                            selectLabelBean.setSelect(!select);
                            adapter.notifyItemChanged(selectLabelBean.getPosition());
                        } else {
                            if (selectLabelBean != null) {
                                if (selectLabelBean.isSelect()) {
                                    selectLabelBean.setSelect(false);
                                    adapter.notifyItemChanged(selectLabelBean.getPosition());
                                }
                            }
                            if (!labelBean.isSelect()) {
                                labelBean.setSelect(true);
                                adapter.notifyItemChanged(position);

                            }
                        }
                        selectLabelBean = labelBean;
                        setTvBg();
                        break;
                }
            }
        });

    }

    public void setData(WSLabelBean labelBean) {
        this.labelBean = labelBean;
        this.datas = labelBean.getTempletData();
        refreshData();
    }

    private void refreshData() {
        tvTitle.setText("规格" + labelBean.getLabelName() + "请选择模板并输入数量");
        mAdapterLabelTemplet.setNewData(datas);
    }

    @OnClick({R.id.iv_close, R.id.tv_print})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                wsDialogCallBackTwo.OnNegativeClick();
                break;
            case R.id.tv_print:
                boolean tag = (boolean) tvPrint.getTag();
                String countStr = editCount.getText().toString().trim();
                if (tag) {
                    if (!TextUtils.isEmpty(countStr) && selectLabelBean != null) {
                        selectLabelBean.setCount(Integer.parseInt(countStr));
                        selectLabelBean.setParentType(labelBean.getLabelType());
                        wsDialogCallBackTwo.OnPositiveClick(selectLabelBean);
                    } else {
                        ToastUtil.showInfoCenterShort("请输入数量");
                    }
                } else {
                    ToastUtil.showInfoCenterShort("请选择模板");
                }
                break;
        }

    }

    private void setTvBg() {
        if (selectLabelBean != null && selectLabelBean.isSelect()) {
            tvPrint.setBackgroundResource(R.drawable.shape_report_work_select);
            tvPrint.setTag(true);
        } else {
            tvPrint.setTag(false);
            tvPrint.setBackgroundResource(R.drawable.shape_report_work_unselect);

        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if(selectLabelBean != null){
            selectLabelBean.setSelect(false);
            mAdapterLabelTemplet.notifyItemChanged(selectLabelBean.getPosition());
        }
        editCount.setText("");
        selectLabelBean = null;
        setTvBg();
    }
}
