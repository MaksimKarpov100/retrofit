import kotlinx.serialization.Serializable

@Serializable
object LoginRoute

@Serializable
object CartsRoute

@Serializable
data class EditCartRoute(val cartId: String)
