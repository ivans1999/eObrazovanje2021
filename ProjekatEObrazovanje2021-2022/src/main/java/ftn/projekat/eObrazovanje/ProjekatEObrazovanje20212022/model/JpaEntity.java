package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class JpaEntity implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    public JpaEntity(Long id) {
        this.id = id;
    }

    public JpaEntity() {
        this.id = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getClass() == obj.getClass() && this.getId() == ((JpaEntity) obj).getId();
    }
}
