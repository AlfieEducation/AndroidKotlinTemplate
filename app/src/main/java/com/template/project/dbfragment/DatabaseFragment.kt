package com.template.project.dbfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.template.project.R
import com.template.project.database.TemplateDatabase
import com.template.project.databinding.FragmentDatabaseBinding

class DatabaseFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentDatabaseBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_database, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = TemplateDatabase.getInstance(application).tempDatabaseDao

        val viewModelFactory = DatabaseViewModelFactory(dataSource, application)

        val dbViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(DatabaseViewModel::class.java)

        binding.dbViewModel = dbViewModel

        val manager = GridLayoutManager(activity, 3)
        manager.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) = when (position) {
                0 -> 3
                else -> 1
            }

        }
        binding.entityList.layoutManager = manager

        val adapter = DatabaseItemAdapter(DatabaseItemListener {
                nightId -> //dbViewModel.onSleepNightClicked(nightId)
        })

        dbViewModel.entities.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.addHeaderAndSubmitList(it)
            }
        })

        binding.entityList.adapter = adapter

//        dbViewModel.navigateToSleepDataQuality.observe(this, Observer {night ->
//            night?.let {
//                this.findNavController().navigate(SleepTrackerFragmentDirections
//                    .actionSleepTrackerFragmentToSleepDetailFragment(night))
//                sleepTrackerViewModel.onSleepDataQualityNavigated()
//            }
//        })


        binding.setLifecycleOwner(this)
//
//        //TODO (05) Add an observer that shows a Snackbar.
//
//        // Add an Observer on the state variable for Navigating when STOP button is pressed.
//        sleepTrackerViewModel.navigateToSleepQuality.observe(this, Observer { night ->
//            night?.let {
//                // We need to get the navController from this, because button is not ready, and it
//                // just has to be a view. For some reason, this only matters if we hit stop again
//                // after using the back button, not if we hit stop and choose a quality.
//                // Also, in the Navigation Editor, for Quality -> Tracker, check "Inclusive" for
//                // popping the stack to get the correct behavior if we press stop multiple times
//                // followed by back.
//                // Also: https://stackoverflow.com/questions/28929637/difference-and-uses-of-oncreate-oncreateview-and-onactivitycreated-in-fra
//                this.findNavController().navigate(
//                    SleepTrackerFragmentDirections
//                        .actionSleepTrackerFragmentToSleepQualityFragment(night.nightId))
//                // Reset state to make sure we only navigate once, even if the device
//                // has a configuration change.
//                sleepTrackerViewModel.doneNavigating()
//            }
//        })
//
        return binding.root
   }
}
