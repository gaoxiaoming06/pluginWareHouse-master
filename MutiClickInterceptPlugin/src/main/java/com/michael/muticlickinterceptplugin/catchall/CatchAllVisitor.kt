package com.michael.muticlickinterceptplugin.catchall


import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type

/**
 * 匹配规则
 */
class CatchAllVisitor(classVisitor: ClassVisitor): ClassVisitor(Opcodes.ASM6,classVisitor) {

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
        isMatchClass = name?.let { matchClass(it) } ?:let { false }
    }

    override fun visitMethod(
        access: Int,
        name: String,
        desc: String,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        val mv = cv.visitMethod(access, name, desc, signature, exceptions)
        if (isMatchClass && Type.getReturnType(desc).toString() == "V") {
            println("visitMethod11: access--$access name--$name desc--$desc " + "isMatchClass-- $isMatchClass" +
                    "this--$this name--$mmname signature--$mmsignature superName--$mmsuperName interfaces--${mminterfaces.toString()}")
            val argumentTypes = Type.getArgumentTypes(desc)
            var index = 1
            for((i, value) in argumentTypes.withIndex()) {
                println("visitMethod22: this--$this value[$i]--$value")
                if (value.toString() == "Landroid/view/View;"){
                    index = i
                }
            }
            println("visitMethod33: this--$this getMethodType--${Type.getMethodType(desc)} getType--${Type.getType(desc)} getReturnType--${Type.getReturnType(desc)}")
            return CatchAllMethodAdapter(mv, access, name, desc)
        }
        return mv
    }

    private fun matchClass(className: String ): Boolean {
        println("matchClass: this--$this className--$className")
        return className.contains("com/michael/pluginwarehouse")
                /*&& !className.contains("com/feierlaiedu/collegelive/databinding")
                && !className.contains("VideoCourseActivity\$playCourse")
                && !className.contains("CourseListFragment\$loadMore")
                && !className.contains("com/feierlaiedu/collegelive/utils/permissions/RxPermissions")
                && !className.contains("CourseCatalogueFragment\$getData")
                && !className.contains("CourseCenterPagerTitleView")
                && !className.contains("BookReaderFragment")
                && !className.contains("SettingFragment\$checkUpdate")
                && !className.contains("SplashActivity\$setAnim")
                && !className.contains("VideoPlayer\$setOnSpeedViewListener")
                && !className.contains("com/feierlaiedu/collegelive/data")
                && !className.contains("CourseUtils\$toExam")
                && !className.contains("CourseDetailFragment\$getCourseDetail")
                && !className.contains("ReceiveCertFragment\$initData")
                && !className.contains("FindCourseFragment\$getData")*/
    }
}