package bot.box.whatsdelete.ui.fragment

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import bot.box.whatsdelete.R
import bot.box.whatsdelete.data.repository.IRepository
import bot.box.whatsdelete.databinding.FragmentChatBinding
import bot.box.whatsdelete.ui.adapter.ChatRoomAdapter
import bot.box.whatsdelete.ui.base.BaseFragment
import bot.box.whatsdelete.ui.viewmodel.ChatViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

typealias _OBSERVER<T> = androidx.lifecycle.Observer<T>

class ChatFragment : BaseFragment() {
    private lateinit var mBinding: FragmentChatBinding
    private val mViewModel by sharedViewModel<ChatViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentChatBinding>(
            inflater,
            R.layout.fragment_chat, container, false
        ).also {
            mBinding = it
            mBinding.viewModel = mViewModel
            mBinding.lifecycleOwner = this
        }.root
    }

    private val mChatRoomAdapter: ChatRoomAdapter by lazy { ChatRoomAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.apply {
            mRecyclerView.adapter = mChatRoomAdapter
            refreshLayout.setOnRefreshListener { mViewModel.loadChatRooms() }
        }

        mViewModel.loadChatRooms()
        mViewModel._loadChatRooms.observe(viewLifecycleOwner, _OBSERVER {
            mChatRoomAdapter.refreshChatRoom(it)
            mBinding.refreshLayout.isRefreshing = false
        })

    }

}