package microbucks.orderservice.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String customer;
    private String barista;
    private String content;
    private String price;
    private int state;
    private Date createTime;
    private Date modifyTime;
}
