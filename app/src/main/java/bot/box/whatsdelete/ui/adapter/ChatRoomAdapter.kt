package bot.box.whatsdelete.ui.adapter

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import android.graphics.BitmapFactory
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import bot.box.whatsdelete.R
import bot.box.whatsdelete.data.database.entity.ChatRoom
import bot.box.whatsdelete.databinding.ItemChatroomBinding
import bot.box.whatsdelete.utils.Utils
import bot.box.whatsdelete.utils.extension.getContextCompatColor
import bot.box.whatsdelete.utils.extension.getContextCompatDrawable

class ChatRoomAdapter : RecyclerView.Adapter<ChatRoomAdapter.ChatRoomHolder>() {

    private lateinit var mBinding: ItemChatroomBinding
    private var mChatRooms: MutableList<ChatRoom>? = null
    private var mSelectedChat: List<String> = ArrayList()

    fun refreshChatRoom(mList: MutableList<ChatRoom>) {
        this.mChatRooms = mList
        notifyDataSetChanged()
    }

    fun refreshSelectedChat(mList: List<String>) {
        this.mSelectedChat = mList
        notifyDataSetChanged()
    }

    fun refreshList() {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRoomHolder {
        return ChatRoomHolder(
            DataBindingUtil.inflate<ItemChatroomBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_chatroom,
                parent,
                false
            ).also { mBinding = it }
        )
    }

    override fun getItemCount(): Int = if (!mChatRooms.isNullOrEmpty()) mChatRooms!!.size else 0

    override fun onBindViewHolder(holder: ChatRoomHolder, position: Int) {
        mBinding.chatRoom = mChatRooms!![position]

        val colorDrawableSelected =
            holder.binding.root.context.getContextCompatDrawable(R.color.foreground_chat_select)
        val colorDrawableTransparent =
            holder.binding.root.context.getContextCompatDrawable(android.R.color.transparent)


        if (mSelectedChat.contains(mChatRooms!![position].name)) { // when items are selected

            holder.binding.ivUserIconSelected.visibility = View.VISIBLE // show dp selected icon


            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) { // showing foreground selection
                holder.binding.parent.foreground = colorDrawableSelected
            } else {
                holder.binding.parent.apply {
                    setBackgroundColor(context.getContextCompatColor(R.color.foreground_chat_select))
                }
            }
        } else { // when items are unselected
            holder.binding.ivUserIconSelected.visibility = View.GONE // hide dp selected icon


            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) { // transparent foreground selection
                holder.binding.parent.foreground = colorDrawableTransparent
            } else {
                holder.binding.parent.apply {
                    setBackgroundColor(context.getContextCompatColor(android.R.color.transparent))
                }
            }
        }
        holder.binding.tvUserName.text = mChatRooms?.get(position)?.name


        if (mChatRooms?.get(position)?.dp != null) { // set dp
            holder.binding.ivUserIcon.setImageBitmap(
                BitmapFactory.decodeByteArray(
                    mChatRooms?.get(position)?.dp,
                    0,
                    mChatRooms!![position].dp.size
                )
            )
        } else {
            holder.binding.ivUserIcon.setImageResource(R.drawable.user_icon)
        }
    }


    class ChatRoomHolder(val binding: ItemChatroomBinding) : RecyclerView.ViewHolder(binding.root)
}