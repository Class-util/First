package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 * Description:前后端接口需要的统一字段
 * User:吴博
 * Date:2021 05 20
 * Time:18:29
 */

@Setter
@Getter
@ToString
public class Response {
    //当前接口响应是否操作成功
    private boolean ok;
    //操作失败时的失败原因
    private String reason;

}
