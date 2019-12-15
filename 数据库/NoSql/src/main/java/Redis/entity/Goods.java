package Redis.entity;

public class Goods {
    private Integer goodsID;
    private String goodsName;
    private String description;
    private Float price;

    public Goods() {
    }

    public Goods(Integer goodsID, String goodsName, String description, Float price) {
        this.goodsID = goodsID;
        this.goodsName = goodsName;
        this.description = description;
        this.price = price;
    }

    public Integer getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(Integer goodsID) {
        this.goodsID = goodsID;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
