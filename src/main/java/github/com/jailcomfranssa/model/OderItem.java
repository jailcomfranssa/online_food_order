package github.com.jailcomfranssa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OderItem extends BaseEntity<Long>{

    @ManyToOne
    private Food food;

    private int quantity;
    private Long totalPrice;
    private List<String> ingredients;



}
