package com.example.testapplication

import android.widget.Filter
import java.util.*
import kotlin.collections.ArrayList

class RecyclerFilter(val adapter: RecyclerAdapter): Filter() {

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        val filterStr = constraint.toString().lowercase()

        val filteredList: ArrayList<MonsterObj> = ArrayList<MonsterObj>()
        if (constraint.toString().isEmpty()) {
            filteredList.addAll(adapter.allMonsterObj)
        } else {
            for (monsterObj in adapter.allMonsterObj) {
                if (monsterObj.name.lowercase(Locale.getDefault()).contains(filterStr)) {
                    filteredList.add(monsterObj)
                }
            }
        }
        val filteredResults = FilterResults()
        filteredResults.values = filteredList
        return filteredResults
    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        (results?.values as? ArrayList<MonsterObj>)?.let{
            adapter.updateShownList(it)
        }
    }
}













