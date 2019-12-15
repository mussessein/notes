package SqEl;


public class Product {

    private String goodsName;
    private Double goodsPrice;
    private Categroy categroy;


    @Override
    public String toString() {
        return "Product{" +
                "goodsName='" + goodsName + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", categroy=" + categroy +
                '}';
    }



    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Categroy getCategroy() {
        return categroy;
    }

    public void setCategroy(Categroy categroy) {
        this.categroy = categroy;
    }


}
