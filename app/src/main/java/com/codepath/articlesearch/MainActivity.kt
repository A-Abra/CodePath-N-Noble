package com.codepath.articlesearch

import android.content.res.Configuration
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.codepath.articlesearch.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.serialization.json.Json


fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var actionEvent: TextView? = null
    private fun replaceFragment(articleListFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.article_frame_layout, articleListFragment)
        fragmentTransaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionEvent = findViewById(R.id.actionEvent);

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // Call helper method to swap the FrameLayout with the fragment
        //replaceFragment(ArticleListFragment())
        val fragmentManager = supportFragmentManager

        // Fragment definition
        val bestSellerBooksFragment: Fragment = BestSellerBooksFragment()
        val articleListFragment: Fragment = ArticleListFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // Navigation selection declaration
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_books -> fragment = bestSellerBooksFragment
                R.id.nav_articles -> fragment = articleListFragment
            }
            replaceFragment(fragment) // Change parameter to article list fragment if you get an error
            true
        }
        // Initializing the default selection
        bottomNavigationView.selectedItemId = R.id.nav_books
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}