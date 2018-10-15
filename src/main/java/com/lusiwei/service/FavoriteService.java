package com.lusiwei.service;

/**
 * Created  by lusiwei on 2018/10/14
 * @author lusiwei
 */
public interface FavoriteService {
    boolean addFavorite(int parseInt, int uid);

    String queryMyFavorite(Integer uid);
}
