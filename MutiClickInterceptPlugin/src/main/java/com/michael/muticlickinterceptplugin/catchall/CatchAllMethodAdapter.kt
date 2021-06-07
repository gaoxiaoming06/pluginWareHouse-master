package com.michael.muticlickinterceptplugin.catchall

import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.commons.AdviceAdapter

/**
 * 代码织入
 */
class CatchAllMethodAdapter(methodVisitor: MethodVisitor, access: Int,
                            name: String,
                            desc: String,) : AdviceAdapter(Opcodes.ASM6,methodVisitor, access, name, desc) {

    lateinit var l1:Label;
    lateinit var l2:Label;
    var  exceptionHandleClass = "com/feierlaiedu/plugin/CatchExceptionHook";
    var  exceptionHandleMethod = "handleException";

    var hasTryCatch = false


    override fun onMethodEnter() {
        super.onMethodEnter()
        println("onMethodEnter: this--$this")
        var l0 = Label();
        l1 = Label();
        l2 = Label();
        mv.visitTryCatchBlock(l0, l1, l2, "java/lang/Exception");
        mv.visitLabel(l0);
    }

    override fun onMethodExit(opcode: Int) {
        super.onMethodExit(opcode)
        println("onMethodExit: this--$this opcode--$opcode")
        if (opcode == ATHROW) {
            //方法体内容为throw
            return
        }
        if (hasTryCatch) {
            println("onMethodExit: this--$this hasTryCatch true--$hasTryCatch")
            return
        }
        println("onMethodExit: this--$this hasTryCatch false--$hasTryCatch")
        mv.visitLabel(l1);
        var l3 = Label();
        mv.visitJumpInsn(GOTO, l3);
        mv.visitLabel(l2);
        mv.visitVarInsn(ASTORE, 1);
        if (exceptionHandleClass != null && exceptionHandleMethod != null) {
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, exceptionHandleClass,
                exceptionHandleMethod, "(Ljava/lang/Exception;)V", false);
        }
        mv.visitLabel(l3);
    }

    override fun visitTryCatchBlock(start: Label?, end: Label?, handler: Label?, type: String?) {
        super.visitTryCatchBlock(start, end, handler, type)
        hasTryCatch = true
        println("visitTryCatchBlock: this--$this start--$start end--$end handler--$handler type--$type")
    }

    override fun visitParameter(name: String?, access: Int) {
        super.visitParameter(name, access)
        println("visitParameter: this--$this access--$access name--$name")
    }

    override fun visitLocalVariable(
        name: String?,
        descriptor: String?,
        signature: String?,
        start: Label?,
        end: Label?,
        index: Int
    ) { //Landroid/view/View;
        super.visitLocalVariable(name, descriptor, signature, start, end, index)
        println("visitLocalVariable: this--$this name--$name descriptor--$descriptor" +
                " signature--$signature start--$start end--$end index--$index")
//        if(descriptor == "Landroid/view/View;"){
//            this.index = index
//        }
    }

    override fun visitVarInsn(opcode: Int, `var`: Int) {
        super.visitVarInsn(opcode, `var`)
        println("visitVarInsn: this--$this opcode--$opcode `var`--$`var`")
    }

    override fun visitFrame(
        type: Int,
        numLocal: Int,
        local: Array<out Any>?,
        numStack: Int,
        stack: Array<out Any>?
    ) {
        super.visitFrame(type, numLocal, local, numStack, stack)
        println("visitFrame: this--$this type--$type numLocal--$numLocal local--$local numStack--$numStack stack--$stack")
    }

    override fun visitInsn(opcode: Int) {
        super.visitInsn(opcode)
        println("visitInsn: this--$this opcode--$opcode ")
    }

    override fun visitMaxs(maxStack: Int, maxLocals: Int) {
        super.visitMaxs(maxStack, maxLocals)
        println("visitMaxs: this--$this maxStack--$maxStack maxLocals--$maxLocals")
    }

    override fun visitMethodInsn(
        opcode: Int,
        owner: String?,
        name: String?,
        descriptor: String?,
        isInterface: Boolean
    ) {
        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
        println("visitMethodInsn: this--$this opcode--$opcode owner--$owner name--$name descriptor--$descriptor isInterface--$isInterface")
    }

    override fun visitCode() {
        super.visitCode()
        println("visitCode: this--$this ")

    }
}