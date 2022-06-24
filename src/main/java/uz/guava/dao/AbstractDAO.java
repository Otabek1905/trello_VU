package uz.guava.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uz.guava.utils.BaseUtils;

public class AbstractDAO<D extends BaseDAO> {
    protected final D dao;
    protected final Gson gson;
    protected final BaseUtils utils;


    public AbstractDAO(D dao, BaseUtils utils) {
        this.dao = dao;
        this.gson = new GsonBuilder().create();
        this.utils = utils;
    }

}
