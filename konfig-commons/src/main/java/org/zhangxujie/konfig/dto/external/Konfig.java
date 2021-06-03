/**
 * FileName: Config
 * Author:   jason
 * Date:     2021/5/27 11:22
 * Description:
 */
package org.zhangxujie.konfig.dto.external;

import lombok.Data;

@Data
public class Konfig {

    private Integer id;

    private String name;

    private String key;

    private String value;

    @Override
    public String toString() {
        return "Konfig{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
