package bot.box.whatsdelete.di

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import bot.box.whatsdelete.data.database.ChatRoomDB
import bot.box.whatsdelete.data.database.dao.ChatDao
import bot.box.whatsdelete.data.preference.IPreference
import bot.box.whatsdelete.data.preference.PreferenceImpl
import bot.box.whatsdelete.data.repository.IRepository
import bot.box.whatsdelete.data.repository.RepositoryImpl
import bot.box.whatsdelete.ui.viewmodel.ChatViewModel
import bot.box.whatsdelete.ui.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinRoomModule = module(override = true) {
    single { ChatRoomDB.invoke(get()) }
    single { ChatRoomDB(get()).getDao() }
}

val koinPreferenceModule = module(override = true) {
    single<IPreference> { PreferenceImpl(get()) }
}

val koinRepositoryModule = module(override = true) {
    single<IRepository> { RepositoryImpl(get()) }
}

val koinViewModel = module {
    viewModel { SplashViewModel(get()) }
    viewModel { ChatViewModel(get(), get(), get()) }
}