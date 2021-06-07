package com.michael.muticlickinterceptplugin.catchall

import com.android.build.api.transform.TransformInvocation
import com.michael.basetransform.HunterTransform
import com.michael.basetransform.asm.BaseWeaver
import com.michael.muticlickinterceptplugin.muticlickhandle.MutiClickHandleVisitor
import com.michael.muticlickinterceptplugin.muticlickhandle.MutiClickHandleWeaver
import org.gradle.api.Project
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter

class CatchAllTransform(project: Project) : HunterTransform(project) {
    init {
        this.bytecodeWeaver = object : BaseWeaver() {
            override fun wrapClassWriter(classWriter: ClassWriter): ClassVisitor {
                return CatchAllVisitor(classWriter)
            }
        }
    }

    override fun transform(transformInvocation: TransformInvocation?) {
        super.transform(transformInvocation)
    }
}
