package com.example.pubtime

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var seekBar: SeekBar
    private lateinit var checkBoxFootball: CheckBox
    private lateinit var checkBoxTennis: CheckBox
    private lateinit var checkBoxSpeedway: CheckBox
    private lateinit var buttonSearch: Button
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar = findViewById(R.id.seekBar)
        checkBoxFootball = findViewById(R.id.checkBoxFootball)
        checkBoxTennis = findViewById(R.id.checkBoxTennis)
        checkBoxSpeedway = findViewById(R.id.checkBoxSpeedway)
        buttonSearch = findViewById(R.id.buttonSearch)
        listView = findViewById(R.id.listView)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listView.adapter = adapter

        buttonSearch.setOnClickListener {
            searchPubs()
        }
    }

    private fun searchPubs() {
        val distance = seekBar.progress * 5
        val pubs = mutableListOf<String>()

        if (distance == 0) {
            pubs.add("Brak barów w żadanej odległości :(")
        } else {
            if (checkBoxFootball.isChecked) {
                pubs.addAll(getFootballPubs(distance))
            }
            if (checkBoxTennis.isChecked) {
                pubs.addAll(getTennisPubs(distance))
            }
            if (checkBoxSpeedway.isChecked) {
                pubs.addAll(getSpeedwayPubs(distance))
            }
        }

        adapter.clear()
        adapter.addAll(pubs)
        adapter.notifyDataSetChanged()
    }

    private fun getFootballPubs(distance: Int): List<String> {
        return when (distance) {
            in 0..5 -> listOf("pub superBowl - mecze piłkarskie", "pub JackRussel - mecze piłkarskie")
            in 6..10 -> listOf("pub superBowl - mecze piłkarskie", "pub JackRussel - mecze piłkarskie", "pub footballGame - mecze piłkarskie")
            in 11..15 -> listOf("pub superBowl - mecze piłkarskie", "pub JackRussel - mecze piłkarskie", "pub footballGame - mecze piłkarskie", "pub ekstraGame - mecze piłkarskie, mecze tenisa, zawody żużlowe", "pub Wilki - mecze piłkarskie")
            else -> listOf("pub superBowl - mecze piłkarskie", "pub JackRussel - mecze piłkarskie", "pub footballGame - mecze piłkarskie", "pub ekstraGame - mecze piłkarskie, mecze tenisa, zawody żużlowe", "pub Wilki - mecze piłkarskie", "pub dzikie Koty - mecze piłkarskie")
        }
    }

    private fun getTennisPubs(distance: Int): List<String> {
        return when (distance) {
            in 0..5 -> listOf("pub superRakieta - mecze tenisa")
            in 6..10 -> listOf("pub superRakieta - mecze tenisa", "pub biforek - mecze tenisowe")
            else -> listOf("pub superRakieta - mecze tenisa", "pub biforek - mecze tenisowe", "pub ekstraGame - mecze piłkarskie, mecze tenisa, zawody żużlowe", "pub czarno na białym - mecze tenisa")
        }
    }

    private fun getSpeedwayPubs(distance: Int): List<String> {
        return when (distance) {
            in 0..5 -> listOf()
            in 6..10 -> listOf("pub Racing - zawody żużlowe")
            else -> listOf("pub Racing - zawody żużlowe", "pub ekstraGame - mecze piłkarskie, mecze tenisa, zawody żużlowe")
        }
    }
}
