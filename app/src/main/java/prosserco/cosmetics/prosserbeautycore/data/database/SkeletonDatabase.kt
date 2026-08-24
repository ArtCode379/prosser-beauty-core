package prosserco.cosmetics.prosserbeautycore.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import prosserco.cosmetics.prosserbeautycore.data.dao.CartItemDao
import prosserco.cosmetics.prosserbeautycore.data.dao.OrderDao
import prosserco.cosmetics.prosserbeautycore.data.database.converter.Converters
import prosserco.cosmetics.prosserbeautycore.data.entity.CartItemEntity
import prosserco.cosmetics.prosserbeautycore.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TSDIKDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}