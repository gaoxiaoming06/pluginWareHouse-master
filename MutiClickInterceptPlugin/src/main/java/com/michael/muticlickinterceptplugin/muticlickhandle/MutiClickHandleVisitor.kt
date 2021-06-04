package com.michael.muticlickinterceptplugin.muticlickhandle

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type

class MutiClickHandleVisitor(classVisitor: ClassVisitor): ClassVisitor(Opcodes.ASM6,classVisitor) {


    private val classFullName = "android/view/View\$OnClickListener"
    private var isMatchClass = false

    private var mmname: String? = null
    private var mmsignature: String? = null
    private var mmsuperName: String? = null
    private var mminterfaces: Array<String>? = null

    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<String>
    ) {
        super.visit(version, access, name, signature, superName, interfaces)
        this.mmname = name
        this.mmsignature = signature
        this.mmsuperName = superName
        this.mminterfaces = interfaces
        isMatchClass = matchClass(interfaces, classFullName)
    }

    override fun visitInnerClass(
        name: String?,
        outerName: String?,
        innerName: String?,
        access: Int
    ) {
        super.visitInnerClass(name, outerName, innerName, access)
        if(isMatchClass){
            println("visitInnerClass: this--$this name--$name outerName--$outerName innerName--$innerName access--$access")
        }
    }

    override fun visitMethod(
        access: Int,
        name: String,
        desc: String,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
//        println("visitMethod: access--$access name--$name desc--$desc")
        val mv = cv.visitMethod(access, name, desc, signature, exceptions)
        if (/*isMatchClass && */matchPackage() && matchMethod(name, desc)){
            println("visitMethod11: access--$access name--$name desc--$desc " + "isMatchClass-- $isMatchClass" +
                    "this--$this name--$mmname signature--$mmsignature superName--$mmsuperName interfaces--${mminterfaces.toString()}")
            return MutiClickHandleMethodAdapter(mv)
        }
        if (matchPackage() && matchMethod1(name, desc)){
            println("visitMethod111111111: access--$access name--$name desc--$desc " + " isMatchClass--$isMatchClass" +
                    "this--$this name--$mmname signature--$mmsignature superName--$mmsuperName interfaces--${mminterfaces.toString()}")
            val argumentTypes = Type.getArgumentTypes(desc)
            var index = 1
            println("visitMethod222222: argumentTypes--$argumentTypes")
            for((i, value) in argumentTypes.withIndex()) {
                println("visitMethod222222: value--$value value.toString()--${value.toString()}")
                if (value.toString() == "Landroid/view/View;"){
                    index = i
                }
            }
            return MutiClickHandleMethodAdapter(mv, "$desc $access", index)
        }
        return mv
    }


    private fun matchMethod(name: String, desc: String): Boolean {
        return (name == "onClick" && desc == "(Landroid/view/View;)V")
    }

    private fun matchMethod1(name: String, desc: String): Boolean {
        return name.contains("\$lambda") && desc.contains("Landroid/view/View;")
    }

    private fun matchClass(
        interfaces: Array<String>,
        classFullName: String
    ): Boolean {
        var isMatch = false
        if(mmname?.contains("com/michael/pluginwarehouse") == true){

        }
        for (anInterface in interfaces) {
            if (anInterface == classFullName) {
                println("visitMethod0000: this--$this name--$mmname signature--$mmsignature superName--$mmsuperName interfaces--${mminterfaces.toString()}"+ " isMatchClass--$isMatchClass")
                isMatch = true
                break
            }
        }
        return isMatch
    }

    private fun matchPackage(): Boolean {
        var isMatch = false
        if(mmname?.contains("com/michael/pluginwarehouse") == true){
            isMatch = true
        }
        return isMatch
    }

}