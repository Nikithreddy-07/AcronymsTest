package com.learning.acronyms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_acronyms.*


/**
 * A simple [Fragment] subclass.
 * Use the [AcronymsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AcronymsFragment : Fragment() {

    private lateinit var acronymViewModel: AcronymViewModel
    private var acronymRecyclerViewAdapter: AcronymRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_acronyms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupViewmodel()
    }

    private fun setupUI() {
        acronymRecyclerViewAdapter = AcronymRecyclerViewAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = acronymRecyclerViewAdapter
        }
    }
    private fun setupViewmodel() {
        acronymViewModel =  ViewModelProvider(this,
            AcronymViewModelFactory(Repository()))
            .get(AcronymViewModel::class.java)

        acronymViewModel.getAcronyms("aaa")
        acronymViewModel.acronymsResponce.observe(viewLifecycleOwner, Observer { acronymsResponce ->
          if(acronymsResponce.isNotEmpty()) {
              acronymRecyclerViewAdapter?.setData(acronymsResponce.first().lfs)
          }

        })
        acronymViewModel.acronymsError.observe(viewLifecycleOwner, Observer {

        })
    }

}