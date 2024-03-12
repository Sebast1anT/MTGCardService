package com.example.apiproject

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CardService {
    @GET("named")
    fun getSpecifiedCardData(@Query("exact") name : String) : Call<Card>
    @GET("catalog/card-names")
    fun getNamedCardData() : Call<Catalog>

    @GET("search?order=cmc&q=c%3Ared+pow%3D3")
    fun getSpecifiedCardDataJson() : Call<ResponseBody>

    //figuring out how to take data from teh search view and put it into the cards/search call in the api
}