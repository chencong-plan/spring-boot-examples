package cc.ccoder.springbootwebflux.entity;

import lombok.Data;

/**
 * @author chencong(ccoder.cc)
 * @since 2019/2/22 16:21
 */
@Data
public class City {

    private Long id;
    private String provinceId;
    private String cityName;
    private String description;
}
