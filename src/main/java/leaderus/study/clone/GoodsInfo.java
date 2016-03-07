package leaderus.study.clone;

public class GoodsInfo {
	
	private Integer goodsId;
	private String goodsName;
	
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	@Override
	public String toString() {
		return "GoodsInfo [goodsId=" + goodsId + ", goodsName=" + goodsName
				+ "]";
	}
	
}
