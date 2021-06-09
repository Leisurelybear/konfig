import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.XML;
import org.junit.Test;

/**
 * FileName: T1
 * Author:   jason
 * Date:     2021/4/16 23:11
 * Description:
 */

public class T1 {
    @Test
    public void t1() {
        //TODO:在8021添加检查是否有用户的接口
//        String res = HttpRequest
//                .post("http://localhost:8301/config/list?token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb290IiwiY3JlYXRlZCI6MTYxODQ5OTU5OTgyMCwiZXhwIjoxNjE5MTA0Mzk5fQ.WnlfxEko3a-An3GrEP_ikEyzYl0O_RsbbvjPZSPIUBj7j6Mqhy009DSU4BGgN5oBtKH7S8Ja27SGrJ6EniXw0A")
//                .body("{\"collectionIds\":[\"6\"],\"nameLike\":\"\",\"keyLike\":\"\",\"sort\":1}")
//                .timeout(20000)
//                .execute().body();
//        res = XML.toJSONObject(res).get("CommonResult").toString();
//        Console.log(res);
    }

    @Test
    public void hasUser() {
//        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb290IiwiY3JlYXRlZCI6MTYxODQ5OTU5OTgyMCwiZXhwIjoxNjE5MTA0Mzk5fQ.WnlfxEko3a-An3GrEP_ikEyzYl0O_RsbbvjPZSPIUBj7j6Mqhy009DSU4BGgN5oBtKH7S8Ja27SGrJ6EniXw0A";
//        String res = HttpRequest
//                .get("http://localhost:8021/admin/info")
//                .header("Authorization", "Bearer " + token)
//                .timeout(20000)
//                .execute().body();
//
//        JSONObject jsonObj = XML.toJSONObject(res);
//        if (jsonObj != null){
//            res = jsonObj.getByPath("CommonResult.data.username").toString();
//        }else {
//            res = "";
//        }
//        Console.log(res);
    }


}
