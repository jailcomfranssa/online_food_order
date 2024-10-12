package github.com.jailcomfranssa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category extends BaseEntity<Long>{

    private String name;

    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;
}
