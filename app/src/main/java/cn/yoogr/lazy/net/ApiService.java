package cn.yoogr.lazy.net;

import java.util.List;

import cn.yoogr.lazy.data.BaseResponse;
import cn.yoogr.lazy.data.GankData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("data/福利/{number}/{page}")
    Call<BaseResponse<List<GankData>>> getWelfare(@Path("page") int page, @Path("number") int number);
}
