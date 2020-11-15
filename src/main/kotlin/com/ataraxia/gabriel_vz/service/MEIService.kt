package com.ataraxia.gabriel_vz.service

import com.ataraxia.gabriel_vz.model.Incipit
import net.minidev.json.JSONObject
import org.rismch.verovio.NativeUtils
import org.rismch.verovio.toolkit
import org.springframework.stereotype.Service
import java.util.*


@Service
class MEIService {

    init {
        NativeUtils.loadLibraryFromJar("/META-INF/lib/libverovio.jnilib")
    }

    fun paeToMei(incipit: Incipit?): String {
        val toolkit = toolkit()
        val meiJSONObject = JSONObject()
        meiJSONObject["clef"] = incipit?.clef
        meiJSONObject["keysig"] = incipit?.keysig
        meiJSONObject["timesig"] = incipit?.timesig
        meiJSONObject["score"] = incipit?.score
        return toolkit.getMEI(meiJSONObject.toJSONString())
    }
}