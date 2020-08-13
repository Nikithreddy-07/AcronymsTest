package com.learning.acronyms.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.learning.acronyms.*
import com.learning.acronyms.networking.Repository
import com.learning.acronyms.viewmodel.AcronymViewModel
import com.learning.acronyms.viewmodel.AcronymViewModelFactory
import kotlinx.android.synthetic.main.fragment_acronyms.*


/**
 * A simple [Fragment] subclass.
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
        acronymRecyclerViewAdapter =
            AcronymRecyclerViewAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = acronymRecyclerViewAdapter
        }
    }
    private fun setupViewmodel() {
        acronymViewModel =  ViewModelProvider(this,
            AcronymViewModelFactory(Repository())
        )
            .get(AcronymViewModel::class.java)

        acronymViewModel.acronymsResponce.observe(viewLifecycleOwner, Observer { acronymsResponce ->
            showProgress(false)
            if(acronymsResponce.isNotEmpty()) {
                acronymRecyclerViewAdapter?.setData(acronymsResponce.first().lfs)
            }else {
                acronymRecyclerViewAdapter?.setData(null)
                showMessate(getString(R.string.empty_result))
            }

        })
        acronymViewModel.acronymsError.observe(viewLifecycleOwner, Observer {
            showProgress(false)
            showMessate(getString(R.string.result_error))
        })
    }
    private fun showMessate(message:String) {
        val snack = Snackbar.make(recyclerView,message,Snackbar.LENGTH_LONG)
        snack.show()
    }
    fun search(name:String) {
        showProgress(true)
        acronymViewModel.getAcronyms(name)
    }
    private fun showProgress(isShow:Boolean) {
        if (isShow) {
            progressBar.visibility = View.VISIBLE
        }else {
            progressBar.visibility = View.GONE
        }
    }
}