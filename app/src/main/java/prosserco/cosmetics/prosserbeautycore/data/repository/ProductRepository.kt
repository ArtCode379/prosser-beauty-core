package prosserco.cosmetics.prosserbeautycore.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import prosserco.cosmetics.prosserbeautycore.data.model.Product
import prosserco.cosmetics.prosserbeautycore.data.model.ProductCategory

class ProductRepository {
    private val products = listOf(
        Product(
            1, "Rose Renewal Serum", "A silky rosehip and squalane serum that supports a luminous complexion.",
            ProductCategory.SKINCARE, 34.00, "https://images.unsplash.com/photo-1620916566398-39f1143ab7be?w=1200"
        ),
        Product(
            2, "Velvet Petal Lipstick", "A comfortable satin lipstick with rich, buildable colour and a soft petal finish.",
            ProductCategory.MAKEUP, 22.00, "https://images.unsplash.com/photo-1586495777744-4413f21062fa?w=1200"
        ),
        Product(
            3, "Botanical Cleansing Balm", "A nourishing balm that melts away makeup without leaving skin feeling tight.",
            ProductCategory.SKINCARE, 28.00, "https://images.unsplash.com/photo-1556228720-195a672e8a03?w=1200"
        ),
        Product(
            4, "Silk Repair Hair Mask", "A weekly ritual with plant proteins and argan oil for smoother, glossy-looking hair.",
            ProductCategory.HAIRCARE, 26.00, "https://images.unsplash.com/photo-1522338242992-e1a54906a8da?w=1200"
        ),
        Product(
            5, "Peony Eau de Parfum", "An elegant composition of fresh peony, soft musk and warm amber.",
            ProductCategory.FRAGRANCE, 48.00, "https://images.unsplash.com/photo-1541643600914-78b084683601?w=1200"
        ),
        Product(
            6, "Mineral Glow Highlighter", "A finely milled powder that blends seamlessly for a fresh, candlelit glow.",
            ProductCategory.MAKEUP, 24.00, "https://images.unsplash.com/photo-1596462502278-27bfdc403348?w=1200"
        ),
        Product(
            7, "Neroli Body Lotion", "A lightweight moisturiser with neroli and shea butter for supple skin.",
            ProductCategory.BODY, 20.00, "https://images.unsplash.com/photo-1608248543803-ba4f8c70ae0b?w=1200"
        ),
        Product(
            8, "Hydra Cloud Cream", "A cushiony daily moisturiser with hyaluronic acid and oat extract.",
            ProductCategory.SKINCARE, 32.00, "https://images.unsplash.com/photo-1570194065650-d99fb4bedf0a?w=1200"
        ),
        Product(
            9, "Sculpt & Define Palette", "Blendable contour, bronze and highlight tones for effortless definition.",
            ProductCategory.MAKEUP, 30.00, "https://images.unsplash.com/photo-1512496015851-a90fb38ba796?w=1200"
        ),
        Product(
            10, "Amber Bath Elixir", "A restorative oil infused with sweet almond, jojoba and warm amber.",
            ProductCategory.BODY, 25.00, "https://images.unsplash.com/photo-1571781926291-c477ebfd024b?w=1200"
        ),
        Product(
            11, "Gloss & Protect Hair Oil", "A featherlight oil that tames flyaways, adds shine and protects dry ends.",
            ProductCategory.HAIRCARE, 23.00, "https://images.unsplash.com/photo-1598440947619-2c35fc9aa908?w=1200"
        ),
        Product(
            12, "Fig & Iris Mist", "A graceful mist balancing green fig, powdery iris and clean cedar.",
            ProductCategory.FRAGRANCE, 29.00, "https://images.unsplash.com/photo-1594035910387-fea47794261f?w=1200"
        )
    )

    fun observeById(id: Int): Flow<Product?> = flowOf(products.find { it.id == id })

    fun getById(id: Int): Product? = products.find { it.id == id }

    fun observeAll(): Flow<List<Product>> = flowOf(products)
}
