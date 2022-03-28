package com.ddn.visitcard.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ddn.visitcard.App
import com.ddn.visitcard.databinding.ActivityMainBinding
import com.ddn.visitcard.util.Image
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


class MainActivity : AppCompatActivity() {

    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    private  val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    private val adapter by lazy { BusinessCardAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvCards.adapter = adapter
        getAllBusinessCard()
        insertListener()
    }

    private fun insertListener(){
        binding.fab.setOnClickListener{
            val intent = Intent(this@MainActivity, AddVisitCardActivity::class.java)
            startActivity(intent)
        }
        adapter.listenerShare = {card ->
            Image.share(this@MainActivity, card)
        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(v: RecyclerView, h: RecyclerView.ViewHolder, t: RecyclerView.ViewHolder) = false
            override fun onSwiped(h: RecyclerView.ViewHolder, dir: Int) {
                lifecycleScope.launch {
                    mainViewModel.removeItem(adapter.currentList[h.adapterPosition])
                }
            }
        }).attachToRecyclerView(binding.rvCards)

    }

    private fun getAllBusinessCard() {
        mainViewModel.getAll().observe(this) { businessCard ->
            adapter.submitList(businessCard)
        }
    }
}