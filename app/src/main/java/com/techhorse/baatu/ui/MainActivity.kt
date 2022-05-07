package com.techhorse.baatu.ui
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.techhorse.baatu.FetchViewModel
import com.techhorse.baatu.MyViewModelFactory
import com.techhorse.baatu.R
import com.techhorse.baatu.RetrofitService
import com.techhorse.baatu.data.remoterepository.RemoteRepository
import com.techhorse.baatu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: FetchViewModel
    private val retrofitService = RetrofitService.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, MyViewModelFactory(RemoteRepository(retrofitService))).get(
            FetchViewModel::class.java)
        viewModel.userList.observe(this, Observer {
        Log.d(TAG,"Main View Model Called")
            binding.textViewLeftBottom.setText(R.string.successful_msg)
            binding.textViewRightBottom.setText(R.string.successful_msg)
            binding.textViewLeftTop.setText(R.string.successful_msg)
            binding.textViewRightTop.setText(R.string.successful_msg)

        })
        binding.startButton.setOnClickListener{
            binding.container.visibility = View.VISIBLE
            val newCase: Fragment = UserDataFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(
                R.id.container,newCase
            )

            transaction.addToBackStack(null) // if written, this transaction will be added to backstack
            transaction.commit()
            binding.startButton.visibility = View.GONE
        }


    }
}