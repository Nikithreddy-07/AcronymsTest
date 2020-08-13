package com.learning.acronyms.view

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.learning.acronyms.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        setUpSearchView(menu)
        return super.onCreateOptionsMenu(menu)
    }
    private fun setUpSearchView(menu: Menu?) {
        val searchBarItem= menu?.findItem(R.id.app_bar_search)
        val searchView = searchBarItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                val fragment  =
                    supportFragmentManager.findFragmentById(R.id.acronymFragment) as AcronymsFragment
                fragment.search(query)
                return false
            }
        })
    }
}