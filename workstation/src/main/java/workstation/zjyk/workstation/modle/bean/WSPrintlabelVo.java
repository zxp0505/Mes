package workstation.zjyk.workstation.modle.bean;

public class WSPrintlabelVo {
	
	private int count;
	//size
	private String labelType;
	//模板
	private String templetType;
	
	private String serielNumber;
	
	private String productNumber;
	
	private String productModel;
	
	private String productModelType;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getSerielNumber() {
		return serielNumber;
	}

	public void setSerielNumber(String serielNumber) {
		this.serielNumber = serielNumber;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public String getProductModelType() {
		return productModelType;
	}

	public void setProductModelType(String productModelType) {
		this.productModelType = productModelType;
	}

	public String getTempletType() {
		return templetType;
	}

	public void setTempletType(String templetType) {
		this.templetType = templetType;
	}

	public String getLabelType() {
		return labelType;
	}

	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}
	
}
