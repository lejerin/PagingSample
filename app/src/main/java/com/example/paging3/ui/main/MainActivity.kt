package com.example.paging3.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.paging3.R
import com.example.paging3.api.FestivalService
import com.example.paging3.base.BaseActivity
import com.example.paging3.databinding.ActivityMainBinding
import com.example.paging3.repository.UserRepository
import com.example.paging3.ui.adapter.FestivalAdapter
import com.example.paging3.ui.adapter.FestivalLoadStateAdapter
import com.example.paging3.ui.detail.DetailActivity.Companion.startActivityWithTransition
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() ,SwipeRefreshLayout.OnRefreshListener{

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    lateinit var festivalAdapter: FestivalAdapter
    lateinit var festivalRecyclerview: RecyclerView
    private var festivalJob: Job? = null
    private val festivalViewModel: FestivalViewModel by lazy { FestivalViewModel(UserRepository(FestivalService.getApiClinet())) }

    lateinit var swiperefresh: SwipeRefreshLayout
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@MainActivity
        }

        swiperefresh = findViewById(R.id.swipe_refresh_layout)
        swiperefresh.setOnRefreshListener(this)

        festivalAdapter = FestivalAdapter { view, item ->
            startActivityWithTransition(this@MainActivity, view, item)
        }

        festivalRecyclerview = findViewById(R.id.festival_recycler_view)
        festivalRecyclerview.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = festivalAdapter.withLoadStateFooter(
                footer = FestivalLoadStateAdapter { festivalAdapter.retry() }
            )
        }

        getFestivalItem()
    }

    private fun getFestivalItem(){
        //festivalJob?.cancel()

        festivalJob = lifecycleScope.launch {
            festivalViewModel.getFestivalPagingSource(getString(R.string.data_api_key))
                .subscribe {
                    lifecycleScope.launch {
                        festivalAdapter.submitData(it)
                    }

                }
        }
    }

    override fun onRefresh() {
        lifecycleScope.launch {
            festivalAdapter.refresh()
            getFestivalItem()
            swiperefresh.isRefreshing = false
        }
    }
}