package com.example.testapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.databinding.RecyclerRowBinding
import com.example.testapplication.databinding.RecyclerRowLowerBodyBinding
import java.util.*
import kotlin.collections.ArrayList

class RecyclerAdapter : RecyclerView.Adapter<ViewHolder>(), Filterable {

    //store entire list when filtering, show only monsterData
    var allMonsterObj: ArrayList<MonsterObj> = ArrayList()
    val monsterData: ArrayList<MonsterObj> = ArrayList()

    private var recyclerFilter: Filter = RecyclerFilter(this)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType){
            R.layout.recycler_row_lower_body -> {
                val itemBinding = RecyclerRowLowerBodyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolder.LowerBodyItemViewHolder(itemBinding)
            }
            else ->{
                val itemBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolder.UpperBodyItemViewHolder(itemBinding)
            }
        }
    }

    fun updateData(newUpdatedList: ArrayList<MonsterObj>){
        allMonsterObj.clear()
        allMonsterObj.addAll(newUpdatedList);
        monsterData.clear()
        monsterData.addAll(newUpdatedList);
        notifyDataSetChanged()
    }

    fun updateShownList(newUpdatedList: ArrayList<MonsterObj>){
        monsterData.clear()
        monsterData.addAll(newUpdatedList)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        monsterData[position].let { monsterObj ->
            holder.bindData(monsterObj)
        }
    }

    override fun getItemCount(): Int {
        return monsterData.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(monsterData[position].type){
            "deco", "gloves", "chest", "head",  -> R.layout.recycler_row
            else -> R.layout.recycler_row_lower_body
        }
    }

    override fun getFilter(): Filter {
        return recyclerFilter
    }
}

data class Defense(
    val base: Int = 0,
    val max: Int = 0,
    val augmented: Int = 0
)
data class Resistances(
    val fire: Int = 0,
    val water: Int = 0,
    val ice: Int = 0,
    val thunder: Int = 0,
    val dragon: Int = 0
)

data class MonsterObj(
    val id: Int= 0,
    val type: String = "",
    val rank: String = "",
    val rarity: Int = 1,
    val defense: Defense,
    val name: String = "",
    val resistances:Resistances
)