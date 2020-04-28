package bot.box.whatsdelete

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */
import android.app.Application
import bot.box.whatsdelete.di.koinPreferenceModule
import bot.box.whatsdelete.di.koinRepositoryModule
import bot.box.whatsdelete.di.koinRoomModule
import bot.box.whatsdelete.di.koinViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    koinPreferenceModule,
                    koinRoomModule,
                    koinRepositoryModule,
                    koinViewModel
                )
            )
        }
    }
}