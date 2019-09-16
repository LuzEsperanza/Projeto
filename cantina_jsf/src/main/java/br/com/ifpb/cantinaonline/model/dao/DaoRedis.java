package br.com.ifpb.cantinaonline.model.dao;

import br.com.ifpb.cantinaonline.model.Produto;
import com.google.gson.Gson;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.List;

public class DAORedis {
    private Jedis jedis;
    private Gson gson;
    private List<Produto> produtoList;

    public DAORedis(){
        jedis = new Jedis();
        gson = new Gson();
        produtoList = null;
    }
    public boolean salvar(int id){
        if (id==0){return false;}
        return jedis.set(""+id,gson.toJson(produtoList),
                SetParams.setParams()) != null;
    }

    public CacheResult buscar(int id){
        return jedis.get(""+id) == null? CacheResult.CACHE_MISS :
                CacheResult.CACHE_HIT;
    }

    public Produto get(int id){
        String json = jedis.get(""+id);
        if(json == null) return null;
        return gson.fromJson(json, Produto.class);
    }

    public void makeList(Produto produto){
        List<Produto> produtoList=null;
        produtoList.add(produto);
    }
}

