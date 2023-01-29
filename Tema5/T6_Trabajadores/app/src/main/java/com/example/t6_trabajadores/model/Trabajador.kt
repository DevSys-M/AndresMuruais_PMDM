package com.example.t6_trabajadores.model

import android.os.Parcel
import android.os.Parcelable

data class Trabajador(
    var nombre: String?,
    var apellidos: String?,
    var correo: String?,
    var edad: Int,
    var puesto: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(apellidos)
        parcel.writeString(correo)
        parcel.writeInt(edad)
        parcel.writeString(puesto)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Trabajador> {
        override fun createFromParcel(parcel: Parcel): Trabajador {
            return Trabajador(parcel)
        }

        override fun newArray(size: Int): Array<Trabajador?> {
            return arrayOfNulls(size)
        }
    }
}