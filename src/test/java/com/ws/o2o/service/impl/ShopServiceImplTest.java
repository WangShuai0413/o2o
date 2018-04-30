package com.ws.o2o.service.impl;

import com.ws.o2o.BaseTest;
import com.ws.o2o.dto.ImageHolder;
import com.ws.o2o.dto.ShopExecution;
import com.ws.o2o.entity.Area;
import com.ws.o2o.entity.PersonInfo;
import com.ws.o2o.entity.Shop;
import com.ws.o2o.entity.ShopCategory;
import com.ws.o2o.enums.ShopStateEnum;
import com.ws.o2o.service.ShopService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.Date;

import static org.junit.Assert.*;

public class ShopServiceImplTest extends BaseTest {


    @Autowired
    private ShopService shopService;

    @Test
    public void addShop() {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(4L);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试店铺111");
        shop.setShopDesc("test111");
        shop.setShopAddr("test111");
        shop.setPhone("test");
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("/helloworld/xiaohuangren.jpg");
        InputStream shopImgIs = null;
        try {
            shopImgIs = new FileInputStream(shopImg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ShopExecution se = shopService.addShop(shop, new ImageHolder(shopImg.getName(), shopImgIs));
        assertEquals(ShopStateEnum.CHECK.getState(), se.getState());

    }
}