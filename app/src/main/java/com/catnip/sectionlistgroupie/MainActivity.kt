package com.catnip.sectionlistgroupie

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.sectionlistgroupie.databinding.ActivityMainBinding
import com.catnip.sectionlistgroupie.model.SectionedData
import com.catnip.sectionlistgroupie.viewitems.DataItem
import com.catnip.sectionlistgroupie.viewitems.HeaderItem
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section


class MainActivity : AppCompatActivity() {

    private val adapter: GroupieAdapter by lazy {
        GroupieAdapter()
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setData()
    }

    private fun setData() {
        binding.rvPage.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        val section = getListData().map {
            val section = Section()
            section.setHeader(HeaderItem(it.name) { data ->
                Toast.makeText(this, "Header Clicked : $data", Toast.LENGTH_SHORT).show()
            })
            val dataSection = it.data.map { data ->
                DataItem(data) { data ->
                    Toast.makeText(this, "Item Clicked : $data", Toast.LENGTH_SHORT).show()
                }
            }
            section.addAll(dataSection)
            section
        }
        adapter.addAll(section)
    }


    private fun getListData(): List<SectionedData> = listOf(
        SectionedData("Category A", listOf("Ayam Goreng", "Ayam Bakar", "Ayam Bumbu kuning")),
        SectionedData("Category B", listOf("Bakwan", "Bakso", "Bebek goreng")),
        SectionedData("Category C", listOf("Cendol", "Cakwe", "Cucur")),
    )
}