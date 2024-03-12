package com.example.apiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import com.example.apiproject.databinding.ActivityCardSearchBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CardSearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCardSearchBinding
    private lateinit var search : SearchView
    private lateinit var allCards : Catalog
    private lateinit var searchedCards : ArrayList<Card>
    private lateinit var cardAdapter : CardAdapter

    companion object {
        val TAG = "CardSearchActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        search = binding.SearchViewCardSearchMainSearch
        retrofitSetup()

    }
    fun retrofitSetup(){
        val cardService = RetrofitHelper.getInstance().create(CardService::class.java)
        val cardCall = cardService.getNamedCardData()
        val inputStream = resources.openRawResource(R.raw.cards)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        Log.d(TAG, "onCreate: jsonString: $jsonString")
        val gson = Gson()
        val type = object : TypeToken<Catalog>() { }.type
        allCards = gson.fromJson<Catalog>(jsonString, type)

                setSearchBar()

                // this is where you get your data.
                // this is where you will set up your adapter for recyclerview
                // don't forget a null check before trying to use the data
                // response.body() contains the object in the <> after Response







            }
    fun addCardFromRetrofitCatalog(exactName : String) {
        val cardService = RetrofitHelper.getInstance().create(CardService::class.java)
        val cardCall = cardService.getSpecifiedCardData(exactName)
        val adapter = CardAdapter(searchedCards)
        cardCall.enqueue(object: Callback<Card> {
            override fun onResponse(
                call: Call<Card>,
                response: Response<Card>
            ) {

                // this is where you get your data.
                // this is where you will set up your adapter for recyclerview
                // don't forget a null check before trying to use the data
                // response.body() contains the object in the <> after Response


                    if(response.body() != null) {
                        searchedCards.add(response.body()!!)



                    }





                Log.d(TAG, "onResponse ${response.body()}")
                Log.d(TAG, "onResponse ${response.raw()}")


            }

            override fun onFailure(call: Call<Card>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")
            }
        } )

    }
    /*fun parseData(query : String) : ArrayList<String> {
        val searchedCards : ArrayList<String> = arrayListOf()
        for (i in 0 until allCards.data.size) {
            if (allCards.data[i].contains(query)){
                searchedCards.add(allCards.data[i])
            }
        }
        return searchedCards
    }
    fun stringToCards(searchedCards : ArrayList<String>){
        val everyCard : ArrayList<Card> = arrayListOf()
        for (i in 0 until searchedCards.size){
            Log.d(TAG, "AHHH:  yes ${searchedCards.size}")
          everyCard.add(retrofitCatalog(searchedCards[i]))

        }
        Log.d(TAG, "stringToCards: $everyCard")
    } */
    fun parseCatalog(query : String) {
        Log.d(TAG, "YepWoohoo!: ${allCards.data[0]}")
        for (i in 0 until allCards.data.size){

            if (allCards.data[i].toLowerCase().indexOf(query.toLowerCase()) != -1){
                Log.d(TAG, "parseCatalog: made it here")
                addCardFromRetrofitCatalog(allCards.data[i])
            }
        }

    }

    fun setSearchBar(){
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query : String?): Boolean {
                searchedCards = arrayListOf()
                if (query != null) {
                    binding.buttonCardSearchAdvancedSearch.visibility = View.GONE
                    binding.SearchViewCardSearchMainSearch.visibility = View.GONE
                    binding.textViewCardSearchTitle.visibility = View.GONE
                    binding.recyclerViewCardDisplayCardSearch.visibility = View.VISIBLE
                    parseCatalog(query)


                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })
    }
}