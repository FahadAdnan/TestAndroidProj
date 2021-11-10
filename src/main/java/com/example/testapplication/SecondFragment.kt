package com.example.testapplication

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.databinding.SecondFragmentBinding
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import android.text.Editable

import android.text.TextWatcher

class SecondFragment : Fragment(R.layout.second_fragment) {

    private lateinit var client: okhttp3.OkHttpClient
    private lateinit var adapter: RecyclerAdapter

    companion object {

        val NAME_STRING = "NAME_STRING"
        val AGE_VALUE = "AGE_STRING"
        val COLOR_STRING = "FAV_COLOR_STRING"

        fun newInstance(secondFragmentArgs: secondFragmentBundle): SecondFragment{
            val fragment = SecondFragment();
            val args = Bundle()
            args.putString(NAME_STRING, secondFragmentArgs.name)
            args.putInt(AGE_VALUE, secondFragmentArgs.age)
            args.putString(COLOR_STRING, secondFragmentArgs.favColor)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var linearLayoutManager: LinearLayoutManager

    private var _binding: SecondFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SecondViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SecondFragmentBinding.inflate(inflater, container, false)

        setStatementText()
        client = okhttp3.OkHttpClient()

        adapter = RecyclerAdapter()
        binding.recyclerView.adapter = adapter

        binding.getDataBtn.setOnClickListener { getData() }
        linearLayoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.layoutManager = linearLayoutManager
        setUpSearchView()
        return binding.root
    }

    private fun setUpSearchView() {
        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s);
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable) { adapter.filter.filter(s) }
        })
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SecondViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun getData(){
        val API_URL: String = "https://mhw-db.com/armor"
        val request = Request.Builder()
            .url(API_URL)
            .build()

        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request");
            }
            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { jsonData ->
                    generateClassFromJSON(jsonData)
                }
            }
        })
    }

    private fun generateClassFromJSON(jsonStr: String){
        val gson = Gson()
        val stateArr: List<MonsterObj> = gson.fromJson(jsonStr , Array<MonsterObj>::class.java).toList()

        val objectList: ArrayList<MonsterObj> = ArrayList()
        objectList.addAll(stateArr)

        activity?.runOnUiThread { adapter.updateData(objectList) }
    }

    @SuppressLint("SetTextI18n")
    private fun setStatementText(){
        val bundle = this.arguments
        bundle?.let {
            val name: String? = it.getString(NAME_STRING);
            val age: Int = it.getInt(AGE_VALUE)
            val favColor: String? = it.getString(COLOR_STRING);

            if(name != null && age > 0 && favColor != null) {
                binding.textStatement.text =
                    "Hello,$name, You are $age years old and your favourite color is $favColor"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    data class secondFragmentBundle(val name: String = "", val age: Int = 0, val favColor: String ="")
}
