package github.com.jailcomfranssa.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity<T extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private T id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity<?> that = (BaseEntity<?>) o;

        // Se ambos os IDs são nulos, consideramos que os objetos não são iguais
        if (id == null || that.id == null) return false;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        // Retorna um valor arbitrário se o ID for nulo, ou o hashCode do ID se não for
        return (id != null) ? id.hashCode() : 0;
    }
}

