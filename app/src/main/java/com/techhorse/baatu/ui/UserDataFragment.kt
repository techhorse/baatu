package com.techhorse.baatu.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.techhorse.baatu.FetchViewModel
import com.techhorse.baatu.MyViewModelFactory
import com.techhorse.baatu.RetrofitService
import com.techhorse.baatu.data.remoterepository.RemoteRepository
import com.techhorse.baatu.databinding.UserDataFragmentBinding


class UserDataFragment : Fragment() {

    private var _binding: UserDataFragmentBinding? = null
    private val binding get() = _binding!!
    private val retrofitService = RetrofitService.getInstance()
    lateinit var viewModel: FetchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserDataFragmentBinding.inflate(inflater, container, false)
        val adapter = MainAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel = ViewModelProvider(requireActivity(), MyViewModelFactory(RemoteRepository(retrofitService))).get(
            FetchViewModel::class.java)

        binding.recyclerview.adapter = adapter
         viewModel.userList.observe(this, Observer {
             Log.d(TAG, "onCreate: $it")
             Log.d(TAG, "Observe Called in Fragment")
             adapter.setUserList(it)
         })
         viewModel.errorMessage.observe(this, Observer {
         })
        viewModel.getAllUserList()
        return binding.root
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // <- whenever we destroy our fragment, _binding is set to null. Hence it will avoid memory leaks.
    }
}