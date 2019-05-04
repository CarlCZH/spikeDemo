package com.hui.miaosha.domain;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 15:50 2019\4\15 0015
 */
public class Goods {

    //商品ID
    private Long id;
    //商品名称
    private String goodsName;
    //商品标题
    private String goodsTitle;
    //商品图片
    private String goodsImg;
    //商品的详细介绍
    private String goodsDetail;
    //商品单价
    private Double goodsPrice;
    //商品库存，-1表示没有限制
    private Integer goods_stock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoods_stock() {
        return goods_stock;
    }

    public void setGoods_stock(Integer goods_stock) {
        this.goods_stock = goods_stock;
    }
}
