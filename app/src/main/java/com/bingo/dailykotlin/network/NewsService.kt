package com.bingo.dailykotlin.network

import com.bingo.dailykotlin.mvp.entity.NewsSummary
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Url
import rx.Observable

/**
 * @author bingo.
 * @date Create on 2017/11/29.
 * @Description
 */
interface NewsService {

    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    fun getNewsList(@Header("Cache-Control") cacheControl: String?,
                    @Path("type") type: String?,
                    @Path("id") id: String?,
                    @Path("startPage") startPage: Int?): Observable<Map<String, List<NewsSummary>>>


    @GET
    fun getNewsBodyHtmlPhoto(
            @Url photoPath: String): Observable<ResponseBody>
    //@Url，它允许我们直接传入一个请求的URL。这样以来我们可以将上一个请求的获得的url直接传入进来，baseUrl将被无视
    // baseUrl 需要符合标准，为空、""、或不合法将会报错


}