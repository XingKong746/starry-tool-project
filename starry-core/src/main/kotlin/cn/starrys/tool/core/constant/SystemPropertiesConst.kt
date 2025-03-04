package cn.starrys.tool.core.constant

/**
 * 系统属性常量（部分属性）
 */
object SystemPropertiesConst {
    /** 操作系统名称（如："Windows 10"，"Linux"） */
    const val OS_NAME: String = "os.name"

    /** 操作系统架构（如："amd64"，"x86_64"） */
    const val OS_ARCH: String = "os.arch"

    /** 操作系统版本（如："10.0"，"5.4.0-80-generic"） */
    const val OS_VERSION: String = "os.version"

    /** 当前用户账号名称 */
    const val USER_NAME: String = "user.name"

    /** 当前用户主目录路径 */
    const val USER_HOME: String = "user.home"

    /** 用户当前工作目录 */
    const val USER_DIR: String = "user.dir"

    /** 用户所在国家/地区（根据区域设置） */
    const val USER_COUNTRY: String = "user.country"

    /** 用户区域变体（用于特定区域设置变体） */
    const val USER_VARIANT: String = "user.variant"

    /** Java运行时版本（如："17.0.1"） */
    const val JAVA_VERSION: String = "java.version"

    /** Java版本发布日期（格式取决于JVM实现） */
    const val JAVA_VERSION_DATE: String = "java.version.date"

    /** Java运行时环境供应商（如："Oracle Corporation"） */
    const val JAVA_VENDOR: String = "java.vendor"

    /** Java供应商官方URL */
    const val JAVA_VENDOR_URL: String = "java.vendor.url"

    /** Java供应商BUG提交URL */
    const val JAVA_VENDOR_URL_BUG: String = "java.vendor.url.bug"

    /** Java供应商特定版本信息 */
    const val JAVA_VENDOR_VERSION: String = "java.vendor.version"

    /** Java规范名称（固定值："Java Platform API Specification"） */
    const val JAVA_SPECIFICATION_NAME: String = "java.specification.name"

    /** Java规范供应商 */
    const val JAVA_SPECIFICATION_VENDOR: String = "java.specification.vendor"

    /** Java规范版本（如："17"） */
    const val JAVA_SPECIFICATION_VERSION: String = "java.specification.version"

    /** 使用的JIT编译器名称（可能为null） */
    const val JAVA_COMPILER: String = "java.compiler"

    /** Java类路径 */
    const val JAVA_CLASS_PATH: String = "java.class.path"

    /** Java类格式版本号（如："61.0"对应Java 17） */
    const val JAVA_CLASS_VERSION: String = "java.class.version"

    /** 加载库时搜索的路径列表 */
    const val JAVA_LIBRARY_PATH: String = "java.library.path"

    /** Java运行时环境名称（如："Java(TM) SE Runtime Environment"） */
    const val JAVA_RUNTIME_NAME: String = "java.runtime.name"

    /** Java运行时环境版本 */
    const val JAVA_RUNTIME_VERSION: String = "java.runtime.version"

    /** JVM实现名称（如："Java HotSpot(TM) 64-Bit Server VM"） */
    const val JAVA_VM_NAME: String = "java.vm.name"

    /** JVM实现版本 */
    const val JAVA_VM_VERSION: String = "java.vm.version"

    /** JVM实现详细信息 */
    const val JAVA_VM_INFO: String = "java.vm.info"

    /** JVM实现供应商 */
    const val JAVA_VM_VENDOR: String = "java.vm.vendor"

    /** JVM规范名称（固定值："Java Virtual Machine Specification"） */
    const val JAVA_VM_SPECIFICATION_NAME: String = "java.vm.specification.name"

    /** JVM规范版本（如："17"） */
    const val JAVA_VM_SPECIFICATION_VERSION: String = "java.vm.specification.version"

    /** JVM规范供应商 */
    const val JAVA_VM_SPECIFICATION_VENDOR: String = "java.vm.specification.vendor"

    /** 压缩指针模式（可能值："32-bit"，"Zero based"等） */
    const val JAVA_VM_COMPRESSED_OOPS_MODE: String = "java.vm.compressedOopsMode"

    /** Java安装目录 */
    const val JAVA_HOME: String = "java.home"

    /** 默认临时文件路径 */
    const val JAVA_IO_TMPDIR: String = "java.io.tmpdir"

    /** 扩展类加载器的搜索路径 */
    const val JAVA_EXT_DIRS: String = "java.ext.dirs"

    /** 文件分隔符（Windows为"\"，Linux为"/"） */
    const val FILE_SEPARATOR: String = "file.separator"

    /** 路径分隔符（Windows为";"，Linux为":"） */
    const val PATH_SEPARATOR: String = "path.separator"

    /** 行分隔符（Windows为"\r\n"，Linux为"\n"） */
    const val LINE_SEPARATOR: String = "line.separator"

    /** 系统默认字符编码（如："UTF-8"） */
    const val NATIVE_ENCODING: String = "native.encoding"

    /** 文件默认编码（通常与`native.encoding`相同） */
    const val FILE_ENCODING: String = "file.encoding"

    /** JDK调试模式（如："release"表示非调试版本） */
    const val JDK_DEBUG: String = "jdk.debug"

    /** CPU指令集列表（平台相关，如："sse4.1, sse4.2, avx, avx2"） */
    const val SUN_CPU_ISALIST: String = "sun.cpu.isalist"

    /** JVM内部使用的字符编码（通常与文件编码相同） */
    const val SUN_JNU_ENCODING: String = "sun.jnu.encoding"

    /** JVM数据模型（如："64"表示64位JVM） */
    const val SUN_ARCH_DATA_MODEL: String = "sun.arch.data.model"

    /** 启动器类型（"SUN_STANDARD"表示标准启动器） */
    const val SUN_JAVA_LAUNCHER: String = "sun.java.launcher"

    /** 引导类加载器的库路径 */
    const val SUN_BOOT_LIBRARY_PATH: String = "sun.boot.library.path"

    /** 启动JVM时传入的命令行参数 */
    const val SUN_JAVA_COMMAND: String = "sun.java.command"

    /** CPU字节序（"little"或"big"） */
    const val SUN_CPU_ENDIAN: String = "sun.cpu.endian"

    /** 使用的JIT编译器信息（如："HotSpot 64-Bit Tiered Compilers"） */
    const val SUN_MANAGEMENT_COMPILER: String = "sun.management.compiler"

    /** 操作系统补丁级别（如服务包版本） */
    const val SUN_OS_PATCH_LEVEL: String = "sun.os.patch.level"

    /** Unicode编码实现方式（如："UnicodeLittle"） */
    const val SUN_IO_UNICODE_ENCODING: String = "sun.io.unicode.encoding"
}
