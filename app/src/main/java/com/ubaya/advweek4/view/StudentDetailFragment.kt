package com.ubaya.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.advweek4.R
import com.ubaya.advweek4.databinding.FragmentStudentDetailBinding
import com.ubaya.advweek4.databinding.StudentListItemBinding
import com.ubaya.advweek4.model.Student
import com.ubaya.advweek4.util.loadImage
import com.ubaya.advweek4.viewmodel.DetailViewModel
import com.ubaya.advweek4.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_list.*
import kotlinx.android.synthetic.main.student_list_item.*
import kotlinx.android.synthetic.main.student_list_item.view.*
import java.util.*
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), ButtonNotifyClickListener {
//    class StudentDetailViewHolder(var view:StudentDetailListBinding) : RecyclerView.ViewHolder(view.root)
    private lateinit var  viewModel: DetailViewModel
    private lateinit var detailBinding: FragmentStudentDetailBinding
    private val binding get() = detailBinding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        detailBinding = FragmentStudentDetailBinding.inflate(inflater,container,false)
        return binding.root

//        val view : View = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater,R.layout.fragment_student_detail,container, false)
//        return inflater.inflate(R.layout.fragment_student_detail,container, false)
//        val view = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater,R.layout.student_list_item,container,false)
//        return view
//        return FragmentStudentDetailBinding.inflate(inflater, container, false).apply {
//            lifecycleOwner
//                // Attach your view model here
//        }.root
//        val view = binding.getRoot()
        //here data must be an instance of the class MarsDataProvider
//        binding.setMarsdata(data);
//        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var studentId : String? = "test"
        arguments?.let {
             studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID
        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(studentId)

        observeViewModel()
    }
    private fun observeViewModel() {

        viewModel.studentLiveData.observe(viewLifecycleOwner) {
            detailBinding.detailstudent = viewModel.studentLiveData.value
            detailBinding.listener = this
//            val student = viewModel.studentLiveData.value
//                student?.let{
//                imageStudentDetail.loadImage(it.photoURL, progressLoadDetailStudent)
//                editID.setText(it.id)
//                editName.setText(it.name)
//                editPhone.setText(it.phone)
//                editDOB.setText(it.dob)
//                buttonNotif.setOnClickListener {
//                    Observable.timer(5, TimeUnit.SECONDS)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe{
//                            Log.d("mynotif","Notification delayed after 5 seconds")
//                            student.name?.let { studentName ->
//                                MainActivity.showNotification(
//                                    studentName, "A new notification created",
//                                    R.drawable.ic_baseline_person_24)
//                            }
//                        }
//                }
//            }
        }
    }
    override fun onButtonNotifyClick(v: View) {
//        val action = StudentListFragmentDirections.actionStudentDetail(v.tag.toString())
//        Navigation.findNavController(v).navigate(action)
        Observable.timer(5, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe{
                            Log.d("mynotif","Notification delayed after 5 seconds")
                            v.tag.toString().let { studentName ->
                                MainActivity.showNotification(
                                    studentName, "A new notification created",
                                    R.drawable.ic_baseline_person_24)
                            }
                        }
    }
}