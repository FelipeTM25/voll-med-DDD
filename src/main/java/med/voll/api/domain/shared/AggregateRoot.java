package med.voll.api.domain.shared;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@MappedSuperclass
@Getter
@EqualsAndHashCode(of = "id")
public abstract class AggregateRoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected AggregateRoot() {}

    protected AggregateRoot(Long id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null;
    }
}
