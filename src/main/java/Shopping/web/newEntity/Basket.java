package Shopping.web.newEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Basket {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basketId;
    private Long memberId;
    private Long productId;
    private String price;
    private Long count;
}
