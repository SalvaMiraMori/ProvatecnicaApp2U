package com.example.provatecnicaapp2u.localdatabase.model

import com.google.gson.annotations.SerializedName
import io.realm.kotlin.types.RealmObject

// Classe fotògraf corresponent a la base de dades local
class RealmPhotographer: RealmObject {
    var id: Int = 0
    var guid: String = ""
    var email: String = ""
    var first_name: String = ""
    var last_name: String = ""
    var is_removed: Boolean = false
    var description: String = ""
    var avatar: String? = null
    var image: String = ""
    var facebook: String? = null
    var instagram: String? = null
    var webpage: String? = null
}