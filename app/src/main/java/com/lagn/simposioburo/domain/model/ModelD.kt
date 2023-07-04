package com.lagn.simposioburo.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.lagn.simposioburo.fragmentsModule.ponentesFragment.model.ModelPonentes

data class ModelD(
    var id: Int = 0,
    var titulo: String? = "",
    var grupo: Int = 0,
    var fecha: String? = "",
    var dia: Int = 0,
    var hora_inicio: String? = "",
    var hora_fin: String? = "",
    var speaker: String? = "",
    var moderador: String? = "",
    var salon: String? = "",
    var taller: Int = 0,
    var descripcion: String? = "",
    var start_date: String? = "",
    var end_date: String? = "",
    var calendario: Int = 0,
    var cant_speakers: Int = 0,
    var mostrar: Int = 0,
    var url_player: String?= "",
    var created: String? = "",
    var modified: String? = "",
    var estado: Int = 0,
    var puesto: String? = "",
    var es_taller: Int = 0,
    var cupo: Int? = 0,
    var cupos_disponibles: Int = 0
) : Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readInt()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {

    }

    companion object CREATOR : Parcelable.Creator<ModelD> {
        override fun createFromParcel(parcel: Parcel): ModelD {
            return ModelD(parcel)
        }

        override fun newArray(size: Int): Array<ModelD?> {
            return arrayOfNulls(size)
        }
    }
}
