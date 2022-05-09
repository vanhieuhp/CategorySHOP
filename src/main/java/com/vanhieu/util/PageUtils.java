package com.vanhieu.util;

public class PageUtils {

    public static int getPage(int totalItem, int limit) {
        int totalPage = (int) Math.ceil( (double) totalItem / limit);
        return totalPage;
    }
}
