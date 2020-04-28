package bot.box.whatsdelete.data.database.typeconverters

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import androidx.room.TypeConverter
import bot.box.whatsdelete.data.database.entity.Conversation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConversationConverter {

    @TypeConverter
    fun fromConversationList(mList: List<Conversation>?): String? {
        if (mList == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Conversation>>() {

        }.type
        return gson.toJson(mList, type)
    }

    @TypeConverter
    fun toConversationList(mString: String?): ArrayList<Conversation>? {
        if (mString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Conversation>>() {

        }.type
        return gson.fromJson<ArrayList<Conversation>>(mString, type)
    }

}