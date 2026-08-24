package prosserco.cosmetics.prosserbeautycore.di

import androidx.room.Room
import prosserco.cosmetics.prosserbeautycore.data.database.TSDIKDatabase
import org.koin.dsl.module

private const val DB_NAME = "tsdik_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = TSDIKDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<TSDIKDatabase>().cartItemDao() }

    single { get<TSDIKDatabase>().orderDao() }
}