package afandi.ac.id.example.jasaonline.services

import afandi.ac.id.example.jasaonline.models.JasaResponse
import retrofit2.Call
import retrofit2.http.GET

interface JasaService {
    @GET("services")
    fun getJasa() : Call<JasaResponse>
}
