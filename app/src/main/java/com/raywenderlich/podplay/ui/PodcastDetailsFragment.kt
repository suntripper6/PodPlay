package com.raywenderlich.podplay.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.raywenderlich.podplay.R
import com.raywenderlich.podplay.viewmodel.PodcastViewModel
import kotlinx.android.synthetic.main.fragment_podcast_details.*

// Displays podcast details
class PodcastDetailsFragment : Fragment() {

    private val podcastViewModel: PodcastViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?) : View? {
        return inflater.inflate(R.layout.fragment_podcast_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        updateControls()
    }

    // 2
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_details, menu)
    }

    // User interface controls
    private fun updateControls() {
        val viewData = podcastViewModel.activePodcastViewData ?: return
        feedTitleTextView.text = viewData.feeTitle
        feedDescTextView.text = viewData.feedDesc
        activity?.let { activity ->
            Glide.with(activity).load(viewData.imageUrl).into(feedImageView)
        }
    }

    // Instance of a fragment
    companion object {
        fun newInstance(): PodcastDetailsFragment {
            return PodcastDetailsFragment()
        }
    }
}