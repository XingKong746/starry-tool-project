package cn.starrys.tool.core

import cn.starrys.tool.core.util.JsonUtils
import kotlin.test.Test
import kotlin.test.assertNotNull

class JsonUtilsTest {

    data class Permission(
        var permissionName: String?,
        var permissionDescription: String?
    )

    @Test
    fun parseTest() {
        val permission: Permission? = JsonUtils.parse(
            """
            {
              "code": 200,
              "message": "成功",
              "data": {
                "username": "用户名0",
                "password": "密码0",
                "roles": [
                  {
                    "roleDescription": "角色描述0",
                    "roleName": "角色0",
                    "permissions": [
                      {
                        "permissionName": "权限0",
                        "permissionDescription": "权限描述0"
                      },
                      {
                        "permissionName": "权限1",
                        "permissionDescription": "权限描述1"
                      }
                    ]
                  }
                ]
              }
            }
        """.trimIndent(), "data.roles[0].permissions[1]", Permission::class.java
        )
        assertNotNull(permission)
    }
}
