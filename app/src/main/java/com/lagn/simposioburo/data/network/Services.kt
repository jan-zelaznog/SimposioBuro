package com.example.servicestest.Common

import com.lagn.simposioburo.domain.model.LoginModel
import com.lagn.simposioburo.domain.model.UserModel
import com.lagn.simposioburo.domain.model.response.conferenciasResponse.ConferenciasResponse
import com.lagn.simposioburo.domain.model.response.loginResponse.LoginResponse
import com.lagn.simposioburo.domain.model.response.ponentesResponse.PonentesResponse
import com.lagn.simposioburo.domain.model.response.presentacionesResponse.PresentacionesResponse
import com.lagn.simposioburo.domain.model.response.talleresResp.Talleresresp
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Services {

    @Headers("Content-Type: application/json")
    @POST ("api/login")
    fun loginService(@Body loginObj:LoginModel) : Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST ("/api/registro")
    fun setRegistro(@Body userObj: UserModel) : Call<ResponseBody>

    @GET ("/api/presentaciones")
    fun getPresentaciones ( @Header("Authorization") token: String ): Call<PresentacionesResponse>

    @GET ("/api/ponentes")
    fun getPonentes ( @Header("Authorization") token: String ): Call<PonentesResponse>


    @GET ("/api/talleres")
    fun getTalleres ( ): Call<Talleresresp>


    @GET ("/api/conferencias")
    fun getConferencias ( @Header("Authorization") token: String ): Call<ConferenciasResponse>

    @GET ("/api/agenda")
    fun getAgenda ( @Header("Authorization") token: String ): Call<ResponseBody>
/*
    @FormUrlEncoded
    @POST ("API/controller.php")
    fun updateMembershipService (@Field("actualizarDatosFiscal") flag:Int,
                                 @Field("id_asociado") id_asociado:String,
                                 @Field("rfc") rfc:String,
                                 @Field("cp") cp: String,
                                 @Field("regimen") regimen:String): Call<String>

    @FormUrlEncoded
    @POST ("API/controller.php")
    fun updateCategoryService ( @Field("actualizarDatosFiscal") flag:Int,
                                @Field("id_asociado") id_asociado:String,
                                @Field("rfc") rfc:String,
                                @Field("cp") cp: String,
                                @Field("regimen") regimen:String,
                                @Field("cat_nueva") newCategory: String): Call<String>

    @FormUrlEncoded
    @POST ("API/controller.php")
    fun updateProfileService ( @Field("actualizarPerfil") flag:Int,
                               @Field("id_asociado") id_asociado:String,
                               @Field("nombre") nombre:String,
                               @Field("apellido_paterno") paterno:String,
                               @Field("apellido_materno") materno:String,
                               @Field("email_principal") correoPrincipal:String,
                               @Field("correo_alterno") correoAlterno:String,
                               @Field("telefono_fijo") telFijo:String,
                               @Field("telefono_celular") telCelular:String): Call<String>

    @Multipart
    @POST("API/upload_photo.php")
    fun uploadPhoto (@Part("id_donante") id_donante: String,
                     @Part foto_rgb: MultipartBody.Part): Call<String>
*/
}