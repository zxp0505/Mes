package workstation.zjyk.workstation.modle.bean;

import java.util.List;

public class WSLabelBean {


    /**
     * labelName : 60x40
     * labelType : 1
     * templetData : [{"templetName":"模板一","templetType":2}]
     */

    private String labelName;
    private String labelType;
    private boolean isSlect;
    private int position;
    private List<WSLabelBean.TempletDataBean> templetData;

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public boolean isSlect() {
        return isSlect;
    }

    public void setSlect(boolean slect) {
        isSlect = slect;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    public List<WSLabelBean.TempletDataBean> getTempletData() {
        return templetData;
    }

    public void setTempletData(List<WSLabelBean.TempletDataBean> templetData) {
        this.templetData = templetData;
    }

    public static class TempletDataBean {
        /**
         * templetName : 模板一
         * templetType : 2
         */

        private String templetName;
        private int templetType;
        private boolean isSelect;
        private int position;
        private int count;
        private String parentType;

        public String getParentType() {
            return parentType == null ? "" : parentType;
        }

        public void setParentType(String parentType) {
            this.parentType = parentType;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getTempletName() {
            return templetName;
        }

        public void setTempletName(String templetName) {
            this.templetName = templetName;
        }

        public int getTempletType() {
            return templetType;
        }

        public void setTempletType(int templetType) {
            this.templetType = templetType;
        }
    }
}
