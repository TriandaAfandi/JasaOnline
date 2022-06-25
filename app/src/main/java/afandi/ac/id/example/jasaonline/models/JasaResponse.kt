package afandi.ac.id.example.jasaonline.models

data class JasaResponse (
    val message: String,
    val error: Boolean,
    val datas: List<Jasa>
)
