package com.example.testapplication

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.testapplication.databinding.RecyclerRowBinding
import com.example.testapplication.databinding.RecyclerRowLowerBodyBinding

sealed class ViewHolder(private val itemBinding: ViewBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    abstract fun bindData(monsterObj: MonsterObj)

    val itemToImageMap: HashMap<String, Int> = hashMapOf(
        "head" to R.drawable.ic_head,
        "chest" to R.drawable.ic_chest,
        "deco" to R.drawable.ic_deco,
        "gloves" to R.drawable.ic_gloves,
        "legs" to R.drawable.ic_legs,
        "sheild" to R.drawable.ic_shield,
        "waist" to R.drawable.ic_waist,
    )

    class UpperBodyItemViewHolder(private val binding: RecyclerRowBinding): ViewHolder(binding) {

       override fun bindData(monsterObj: MonsterObj) {

            binding.root.setOnClickListener {
                Toast.makeText(
                    binding.root.context,
                    "Item is: ${monsterObj.type}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            itemToImageMap.get(monsterObj.type)?.let { image ->
                binding.imageView.setImageResource(image)
            }
            binding.itemName.text = monsterObj.name
            binding.itemId.text = "Item id is: " + monsterObj.id.toString()
            binding.itemRank.text = "Item rank is: " + monsterObj.rank
            binding.itemRarity.text = "Item rarity is: " + monsterObj.rarity.toString()

            binding.fire.text = "Fire: " + monsterObj.resistances.fire.toString()
            binding.water.text = "Water: " + monsterObj.resistances.water.toShort()
            binding.ice.text = "Ice: " + monsterObj.resistances.ice.toString()
            binding.thunder.text = "Thunder : " + monsterObj.resistances.thunder.toString()
            binding.dragon.text = "Dragon : " + monsterObj.resistances.dragon.toString()

       }
    }

    class LowerBodyItemViewHolder(private val binding: RecyclerRowLowerBodyBinding): ViewHolder(binding) {

        override fun bindData(monsterObj: MonsterObj) {

            binding.root.setOnClickListener {
                Toast.makeText(
                    binding.root.context,
                    "Item is: ${monsterObj.type}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            itemToImageMap.get(monsterObj.type)?.let { image ->
                binding.imageView.setImageResource(image)
            }
            binding.itemName.text = monsterObj.name
            binding.itemId.text = "Item id is: " + monsterObj.id.toString()
            binding.itemRank.text = "Item rank is: " + monsterObj.rank
            binding.itemRarity.text = "Item rarity is: " + monsterObj.rarity.toString()

            binding.baseDefense.text = "Base Def: " + monsterObj.defense.base.toString()
            binding.maxDefense.text = "Max Def: " + monsterObj.defense.max.toShort()
            binding.augmentedDefense.text = "Aug Def: " + monsterObj.rarity.toString()
        }
    }

}