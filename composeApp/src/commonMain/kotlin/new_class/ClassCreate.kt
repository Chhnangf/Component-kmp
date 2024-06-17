package new_class

class ClassCreate {

    // 可变属性
    var name: String? = null

    // 不可变属性
    val age: Int = 0

    // 如果使用private修饰，则不会有getter和setter方法
    // 外部无法访问改属性
    private var gender: Int = -1

    val classPrimaryConstructor = ClassPrimaryConstructor(null)

}

class MathUtils {
    companion object {
        fun add(a: Int, b: Int): Int {
            return a + b
        }
    }
}