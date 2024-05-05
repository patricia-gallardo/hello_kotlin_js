data class ZipResult(
    val country: String,
    val state: String,
    val city: String,
)

external interface ZipData {
    val country: String
    val state: String
    val city: String
}
