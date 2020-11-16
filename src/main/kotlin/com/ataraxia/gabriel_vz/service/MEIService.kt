package com.ataraxia.gabriel_vz.service

import com.ataraxia.gabriel_vz.model.Incipit
import org.json.JSONObject
import org.rismch.verovio.NativeUtils
import org.rismch.verovio.toolkit
import org.springframework.stereotype.Service

@Service
class MEIService {

    init {
        NativeUtils.loadLibraryFromJar("/META-INF/lib/libverovio.jnilib")
    }

    fun paeToMei(incipit: Incipit?): String {
        val toolkit = toolkit()
        val meiJSONObject =  JSONObject()
        meiJSONObject.put("clef", incipit?.clef)
        meiJSONObject.put("keysig", incipit?.keysig)
        meiJSONObject.put("timesig", incipit?.timesig)
        meiJSONObject.put("score", incipit?.score)
        toolkit.setResourcePath("/Users/max/Desktop/verovio/verovio/data")
        toolkit.loadData("@clef:" + incipit?.clef + "\n" + "@keysig:" + incipit?.keysig + "\n" + "@timesig:" + incipit?.timesig + "\n" + "@data:" + incipit?.score)
        return toolkit.getMEI(meiJSONObject.toString())
    }
}