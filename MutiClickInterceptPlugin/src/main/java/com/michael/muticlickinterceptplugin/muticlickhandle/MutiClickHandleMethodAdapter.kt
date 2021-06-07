package com.michael.muticlickinterceptplugin.muticlickhandle

import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes


class MutiClickHandleMethodAdapter(methodVisitor: MethodVisitor, descriptor: String? = "", index: Int = 1) : MethodVisitor(Opcodes.ASM6,methodVisitor) {


    private var index = index
    private var descriptor = descriptor

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

        val split = descriptor?.split(";")
        for(value in split!!) {
            println("visitCode: this--$this value--$value")
        }

//        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//        mv.visitLdcInsn("Hello World!1111");
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

        println("visitCode: this--$this index_pre--$index")
        mv.visitVarInsn(Opcodes.ALOAD, index)
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/michael/pluginwarehouse/FastClickUtilHook", "isFastDoubleClick", "(Landroid/view/View;)Z", false)
        var label = Label()
        mv.visitJumpInsn(Opcodes.IFEQ, label)
        mv.visitInsn(Opcodes.RETURN)
        mv.visitLabel(label)
        println("visitCode: this--$this index_behind--$index")

//        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false)
//        mv.visitVarInsn(Opcodes.LSTORE, 2)
//
//        mv.visitVarInsn(Opcodes.ALOAD, 1)
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "android/view/View", "getId", "()I", false)
//        mv.visitVarInsn(Opcodes.ISTORE, 4)
//
//        mv.visitVarInsn(Opcodes.ALOAD, 1)
//        mv.visitVarInsn(Opcodes.ILOAD, 4)
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "android/view/View", "getTag", "(I)Ljava/lang/Object;", false)
//        mv.visitVarInsn(Opcodes.ASTORE, 5)
//
//        mv.visitVarInsn(Opcodes.ALOAD, 1)
//        mv.visitVarInsn(Opcodes.ILOAD, 4)
//        mv.visitVarInsn(Opcodes.LLOAD, 2)
//        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;", false)
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "android/view/View", "setTag", "(ILjava/lang/Object;)V", false)
//
//
//        mv.visitVarInsn(Opcodes.ALOAD, 5)
//        val l5 = Label()
//        mv.visitJumpInsn(Opcodes.IFNULL, l5)
//
//        mv.visitVarInsn(Opcodes.ALOAD, 5)
//        mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Long")
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Long", "longValue", "()J", false)
//        mv.visitVarInsn(Opcodes.LSTORE, 6)
//
//        mv.visitVarInsn(Opcodes.LLOAD, 2)
//        mv.visitVarInsn(Opcodes.LLOAD, 6)
//        mv.visitInsn(Opcodes.LSUB)
//        mv.visitLdcInsn(1500L)
//        mv.visitInsn(Opcodes.LCMP)
//        mv.visitJumpInsn(Opcodes.IFGE, l5)
//
//        mv.visitInsn(Opcodes.RETURN);
//        mv.visitLabel(l5)
//        mv.visitFrame(
//            Opcodes.F_APPEND,
//            3,
//            arrayOf<Any>(
//                Opcodes.LONG,
//                Opcodes.INTEGER,
//                "java/lang/Object"
//            ),
//            0,
//            null
//        )

    }

}